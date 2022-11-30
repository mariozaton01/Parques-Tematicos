
package com.mycompany.proyectonetbean;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.mycompany.proyectonetbean.Clases.*;
import com.mycompany.proyectonetbean.Clases.Espectaculo;
import com.mycompany.proyectonetbean.Ventanas.*;
import com.mycompany.proyectonetbean.DB.Db;

public class ProyectoNetBean {
    
    private static Ventana_inicio vInicio;
    private static Ventana_gestion v_gestion;
    private static Ventana_ParqueInicio v_parque_inicio;
    private static javax.swing.JFrame vAnterior;
    private static String chosenDb;
    private static Ventana_datos_parque v_datos_parque;
    
    public static void main(String[] args) {    
        //declaramos la ventana inicial
        vInicio = new Ventana_inicio();

        // Hacemos visible la ventana inicial.
        vInicio.setVisible(true);
        vInicio.setLocationRelativeTo(null);
    }
    
    public static void Cancelar(javax.swing.JFrame v) {
        if(v == vInicio){
            System.exit(0);
        }
        if(v == v_parque_inicio){
            vAnterior = vInicio;
        }
        //v = ventana actual
        if(v.isVisible()){
            v.setVisible(false);
            vAnterior.setVisible(true);
        }
        

    }
    public static void chooseDB(String db){
        chosenDb = db;
        showInicioParque();
    }
    
    public static void showDatosRelevantes(){
        vAnterior = v_parque_inicio;
        vAnterior.setVisible(false);
        
        v_datos_parque = new Ventana_datos_parque();
        v_datos_parque.setVisible(true);
        v_datos_parque.setLocationRelativeTo(null);

    }
    
    public static void showGestion(){
        vAnterior = v_parque_inicio;
        vAnterior.setVisible(false);
        
        v_gestion = new Ventana_gestion();
        v_gestion.setVisible(true);
        v_gestion.setLocationRelativeTo(null);
        
    }
    
    public static void showInicioParque(){
        vAnterior = vInicio;
        vAnterior.setVisible(false);
        
        v_parque_inicio = new Ventana_ParqueInicio();
        v_parque_inicio.setVisible(true);
        v_parque_inicio.setLocationRelativeTo(null);
        
    }
    
    public static String getChosenDb(){
        return chosenDb;
    }

    public static void  getEmpleados(JComboBox<String> cb_empleados){

    }

    public static void getEspectaculostoComboBox(JComboBox<String> cb_espectaculos) {
        String sql = "SELECT * from espectaculos;";
        ResultSet result = null;
        try{
            result = Db.selects(sql, chosenDb);

            while(result.next()){
                String nombre = result.getString("nombre");
                String id = String.valueOf(result.getInt("id"));
                System.out.println(nombre);
                cb_espectaculos.addItem(id+"- " + nombre);

            }

        }
        catch (Exception e){
            System.out.println("Error al mostrar la lista");
            System.out.println(e.getMessage());
        }

    }
    public static void getClientestoComboBox(JComboBox<String> cb_clientes) {
        String sql = "SELECT * from clientes";
        ResultSet result = null;
        try{
            result = Db.selects(sql, chosenDb);

            while(result.next()){
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                String id = String.valueOf(result.getInt("id"));
                cb_clientes.addItem(id+"- " + nombre +" "+apellido);
            }

        }
        catch (Exception e){
            System.out.println("Error al mostrar la lista");
            System.out.println(e.getMessage());
        }

    }
    public static void getEmpleadostoComboBox(JComboBox<String> cb_empleados) {
        String sql = "SELECT * from empleados where";
        ResultSet result = null;
        try{
            result = Db.selects(sql, chosenDb);

            while(result.next()){
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                String id = String.valueOf(result.getInt("id"));

                cb_empleados.addItem(id+"- " + nombre + " "+ apellido);
            }

        }
        catch (Exception e){
            System.out.println("Error al mostrar la lista");
            System.out.println(e.getMessage());
        }

    }

    public static Espectaculo getEspectaculoByID(String id) throws SQLException {
        String sql = "Select * from espectaculos where id = "+id;
        ResultSet result = null;
        result = Db.selects(sql, chosenDb);
        Espectaculo espectaculo = new Espectaculo();

        if (result.next()){
            espectaculo.setNombre(result.getString("nombre"));
            espectaculo.setAforo(result.getInt("aforo"));
            espectaculo.setDescripcion(result.getString("descripcion"));
            espectaculo.setLugar(result.getString("lugar"));
            espectaculo.setCoste(result.getInt("coste"));
        }

        return  espectaculo;
    }
    public static Empleado getEmpleadoByID(String id) throws SQLException {
        String sql = "Select * from empleados where id = "+id;
        ResultSet result = null;
        result = Db.selects(sql, chosenDb);
        Empleado empleado = new Empleado();

        if (result.next()){
            empleado.setNombre(result.getString("nombre"));
            empleado.setApellido(result.getString("apellido"));
            empleado.setEdad(result.getInt("edad"));
            empleado.setDni(result.getString("dni"));
            empleado.setCargo(result.getInt("cargo"));
            empleado.setNacionalidad(result.getString("nacionalidad"));
            empleado.setFecha_nac(result.getDate("fecha_nac"));
            empleado.setFecha_contrato(result.getDate("fecha_contrato"));

        }

        return  empleado;
    }

