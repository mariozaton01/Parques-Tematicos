package com.mycompany.proyectonetbean.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2 {
    public static Connection conectar() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el driver");
            throw new RuntimeException(e);
        }

        try {
            //Connection conexion = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/parques", "root","");
            Connection conexion = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/parques;DB_CLOSE_DELAY=-1", "sa","");
            //System.out.println("Conectado a la base de datos");
            return conexion;
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos.");
            throw new RuntimeException(e);
        }
    }
}
