package com.mycompany.proyectonetbean.DB;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.*;
import com.mycompany.proyectonetbean.Clases.*;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

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

            Cliente c = new Cliente();
            c.setId(cliente.getId());
            ObjectSet result =  con.queryByExample(c);

            if (result.hasNext()){
                Cliente e = (Cliente) result.next();
                e.setNombre(cliente.getNombre());
                e.setApellido(cliente.getApellido());
                e.setEdad(cliente.getEdad());

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
    public static void insertParque(){
        try{

            File f = new File("parques.db4o");
            if(!f.isFile()){
                open();

                Parque p  = new Parque();
                p.setNombre("Isla Magica");
                p.setAforo(100);
                p.setApertura(new Date());
                p.setDireccion("Direccion parque db4o");
                con.store(p);
                con.close();
            }

            ArrayList<Cliente> clientes = selectClientes(new Cliente());
            ArrayList<Empleado> empleados = selectEmpleados(new Empleado());
            ArrayList<Espectaculo> espectaculos = selectEspectaculos(new Espectaculo());

            int idCliente = 0;
            int idEmpleado = 0;
            int idEspectaculo = 0;
            for (Cliente c : clientes){
                if(Integer.parseInt(c.getId()) > idCliente){
                    idCliente = Integer.parseInt(c.getId());
                }
            }

            for (Espectaculo e : espectaculos){
                if(Integer.parseInt(e.getId()) > idEspectaculo){
                    idEspectaculo= Integer.parseInt(e.getId());

                }
            }
            for (Empleado em : empleados){
                if(Integer.parseInt(em.getId()) > idEmpleado){
                    idEmpleado = Integer.parseInt(em.getId());

                }
            }

            int listaIds[] = {idCliente, idEmpleado, idEspectaculo};
            Arrays.sort(listaIds);
            System.out.println(listaIds[0]);
            System.out.println(listaIds[1]);
            System.out.println(listaIds[2]);
            id = listaIds[2]+1;
            System.out.println(id);

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());

        }

    }
    public static Parque selectParque(Parque parque) {
        Parque p = null;
        open();

        ObjectSet result = con.queryByExample(parque);

        if (result.hasNext()){
             p = (Parque) result.next();
        }
        con.close();

        return  p;
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

            Empleado emple = new Empleado();
            emple.setId(empleado.getId());
            ObjectSet result =  con.queryByExample(emple);

            if (result.hasNext()){
                Empleado e = (Empleado) result.next();
                e.setNombre(empleado.getNombre());
                e.setApellido(empleado.getApellido());
                e.setEdad(empleado.getEdad());
                e.setCargo(empleado.getCargo());
                e.setFecha_contrato(empleado.getFecha_contrato());
                e.setFecha_nac(empleado.getFecha_nac());
                e.setNacionalidad(empleado.getNacionalidad());

                con.store(e);

            }
            con.close();
            JOptionPane.showMessageDialog(null, "Actualizado");

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
            Espectaculo espec = new Espectaculo();
            espec.setId(espectaculo.getId());

            ObjectSet result =  con.queryByExample(espec);

            if (result.hasNext()){
                Espectaculo e = (Espectaculo) result.next();
                /*if(e.getNombre().equals("")){

                }
                else{*/
                    e.setEmpleado_cargo(espectaculo.getEmpleado_cargo());

                    e.setCoste(espectaculo.getCoste());
                    e.setNombre(espectaculo.getNombre());
                    e.setAforo(espectaculo.getAforo());
                    e.setDescripcion(espectaculo.getDescripcion());
                    e.setLugar(espectaculo.getLugar());
                //}
                con.store(e);

            }
            con.close();
            JOptionPane.showMessageDialog(null, "Actualizado");

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
            Espectaculo e;

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
            con.store(newCliente);
            id++;

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
    public static void metadatos(JTextArea textarea){
        open();
        String texto = "";
        SystemInfo identity = con.ext().systemInfo();

        //System.out.println("INFO: " + identity.totalSize());
        texto += "\nMAX SIZE: " +identity.totalSize();


        StoredClass[] classes = con.ext().storedClasses();

        for (StoredClass clase: classes)
        {
            //System.out.println(clase.getName());
            texto += "\nCLASE: " + clase.getName();

            for(StoredField field : clase.getStoredFields())
            {
                texto += "\nCOLUMNA: " + field.getName() +"--- TIPO: " + field.getStoredType().getName();
                //System.out.println( field.getName()+" --" + field.getStoredType().getName());
            }
        }
        textarea.setText(texto);

        con.close();


    }
}
