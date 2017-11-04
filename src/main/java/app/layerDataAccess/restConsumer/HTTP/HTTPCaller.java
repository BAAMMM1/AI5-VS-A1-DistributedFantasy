package app.layerDataAccess.restConsumer.HTTP;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Christian G. on 02.11.2017
 */
public class HTTPCaller {

    private static final int BUFFER_LENGHT = 4096;

    public HTTPResponse call(HTTPRequest request) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) request.getUrl().openConnection();

        connection.setRequestMethod(request.getMethod().toString());
        connection.setDoOutput(true);

        // Body?
        if (request.getBody() != null) {
            connection.getOutputStream().write(request.getBody().getBytes());
        }

        // java.net.ConnectException
        connection.connect();

        // Get the response
        int responeCode = connection.getResponseCode();
        int responeLen = connection.getInputStream().available();

        byte[] responeBody = new byte[BUFFER_LENGHT];


        // Which stream is available depends on the return code...
        if(responeCode < 400){
            connection.getInputStream().read(responeBody);

        } else {
            connection.getErrorStream().read(responeBody);

        }

        String Body = new String(responeBody, 0, responeLen);

        HTTPResponse respone = new HTTPResponse(responeCode, Body);

        System.out.println(respone.toString());

        return respone;
    }


    public static void main(String[] args) throws IOException {

        HTTPCaller caller = new HTTPCaller();

        caller.call(new HTTPRequest(
                new URL("http://127.0.0.1:8080/appoints/1"),
                EnumHTTPMethod.GET,
                null
        ));

        caller.call(new HTTPRequest(
                new URL("http://127.0.0.1:8080/appoints/1"),
                EnumHTTPMethod.GET
        ));

    }

}
