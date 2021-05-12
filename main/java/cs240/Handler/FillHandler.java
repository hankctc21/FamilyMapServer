package cs240.Handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cs240.DAO.ErrorMessage;
import cs240.Result.FillResult;
import cs240.Service.FillService;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-11-14.
 */

public class FillHandler implements HttpHandler {
    FillService FS = new FillService();
    Gson myGson = new Gson();

    public FillHandler() throws SQLException, ClassNotFoundException {
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
        try {
            Pattern pattern = Pattern.compile("/fill/(\\w+)(/(\\w+))?");
            Matcher matcher = pattern.matcher(exchange.getRequestURI().toString());
            matcher.find();
            String username = matcher.group(1);
            String numGen = matcher.group(3);
            String response;
            FillResult res;

            try{
                if (numGen == null) {
                    res = FS.fill(username, 4);
                } else {
                    res = FS.fill(username, Integer.parseInt(numGen));
                }
                response = myGson.toJson(res, FillResult.class);
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
            System.err.println("Error while in FillHandler: " + e.getMessage());
        }

    }
}
