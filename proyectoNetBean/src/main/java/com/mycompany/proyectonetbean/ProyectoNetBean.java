
package com.mycompany.proyectonetbean;

import java.io.IOException;
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

import com.mycompany.proyectonetbean.Ventanas.*;
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
}
