package cs240.Handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;

import cs240.Server;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-11-07.
 */

public class    FileHandler implements HttpHandler{

    Server SV = new Server();

    String WebPath = SV.getWebPath();

    @Override
    public void handle(HttpExchange exchange) {
        try {
            String filename = "";
            if (exchange.getRequestURI().toString().equals("/")) {
                filename = WebPath + "/index.html";
            } else {
                filename = WebPath + exchange.getRequestURI();
            }
            File file = new File(filename);
            if (!file.exists()) {
                file = new File(WebPath + "/HTML/404.html");
            }


            exchange.sendResponseHeaders(HTTP_OK, file.length());
            OutputStream outputStream = exchange.getResponseBody();
            Files.copy(file.toPath(), outputStream);
            outputStream.close();
            exchange.getResponseBody().close();
        } catch (Exception e) {
            System.err.println("Error while attempting to load from root handler: " + e.getMessage());
        }

    }
}
