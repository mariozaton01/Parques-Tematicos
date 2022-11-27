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
    public static void bdinfo(String baseDeDatos) {
        Connection conexion = null;
        if (baseDeDatos.equalsIgnoreCase("H2")) {
            conexion = H2.conectar();
        } else if (baseDeDatos.equalsIgnoreCase("SQLLite")) {
            conexion = SQLlite.conectar();
        } else if (baseDeDatos.equalsIgnoreCase("MySQL")) {
            conexion = MySQL.conectar();
        }

        try {
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet rs = null;
            String nombre = dbmd.getDatabaseProductName();
            String driver = dbmd.getDriverName();
            String url = dbmd.getURL();
            String usuario = dbmd.getUserName();

            ArrayList<String> datosBd = new ArrayList<String>();
            datosBd.add(nombre);
            datosBd.add(driver);
            datosBd.add(url);
            datosBd.add(usuario);

            //return datosBd;

            rs = dbmd.getSchemas();
            while (rs.next()) {
                System.out.println("Esquema: " + rs.getString(1));
            }
            //Nombres de tablas
            rs = dbmd.getTables(null, null, null, null);
            ArrayList<String> tablas = new ArrayList<>();
            while (rs.next()) {
                System.out.println("Tabla: " + rs.getString(3));
                tablas.add(rs.getString(3));
            }
            //Nombres de columnas
            rs = dbmd.getColumns(null, null, null, null);
            while (rs.next()) {
                //Invalid value null for parameter "table" exception
                try {
                    System.out.println("Columna: " + rs.getString(4));
                } catch (Exception e) {
                    System.out.println("Error al obtener el nombre de la columna");
                }
            }
            //Cual es la clave primaria de cada tabla
            for (String tabla : tablas) {
                rs = dbmd.getPrimaryKeys(null, null, tabla);
                while (rs.next()) {
                    System.out.println("Clave primaria de " + tabla + ": " + rs.getString(4));
                }
            }
            System.out.println("*********");

            //Por cada tabla, mostrar sus columnas
            rs = dbmd.getTables(null, null, null, null);
            while (rs.next()) {
                System.out.println("Tabla: " + rs.getString(3));
                ResultSet rs2 = dbmd.getColumns(null, null, rs.getString(3), null);
                while (rs2.next()) {
                    System.out.println("Columna: " + rs2.getString(4));
                    //Tipo
                    System.out.println("Tipo: " + rs2.getString(6));
                    //Tamaño
                    System.out.println("Tamaño: " + rs2.getString(7));
                    //Nulo
                    System.out.println("Nulo: " + rs2.getString(18));
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
