package edu.escuelaing.mcsw.services;

import edu.escuelaing.mcsw.persistencia.Controller;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class ServiciosNiubank {

    private Controller controller = new Controller();


    public ServiciosNiubank() {


    }

    public JSONObject login(String parametros){
        String[] paths = parametros.split("&");
        return controller.findUser(paths[0].split("=")[1], paths[1].split("=")[1]);
    }

    public JSONObject registrarUser(String parametros){
        String[] paths = parametros.split("&");
        System.out.println(Arrays.toString(paths));
        return controller.registrarUser(paths[0].split("=")[1], paths[1].split("=")[1], paths[2].split("=")[1], paths[3].split("=")[1]
        , paths[4].split("=")[1], paths[5].split("=")[1]);
    }

    public JSONObject tranferencia(String parametros){
        String[] paths = parametros.split("&");
        System.out.println(Arrays.toString(paths));
        return controller.transferencia(paths[0].split("=")[1], paths[1].split("=")[1], paths[2].split("=")[1]);
    }

<<<<<<< HEAD
    public JSONObject verFondos(String parametros){
        String[] paths = parametros.split("=");
        System.out.println(Arrays.toString(paths));
        return controller.verMonto(paths[1]);
    }
=======
    /*public JSONObject verFondos(String parametros){
        String[] paths = parametros.split();
    }*/
>>>>>>> eed82a97e088f91b141a53a6e3a1a06574cbb096



}
