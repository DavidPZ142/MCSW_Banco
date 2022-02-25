package edu.escuelaing.mcsw.util;

import edu.escuelaing.mcsw.persistencia.Controller;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ReaderHTML  implements Reader {

    private String pathA = "src/main/resources/public";

    public void reader(String path, Socket clientSocket){

        String fpath = pathA + path;
        String temp="";
        String cadena="";
        File f = new File(fpath);
        FileReader fr = null;
        try {
            Controller controller = new Controller();
            List<String> select = controller.selectUser();
            System.out.println(select);
            fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            while((cadena=bf.readLine())!=null) {
                temp= temp + cadena;
            }
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            System.out.println(temp);
            out.println( validOKHttpHeader() +temp + select);
        } catch (FileNotFoundException e) {
            error(clientSocket);
        } catch (IOException e) {
            error(clientSocket);
        }
    }

    private String validOKHttpHeader(){
        return "HTTP/1.1 200 OK\r\n"
                + "Conten-Type: text/html\r\n"
                + "\r\n";

    }
}
