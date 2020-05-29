/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class ConexionControl {
    public Connection getConexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return java.sql.DriverManager.getConnection("jdbc:mysql://127.0.0.1/RolesPermisos", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
