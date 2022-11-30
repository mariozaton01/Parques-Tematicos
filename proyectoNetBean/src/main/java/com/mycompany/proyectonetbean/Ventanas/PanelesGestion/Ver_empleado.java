/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.proyectonetbean.Ventanas.PanelesGestion;
import com.mycompany.proyectonetbean.Clases.Cliente;
import com.mycompany.proyectonetbean.Clases.Empleado;
import com.mycompany.proyectonetbean.ProyectoNetBean;

import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author mario
 */
public class Ver_empleado extends javax.swing.JPanel {

    /**
     * Creates new form Ver_empleado
     */
    ArrayList<Empleado>lista_empleados;
    public Ver_empleado() {
        initComponents();
        ProyectoNetBean.getEmpleadostoComboBox(cb_emple);


    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        t_name = new javax.swing.JTextField();
        t_apellido = new javax.swing.JTextField();
        t_dni = new javax.swing.JTextField();
        n_edad = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        t_nacionalidad = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cb_cargo = new javax.swing.JComboBox<>();
        b_update_emple = new javax.swing.JButton();
        b_editar_emple = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        cb_emple = new javax.swing.JComboBox<>();
        c_fecha_nac = new com.toedter.calendar.JCalendar();
        c_fecha_contrato = new com.toedter.calendar.JCalendar();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Ver empleados");

        jLabel2.setText("Nombre:");

        jLabel3.setText("Apellido");

        jLabel4.setText("DNI:");

        jLabel5.setText("Edad:");

        t_name.setText("name");
        t_name.setEnabled(false);

        t_apellido.setText("ape");
        t_apellido.setEnabled(false);

        t_dni.setText("dni");
        t_dni.setEnabled(false);

        n_edad.setEnabled(false);
        n_edad.setPreferredSize(new java.awt.Dimension(84, 22));

        jLabel6.setText("Fecha nacimiento:");

        jLabel7.setText("Fecha contratacion:");

        t_nacionalidad.setEnabled(false);
        t_nacionalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_nacionalidadActionPerformed(evt);
            }
        });

        jLabel8.setText("Nacionalidad:");

        jLabel9.setText("Cargo:");

        cb_cargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gerente", "Trabajador", " " }));
        cb_cargo.setEnabled(false);
        cb_cargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_cargoActionPerformed(evt);
            }
        });

        b_update_emple.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        b_update_emple.setText("Guardar");
        b_update_emple.setEnabled(false);
        b_update_emple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_update_empleActionPerformed(evt);
            }
        });

        b_editar_emple.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        b_editar_emple.setText("Editar");
        b_editar_emple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_editar_empleActionPerformed(evt);
            }
        });

        jLabel10.setText("Seleccionar empleado:");

        cb_emple.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                try {
                    cb_empleItemStateChanged(evt);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(556, 556, 556)
                .addComponent(jLabel1)
                .addGap(536, 536, 536))
            .addGroup(layout.createSequentialGroup()
                .addGap(149, 149, 149)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(b_editar_emple, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t_nacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(178, 178, 178)
                        .addComponent(b_update_emple, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(jLabel10)
                        .addGap(65, 65, 65)
                        .addComponent(cb_emple, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(cb_cargo, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6))
                                .addGap(67, 67, 67)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(c_fecha_nac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(t_name)
                                    .addComponent(t_dni, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel5))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(n_edad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(t_apellido)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(c_fecha_contrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(346, 346, 346))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cb_emple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(t_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(n_edad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(t_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(t_dni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(c_fecha_nac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c_fecha_contrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(t_nacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(jLabel9)
                        .addComponent(cb_cargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(b_update_emple, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b_editar_emple, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(74, 74, 74))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cb_cargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_cargoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_cargoActionPerformed

    private void b_update_empleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_update_empleActionPerformed
        // TODO add your handling code here:
        Empleado emple = new Empleado(t_name.getText(),t_apellido.getText(),t_dni.getText(),9 ,t_nacionalidad.getText(), c_fecha_nac.getDate(),  c_fecha_contrato.getDate(), cb_cargo.getSelectedIndex());
        
        //db.updateEmpleado(emple);
    }//GEN-LAST:event_b_update_empleActionPerformed

    private void b_editar_empleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_editar_empleActionPerformed
        // TODO add your handling code here:
        t_name.setEnabled(true);
        t_apellido.setEnabled(true);
        c_fecha_nac.setEnabled(true);
        c_fecha_contrato.setEnabled(true);
        n_edad.setEnabled(true);
        t_nacionalidad.setEnabled(true);

        b_update_emple.setEnabled(true);
           
    }//GEN-LAST:event_b_editar_empleActionPerformed

    private void cb_empleItemStateChanged(java.awt.event.ItemEvent evt) throws SQLException {//GEN-FIRST:event_cb_empleItemStateChanged
        // TODO add your handling code here:
        //db.get_empleados();
        if(cb_emple.getItemCount() > 0) {
            String selectedValue = cb_emple.getSelectedItem().toString();
            String id = selectedValue.split("-")[0];
            Empleado emple = ProyectoNetBean.getEmpleadoByID(id);

            t_name.setText(emple.getNombre());
            t_apellido.setText(emple.getApellido());
            c_fecha_nac.setDate(emple.getFecha_nac());
            c_fecha_contrato.setDate(emple.getFecha_contrato());
            n_edad.setValue(emple.getEdad());
            t_nacionalidad.setText(emple.getNacionalidad());

            b_update_emple.setEnabled(false);

            t_name.setEnabled(false);
            t_apellido.setEnabled(false);
            c_fecha_nac.setEnabled(false);
            c_fecha_contrato.setEnabled(false);
            n_edad.setEnabled(false);
            t_nacionalidad.setEnabled(false);

            b_update_emple.setEnabled(false);
        }


        
    }//GEN-LAST:event_cb_empleItemStateChanged

    private void t_nacionalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_nacionalidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_nacionalidadActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_editar_emple;
    private javax.swing.JButton b_update_emple;
    private com.toedter.calendar.JCalendar c_fecha_contrato;
    private com.toedter.calendar.JCalendar c_fecha_nac;
    private javax.swing.JComboBox<String> cb_cargo;
    private javax.swing.JComboBox<String> cb_emple;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSpinner n_edad;
    private javax.swing.JTextField t_apellido;
    private javax.swing.JTextField t_dni;
    private javax.swing.JTextField t_nacionalidad;
    private javax.swing.JTextField t_name;
    // End of variables declaration//GEN-END:variables
}
