package edu.escuelaing.mcsw.persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    ConnectionDb connectionDb;
    Connection connection;


    public Controller(){

        this.connectionDb = new ConnectionDb();
        connection = connectionDb.getConnect();
    }


    public List<String> selectUser(){
        List<String> list = new ArrayList<>();
        String select = "SELECT * FROM usuario;";
        try {

            ResultSet resultSet = connection.prepareStatement(select).executeQuery();
            connection.close();
            while (resultSet.next()){
                list.add(resultSet.getString("nombre"));
                list.add(resultSet.getString("cedula"));
                list.add(resultSet.getString("apellido"));
                list.add(resultSet.getString("correo"));
                list.add(resultSet.getString("contrasena"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }



}
