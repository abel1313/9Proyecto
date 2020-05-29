/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Usuario;

/**
 *
 * @author NoelCS
 */
public class UsuarioControl {
    public Usuario Acceso(String user, String pass){
        Usuario us=new Usuario();
        try {
            Connection conn=new ConexionControl().getConexion();
            PreparedStatement ps=conn.prepareStatement("select u.idusuario,u.nombre as nombreu,u.idrol,r.nombre as nombrer from usuario u join rol r on r.idrol=u.idrol where u.activo=1 and r.activo=1 and u.nombre=? and u.password=?");
            ps.setString(1,user);
            ps.setString(2,pass);
            ResultSet res=ps.executeQuery();
            if(res!=null&&res.next()){
                us.setIdusuario(res.getInt("idusuario"));
                us.setNombre(res.getString("nombreu"));
                us.getRol().setIdrol(res.getInt("idrol"));
                us.getRol().setNombre(res.getString("nombrer"));
                ps=conn.prepareStatement("select idmenu from permiso where idrol=?");
                ps.setInt(1,us.getRol().getIdrol());
                res=ps.executeQuery();
                while(res!=null&&res.next()){
                    us.getRol().getPermisos().add(res.getInt("idmenu"));
                }              
            }
            res.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return us;
    }
}
