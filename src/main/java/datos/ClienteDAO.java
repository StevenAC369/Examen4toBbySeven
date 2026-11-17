/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Cliente;
import java.util.*;
import modelo.Vehiculo;


/**
 *
 * @author Steven
 */
public class ClienteDAO {
    public ClienteDAO() throws SQLException
    {
        String sql = "CREATE TABLE IF NOT EXISTS `vehiculos` ("
                + "  `idvehiculo` INT(11) NOT NULL AUTO_INCREMENT,"
                + "  `placa` VARCHAR(45) DEFAULT NULL,"
                + "  PRIMARY KEY (`idvehiculo`)"
                + ") ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;";
        
        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql))
                {
                      ps.executeUpdate();
                }
        
        sql = "CREATE TABLE IF NOT EXISTS `clientes` ("
                + "  `cedula` VARCHAR(10),"
                + "  `nombre` VARCHAR(45) DEFAULT NULL,"
                + "  `correo` VARCHAR(45) DEFAULT NULL,"
                + "  `telefono` VARCHAR(45) DEFAULT NULL,"
                + "  `idvehiculo` INT(11) DEFAULT NULL,"
                + "  `ciudad` VARCHAR(45) DEFAULT NULL,"
                + "  `tipo_cliente` VARCHAR(45) DEFAULT NULL,"
                + "  `estado` VARCHAR(45) DEFAULT NULL,"
                + "  PRIMARY KEY (`cedula`),"
                + "  KEY `cliente_vehiculo_idx` (`idvehiculo`),"
                + "  CONSTRAINT `cliente_vehiculo` FOREIGN KEY (`idvehiculo`) "
                + "     REFERENCES `vehiculos` (`idvehiculo`) ON DELETE NO ACTION ON UPDATE NO ACTION"
                + ") ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;";
        
         try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql))
                {
                      ps.executeUpdate();
                }
                   
    }
    
    public void guardar(Cliente cliente) throws SQLException {
        String sql = """
            INSERT INTO clientes (cedula, nombre, telefono, correo, idvehiculo, ciudad, tipo_cliente, estado)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, cliente.getCedula());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getCorreo());
            ps.setInt(5, cliente.getVehiculo().getIdvehiculo());
            ps.setString(6, cliente.getCiudad());
            ps.setString(7, cliente.getTipoCliente());
            ps.setString(8, cliente.getEstado());

            ps.executeUpdate();
        }
    }

    public List<Cliente> listar() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();

        String sql = """
            SELECT clientes.cedula, clientes.nombre, clientes.telefono,
                   clientes.correo, clientes.idvehiculo, clientes.ciudad,
                   clientes.tipo_cliente, clientes.estado, vehiculos.placa
            FROM clientes
            JOIN vehiculos ON clientes.idvehiculo = vehiculos.idvehiculo
            ORDER BY clientes.cedula
            """;

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getString("cedula"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("correo"),
                        new Vehiculo(rs.getInt("idvehiculo"), rs.getString("placa")),
                    rs.getString("ciudad"),
                    rs.getString("tipo_cliente"),
                    rs.getString("estado")
                );

                clientes.add(cliente);
            }
        }

        return clientes;
    }
    public List<Cliente> buscar(String Nombre, String Cedula, String Ciudad) throws SQLException {
        List<Cliente> clientes = new ArrayList<>();

        String sql = """
            SELECT clientes.cedula, clientes.nombre, clientes.telefono,
                   clientes.correo, clientes.idvehiculo, clientes.ciudad,
                   clientes.tipo_cliente, clientes.estado, vehiculos.placa
            FROM clientes
            JOIN vehiculos ON clientes.idvehiculo = vehiculos.idvehiculo
            WHERE clientes.nombre = ? OR clientes.cedula = ? OR clientes.ciudad = ?
            ORDER BY clientes.cedula
            """;

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, Nombre);
            ps.setString(2, Cedula);
            ps.setString(3, Ciudad);

            try (ResultSet rs = ps.executeQuery()) {
            
             
                while (rs.next()) {
                    Cliente cliente = new Cliente(
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("correo"),
                        new Vehiculo(rs.getInt("idvehiculo"), rs.getString("placa")),
                        rs.getString("ciudad"),
                        rs.getString("tipo_cliente"),
                        rs.getString("estado")
                    );

                    clientes.add(cliente);
                }
            }
        }

        return clientes;
    }
    public boolean actualizar(String cedulaOriginal, Cliente cliente)
            throws SQLException {

        String sql = """
            UPDATE clientes
            SET cedula = ?, nombre = ?, telefono = ?, correo = ?, ciudad = ?, tipo_cliente = ?, estado = ?
            WHERE cedula = ?
            """;

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, cliente.getCedula());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getCorreo());
            ps.setString(5, cliente.getCiudad());
            ps.setString(6, cliente.getTipoCliente());
            ps.setString(7, cliente.getEstado());
            ps.setString(8, cedulaOriginal);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(String cedula) throws SQLException {
        String sql = "DELETE FROM clientes WHERE cedula = ?";

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, cedula);
            return ps.executeUpdate() > 0;
        }
    }
}
