package edu.escuelaing.mcsw.services;

import edu.escuelaing.mcsw.persistencia.Controller;
import org.json.JSONObject;

import java.util.List;

public class ServiciosNiubank {

    private Controller controller = new Controller();


    public ServiciosNiubank() {


    }

    public JSONObject login(String parametros){
        String[] paths = parametros.split("&");
        return controller.findUser(paths[0].split("=")[1], paths[1].split("=")[1]);
    }

    public JSONObject montoUsuario(){
        //String[] paths = parametros.split("&");
        return controller.montoTotal();
    }


}
