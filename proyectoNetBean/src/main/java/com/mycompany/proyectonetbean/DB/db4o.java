package com.mycompany.proyectonetbean.DB;

import EDU.purdue.cs.bloat.util.ResizeableArrayList;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.mycompany.proyectonetbean.Clases.*;
import com.db4o.query.Predicate;
import org.boon.Str;
import org.boon.core.Sys;

import javax.swing.*;
import java.sql.ResultSet;
import java.util.ArrayList;

public class db4o {
    static ObjectContainer con;
    private static int id = 1;

    private static void open(){
         con = Db4oEmbedded.openFile("parques.db4o");

    }

    public static void InsertCliente(Cliente cliente){
        try{
            open();
            cliente.setId(String.valueOf(id));
            id++;
            con.store(cliente);

            con.close();

            JOptionPane.showMessageDialog(null, "Insertado");

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al insertar cliente");

        }

    }
    public static void updateCliente(Cliente cliente){
        try{
            open();

            ObjectSet result =  con.queryByExample(cliente);

            if (result.hasNext()){
                Cliente e = (Cliente) result.next();
                e.setNombre(cliente.getNombre());
                con.store(e);

            }

            con.close();

            JOptionPane.showMessageDialog(null, "Actualizado");

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al actualizar cliente");

        }

    }

    public static ArrayList<Espectaculo> selectEspectaculos(Espectaculo espectaculo) {

        open();

        ObjectSet result = con.queryByExample(espectaculo);
        ArrayList<Espectaculo> lista = new ArrayList<Espectaculo>();
        while (result.hasNext()){
            lista.add((Espectaculo) result.next());
        }
        con.close();
        return  lista;
    }
    public static ArrayList<Cliente> selectClientes(Cliente cliente) {
        open();


        ObjectSet result = con.queryByExample(cliente);

        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        while (result.hasNext()){
            lista.add((Cliente) result.next());
        }
        con.close();


        return  lista;
    }
    public static ArrayList<Empleado> selectEmpleados(Empleado emple) {

        open();

        ObjectSet result = con.queryByExample(emple);

        ArrayList<Empleado> lista = new ArrayList<Empleado>();
        while (result.hasNext()){
            lista.add((Empleado) result.next());
        }
        con.close();
        return  lista;
    }
    public static ObjectSet selectParque(Parque parque) {

        open();

        ObjectSet result = con.queryByExample(parque);

        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        while (result.hasNext()){
            lista.add((Cliente) result.next());
        }
        con.close();

        return  result;
    }

    public static void InsertEmpleado(Empleado empleado){
        try{

            open();

            empleado.setId(String.valueOf(id));
            id++;

            con.store(empleado);
            con.close();
            JOptionPane.showMessageDialog(null, "Insertado");

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al insertar empleado");

        }

    }
    public static void updateEmpleado(Empleado empleado){
        try{

            open();

            ObjectSet result =  con.queryByExample(empleado);

            if (result.hasNext()){
                Empleado e = (Empleado) result.next();
                con.store(empleado);

            }
            con.close();
            JOptionPane.showMessageDialog(null, "Insertado");

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al insertar empleado");

        }

    }
    public static void InsertEspectaculo(Espectaculo espectaculo){
        try{

            open();

            espectaculo.setId(String.valueOf(id));
            id++;

            con.store(espectaculo);
            con.close();
            JOptionPane.showMessageDialog(null, "Insertado");

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "parques.db4o");

        }

    }
    public static void updateEspectaculo(Espectaculo espectaculo){
        try{

            open();

            ObjectSet result =  con.queryByExample(espectaculo);

            if (result.hasNext()){
                Espectaculo e = (Espectaculo) result.next();
                con.store(espectaculo);

            }
            con.close();
            JOptionPane.showMessageDialog(null, "Insertado");

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "parques.db4o");

        }

    }
    public static Cliente selectClienteById(Cliente cliente){
        try{
            Cliente c = new Cliente();
            open();
            ObjectSet result = null;


            result = con.queryByExample(cliente);
            if(result.hasNext()){
                c = (Cliente) result.next();
                System.out.println(c.getNombre() + c.getId());
            }
            else {
                c = null;
            }
            con.close();

            return  c;
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al recoger cliente");
            return  null;

        }
    }
    public static Empleado selectEmpleadoById(Empleado empleado){
        try{
            Empleado e = new Empleado();

            open();

            ObjectSet result = null;


            result = con.queryByExample(empleado);
            if(result.hasNext()){
                e = (Empleado) result.next();
            }
            else{
                e = null;
            }
            con.close();

            return  e;
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al recoger empleado");
            return  null;

        }
    }
    public static Espectaculo selectEspectaculoById(Espectaculo espectaculo){
        try{
            Espectaculo e = new Espectaculo();

            open();

            ObjectSet result = null;


            result = con.queryByExample(espectaculo);
            if(result.hasNext()){
                e = (Espectaculo) result.next();
            }
            else{
                e = null;
            }
            con.close();

            return  e;
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al recoger espectaculo");
            return  null;

        }
    }

    public static void deleteEspectaculo(Espectaculo espectaculo){
        try{

            open();

            ObjectSet result =  con.queryByExample(espectaculo);

            if (result.hasNext()){
                Espectaculo e = (Espectaculo) result.next();
                con.delete(e);

            }
            con.close();

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al eliminar espectaculo");

        }

    }
    public static void deleteCliente(Cliente cliente){
        try{
            open();

            ObjectSet result =  con.queryByExample(cliente);

            if (result.hasNext()){
                Cliente c = (Cliente) result.next();
                con.delete(c);

            }
            con.close();

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al eliminar cliente");

        }

    }
    public static void deleteEmpleado(Empleado empleado){
        try{
            open();

            ObjectSet result =  con.queryByExample(empleado);

            if (result.hasNext()){
                Empleado e = (Empleado) result.next();
                con.delete(e);

            }
            con.close();

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al eliminar empleado");

        }

    }

    public static ArrayList<RelacionEspectaculosClientes> getRelacionAforo(RelacionEspectaculosClientes clienteRepetido) {
        ArrayList<RelacionEspectaculosClientes> aforo = new ArrayList<RelacionEspectaculosClientes>();

        open();

        ObjectSet result = con.queryByExample(clienteRepetido);

        while(result.hasNext()){
            aforo.add ((RelacionEspectaculosClientes) result.next());
        }
        con.close();
        return  aforo;
    }

    public static void setRelacion(RelacionEspectaculosClientes newCliente){
        try{
            open();

            newCliente.setId(String.valueOf(id));
            id++;

            con.store(newCliente);
            con.close();

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al insertar en la relacion");
        }

    }

    public static RelacionEspectaculosClientes getRelacionRepetido(RelacionEspectaculosClientes clienteRepetido) {
        RelacionEspectaculosClientes rel = new RelacionEspectaculosClientes();

        open();

        ObjectSet result = con.queryByExample(clienteRepetido);

        if(result.hasNext()){
            rel= ((RelacionEspectaculosClientes) result.next());
        }else{
            rel = null;
        }
        con.close();
        return  rel;
    }
}
