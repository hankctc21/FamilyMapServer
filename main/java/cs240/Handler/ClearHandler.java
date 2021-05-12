package cs240.Handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.PrintWriter;
import java.util.Scanner;

import cs240.DAO.ErrorMessage;
import cs240.Result.ClearResult;
import cs240.Service.ClearService;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-11-14.
 */

public class ClearHandler implements HttpHandler {

    ClearService CS = new ClearService();
    Gson myGson = new Gson();


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
        try {
            String response = "";


            try{
                ClearResult res = CS.clear();
                response = myGson.toJson(res, ClearResult.class);
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
            System.err.println("Error while in ClearHandler: " + e.getMessage());
        }
    }
}
