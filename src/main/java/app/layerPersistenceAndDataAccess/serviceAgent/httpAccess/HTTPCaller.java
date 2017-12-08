package app.layerPersistenceAndDataAccess.serviceAgent.httpAccess;

import app.layerLogicAndService.cmpBlackboard.entity.Blackboard;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.tavernaConsumer.PathTaverna;

import javax.el.MethodNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.rmi.ServerException;
import java.util.Base64;

/**
 * @author Christian G. on 02.11.2017
 */
public class HTTPCaller {

    private static final int BUFFER_LENGHT = 819200;
    private static final String MEDIA_TYPE_APPLICATION_JSON = "application/json";

    public HTTPResponse doGET(String url){
        return doGET(url, null);
    }

    public HTTPResponse doGET(String url, String token){

        HTTPRequest httpRequest =
                new HTTPRequest(
                        url,
                        EnumHTTPMethod.GET
                );

        if(token != null){
            httpRequest.setAuthorizationToken(token);
        }

        return call(httpRequest);
    }

    public HTTPResponse call(HTTPRequest request) {

        if (request.getUrl().contains("null")) {
            throw new IllegalArgumentException("error: no host or port");
        }

        try {
            URL url = new URL(request.getUrl());
            HttpURLConnection connection = null;

            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(10000);



            connection.setRequestMethod(request.getMethod().toString());
            connection.setRequestProperty("Accept", MEDIA_TYPE_APPLICATION_JSON);
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

            try{
                connection.connect();

            } catch (Exception e){
                throw new IllegalArgumentException(e.getMessage());

            }


            // Get the response
            int responeCode = connection.getResponseCode();
            int responeLen;

            byte[] responeBody = new byte[BUFFER_LENGHT];


            if(responeCode >= 500){
                throw new ServerException("code: " + responeCode);
            }

            if(responeCode == 404){
                throw new MethodNotFoundException();
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

            String Body = new String(responeBody, 0, responeLen);

            System.out.println(responeCode);
            System.out.println(Body.toString());

            HTTPResponse response = new HTTPResponse(responeCode, Body);

            connection.disconnect();

            return response;

        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }


    public static void main(String[] args) throws IOException {

        HTTPCaller caller = new HTTPCaller();
        HTTPResponse response;

        response = caller.call(
                new HTTPRequest(
                        "http://127.0.0.1:8080/appoints/1",
                        EnumHTTPMethod.GET,
                        null
                ));

        System.out.println(response.toString());

        response = caller.call(
                new HTTPRequest(
                        "http://127.0.0.1:8080/appoints/10",
                        EnumHTTPMethod.GET
                ));

        System.out.println(response.toString());

        response = caller.call(
                new HTTPRequest(
                        "http://127.0.0.1:8080/appoints/1",
                        EnumHTTPMethod.GET
                ));

        System.out.println(response.toString());

    }

}
