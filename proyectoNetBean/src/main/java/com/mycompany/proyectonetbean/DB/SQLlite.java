package com.mycompany.proyectonetbean.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLlite {
    public static Connection conectar() {
        try {
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:parques");
            return conexion;
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos.");
            throw new RuntimeException(e);
        }
    }
}
