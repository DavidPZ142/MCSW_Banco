package edu.escuelaing.mcsw.HttpServerNiuBank;

import edu.escuelaing.mcsw.frameworkNiuBank.Framework;
import edu.escuelaing.mcsw.util.ReaderHTML;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import  edu.escuelaing.mcsw.frameworkNiuBank.FrameworkNiuBank;

public class HttpServer {

    Map<String, FrameworkNiuBank> routes = new HashMap<>();


    public void startServer(int port) throws IOException {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);
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
                    firstline = false;
                }
                System.out.println("recive: "+ inputLine);

                if(!in.ready()){
                    break;
                }
            }

            System.out.println("path: "+path);
            if(path.contains("?")){
                Framework framework = Framework.getInstance();
                String pathNew = path.split("\\?")[0];
                String[] parametros = pathNew.split("&");
                String res = framework.handle(path.split("\\?")[0], parametros, null);
                leerArchivo(path, clientSocket);
                System.out.println(res);

            }else {
                leerArchivo(path, clientSocket);
            }

            in.close();
            out.close();
            clientSocket.close();
        }
        serverSocket.close();

    }

    public void leerArchivo(String path, Socket clienteSocket) throws IOException {
        ReaderHTML reader = new ReaderHTML();
        reader.reader(path, clienteSocket);
    }




}
