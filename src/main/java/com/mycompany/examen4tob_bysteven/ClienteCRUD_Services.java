/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examen4tob_bysteven;

import java.sql.SQLException;
import java.util.*;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import modelo.Vehiculo;
import servicios.ClienteServicio;
import servicios.VehiculosServicio;

/**
 *
 * @author Steven
 */
public class ClienteCRUD_Services {
    
    private DefaultTableModel modeloTabla;
    public final ClienteServicio clienteServicio;
    private final VehiculosServicio vehiculoServicio;
    private String cedulaSeleccionado;
    
    private JTextField txtNombre;
    private JTextField txtCedula;
    private JTextField txtCorreo;
    private JTextField txtTelefono;
    private JTextField txtPlaca;
    private JComboBox<String> cmbEstado;
    private JComboBox<String> cmbTipoCliente;
    private JComboBox<String> cmbPlaca;
    private JTextField txtCiudad;
    private JTable tablaClientes;
    private JComboBox<String> cmbEstadoFiltro;
    
    
    public ClienteCRUD_Services(JTable tablaClientes, JTextField txtNombre, JTextField txtCedula, JTextField txtCorreo, JTextField txtTelefono, JTextField txtPlaca, JComboBox<String> cmbPlaca, JComboBox<String> cmbEstado, JComboBox<String> cmbTipoCliente, JTextField txtCiudad, JComboBox<String> cmbEstadoFiltro) throws SQLException
    {
        this.tablaClientes = tablaClientes;
        this.txtNombre = txtNombre;
        this.txtCedula = txtCedula;
        this.txtCorreo = txtCorreo;
        this.txtTelefono = txtTelefono;
        this.txtPlaca = txtPlaca;
        this.cmbEstado = cmbEstado;
        this.cmbTipoCliente = cmbTipoCliente;
        this.txtCiudad = txtCiudad;
        this.cmbPlaca = cmbPlaca;
        this.cmbEstadoFiltro = cmbEstadoFiltro;
        
        
        clienteServicio = new ClienteServicio();
        vehiculoServicio = new VehiculosServicio();
        modeloTabla = new DefaultTableModel(
            new Object[]{"Cedula", "Nombre", "Correo", "Telefono", "Placa", "Ciudad", "Estado", "Tipo de Cliente"}, 0
        ) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        
    }
    
   

    
    public void CargarTablaCliente()
    {
        modeloTabla.setRowCount(0);

    try {
         List<Vehiculo> lisV= vehiculoServicio.listar();

        for(Vehiculo v : lisV ){
            cmbPlaca.addItem(v.getPlaca());            
        }
        
        if (cmbEstadoFiltro.getSelectedItem() != null) {
            String estadoFiltro = cmbEstadoFiltro.getSelectedItem().toString();
            List<Cliente> clientesFiltrados;

            if ("Activo".equals(estadoFiltro)) {
                clientesFiltrados = clienteServicio.listarPorActivos();
            } else if ("Inactivo".equals(estadoFiltro)) {
                clientesFiltrados = clienteServicio.listarPorInactivos();
            } else {
                clientesFiltrados = clienteServicio.listar();
            }

            for (Cliente cliente : clientesFiltrados) {
                modeloTabla.addRow(new Object[]{
                    cliente.getCedula(),
                    cliente.getNombre(),
                    cliente.getTelefono(),
                    cliente.getCorreo(),
                    cliente.getVehiculo().getPlaca(),
                    cliente.getCiudad(),
                    cliente.getEstado(),
                    cliente.getTipoCliente()
                });
            }
        } 

        

        tablaClientes.setModel(modeloTabla);

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(
            null,
            "No se pudieron cargar los clientes.\n"
                    + e.getMessage(),
            "Error de base de datos",
            JOptionPane.ERROR_MESSAGE
        );
    }
    }

    public void limpiar()
    {
        txtNombre.setText("");
        txtCedula.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
        txtPlaca.setText("");
        cmbEstado.setSelectedIndex(0);
        cmbTipoCliente.setSelectedIndex(0);
        txtCiudad.setText("");
    }
    
