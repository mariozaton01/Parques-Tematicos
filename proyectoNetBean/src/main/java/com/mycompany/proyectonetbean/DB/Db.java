package com.mycompany.proyectonetbean.DB;



import org.h2.jdbc.*;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;


public class Db {
    public static void desconectar(Connection conexion) {
        try {
            conexion.close();
            //System.out.println("Desconectado de la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al desconectar de la base de datos SQLLite");
            throw new RuntimeException(e);
        }
    }

    //Insertar
    public static void inserts(String SQL, String baseDeDatos) {
        Connection conexion = null;
        if (baseDeDatos.equalsIgnoreCase("H2")) {
            conexion = H2.conectar();
        } else if (baseDeDatos.equalsIgnoreCase("SQLLite")) {
            conexion = SQLlite.conectar();
        } else if (baseDeDatos.equalsIgnoreCase("MySQL")) {
            conexion = MySQL.conectar();
        }

        try {
            conexion.createStatement().executeUpdate(SQL);
            JOptionPane.showMessageDialog(null, "Insertado");


        } catch (SQLException e) {
            System.out.println("Error al insertar en la base de datos");
            throw new RuntimeException(e);
        } finally {
            desconectar(conexion);
        }
    }

    //Modificar
    public static void updates(String SQL, String baseDeDatos) {
        try {
            Connection conexion = null;
            if (baseDeDatos.equalsIgnoreCase("H2")) {
                conexion = H2.conectar();
            } else if (baseDeDatos.equalsIgnoreCase("SQLLite")) {
                conexion = SQLlite.conectar();
            } else if (baseDeDatos.equalsIgnoreCase("MySQL")) {
                conexion = MySQL.conectar();
            }
            conexion.createStatement().executeUpdate(SQL);
            JOptionPane.showMessageDialog(null, "MOdificado");

            desconectar(conexion);
        } catch (SQLException e) {
            System.out.println("Error al modificar.");
            throw new RuntimeException(e);
        }
    }

    //Eliminar
    public static void deletes(String SQL, String baseDeDatos) {
        try {
            Connection conexion = null;
            if (baseDeDatos.equalsIgnoreCase("H2")) {
                conexion = H2.conectar();
            } else if (baseDeDatos.equalsIgnoreCase("SQLLite")) {
                conexion = SQLlite.conectar();
            } else if (baseDeDatos.equalsIgnoreCase("MySQL")) {
                conexion = MySQL.conectar();
            }
            conexion.createStatement().executeUpdate(SQL);
            JOptionPane.showMessageDialog(null, "Eliminado");

            desconectar(conexion);
        } catch (SQLException e) {
            System.out.println("Error al eliminar.");
            throw new RuntimeException(e);
        }
    }

    //Consultar
    public static ResultSet selects(String SQL, String baseDeDatos) {
        try {
            Connection conexion = null;
            if (baseDeDatos.equalsIgnoreCase("H2")) {
                conexion = H2.conectar();
            } else if (baseDeDatos.equalsIgnoreCase("SQLLite")) {
                conexion = SQLlite.conectar();
            } else if (baseDeDatos.equalsIgnoreCase("MySQL")) {
                conexion = MySQL.conectar();
            }
            ResultSet result = conexion.createStatement().executeQuery(SQL);
            //desconectar(conexion);

            //Obtener los datos de la consulta
            return result;
        } catch (SQLException e) {
            System.out.println("Error al listar.");
            throw new RuntimeException(e);
        }
    }
    //Obtener información de la BBDD
    public static ArrayList<String> bdinfo(String sql,String baseDeDatos) {
        Connection conexion = null;
        if (baseDeDatos.equalsIgnoreCase("H2")) {
            conexion = H2.conectar();
        } else if (baseDeDatos.equalsIgnoreCase("SQLLite")) {
            conexion = SQLlite.conectar();
        } else if (baseDeDatos.equalsIgnoreCase("MySQL")) {
            conexion = MySQL.conectar();
        }
        ArrayList<String> datosBd = new ArrayList<String>();

        try {
            ResultSet result = conexion.createStatement().executeQuery(sql);
            result.next();
            DatabaseMetaData dbmd = conexion.getMetaData();
            String nombreDb = dbmd.getDatabaseProductName();
            String nombre = result.getString("nombre");
            String apertura = result.getString("apertura");
            String direccion = result.getString("direccion");
            String driver = dbmd.getDriverName();
            String url = dbmd.getURL();
            String usuario = dbmd.getUserName();

            datosBd.add(nombreDb);
            datosBd.add(nombre);
            datosBd.add(apertura);
            datosBd.add(direccion);
            datosBd.add(driver);
            datosBd.add(url);
            datosBd.add(usuario);

        } catch (Exception e) {
            System.out.println(e);
        }
        return datosBd;

    }


}
