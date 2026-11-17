/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

/**
 *
 * @author Steven
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL =
            "jdbc:mysql://localhost:3306/db_empresa";

    private static final String USUARIO = "yuki";
    private static final String CLAVE = "dafvbbfd";

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }
}
