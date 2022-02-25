package edu.escuelaing.mcsw.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public interface Reader {


    void reader(String path, Socket clientSocket) throws IOException;

    default void error (Socket clientSocket){
        String value="HTTP/1.1 404 Not Found\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<title>Error 404</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>HTTP 404 Not Found</h1>\n"
                + "</body>\n"
                + "</html>\n";
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



