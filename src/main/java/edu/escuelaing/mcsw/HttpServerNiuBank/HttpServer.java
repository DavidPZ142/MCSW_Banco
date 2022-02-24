package edu.escuelaing.mcsw.HttpServerNiuBank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    public void startServer(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(getPort());
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        boolean running = true;
        while (running){
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir . . .");
                clientSocket = serverSocket.accept();
            }catch (IOException e ){
                System.err.println("Accept failed");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine , outputLine;
            boolean firstline = true;
            String path ="";

            while((inputLine = in.readLine()) != null){
                if(firstline){
                    path = inputLine.split(" ")[1];
                }
                System.out.printf("recive: "+ inputLine);

                if(!in.ready()){
                    break;
                }
            }
            if(path != ""){
                leerArchivo(path, clientSocket);
            }
        }

    }

    public void leerArchivo(String path, Socket clienteSocket){
        if(path.contains(".html")){

        }



    }




    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }


}
