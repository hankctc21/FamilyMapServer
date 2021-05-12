

package cs240;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

import cs240.Handler.ClearHandler;
import cs240.Handler.EventHandler;
import cs240.Handler.FillHandler;
import cs240.Handler.LoadHandler;
import cs240.Handler.LoginHandler;
import cs240.Handler.PersonHandler;
import cs240.Handler.RegisterHandler;

public class    Server {

//    private static Logger logger;

    public static final String WebPath = "web";

    public static String getWebPath() {
        return WebPath;
    }




    public static void main(String[] args) throws Exception {


        Server server = new Server();
        server.startServer();

    }

    void startServer() throws Exception {

        int port = 8888;

        System.out.println("server listening on port: " + port);
        System.out.println();

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);


        server.createContext("/", new cs240.Handler.FileHandler());
        server.createContext("/index.html", new cs240.Handler.FileHandler());
        server.createContext("/user/register", new RegisterHandler());
        server.createContext("/user/login", new LoginHandler());
        server.createContext("/clear", new ClearHandler());
        server.createContext("/fill", new FillHandler());
        server.createContext("/load", new LoadHandler());
        server.createContext("/person", new PersonHandler());
        server.createContext("/event", new EventHandler());

        server.start();

    }



}

