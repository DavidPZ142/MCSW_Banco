package edu.escuelaing.mcsw.persistencia;

import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    ConnectionDb connectionDb;
    Connection connection;
    private JSONObject  res;

    public Controller(){

        this.connectionDb = new ConnectionDb();
        connection = connectionDb.getConnect();
    }

    public JSONObject findUser(String correo, String password){
        System.out.println(correo+ " "+ password);
        res = new JSONObject();
        String select = "SELECT * FROM usuario where usuario.correo = '"+correo+"' and usuario.contrasena = '"+password+"';";
        System.out.println(select);
        try{
            ResultSet resultSet = connection.prepareStatement(select).executeQuery();
            resultSet.next();
            res.put("nombre",resultSet.getString("nombre"));
            res.put("apellido",resultSet.getString("apellido"));
            res.put("fondos",resultSet.getString("fondos"));
            //connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }


    public JSONObject  selectUser(){
        res = new JSONObject ();
        String select = "SELECT * FROM usuario;";
        try {

            ResultSet resultSet = connection.prepareStatement(select).executeQuery();
            connection.close();
            while (resultSet.next()){
                res.put("Nombre",resultSet.getString("nombre"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    public JSONObject montoTotal(){
        res = new JSONObject();
        String select = "SELECT fondos FROM usuario;";
        try {
            ResultSet resultSet = connection.prepareStatement(select).executeQuery();
            res.put("Fondos",resultSet.getString("fondos"));
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println(res);
        return res;
    }

}
