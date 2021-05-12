package cs240.Handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import cs240.DAO.ErrorMessage;
import cs240.Request.RegisterRequest;
import cs240.Result.RegisterResult;
import cs240.Service.RegisterService;

import static java.net.HttpURLConnection.HTTP_OK;


//import cs240.Request.LoadRequest;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-10-12.
 */

public class RegisterHandler implements HttpHandler{





//    public RegisterHandler() throws Exception {
//        EnDecoder EDcoder = new EnDecoder();
//        Gson myGson = new Gson();
//        RegisterService RS = new RegisterService();
//
//    }

    public RegisterRequest getRegReqFromFile() throws IOException {
        EnDecoder EDcoder = new EnDecoder();



        String input = EDcoder.fileToString("fms/example.json");

        RegisterRequest myReq = RegReqJsonToObject(input);

        return myReq;
    }

//    public RegisterRequest getLocationFromFile() throws Exception {
//        EnDecoder ED = new EnDecoder();
//
//        String input = ED.fileToString("sqlite/locations.json");
//
//        RegisterRequest myLocReq = LocJsonToObject(input);
//
//        return myLocReq;
//    }

    public RegisterRequest RegReqJsonToObject(String input){
        Gson myGson = new Gson();
        RegisterRequest RegRequest = new RegisterRequest();


        RegRequest = myGson.fromJson(input, RegisterRequest.class);

        return RegRequest;
    }

//    public RegisterRequest LocJsonToObject(String input){
//        RegisterRequest RegReq = new RegisterRequest();
//        Gson myGson = new Gson();
//        RegReq = myGson.fromJson(input, RegisterRequest.class);
//
//        return RegReq;
//    }

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
    public void handle(HttpExchange exchange)  {
        Gson myGson = new Gson();
        RegisterService RS = null;
        try {
            RS = new RegisterService();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String reqJson = getRequestBody(exchange);
            RegisterRequest rr = myGson.fromJson(reqJson, RegisterRequest.class);
            String response = "";

            try{
                RegisterResult res = RS.register(rr);
                if(res != null){
                    response = myGson.toJson(res, RegisterResult.class);
                }
                else{
                    ErrorMessage err = new ErrorMessage();
                    err.setMessage("Error occured");
                    response = myGson.toJson(err, Error.class);
                }

            }catch (Exception e){
                e.printStackTrace();
                ErrorMessage err = new ErrorMessage();
                err.setMessage("Error occured");
                response = myGson.toJson(err, ErrorMessage.class);
            }



            exchange.sendResponseHeaders(HTTP_OK, 0);
            sendResponseBody(exchange, response);
            exchange.getResponseBody().close();
        } catch (Exception e) {
            System.err.println("Error while in RegisterHandler: " + e.getMessage());
        }
    }
}
