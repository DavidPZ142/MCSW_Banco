package edu.escuelaing.mcsw;

import edu.escuelaing.mcsw.HttpServerNiuBank.HttpServer;
import edu.escuelaing.mcsw.services.ServiciosNiubank;

import static edu.escuelaing.mcsw.frameworkNiuBank.ImplementsFramework.*;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class NiuBank

{
    public static void main( String[] args ) throws IOException {
        ServiciosNiubank serviciosNiubank = new ServiciosNiubank();
        port(getPort());
        get("/Login",(req, res) -> serviciosNiubank.login(req));
        startServer();
    }


    private static int getPort(){

        if(System.getenv("PORT") != null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;

    }

}
