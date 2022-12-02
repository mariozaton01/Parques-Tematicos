
package com.mycompany.proyectonetbean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import javax.swing.*;

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
        String sql = "SELECT * from empleados where cargo = 1";
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
        LocalDate fecha_nac = emple.getFecha_nac().toInstant().atZone(ZoneId.of("Europe/Madrid")).toLocalDate();
        LocalDate fecha_contrato = emple.getFecha_contrato().toInstant().atZone(ZoneId.of("Europe/Madrid")).toLocalDate();

        String sql = "INSERT INTO empleados (nombre,apellido,dni,edad,fecha_nac,fecha_contrato,nacionalidad,cargo) VALUES ('"+emple.getNombre()+"', '" +emple.getApellido()+"', '"+emple.getDni()+"', '"+emple.getEdad()+"', '"+fecha_nac+"', '"+fecha_contrato+"', '"+emple.getNacionalidad()+"', '"+emple.getCargo()+"');";
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
        String sql = "Select * from clientes as c, empleados e where c.dni = '"+dni + "' or e.dni = '"+dni+"';";
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
        LocalDate fecha_nac = e.getFecha_nac().toInstant().atZone(ZoneId.of("Europe/Madrid")).toLocalDate();
        LocalDate fecha_contrato = e.getFecha_contrato().toInstant().atZone(ZoneId.of("Europe/Madrid")).toLocalDate();
        
        String sql = "UPDATE empleados set nombre = '"+e.getNombre()+"',apellido = '"+e.getApellido() +"',dni = '"+e.getDni()+ "',edad = '"+ e.getEdad() + "',fecha_nac = '"+fecha_nac+
                "',fecha_contrato = '"+fecha_contrato+ "',nacionalidad = '"+e.getNacionalidad()+ "',cargo = '"+e.getCargo()+"' where id = '"+e.getId()+"';";
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

    public static void addClienteToEspectaculo(String idCliente, String idEspectaculo) {
        String selectEspectaculo = "SELECT aforo from espectaculos where id = '"+idEspectaculo+"';";
        String selectCliente = "SELECT count(id) as total FROM espectaculo_clientes where id_cliente = '"+idCliente+"';";
        String selectrepetido = "SELECT id_cliente, id_espectaculo FROM espectaculo_clientes where id_cliente = '"+idCliente+"' and id_espectaculo = '"+idEspectaculo+"';";
        ResultSet result = null;
        try {
            result = Db.selects(selectrepetido , chosenDb);
            if(!result.next()){

                result = Db.selects(selectEspectaculo , chosenDb);
                result.next();
                int aforo = result.getInt("aforo");
                result = Db.selects(selectCliente , chosenDb);
                result.next();
                int total = result.getInt("total");

                if (total < aforo ){
                    String sql = "INSERT INTO espectaculo_clientes(id_espectaculo, id_cliente)  VALUES('"+ idEspectaculo +"', '"+idCliente+"');";
                    Db.inserts(sql, chosenDb);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Aforo maximo alcanzado.");

                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Este cliente ya está añadido.");

            }

        }
        catch (Exception e){
            System.out.println("Herror: "+ e.getMessage());
        }



    }

    public static void getEspectaculosOfCliente(JList<String> l_espectaculos, String idCliente) {
        String sql = "SELECT e.nombre as nombre, b.id_espectaculo as id FROM espectaculos as e inner join espectaculo_clientes as b on e.id = b.id_espectaculo where b.id_cliente = '"+idCliente + "'";
        ResultSet result = null;

        try{
            result = Db.selects(sql,chosenDb);
            DefaultListModel model = new DefaultListModel();
            l_espectaculos.setModel(model);

            while(result.next()){
                model.addElement(result.getInt("id")+"-"+result.getString("nombre"));
            }
        }
        catch (Exception e){
            System.out.println("Ha ocurrido un error: "+ e.getMessage());

        }
    }


    public static void getEspectaculoACargo(String id, JTextField text) {
        String sql = "SELECT nombre FROM espectaculos where empleado_cargo = '"+id + "'";
        ResultSet result = null;
        try{
            result = Db.selects(sql,chosenDb);

            if (result.next()){
                text.setText(result.getString("nombre"));
            }else {
                text.setText("--Sin asignar--");
            }
        }
        catch (Exception e){
            System.out.println("Ha ocurrido un error: "+ e.getMessage());

        }
    }

    public static void setEmpleadoaCargo(String id, String idEspect) {
        String sql = "UPDATE espectaculos set empleado_cargo = '"+id+"' where id = '"+idEspect+ "';";
        Db.updates(sql,chosenDb);
    }

    public static void getClientesToList(String idEspectaculo, JList<String> l_clientes) {
        String sql = "SELECT c.nombre as nombre, c.apellido as apellido FROM clientes as c inner join espectaculo_clientes as b on c.id = b.id_cliente where b.id_espectaculo = '"+idEspectaculo + "'";
        ResultSet result = null;

        try{
            result = Db.selects(sql,chosenDb);
            DefaultListModel model = new DefaultListModel();
            l_clientes.setModel(model);

            while(result.next()){
                model.addElement(result.getString("nombre")+"-"+result.getString("apellido"));
            }
        }
        catch (Exception e){
            System.out.println("Ha ocurrido un error: "+ e.getMessage());

        }
    }
}
