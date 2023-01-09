
package com.mycompany.proyectonetbean;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import javax.swing.*;
import com.db4o.ObjectSet;


import com.mycompany.proyectonetbean.Clases.*;
import com.mycompany.proyectonetbean.Clases.Espectaculo;
import com.mycompany.proyectonetbean.DB.db4o;
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
        if(chosenDb.equals("DB4o")){
            try{

                ArrayList<Espectaculo> espectaculos = db4o.selectEspectaculos( new Espectaculo());

                for (Espectaculo espectaculo: espectaculos ) {
                    String nombre = espectaculo.getNombre();
                    String id = String.valueOf(espectaculo.getId());
                    System.out.println(nombre);
                    cb_espectaculos.addItem(id+"- " + nombre);
                }

            }catch (Exception e){
                System.out.println("Error al mostrar los espectaculos");
                System.out.println(e.getMessage());
            }


        }
        else{
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


    }
    public static void getClientestoComboBox(JComboBox<String> cb_clientes) {
        if(chosenDb.equals("DB4o")){
            try{
                Cliente c = new Cliente();
                ArrayList<Cliente> clientes = db4o.selectClientes(c);

                for (Cliente cliente : clientes){

                    String nombre = cliente.getNombre();
                    String id = String.valueOf(cliente.getId());
                    System.out.println(nombre);
                    cb_clientes.addItem(id+"- " + nombre);
                }

            }catch (Exception e){
                System.out.println("Error al mostrar los clientes");
                System.out.println(e.getMessage());
            }
        }
        else{
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


    }
    public static void getEmpleadostoComboBox(JComboBox<String> cb_empleados) {

        if(chosenDb.equals("DB4o")){
            try{

                ArrayList<Empleado> empleados = db4o.selectEmpleados( new Empleado());

                for (Empleado empleado : empleados){

                    String nombre = empleado.getNombre();
                    String apellido = empleado.getApellido();
                    String id = String.valueOf(empleado.getId());
                    System.out.println(nombre);

                    cb_empleados.addItem(id+"- " + nombre + " "+ apellido);

                }

            }catch (Exception e){
                System.out.println("Error al mostrar los empleados");
                System.out.println(e.getMessage());
            }
        }
        else{
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


    }

    public static Espectaculo getEspectaculoByID(String id) throws SQLException {
        Espectaculo espectaculo = new Espectaculo();

        if(chosenDb.equals("DB4o")){
            espectaculo.setId(id);

            try{

                Espectaculo espectaculoRes = db4o.selectEspectaculoById( espectaculo);
                espectaculo  = espectaculoRes;


            }catch (Exception e){
                System.out.println("Error al recoger el espectaculo");
                System.out.println(e.getMessage());
            }


        }
        else{
            String sql = "Select * from espectaculos where id = "+id;
            ResultSet result = null;
            result = Db.selects(sql, chosenDb);

            if (result.next()){
                espectaculo.setNombre(result.getString("nombre"));
                espectaculo.setAforo(result.getInt("aforo"));
                espectaculo.setDescripcion(result.getString("descripcion"));
                espectaculo.setLugar(result.getString("lugar"));
                espectaculo.setCoste(result.getInt("coste"));
            }
        }


        return  espectaculo;
    }
    public static Empleado getEmpleadoByID(String id) throws SQLException {
        Empleado empleado = new Empleado();

        if(chosenDb.equals("DB4o")){

            try{
                empleado.setId(id);

                Empleado empleadoRes = db4o.selectEmpleadoById(empleado);

                empleado = empleadoRes;

            }catch (Exception e){
                System.out.println("Error al recoger el empleado");
                System.out.println(e.getMessage());
            }
        }
        else{
            String sql = "Select * from empleados where id = "+id;
            ResultSet result = null;
            result = Db.selects(sql, chosenDb);

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
        }

        return  empleado;
    }

    public static void insertEspectaculo(Espectaculo espectaculo){
        if(chosenDb.equals("DB4o")){

            db4o.InsertEspectaculo(espectaculo);
        }
        else{
            String sql = "INSERT INTO espectaculos (nombre, aforo, descripcion, lugar,coste,empleado_cargo) VALUES('"+espectaculo.getNombre()+"', '" +espectaculo.getAforo()+"', '"+espectaculo.getDescripcion()+"', '"+espectaculo.getLugar()+"', '"+espectaculo.getCoste()+"',"+ null +")";
            Db.inserts(sql,chosenDb);
        }

    }

    public static void insertEmpleado(Empleado emple){


        if(chosenDb.equals("DB4o")){
            db4o.InsertEmpleado(emple);

        }
        else{
            LocalDate fecha_nac = emple.getFecha_nac().toInstant().atZone(ZoneId.of("Europe/Madrid")).toLocalDate();
            LocalDate fecha_contrato = emple.getFecha_contrato().toInstant().atZone(ZoneId.of("Europe/Madrid")).toLocalDate();
            String sql = "INSERT INTO empleados (nombre,apellido,dni,edad,fecha_nac,fecha_contrato,nacionalidad,cargo) VALUES ('"+emple.getNombre()+"', '" +emple.getApellido()+"', '"+emple.getDni()+"', '"+emple.getEdad()+"', '"+fecha_nac+"', '"+fecha_contrato+"', '"+emple.getNacionalidad()+"', '"+emple.getCargo()+"');";
            Db.inserts(sql,chosenDb);
        }


    }
    public static void insertCliente(Cliente cliente){
        if(chosenDb.equals("DB4o")){
            db4o.InsertCliente(cliente);

        }
        else{
            String sql = "INSERT INTO clientes (nombre,apellido,dni,edad) VALUES ('"+cliente.getNombre()+"', '" +cliente.getApellido()+"', '"+cliente.getDni()+"', '"+cliente.getEdad()+"')";
            Db.inserts(sql,chosenDb);
        }

    }

    public static void deleteEspectaculo(String id) {
        if(chosenDb.equals("DB4o")){
            Espectaculo espectaculo = new Espectaculo();
            espectaculo.setId(id);

            db4o.deleteEspectaculo(espectaculo);

        }
        else{
            String sql = "DELETE FROM espectaculos where id = '"+id+"'";
            Db.deletes(sql,chosenDb);
        }


    }
    public static void deleteCliente(String id) {
        if(chosenDb.equals("DB4o")){
            Cliente cliente = new Cliente();
            cliente.setId(id);
            db4o.deleteCliente(cliente);

        }
        else{
            String sql = "DELETE FROM clientes where id = '"+id+"'";
            Db.deletes(sql,chosenDb);
        }

    }
    public static void deleteEmpleado(String id) {
        if(chosenDb.equals("DB4o")){
            Empleado empleado = new Empleado();
            empleado.setId(id);
            db4o.deleteEmpleado(empleado);
        }
        else{
            String sql = "DELETE FROM empleados where id = '"+id+"'";
            Db.deletes(sql,chosenDb);
        }


    }
    public static ArrayList<String> getBDdata(){
        ArrayList<String> dataDb = new ArrayList<>();
        if(chosenDb.equals("DB4o")){
           Parque parque = db4o.selectParque(new Parque());

               dataDb.add("DB4o");
               dataDb.add(parque.getNombre());
               dataDb.add(parque.getApertura().toString());
               dataDb.add(parque.getDireccion());
               dataDb.add("-----");
               dataDb.add("C:\\xampp\\htdocs\\bd\\parques.yap");
               dataDb.add("----");

        }
        else{
            String sql = "SELECT * FROM parque;";
            dataDb = Db.bdinfo(sql,chosenDb);
        }


        return dataDb;


    }
    public static boolean getDni(String dni) throws SQLException {
        Boolean error = false;

        if(chosenDb.equals("DB4o")){

            try{
                Cliente cliente = new Cliente();
                cliente.setDni(dni);
                cliente = db4o.selectClienteById(cliente);

                Empleado empleado = new Empleado();
                empleado.setDni(dni);
                empleado = db4o.selectEmpleadoById(empleado);

                if(cliente == null && empleado == null){

                    return false;
                }
                else{
                    System.out.println("Entra");

                    return true;
                }
            }catch (Exception e){
                System.out.println("ERROR"+ e.getMessage());
            }


        }
        else{
            String sql = "Select * from clientes as c, empleados e where c.dni = '"+dni + "' or e.dni = '"+dni+"';";
            ResultSet result = Db.selects(sql,chosenDb);
            if(!result.next()){
                error = false;
            }
            else {
                error = true;
            }
        }

        return error;

    }

    public static void updateCliente(Cliente c) {
        if(chosenDb.equals("DB4o")){
            db4o.updateCliente(c);

        }
        else{
            String sql = "UPDATE clientes set nombre = '"+c.getNombre()+"',apellido = '"+c.getApellido() + "',edad = '"+ c.getEdad() + "' where id = '"+c.getId()+"';";
            Db.updates(sql,chosenDb);
        }


    }
    public static void updateEmpleado(Empleado e) {
        LocalDate fecha_nac = e.getFecha_nac().toInstant().atZone(ZoneId.of("Europe/Madrid")).toLocalDate();
        LocalDate fecha_contrato = e.getFecha_contrato().toInstant().atZone(ZoneId.of("Europe/Madrid")).toLocalDate();

        if(chosenDb.equals("DB4o")){
            db4o.updateEmpleado(e);

        }
        else{
            String sql = "UPDATE empleados set nombre = '"+e.getNombre()+"',apellido = '"+e.getApellido() +"',dni = '"+e.getDni()+ "',edad = '"+ e.getEdad() + "',fecha_nac = '"+fecha_nac+
                    "',fecha_contrato = '"+fecha_contrato+ "',nacionalidad = '"+e.getNacionalidad()+ "',cargo = '"+e.getCargo()+"' where id = '"+e.getId()+"';";
            Db.updates(sql,chosenDb);
        }


    }

    public static void updateEspectaculo(Espectaculo e) {
        if(chosenDb.equals("DB4o")){
            db4o.updateEspectaculo(e);
        }
        else{
            String sql = "UPDATE espectaculos set nombre = '"+e.getNombre() + "',aforo = '"+ e.getAforo()+ "',descripcion = '"+ e.getDescripcion()+"',lugar = '"+e.getLugar()+  "',coste = '"+ e.getCoste()+ "' where id = '"+e.getId()+"';";
            Db.updates(sql,chosenDb);
        }

    }

    public static Cliente getClienteByID(String id) throws SQLException {
        Cliente cliente = new Cliente();

        if(chosenDb.equals("DB4o")){
            cliente.setId(id);
            cliente = db4o.selectClienteById(cliente);

        }
        else{
            String sql = "Select * from clientes where id = "+id;
            ResultSet result = null;
            result = Db.selects(sql, chosenDb);

            if (result.next()){
                cliente.setNombre(result.getString("nombre"));
                cliente.setApellido(result.getString("apellido"));
                cliente.setDni(result.getString("dni"));
                cliente.setEdad(result.getInt("edad"));
            }
        }


        return  cliente;
    }

    public static void addClienteToEspectaculo(String idCliente, String idEspectaculo) {
        if(chosenDb.equals("DB4o")){

            RelacionEspectaculosClientes clienteRepetido = new RelacionEspectaculosClientes();
            clienteRepetido.setId_cliente(idCliente);
            RelacionEspectaculosClientes clienteRep = db4o.getRelacionRepetido(clienteRepetido);

            if (clienteRep == null){
                Espectaculo espectaculo = new Espectaculo();
                espectaculo.setId(idEspectaculo);
                Espectaculo aforoResultado=  db4o.selectEspectaculoById(espectaculo);


                RelacionEspectaculosClientes totalClientes = new RelacionEspectaculosClientes();
                totalClientes.setId_espectaculo(idEspectaculo);
                ArrayList<RelacionEspectaculosClientes> total = db4o.getRelacionAforo(totalClientes);
                System.out.println("size " + aforoResultado.getAforo());
                if(total.size() >= aforoResultado.getAforo()){
                    JOptionPane.showMessageDialog(null, "Limite de aforo alcanzado.");

                }
                else{
                    RelacionEspectaculosClientes newCliente = new RelacionEspectaculosClientes();
                    newCliente.setId_cliente(idCliente);
                    newCliente.setId_espectaculo(idEspectaculo);
                    db4o.setRelacion(newCliente);

                }


            }
            else{
                JOptionPane.showMessageDialog(null, "Este cliente ya está añadido.");
            }

        }
        else{
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

    }

    public static void getEspectaculosOfCliente(JList<String> l_espectaculos, String idCliente) {
        if(chosenDb.equals("DB4o")){
            RelacionEspectaculosClientes espesctaculosDeCliente = new RelacionEspectaculosClientes();
            espesctaculosDeCliente.setId_cliente(idCliente);
            ArrayList<RelacionEspectaculosClientes> listaEspectaculos = db4o.getRelacionAforo(espesctaculosDeCliente);

            DefaultListModel model = new DefaultListModel();
            l_espectaculos.setModel(model);

            if(listaEspectaculos.size() > 0){
                for (RelacionEspectaculosClientes espectaculo : listaEspectaculos){
                    Espectaculo espec = new Espectaculo();
                    espec.setId(espectaculo.getId_espectaculo());
                    Espectaculo e = db4o.selectEspectaculoById(espec);
                    model.addElement(e.getId()+"-"+e.getNombre());

                }
            }
            else{
                System.out.println("NO hay relacion");
            }



        }
        else{
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

    }


    public static void getEspectaculoACargo(String id, JTextField text) {
        if(chosenDb.equals("DB4o")){
        //todo
            Espectaculo espectaculo  =new Espectaculo();
            espectaculo.setEmpleado_cargo(id);
            Espectaculo e = db4o.selectEspectaculoById(espectaculo);

            if(e != null){
                text.setText(e.getNombre());
            }
            else{
                text.setText("--Sin asignar--");

            }
        }
        else{
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

    }

    public static void setEmpleadoaCargo(String id, String idEspect) {
        if(chosenDb.equals("DB4o")){
            Espectaculo espectaculo = new Espectaculo();
            espectaculo.setId(idEspect);
            espectaculo = db4o.selectEspectaculoById(espectaculo);
            espectaculo.setEmpleado_cargo(id);
            System.out.println(id +" " + idEspect);
            db4o.updateEspectaculo(espectaculo);
        }
        else{
            String sql = "UPDATE espectaculos set empleado_cargo = '"+id+"' where id = '"+idEspect+ "';";
            Db.updates(sql,chosenDb);
        }

    }

    public static void getClientesToList(String idEspectaculo, JList<String> l_clientes) {
        if(chosenDb.equals("DB4o")){

            RelacionEspectaculosClientes espectaculo = new RelacionEspectaculosClientes();
            espectaculo.setId_cliente(idEspectaculo);
            ArrayList<RelacionEspectaculosClientes> listaEspectaculos = db4o.getRelacionAforo(espectaculo);

            DefaultListModel model = new DefaultListModel();
            l_clientes.setModel(model);

            for (RelacionEspectaculosClientes e : listaEspectaculos){
                Cliente c = new Cliente();
                c.setId(e.getId_cliente());
                c = db4o.selectClienteById(c);
                model.addElement(c.getId()+"-"+c.getNombre());

            }
        }
        else{
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

    public static void insertParque() {
        if (chosenDb == "DB4o"){
            db4o.insertParque();
        }
    }

    public static void showMetadatos(JTextArea textarea) {
        if(chosenDb.equals("DB4o")) {
            db4o.metadatos(textarea);

        }
        else{
            Db.metadatos(chosenDb, textarea);

        }

    }
}
