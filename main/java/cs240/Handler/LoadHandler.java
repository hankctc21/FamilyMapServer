package cs240.Handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import cs240.DAO.ErrorMessage;
import cs240.Request.LoadRequest;
import cs240.Result.LoadResult;
import cs240.Service.LoadService;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-11-05.
 */

public class LoadHandler implements HttpHandler {





    public LoadHandler() {

    }

    public LoadRequest getLoadRequestFromFile() throws IOException {

        EnDecoder EDcoder = new EnDecoder();

        String input = EDcoder.fileToString("fms/example.json");

        LoadRequest myLReq = LRequsestJsonToObject(input);

        return myLReq;
    }



    public LoadRequest LRequsestJsonToObject(String input){
        Gson myGson = new Gson();
        LoadRequest LRequest = new LoadRequest();


        LRequest = myGson.fromJson(input, LoadRequest.class);

        return LRequest;
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
        LoadService LS = null;
        Gson myGson = new Gson();

        try {
            LS = new LoadService();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String reqJson = getRequestBody(exchange);
            LoadRequest lr = myGson.fromJson(reqJson, LoadRequest.class);
            String response = "";




            try{
                LoadResult res = LS.load(lr);
                response = myGson.toJson(res, LoadResult.class);
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
            System.err.println("Error while in LoadHandler: " + e.getMessage());
        }
    }

}
