package edu.escuelaing.mcsw.frameworkNiuBank;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

import java.io.IOException;
import java.util.function.BiFunction;

public class ImplementsFramework {


    public static void get(String route, BiFunction<String[], HttpResponse, String> biFunction){
        Framework framework = Framework.getInstance();
        framework.get(route, biFunction);

    }

    public static void port(int port){
        Framework framework = Framework.getInstance();
        framework.port(port);
    }

    public static void startServer() throws IOException {
        Framework framework = Framework.getInstance();
        framework.startServer();
    }



}
