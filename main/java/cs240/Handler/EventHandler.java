package cs240.Handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cs240.DAO.ErrorMessage;
import cs240.Result.EventID;
import cs240.Result.EventResult;
import cs240.Service.EventService;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-11-14.
 */

public class EventHandler implements HttpHandler {
    EventService ES = new EventService();
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
            String auth = exchange.getRequestHeaders().getFirst("Authorization");
            String uri = exchange.getRequestURI().toString();
            String eventID = null;
            if (uri.matches("/event/(\\w+)")) {
                Pattern pattern = Pattern.compile("/event/(\\w+)");
                Matcher matcher = pattern.matcher(exchange.getRequestURI().toString());
                matcher.find();
                eventID = matcher.group(1);
            }
            String response = "";

            try{
                if (eventID == null) {
                    EventResult res = ES.eventResult(auth);
                    response = myGson.toJson(res, EventResult.class);
                } else {
                    EventID res = ES.eventResult(auth, eventID);
                    response = myGson.toJson(res, EventID.class);
                }
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
            System.err.println("Error while in EventHandler: " + e.getMessage());
        }
    }
}
