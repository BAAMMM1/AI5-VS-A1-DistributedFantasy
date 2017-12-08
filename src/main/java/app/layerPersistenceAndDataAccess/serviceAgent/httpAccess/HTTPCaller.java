package app.layerPersistenceAndDataAccess.serviceAgent.httpAccess;

import app.layerLogicAndService.cmpBlackboard.entity.Blackboard;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.UnexpectedResponseCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.tavernaConsumer.PathTaverna;
import com.google.gson.Gson;

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

    private Gson gson = new Gson();

    public HTTPResponse doDELETE(String url) throws UnexpectedResponseCodeException {
        return this.doDELETE(url, null);
    }

    public HTTPResponse doDELETE(String url, String token) throws UnexpectedResponseCodeException {

        HTTPRequest httpRequest =
                new HTTPRequest(
                        url,
                        EnumHTTPMethod.DELETE
                );

        if(token != null){
            httpRequest.setAuthorizationToken(token);
        }

        HTTPResponse result = this.call(httpRequest);

        if (result.getCode() != 200) {
            ErrorCodeDTO errorCodeDTO = gson.fromJson(result.getBody(), ErrorCodeDTO.class);

            throw new UnexpectedResponseCodeException(errorCodeDTO);

        }


        return result;
    }

    public HTTPResponse doPOST(String url, String body) throws UnexpectedResponseCodeException {
        return this.doPOST(url, null, body);
    }

    public HTTPResponse doPOST(String url, String token, String body) throws UnexpectedResponseCodeException {

        HTTPRequest httpRequest =
                new HTTPRequest(
                        url,
                        EnumHTTPMethod.POST,
                        body
                );

        if (token != null) {
            httpRequest.setAuthorizationToken(token);
        }

        HTTPResponse result = this.call(httpRequest);

        if (result.getCode() != 201) {
            ErrorCodeDTO errorCodeDTO = gson.fromJson(result.getBody(), ErrorCodeDTO.class);

            throw new UnexpectedResponseCodeException(errorCodeDTO);

        }

        return result;
    }

    public HTTPResponse doGET(String url) throws UnexpectedResponseCodeException {
        return doGET(url, null);
    }

    public HTTPResponse doGET(String url, String token) throws UnexpectedResponseCodeException {

        HTTPRequest httpRequest =
                new HTTPRequest(
                        url,
                        EnumHTTPMethod.GET
                );

        if (token != null) {
            httpRequest.setAuthorizationToken(token);
        }

        HTTPResponse result = this.call(httpRequest);

        if (result.getCode() != 200) {
            ErrorCodeDTO errorCodeDTO = gson.fromJson(result.getBody(), ErrorCodeDTO.class);

            throw new UnexpectedResponseCodeException(errorCodeDTO);

        }

        return result;
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

            try {
                connection.connect();

            } catch (Exception e) {
                throw new IllegalArgumentException(e.getMessage());

            }


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

            String Body = new String(responeBody, 0, responeLen);

            /* TODO - Falls eine Anfrage durch ging aber keinen Body enthält
            if(Body.equals("")){
                throw BodyIsEmptyException();
            }
            */

            System.out.println(responeCode);
            System.out.println(Body.toString());

            HTTPResponse response = new HTTPResponse(responeCode, Body);

            connection.disconnect();

            return response;

        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}
