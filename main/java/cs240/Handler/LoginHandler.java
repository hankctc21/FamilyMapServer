package cs240.Handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import cs240.DAO.ErrorMessage;
import cs240.Request.LoginRequest;
import cs240.Result.LoginResult;
import cs240.Service.LoginService;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-11-06.
 */

public class LoginHandler implements HttpHandler{




    public LoginHandler() {
    }

    public LoginRequest LoginHandle() throws IOException {

        EnDecoder EDcoder = new EnDecoder();

        String input = EDcoder.fileToString("sqlite/example.json");

        LoginRequest myLReq = LogReqJsonToObject(input);

        return myLReq;
    }

    public LoginRequest LogReqJsonToObject(String input){
        LoginRequest LogRequest = new LoginRequest();
        Gson myGson = new Gson();

        LogRequest = myGson.fromJson(input, LoginRequest.class);

        return LogRequest;
    }

    String getRequestBody(HttpExchange exchange) {
        String request = "";

        Scanner in = new Scanner(exchange.getRequestBody());
        while (in.hasNextLine()) {
            String line = in.nextLine();
            request += line + "\n";
        }
        in.close();
        return request;
    }

    void sendResponseBody(HttpExchange exchange, String response) {
        PrintWriter out = new PrintWriter(exchange.getResponseBody());
        out.print(response);
        out.close();
    }

    @Override
    public void handle(HttpExchange exchange) {
        LoginService LS = null;
        Gson myGson = new Gson();
        try {
            LS = new LoginService();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String reqJson = getRequestBody(exchange);
            LoginRequest lr = myGson.fromJson(reqJson, LoginRequest.class);
            String response = "";



            try{
                LoginResult res = LS.login(lr);
                response = myGson.toJson(res, LoginResult.class);
            }catch (Exception e){
                System.err.println( e.getClass().getName() + ": " + e.getMessage());
                ErrorMessage err = new ErrorMessage();
                err.setMessage("Error occured");
                response = myGson.toJson(err, ErrorMessage.class);
            }

            exchange.sendResponseHeaders(HTTP_OK, 0);
            sendResponseBody(exchange, response);

            exchange.getResponseBody().close();
        } catch (Exception e) {
            System.err.println("Error while in LoginHandler: " + e.getMessage());
        }
    }

}
