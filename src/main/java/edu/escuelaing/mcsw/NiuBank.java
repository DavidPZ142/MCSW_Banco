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
        get("/Registrar",(req, res) -> serviciosNiubank.registrarUser(req));
        get("/Transferencia", (req, res) -> serviciosNiubank.tranferencia(req));
        get("/VerFondos", (req, res) -> serviciosNiubank.verFondos(req));
        get("/verTransferencia", (req, res) -> serviciosNiubank.verTransaccion());
        get("/modificarMonto", (req, res) -> serviciosNiubank.modificarMonto(req));
        get("/solicitarSobregiro",(req,res)-> serviciosNiubank.solicitarSobregiro(req));
        startServer();
    }


    private static int getPort(){

        if(System.getenv("PORT") != null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;

    }

}
