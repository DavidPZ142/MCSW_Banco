package edu.escuelaing.mcsw.persistencia;

import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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

            if(resultSet.next()) {
                res.put("cedula", resultSet.getString("cedula"));
                res.put("nombre", resultSet.getString("nombre"));
                res.put("apellido", resultSet.getString("apellido"));
                res.put("fondos", resultSet.getString("fondos"));
                res.put("rol", resultSet.getString("rol"));
                //connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    public JSONObject registrarUser(String cedula, String nombre, String apellido, String correo, String contrasena, String fondos){
        res = new JSONObject();
        String rol = "USER";
        String select = "INSERT INTO usuario Values (?,?,?,?,?,?,?,?)";
        System.out.println(select);
        try {

            PreparedStatement stmt = connection.prepareStatement(select);
            stmt.setString(1, cedula);
            stmt.setString(2, apellido);
            stmt.setString(3, nombre);
            stmt.setString(4, correo);
            stmt.setString(5, contrasena);
            stmt.setInt(6, Integer.parseInt(fondos));
            stmt.setString(7, rol);
            stmt.setBoolean(8, false);
            System.out.println(stmt.executeUpdate());
            //connection.close();
            return res.put("Registro",true);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return res.put("Registro", false);
    }

    public JSONObject transferencia(String ccOrigen, String ccDestino, String monto){

        res = new JSONObject();
        String ccOrigenSelect = "UPDATE usuario SET fondos = fondos + ? where cedula = ?";
        String ccDestinoSelect = "UPDATE usuario SET fondos = fondos - ? where cedula = ?";
        try {

            PreparedStatement stmt = connection.prepareStatement(ccOrigenSelect);
            stmt.setInt(1, Integer.parseInt(monto));
            stmt.setString(2, ccDestino);
            PreparedStatement stmt2 = connection.prepareStatement(ccDestinoSelect);
            stmt2.setInt(1, Integer.parseInt(monto));
            stmt2.setString(2, ccOrigen);
            System.out.println(stmt.executeUpdate());
            System.out.println(stmt2.executeUpdate());

            return res.put("transferencia", true);

        }catch (SQLException e){
            e.printStackTrace();
        }

        return res.put("transferencia", false);

    }




    public JSONObject  selectUser(){
        res = new JSONObject ();
        String select = "SELECT * FROM usuario;";
        try {

            ResultSet resultSet = connection.prepareStatement(select).executeQuery();
            connection.close();
            while (resultSet.next()){
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }



}
