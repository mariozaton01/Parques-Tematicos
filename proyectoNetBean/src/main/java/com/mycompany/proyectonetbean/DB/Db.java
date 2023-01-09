package com.mycompany.proyectonetbean.DB;



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

    public static void metadatos(String bd, JTextArea textarea){
        Connection conexion = null;
        String catalog = "";
        if (bd.equalsIgnoreCase("H2")) {
            conexion = H2.conectar();
            catalog = "PARQUES";
        } else if (bd.equalsIgnoreCase("MySQL")) {
            conexion = MySQL.conectar();
            catalog = "parques";

        }
        String texto = "";

        try {
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet rs = null;

            //Nombre de esquema
            rs = dbmd.getSchemas();

            while (rs.next()) {
                texto += "\nEsquema: " + rs.getString(1);
                //get schemas no saca nada.

            }
            texto += "\nEsquema: parques\n-------------------";

            //Nombres de tabla

            rs = dbmd.getTables(catalog,null,null,null);
            ArrayList<String> tablas = new ArrayList<>();
            while (rs.next()) {
                if(rs.getString(3).toLowerCase().equals("clientes") || rs.getString(3).toLowerCase().equals("empleados") || rs.getString(3).toLowerCase().equals("espectaculos") ||
                        rs.getString(3).toLowerCase().equals("espectaculo_cliente") || rs.getString(3).toLowerCase().equals("parque") ){
                    texto += "\nTabla: " + rs.getString(3);
                    tablas.add(rs.getString(3));

                }
            }

            //Nombres de columnas
           // rs = dbmd.getColumns("parques", null, null, null);
           /* rs = dbmd.getColumns(catalog, null, null, null);

            while (rs.next()) {
                //Invalid value null for parameter "table" exception
                try {

                    texto += "\nColumna: " + rs.getString(4);

                } catch (Exception e) {
                    System.out.println("Error al obtener el nombre de la columna");
                }
            }*/
            //Cual es la clave primaria de cada tabla
            for (String tabla : tablas) {
                rs = dbmd.getPrimaryKeys(catalog, null, tabla);
                while (rs.next()) {
                    try{
                        texto += "\nClave primaria de " + tabla + ": " + rs.getString(4);
                    }
                    catch (Exception e){
                        System.out.println("Error al obtener claves primarias: " + e.getMessage());

                    }

                }
            }
            texto += "\n-------------------------------";

            //Por cada tabla, mostrar sus columnas
            //rs = dbmd.getTables("parques", null, null, null);
            for (String tabla : tablas) {
                rs = dbmd.getTables(catalog, null, tabla, null);
                while (rs.next()) {
                    texto += "\nTabla: " + rs.getString(3);
                    //ResultSet rs2 = dbmd.getColumns("parques", null, rs.getString(3), null);
                    ResultSet rs2 = dbmd.getColumns(catalog, null, rs.getString(3), null);
                    while (rs2.next()) {

                        texto += "\nColumna: " + rs2.getString(4);

                        //Tipo
                        texto += "\nTipo: " + rs2.getString(6);

                        //Tamaño
                        texto +=  "\nTamaño: " + rs2.getString(7);

                        //Nulo
                        texto +=  "\nNulo: " + rs2.getString(18);
                    }
                }
            }

            textarea.setText(texto);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