    public void ExtraerDatosTabla()
    {
        int filaVista = tablaClientes.getSelectedRow();

        if (filaVista == -1) {
            return;
        }

        int filaModelo = tablaClientes.convertRowIndexToModel(
            filaVista
    );

    cedulaSeleccionado = modeloTabla
            .getValueAt(filaModelo, 0)
            .toString();

    txtCedula.setText(
        modeloTabla.getValueAt(filaModelo, 0).toString()
    );

    txtNombre.setText(
        modeloTabla.getValueAt(filaModelo, 1).toString()
    );

    txtCorreo.setText(
        modeloTabla.getValueAt(filaModelo, 2).toString()
    );

    txtTelefono.setText(
        modeloTabla.getValueAt(filaModelo, 3).toString()
    );
    txtPlaca.setText(
        modeloTabla.getValueAt(filaModelo, 4).toString()
    );
    
    txtCiudad.setText(
        modeloTabla.getValueAt(filaModelo, 5).toString()
    );
    
    if( "Activo".equals(modeloTabla.getValueAt(filaModelo, 6).toString()))
    {
        cmbEstado.setSelectedIndex(0);
    }
    else 
    {
        cmbEstado.setSelectedIndex(1);
    }   
    
    if ("Regular".contains(modeloTabla.getValueAt(filaModelo, 7).toString()))
    {
        cmbTipoCliente.setSelectedItem("Regular");
    } else if ("Preferencial".contains(modeloTabla.getValueAt(filaModelo, 7).toString()))
    {
         cmbTipoCliente.setSelectedItem("Preferencial");
    }
    else if ("Corporativo".contains(modeloTabla.getValueAt(filaModelo, 7).toString()))
    {
         cmbTipoCliente.setSelectedItem("Corporativo");
    }
    }
    
    public Cliente leerClienteFormulario() throws SQLException
    {
        String cedula = txtCedula.getText().trim();
        String nombre = txtNombre.getText().trim();
        String correo = txtCorreo.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String placa = txtPlaca.getText().trim();
        String ciudad = txtCiudad.getText().trim();
        String estado = Objects.toString(cmbEstado.getSelectedItem(), "").trim();
        String tipoCliente = Objects.toString(cmbTipoCliente.getSelectedItem(), "").trim();

        if (cedula.isEmpty()
                || nombre.isEmpty()
                || correo.isEmpty()
                || telefono.isEmpty()
                || placa.isEmpty()
                || ciudad.isEmpty()) {
            throw new IllegalArgumentException("Debe completar todos los campos.");
        }

        Vehiculo vehiculoSeleccionado = null;
        for (Vehiculo vehiculo : vehiculoServicio.listar()) {
            if (vehiculo.getPlaca() != null
                    && vehiculo.getPlaca().trim().equalsIgnoreCase(placa)) {
                vehiculoSeleccionado = vehiculo;
                break;
            }
        }

        if (vehiculoSeleccionado == null) {
            throw new IllegalArgumentException(
                "La placa ingresada no corresponde a un vehículo registrado."
            );
        }

        return new Cliente(
            cedula,
            nombre,
            correo,
            telefono,
            vehiculoSeleccionado,
            ciudad,
            tipoCliente,
            estado
        );
    }
    
    public void GuardarCliente()
    {
        try 
        {
            vehiculoServicio.Guardar(txtPlaca.getText().trim());

            Cliente cliente = leerClienteFormulario();
        
            clienteServicio.guardar(cliente);
        
            CargarTablaCliente();

            limpiar();

            JOptionPane.showMessageDialog(
                null,
                "Cliente guardado correctamente."
            );

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(
                null,
                e.getMessage(),
                "Datos incorrectos",
                JOptionPane.WARNING_MESSAGE
            );

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                null,
                "No se pudo guardar el cliente.\n" + e.getMessage(),
            "Error de base de datos",
            JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void ActualizarCliente()
    {
        try 
        {

            Cliente cliente = leerClienteFormulario();
        
            clienteServicio.actualizar(cedulaSeleccionado, cliente);
        
            CargarTablaCliente();

            limpiar();

            JOptionPane.showMessageDialog(
                null,
                "Cliente actualizado correctamente."
            );

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(
                null,
                e.getMessage(),
                "Datos incorrectos",
                JOptionPane.WARNING_MESSAGE
            );

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                null,
                "No se pudo actualizar el cliente.\n" + e.getMessage(),
            "Error de base de datos",
            JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void EliminarCliente()
    {
        try 
        {
            ExtraerDatosTabla();

            Cliente cliente = leerClienteFormulario();

            cliente.setEstado("Inactivo");

            clienteServicio.actualizar(cedulaSeleccionado, cliente);
            
            limpiar();

            JOptionPane.showMessageDialog(
                null,
                "Cliente eliminado correctamente."
            );

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                null,
                "No se pudo eliminar el cliente.\n" + e.getMessage(),
            "Error de base de datos",
            JOptionPane.ERROR_MESSAGE
            );
        }
    }




}
