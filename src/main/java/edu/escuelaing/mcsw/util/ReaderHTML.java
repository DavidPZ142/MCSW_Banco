package edu.escuelaing.mcsw.util;

import edu.escuelaing.mcsw.persistencia.Controller;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ReaderHTML  implements Reader {

    private String pathA = "src/main/resources/public";

    public void reader(String path, Socket clientSocket) throws IOException {
        String input;
        String output = "";
        System.out.println(pathA+path);
        BufferedReader leer = new BufferedReader(new FileReader(pathA+path));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        while ((input = leer.readLine()) !=null){
            output += input;
            output += "\n";
        }
        System.out.println(output);
        out.println(validOKHttpHeader()+output);

        leer.close();
        out.close();

    }

    private String validOKHttpHeader(){
        return "HTTP/1.1 200 OK\r\n"
                + "Conten-Type: text/html\r\n"
                + "\r\n";

    }
}
