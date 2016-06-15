/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sakila.ui;

import SNMP.client.SNMPManager;
import java.io.IOException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.snmp4j.smi.OID;
import snmp.entity.*;
import snmp.util.*;
import javax.swing.JOptionPane;

/**
 * This class search the snmp information of an IP and saves this information
 * into a database called Dispositivos,
 *
 * @author Aitor
 * @version 1.0
 */
public class SNMPInventario extends javax.swing.JFrame {

    String ip1 = "";

    /**
     * Creates new form SNMPInventario
     */
    public SNMPInventario() {
        initComponents();
    }

    /**
     *
     * Method that checks if the string is not empty and is not bigger than 255
     *
     * @param content String that will be check
     * @return Boolean if the parameter is between 0 and 255 and is not empty
     */
    public boolean checkJtextField(String content) {
        boolean verification;
        if (content.length() == 0 || Integer.parseInt(content) > 255) {
            verification = true;
        } else {
            verification = false;
        }
        return verification;
    }
public void delete (String ip1) {
try {
                        Session session = NewHibernateUtil.getSessionFactory().openSession();
                        session.beginTransaction();
                        // Search for results with the same primary key
                        Query q = session.createQuery("from Dispositivos where ip='" + ip1 + "'");
                        List resultList = q.list();
                        if (resultList.isEmpty() == false) {
                            Dispositivos c = (Dispositivos) resultList.get(0);
                            session.delete(c);
                            JOptionPane.showMessageDialog(null, "Registro Borrado", "Atención", JOptionPane.ERROR_MESSAGE);
                            session.getTransaction().commit();
                        } else {
                            JOptionPane.showMessageDialog(null, "No hay registros con esa IP", "Atención", JOptionPane.ERROR_MESSAGE);
                            
                        }
                        
                    } catch (NullPointerException e) {
                        System.out.println("Error esperado");
                    }

}
/**
 * Deletes the lines with the ip in the textfield
 * @param ip1
 * 
 */
    public void insert( String SysName, String SysDescr, String ip1) {
        try {
                        Session session = NewHibernateUtil.getSessionFactory().openSession();
                        session.beginTransaction();
                        Query q = session.createQuery("from Dispositivos where ip='" + ip1 + "'");
                        List resultList = q.list();
                        
                                    if (resultList.isEmpty() == false) {
                                        Dispositivos c = (Dispositivos) resultList.get(0);
                                        session.delete(c);
                                    }
                                    else {
                            session.getTransaction().commit();
                            Dispositivos dispositivos;
                            dispositivos = new Dispositivos();
                            dispositivos.setIp(ip1);
                            dispositivos.setName(SysName);
                            dispositivos.setDescripcion(SysDescr);

                            session = NewHibernateUtil.getSessionFactory().openSession();
                            session.beginTransaction();

                            session.save(dispositivos);

                            session.getTransaction().commit();
                                    }
                            runQueryBasedOnFirstIp();

                                
                    } 
                    catch (NullPointerException e) {
                        System.out.println("Error esperado");

                    }
        
    }
    /**
     * Method insert the information that gives SNMP Manager into the Database
     * @param ip, Sysname,Sysdescr
     * 
     */
    
    
    private static final String QUERY_BASED_FIRST_IP = "from Dispositivos d where d.ip like '";
    private static final String QUERY_ALL = "from Dispositivos";

    private void runQueryBasedOnFirstIp() {
        executeHQLQuery(QUERY_BASED_FIRST_IP + ip1A.getText() + "." + ip1B.getText() + "." + ip1C.getText() + "." + ip1D.getText() + "%'");
    }
    

    /**
     * Method command in HQL that searchs all the devices in the database
     *
     * @param ip1 in the text fields
     */

    private void runQueryAll() {
        executeHQLQuery(QUERY_ALL);
    }

    /**
     *
     * Method that search an IP
     *
     * @param ip1 in the text fields
     */

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        ip1A = new javax.swing.JFormattedTextField();
        ip1B = new javax.swing.JFormattedTextField();
        ip1C = new javax.swing.JFormattedTextField();
        ip1D = new javax.swing.JFormattedTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Buscar en guardados por IP");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        resultTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(resultTable);

