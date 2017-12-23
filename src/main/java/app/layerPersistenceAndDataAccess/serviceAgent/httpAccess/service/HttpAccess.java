package app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.service;

import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.enums.EnumHTTPMethod;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.entity.HttpRequest;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.entity.HttpResponse;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.el.MethodNotFoundException;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.rmi.ServerException;
import java.util.Base64;

/**
 * @author Christian G. on 02.11.2017
 */
public class HttpAccess {

    public final static Logger logger = Logger.getLogger(new Object() { }.getClass().getEnclosingClass());

    private static final int BUFFER_LENGHT = 819200;
    private static final String MEDIA_TYPE_APPLICATION_JSON = "application/json";
    public static final int CONNECT_TIMEOUT = 1000; // before 10000

    private Gson gson = new Gson();

    public HttpResponse call(HttpRequest request) {
        return this.docall(request, false);
    }

    public HttpResponse docall(HttpRequest request, boolean buffered) {

        if (request.getUrl().contains("null")) {
            throw new IllegalArgumentException("error: no host or port");
        }

        try {
            URL url = new URL(request.getUrl());
            HttpURLConnection connection = null;

            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(CONNECT_TIMEOUT);


            connection.setRequestMethod(request.getMethod().toString());

            if(request.isAcceptText()){
                connection.setRequestProperty("Accept", "text/text");
            } else {
                connection.setRequestProperty("Accept", MEDIA_TYPE_APPLICATION_JSON);

            }

            connection.setDoOutput(true);

            // basicAuth?
            if (request.isBasicAuth()) {
                String encoded = Base64.getEncoder().encodeToString((request.getBasicAuthUser() + ":" + request.getBasicAuthPw()).getBytes(StandardCharsets.UTF_8));
                connection.setRequestProperty("Authorization", "Basic " + encoded);
            }

            // Authorization Token?
            if (request.isAuthorization()) {
                connection.setRequestProperty("Authorization", "Token "
                        + request.getAuthorizationToken());
            }

            // Body?
            if (request.getBody() != null) {
                connection.setRequestProperty("Content-Type", MEDIA_TYPE_APPLICATION_JSON);
                //connection.setRequestProperty("Content-Length", "" + request.getBody().getBytes().length);
                connection.getOutputStream().write(request.getBody().getBytes());

            }

            try {
                connection.connect();

            } catch (Exception e) {
                throw new IllegalArgumentException(e.getMessage());

            }

            String body = "";
            HttpResponse response = null;

            if(buffered){
                System.out.println("read");
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));

                String responeBody = "";
                String inputLine;
                while ((inputLine = in.readLine()) != null){
                    responeBody = responeBody + inputLine;
                    System.out.println(inputLine);
                }
                in.close();
                System.out.println("close");

                body = responeBody;
                System.out.println(responeBody);

                response = new HttpResponse(200, body);

            } else {

                // Get the response
                int responeCode = connection.getResponseCode();
                int responeLen;

                byte[] responeBody = new byte[BUFFER_LENGHT];


                if (responeCode >= 500) {
                    throw new ServerException("code: " + responeCode);
                }

                if (responeCode == 404) {
                    throw new MethodNotFoundException("method " + request.getMethod() + " could not be found for " + request.getUrl());
                }


                // Which stream is available depends on the return code...
                if (responeCode < 400) {
                    responeLen = connection.getInputStream().available();
                    connection.getInputStream().read(responeBody);

                } else {
                    responeLen = connection.getErrorStream().available();
                    connection.getErrorStream().read(responeBody);

                }

                //System.out.println(responeBody.length);
                //System.out.println(responeLen);

                body = new String(responeBody, 0, responeLen);

                logger.info(responeCode + " for: " + request.getUrl());
                if(body != null) {
                    if(!body.isEmpty()){
                        logger.info(body.toString());
                    }
                }
                //System.out.println(responeCode);
                //System.out.println(Body.toString());

                response = new HttpResponse(responeCode, body);

            }

            /* TODO - Falls eine Anfrage durch ging aber keinen Body enthält
            if(Body.equals("")){
                throw BodyIsEmptyException();
            }
            */



            connection.disconnect();

            return response;

        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public HttpResponse get(String url) {

        return this.call(this.buildRequest(url, EnumHTTPMethod.GET, null, null, null, null));

    }

    public HttpResponse get(String url, String basicName, String basicPassword) {

        return this.call(this.buildRequest(url, EnumHTTPMethod.GET, null, null, basicName, basicPassword));

    }

    public HttpResponse get(String url, String token) {

        return this.call(this.buildRequest(url, EnumHTTPMethod.GET, token, null, null, null));

    }

    public HttpResponse post(String url, String body) throws UnexpectedResponseCodeException {
        return this.post(url, null, body);
    }

    public HttpResponse post(String url, String token, String body) throws UnexpectedResponseCodeException {

        return this.call(this.buildRequest(url, EnumHTTPMethod.POST, token, body, null, null));

    }

    public HttpResponse postBuffered(String url, String token, String body) throws UnexpectedResponseCodeException {

        HttpRequest request = new HttpRequest(url, EnumHTTPMethod.POST, body);
        request.setAuthorizationToken(token);
        request.setAcceptText(true);

        return this.docall(request, true);

    }

    public HttpResponse delete(String url) throws UnexpectedResponseCodeException {

        return this.call(this.buildRequest(url, EnumHTTPMethod.DELETE, null, null, null, null));

    }

    public HttpResponse delete(String url, String token) throws UnexpectedResponseCodeException {

        return this.call(this.buildRequest(url, EnumHTTPMethod.DELETE, token, null, null, null));

    }


    private HttpRequest buildRequest(String url, EnumHTTPMethod method, String token, String body, String basicName, String basicPassword) {

        HttpRequest request;

        if (body != null) {
            request = new HttpRequest(url, method, body);
        } else {
            request = new HttpRequest(url, method);
        }

        if (token != null) {
            request.setAuthorizationToken(token);
        }

        if(basicName != null && basicPassword != null){
            request.setBasicAuth(basicName, basicPassword);
        }


        return request;
    }


}