    public static void insertEspectaculo(Espectaculo espectaculo){
        String sql = "INSERT INTO espectaculos (nombre, aforo, descripcion, lugar,coste,empleado_cargo) VALUES('"+espectaculo.getNombre()+"', '" +espectaculo.getAforo()+"', '"+espectaculo.getDescripcion()+"', '"+espectaculo.getLugar()+"', '"+espectaculo.getCoste()+"',"+ null +")";
        Db.inserts(sql,chosenDb);
    }

    public static void insertEmpleado(Empleado emple){
        String sql = "INSERT INTO empleados (nombre,apellido,dni,edad,fecha_nac,fecha_contrato,nacionalidad,cargo) VALUES ("+emple.getNombre()+", " +emple.getApellido()+", "+emple.getDni()+", "+emple.getEdad()+", "+emple.getFecha_nac()+", "+emple.getFecha_contrato()+", "+emple.getNacionalidad()+", "+emple.getCargo()+")";
        Db.inserts(sql,chosenDb);
    }
    public static void insertCliente(Cliente cliente){
        String sql = "INSERT INTO clientes (nombre,apellido,dni,edad) VALUES ('"+cliente.getNombre()+"', '" +cliente.getApellido()+"', '"+cliente.getDni()+"', '"+cliente.getEdad()+"')";
        Db.inserts(sql,chosenDb);
    }

    public static void deleteEspectaculo(String id) {
        String sql = "DELETE FROM espectaculos where id = '"+id+"'";
        Db.deletes(sql,chosenDb);

    }
    public static void deleteCliente(String id) {
        String sql = "DELETE FROM clientes where id = '"+id+"'";
        Db.deletes(sql,chosenDb);
    }
    public static void deleteEmpleado(String id) {
        String sql = "DELETE FROM empleados where id = '"+id+"'";
        Db.deletes(sql,chosenDb);

    }
    public static ArrayList<String> getBDdata(){
        String sql = "SELECT * FROM parque;";
        ArrayList<String> dataDb = Db.bdinfo(sql,chosenDb);

        return dataDb;


    }
    public static boolean getDni(String dni) throws SQLException {
        String sql = "Select * from clientes where dni = '"+dni + "';";
        ResultSet result = Db.selects(sql,chosenDb);
        Boolean error = false;
        if(!result.next()){
            error = false;
        }
        else {
            error = true;
        }
        return error;

    }

    public static void updateCliente(Cliente c) {
        String sql = "UPDATE clientes set nombre = '"+c.getNombre()+"',apellido = '"+c.getApellido() + "',edad = '"+ c.getEdad() + "' where id = '"+c.getId()+"';";
        Db.updates(sql,chosenDb);

    }
    public static void updateEmpleado(Empleado e) {
        String sql = "UPDATE empleados set nombre = '"+e.getNombre()+"',apellido = '"+e.getApellido() +"',dni = '"+e.getDni()+ "',edad = '"+ e.getEdad() + "',fecha_nac = '"+e.getFecha_nac()+
                "',fecha_contrato = '"+e.getFecha_contrato()+ "',nacionalidad = '"+e.getNacionalidad()+ "',cargo = '"+e.getCargo()+"' where id = '"+e.getId()+"');";
        Db.updates(sql,chosenDb);

    }

    public static void updateEspectaculo(Espectaculo e) {
        String sql = "UPDATE espectaculos set nombre = '"+e.getNombre() + "',aforo = '"+ e.getAforo()+ "',descripcion = '"+ e.getDescripcion()+"',lugar = '"+e.getLugar()+  "',coste = '"+ e.getCoste()+ "' where id = '"+e.getId()+"';";
        Db.updates(sql,chosenDb);
    }

    public static Cliente getClienteByID(String id) throws SQLException {
        String sql = "Select * from clientes where id = "+id;
        ResultSet result = null;
        result = Db.selects(sql, chosenDb);
        Cliente cliente = new Cliente();

        if (result.next()){
            cliente.setNombre(result.getString("nombre"));
            cliente.setApellido(result.getString("apellido"));
            cliente.setDni(result.getString("dni"));
            cliente.setEdad(result.getInt("edad"));
        }

        return  cliente;
    }
}
