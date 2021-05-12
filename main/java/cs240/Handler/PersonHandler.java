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
import cs240.Result.PersonIDResult;
import cs240.Result.PersonResult;
import cs240.Service.PersonIDService;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-11-14.
 */

public class PersonHandler implements HttpHandler {




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
        PersonIDService PS = new PersonIDService();
        Gson myGson = new Gson();
        try {
            String auth = exchange.getRequestHeaders().getFirst("Authorization");
            String uri = exchange.getRequestURI().toString();
            String personID = null;
            if (uri.matches("/person/(\\w+)")) {
                Pattern pattern = Pattern.compile("/person/(\\w+)");
                Matcher matcher = pattern.matcher(exchange.getRequestURI().toString());
                matcher.find();
                personID = matcher.group(1);
            }
            String response = "";


            try{
                if (personID == null) {
                    PersonResult res = PS.pResult(auth);
                    response = myGson.toJson(res, PersonResult.class);

                } else {
                    PersonIDResult res2 = PS.pIDResult(auth, personID);
                    response = myGson.toJson(res2, PersonIDResult.class);
                    if(res2 == null){
                        ErrorMessage err = new ErrorMessage();
                        err.setMessage("Error occured");
                        response = myGson.toJson(err, ErrorMessage.class);
                    }
                }
            }catch (SQLException e){
                System.err.println( e.getClass().getName() + ": " + e.getMessage());
                ErrorMessage err = new ErrorMessage();
                err.setMessage("Error occured");
                response = myGson.toJson(err, ErrorMessage.class);
            }

            exchange.sendResponseHeaders(HTTP_OK, 0);
            sendResponseBody(exchange, response);
            exchange.getResponseBody().close();
        } catch (Exception e) {
            System.err.println("Error while in PersonHandler: " + e.getMessage());
        }
    }
}
