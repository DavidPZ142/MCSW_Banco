package edu.escuelaing.mcsw.persistencia;

import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


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
        UUID uuid = UUID.randomUUID();
        res = new JSONObject();
        String ccOrigenSelect = "UPDATE usuario SET fondos = fondos + ? where cedula = ?";
        String ccDestinoSelect = "UPDATE usuario SET fondos = fondos - ? where cedula = ?";
        String transaccion  = "INSERT INTO transaccion values (?,?,?,?)";
        try {

            PreparedStatement stmt = connection.prepareStatement(ccOrigenSelect);
            stmt.setInt(1, Integer.parseInt(monto));
            stmt.setString(2, ccDestino);
            PreparedStatement stmt2 = connection.prepareStatement(ccDestinoSelect);
            stmt2.setInt(1, Integer.parseInt(monto));
            stmt2.setString(2, ccOrigen);
            PreparedStatement stmt3 = connection.prepareStatement(transaccion);
            stmt3.setString(1,uuid.toString());
            stmt3.setString(2,ccOrigen);
            stmt3.setString(3,ccDestino);
            stmt3.setInt(4,Integer.parseInt(monto));
            System.out.println(stmt.executeUpdate());
            System.out.println(stmt2.executeUpdate());
            System.out.println(stmt3.executeUpdate());

            return res.put("transferencia", true);

        }catch (SQLException e){
            e.printStackTrace();
        }

        return res.put("transferencia", false);

    }

    public JSONObject verTransferencias(){
        res =new JSONObject();
        int key = 0;
        String select = "SELECT * FROM transaccion ;";
        try {
            ResultSet resultSet = connection.prepareStatement(select).executeQuery();
            while ( resultSet.next()){
                JSONObject res2 = new JSONObject();
                key +=1;
                res2.put("numtransaccion ", resultSet.getString("numtransaccion"));
                res2.put("cedulaemisor", resultSet.getString("cedulaemisor"));
                res2.put("cedulareceptor",resultSet.getString("cedulareceptor"));
                res2.put("cantidad",resultSet.getInt("cantidad"));
                res.put(String.valueOf(key), res2);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }


    public JSONObject verMonto(String cedula){
        res = new JSONObject();
        String select = "SELECT nombre, fondos FROM usuario where usuario.cedula ='"+cedula+"';";
        try {
            ResultSet resultSet = connection.prepareStatement(select).executeQuery();
            resultSet.next();
            res.put("nombre", resultSet.getString("nombre"));
            res.put("fondos", resultSet.getString("fondos"));

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
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    public JSONObject modificarMonto(String cedula, String cantidad){
        res = new JSONObject();
        String update = "UPDATE usuario SET fondos = ? where cedula = ? ;";

        try {
            PreparedStatement stmt = connection.prepareStatement(update);
            stmt.setInt(1, Integer.parseInt(cantidad));
            stmt.setString(2, cedula);
            stmt.executeUpdate();
            return res.put("modificacion", true);


        }catch (SQLException e){
            e.printStackTrace();
        }

        return res.put("modificacion", false);
    }

    public JSONObject solicitarSobregiro(String cedula , String monto){
        res = new JSONObject();
        UUID uuid = UUID.randomUUID();
        String insert = "INSERT INTO autorizacion values (?,?,?);";
        try{
            PreparedStatement stmt = connection.prepareStatement(insert);
            stmt.setString(1,uuid.toString());
            stmt.setString(2,cedula);
            stmt.setInt(3,Integer.parseInt(monto));
            System.out.println(stmt.executeUpdate());
            return res.put("Sobregiro", true);


        }catch(SQLException e){
            e.printStackTrace();
        }
        return res.put("Sobregiro", false);

    }

}