        jButton2.setText("Obtener Nombre ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        ip1A.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###"))));
        ip1A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ip1AActionPerformed(evt);
            }
        });

        ip1B.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###"))));
        ip1B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ip1BFocusLost(evt);
            }
        });
        ip1B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ip1BActionPerformed(evt);
            }
        });

        ip1C.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###"))));
        ip1C.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ip1CActionPerformed(evt);
            }
        });

        ip1D.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###"))));
        ip1D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ip1DActionPerformed(evt);
            }
        });

        jButton3.setText("Listar todos");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Eliminar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setText(".");

        jLabel2.setText(".");

        jLabel3.setText(".");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("IP");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(ip1A, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ip1B, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ip1C, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ip1D, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(83, 83, 83))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ip1B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ip1A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ip1C, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ip1D, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jButton2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        runQueryBasedOnFirstIp();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        /**
         * 1.Check if jtextfield are minor than 255 2.Search for snmp
         * information 3. If ther is information saves it in the database
         *
         * @param ip in the textt fields
         * @throws IOException
         */
        etiqueta:
        try {
            if (checkJtextField(ip1A.getText()) == true) {
                ip1A.setText("");
                ip1A.getCursor();
                JOptionPane.showMessageDialog(null, "Error", "Solo numeros del rango 0 a 255", JOptionPane.ERROR_MESSAGE);

            } else if (checkJtextField(ip1B.getText()) == true) {
                ip1B.setText("");
                ip1B.getCursor();
                JOptionPane.showMessageDialog(null, "Error", "Solo numeros del rango 0 a 255", JOptionPane.ERROR_MESSAGE);

            } else if (checkJtextField(ip1C.getText()) == true) {
                ip1C.setText("");
                ip1C.getCursor();
                JOptionPane.showMessageDialog(null, "Error", "Solo numeros del rango 0 a 255", JOptionPane.ERROR_MESSAGE);

            } else if (checkJtextField(ip1D.getText()) == true) {
                ip1D.setText("");
                ip1D.getCursor();
                JOptionPane.showMessageDialog(null, "Error", "Solo numeros del rango 0 a 255", JOptionPane.ERROR_MESSAGE);

            } else {

                String ip1 = ip1A.getText() + "." + ip1B.getText() + "." + ip1C.getText() + "." + ip1D.getText();
                SNMPManager client = new SNMPManager("udp:" + ip1 + "/161");
                client.start();
                // OID oid = new OID(".1.3.6.1.2.1.1.1.0");
                String SysName = client.getAsString(new OID(".1.3.6.1.2.1.1.1.0"));
                String SysDescr = client.getAsString(new OID(".1.3.6.1.2.1.1.5.0"));

                if (SysName == null && SysDescr == null) {
                    JOptionPane.showMessageDialog(
                            null, "No se  ha encontrado la siguiente informacion para la ip: " + ip1);
                    break etiqueta;
                }
                int confirmado = JOptionPane.showConfirmDialog(
                        null, "Se ha encontrado la siguiente informacion para la ip: " + ip1 + "\n Nombre Equipo:" + SysDescr + "\n Descripción: " + SysName + "\n ¿Desea añdir la informacion a la base de datos?"
                );
                if (JOptionPane.OK_OPTION == confirmado) {
                    insert(SysName, SysDescr, ip1); 
                    
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(SNMPInventario.class.getName()).log(Level.SEVERE, null, ex);
        }
        /**
         *
         *
         */

    }//GEN-LAST:event_jButton2ActionPerformed

    private void ip1AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ip1AActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ip1AActionPerformed

    private void ip1BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ip1BActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ip1BActionPerformed

    private void ip1DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ip1DActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ip1DActionPerformed

    private void ip1CActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ip1CActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ip1CActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        /**
         *
         * @see runqueryall
         */
        runQueryAll();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void ip1BFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ip1BFocusLost

    }//GEN-LAST:event_ip1BFocusLost

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        /**
         * Check jtextfield are minor than 255 If there is any result in the
         * database and the user confirms it the line will be delete
         *
         * @throws IOException
         */

        etiqueta:
        try {
            ip1 = ip1A.getText() + "." + ip1B.getText() + "." + ip1C.getText() + "." + ip1D.getText();
            if (checkJtextField(ip1A.getText()) == true) {
                JOptionPane.showMessageDialog(null, "Solo numeros del rango 0 a 255", "Error", JOptionPane.ERROR_MESSAGE);
                ip1A.setText("");
                ip1A.getCursor();
                break etiqueta;
            } else if (checkJtextField(ip1B.getText()) == true) {

                JOptionPane.showMessageDialog(null, "Solo numeros del rango 0 a 255", "Error", JOptionPane.ERROR_MESSAGE);
                ip1B.setText("");
                ip1B.getCursor();
                break etiqueta;
            } else if (checkJtextField(ip1C.getText()) == true) {
                JOptionPane.showMessageDialog(null, "Solo numeros del rango 0 a 255", "Error", JOptionPane.ERROR_MESSAGE);
                ip1C.setText("");
                ip1C.getCursor();
                break etiqueta;
            } else if (checkJtextField(ip1D.getText()) == true) {
                JOptionPane.showMessageDialog(null, "Solo numeros del rango 0 a 255", "Error", JOptionPane.ERROR_MESSAGE);
                ip1D.setText("");
                ip1D.getCursor();
                break etiqueta;
            } else {

                int confirmado = JOptionPane.showConfirmDialog(
                        null, "¿Desea borrar la información de: " + ip1 + "?"
                );
                if (JOptionPane.OK_OPTION == confirmado) {
                    delete(ip1); 
                    
                }
            }
        } catch (NullPointerException e) {

        }
        /**
         *
         * @see runqueryall
         */
        runQueryAll();
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SNMPInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SNMPInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SNMPInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SNMPInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SNMPInventario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField ip1A;
    private javax.swing.JFormattedTextField ip1B;
    private javax.swing.JFormattedTextField ip1C;
    private javax.swing.JFormattedTextField ip1D;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTable resultTable;
    // End of variables declaration//GEN-END:variables
private void executeHQLQuery(String hql) {
        try {
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery(hql);
            List resultList = q.list();
            
            displayResult(resultList);
            session.getTransaction().commit();

        } catch (HibernateException he) {
            he.printStackTrace();
        }

    }

    private void displayResult(List resultList) {
        Vector<String> tableHeaders = new Vector<String>();
        Vector tableData = new Vector();
        tableHeaders.add("IP");
        tableHeaders.add("Descripcion");
        tableHeaders.add("Nombre");

        for (Object o : resultList) {
            Dispositivos dispositivos = (Dispositivos) o;
            Vector<Object> oneRow = new Vector<Object>();
            oneRow.add(dispositivos.getIp());
            oneRow.add(dispositivos.getName());
            oneRow.add(dispositivos.getDescripcion());

            tableData.add(oneRow);
        }
        resultTable.setModel(new DefaultTableModel(tableData, tableHeaders));
    }

}
