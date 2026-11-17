/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

/**
 *
 * @author Steven
 */

import datos.ClienteDAO;
import java.sql.SQLException;
import java.util.*;
import modelo.Cliente;

public class ClienteServicio {
    private final ClienteDAO clienteDAO;

    public ClienteServicio() throws SQLException {
        this.clienteDAO = new ClienteDAO();
    }

    public void guardar(Cliente cliente) throws SQLException {
        validar(cliente);
        validarEstadoDuplicado(cliente);
        clienteDAO.guardar(cliente);
    }

    public List<Cliente> listar() throws SQLException {
        return clienteDAO.listar();
    }
    
    public List<Cliente> listarPorActivos() throws SQLException
    {
        List<Cliente> list = new ArrayList<>();
        for (Cliente c: listar())
        {
            if ("Activo".equals(c.getEstado()))
            {
                list.add(c);
            }
        }
        return list;
    }
            
    public List<Cliente> listarPorInactivos() throws SQLException
    {
        List<Cliente> list = new ArrayList<>();
        for (Cliente c: listar())
        {
            if ("Inactivo".equals(c.getEstado()))
            {
                list.add(c);
            }
        }
        return list;
    }

    public List<Cliente> buscar(String Nombre, String Cedula, String Ciudad) throws SQLException {
        return clienteDAO.buscar(Nombre, Cedula, Ciudad);
    }
    public boolean actualizar(String codigoOriginal, Cliente cliente)
            throws SQLException {

        validar(cliente);

        if (codigoOriginal == null || codigoOriginal.isBlank()) {
            throw new IllegalArgumentException(
                "Seleccione un cliente para actualizar."
            );
        }

        return clienteDAO.actualizar(codigoOriginal, cliente);
    }

    public boolean eliminar(String codigo) throws SQLException {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException(
                "Seleccione un cliente para eliminar."
            );
        }

        return clienteDAO.eliminar(codigo);
    }

    private void validar(Cliente cliente) throws SQLException {
        if (cliente.getCedula().isBlank()
                || cliente.getNombre().isBlank()) 
        {

            throw new IllegalArgumentException(
                "La cedula y el nombre son obligatorios."
            );
        }
        
        if (cliente.getTelefono().isBlank())
        {
            throw new IllegalArgumentException(
                "El teléfono es obligatorio"
            );
        }
        
        if (cliente.getCiudad().isBlank())
        {
            throw new IllegalArgumentException(
                "La ciudad es obligatoria"
            );
        }
        
        if (!cliente.getCorreo().contains("@") || !cliente.getCorreo().contains("."))
        {
            throw new IllegalArgumentException(
                 "Ingrese un correo electrónico válido"
            );
        }
        
        if (cliente.getCedula().length()!=10)
        {
            throw new IllegalArgumentException(
                    "La cedula debe contener 10 números"
            ); 
        }
        
        if(cliente.getTelefono().length()!=10)
        {
           throw new IllegalArgumentException(
                    "El teléfono debe contener 10 números"
            ); 
        }
        
    }

    private void validarEstadoDuplicado(Cliente cliente) throws SQLException {
        for (Cliente cli : clienteDAO.buscar(
                cliente.getNombre(),
                cliente.getCedula(),
                cliente.getCiudad())) 
        {
            if (Objects.equals(cli.getCedula(), cliente.getCedula()))
            {
                if (Objects.equals(cli.getEstado(), cliente.getEstado()))
                {
                    throw new IllegalArgumentException(
                        "El cliente ya está "+cliente.getEstado()
                    );
                }
            }
        }
        
    }
}
