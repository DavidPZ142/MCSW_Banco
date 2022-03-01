package edu.escuelaing.mcsw.services;

import edu.escuelaing.mcsw.persistencia.Controller;
import org.json.JSONObject;

import java.security.PublicKey;
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


    public JSONObject verFondos(String parametros){
        String[] paths = parametros.split("=");
        System.out.println(Arrays.toString(paths));
        return controller.verMonto(paths[1]);
    }

    public JSONObject verTransaccion(){
        return controller.verTransferencias();
    }

    public JSONObject modificarMonto(String parametros){
        String[] paths = parametros.split("&");
        return controller.modificarMonto(paths[0].split("=")[1], paths[1].split("=")[1]);
    }

    public JSONObject solicitarSobregiro(String parametros){
        String[] paths = parametros.split("&");
        return controller.solicitarSobregiro(paths[0].split("=")[1], paths[1].split("=")[1]);
    }

    public JSONObject crearUser(String parametros){

        String[] paths = parametros.split("&");
        return controller.crearUser(paths[0].split("=")[1], paths[1].split("=")[1]);

    }

    public JSONObject mostrarAutorizaiones(){return controller.mostrarAutorizaciones();}

    public JSONObject autorizar(String parametros){
        String[] paths = parametros.split("&");
        return controller.autorizar(paths[0].split("=")[1], paths[1].split("=")[1]);



    }





}
