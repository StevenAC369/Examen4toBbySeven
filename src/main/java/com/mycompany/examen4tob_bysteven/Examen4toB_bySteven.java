/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.examen4tob_bysteven;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author Steven
 */
public class Examen4toB_bySteven {

    public static void main(String[] args) {
       try {
            // Activa el estilo nativo de Windows para botones, cajas de texto y bordes
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Inicia el formulario en el hilo gráfico de Swing (EDT)
        SwingUtilities.invokeLater(() -> {
            try
            {
                Interfaz ventana = new Interfaz();
                ventana.setLocationRelativeTo(null); // Centra el formulario en la pantalla
                ventana.setVisible(true);
            }
            catch (SQLException ex)
            {
                 JOptionPane.showMessageDialog(null, "Error en base de datos: "+ex.toString());
            }
        });
    }
}
