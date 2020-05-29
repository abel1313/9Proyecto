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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Menu;
import modelo.Rol;

/**
 *
 * @author NoelCS
 */
public class AdminControl {

    public ArrayList<Rol> ConsultaRoles(String filtro) {
        ArrayList<Rol> lista = new ArrayList();
        try {
            Connection conn = new ConexionControl().getConexion();
            PreparedStatement ps = null;
            if (filtro.equals("")) {
                ps = conn.prepareStatement("select r.idrol,r.nombre,r.activo,p.idmenu from rol r left join permiso p on p.idrol=r.idrol order by r. idrol");
            } else {
                ps = conn.prepareStatement("select r.idrol,r.nombre,r.activo,p.idmenu from rol r left join permiso p on p.idrol=r.idrol where r.nombre like ? order by r. idrol");
                ps.setString(1, "%" + filtro + "%");
            }
            ResultSet res = ps.executeQuery();
            Rol rol=new Rol();
            while (res != null && res.next()) {
                if (res.isFirst()) {
                    rol.setIdrol(res.getInt("idrol"));
                    rol.setNombre(res.getString("nombre"));
                    rol.setActivo(res.getInt("activo"));
                } else if (rol.getIdrol() != res.getInt("idrol")) {
                    lista.add(rol);
                    rol = new Rol();
                    rol.setIdrol(res.getInt("idrol"));
                    rol.setNombre(res.getString("nombre"));
                    rol.setActivo(res.getInt("activo"));
                }
                rol.getPermisos().add(res.getInt("idmenu"));
                if(res.isLast())
                    lista.add(rol);
            }
            res.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public boolean ExisteRol(String nombre) {
        boolean r = true;
        try {
            Connection conn = new ConexionControl().getConexion();
            PreparedStatement ps = conn.prepareStatement("select idrol from rol where nombre = ?");
            ps.setString(1, nombre);
            ResultSet res = ps.executeQuery();
            if (res != null && res.next()) {
                r = true;
            } else {
                r = false;
            }
            res.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    public boolean ExisteRol(String nombre,int idrol) {
        boolean r = true;
        try {
            Connection conn = new ConexionControl().getConexion();
            PreparedStatement ps = conn.prepareStatement("select idrol from rol where nombre = ? and idrol<>?");
            ps.setString(1, nombre);
            ps.setInt(2, idrol);
            ResultSet res = ps.executeQuery();
            if (res != null && res.next()) {
                r = true;
            } else {
                r = false;
            }
            res.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public ArrayList<Menu> CargaPermisos() {
        ArrayList<Menu> lista = new ArrayList();
        try {
            Connection conn = new ConexionControl().getConexion();
            PreparedStatement ps = conn.prepareStatement("select idmenu,nombre,activo from menu");
            ResultSet res = ps.executeQuery();
            while (res != null && res.next()) {
                Menu menu = new Menu();
                menu.setIdmenu(res.getInt("idmenu"));
                menu.setNombre(res.getString("nombre"));
                menu.setActivo(res.getInt("activo"));
                lista.add(menu);
            }
            res.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public boolean CreaRol(Rol rol) {
        boolean r=true;
        try {
            Connection conn = new ConexionControl().getConexion();
            PreparedStatement ps = conn.prepareStatement("insert into rol(nombre,activo) values(?,?)");
            ps.setString(1, rol.getNombre());
            ps.setInt(2, rol.getActivo());
            ps.executeUpdate();
            ps = conn.prepareStatement("select last_insert_id() as ultimo");
            ResultSet res = ps.executeQuery();
            if (res != null && res.next()) {
                rol.setIdrol(res.getInt("ultimo"));
            }
            res.close();
            for (int i = 0; i < rol.getPermisos().size(); i++) {
                ps = conn.prepareStatement("insert into permiso (idrol,idmenu) values(?,?)");
                ps.setInt(1, rol.getIdrol());
                ps.setInt(2, rol.getPermisos().get(i));
                ps.executeUpdate();
            }
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            r=false;
            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    public boolean ActualizaRol(Rol rol) {
        boolean r=true;
        try {
            Connection conn = new ConexionControl().getConexion();
            PreparedStatement ps = conn.prepareStatement("update rol set nombre=?,activo=? where idrol=?");
            ps.setString(1, rol.getNombre());
            ps.setInt(2, rol.getActivo());
            ps.setInt(3, rol.getIdrol());
            ps.executeUpdate();
            ps = conn.prepareStatement("delete from permiso where idrol=?");
            ps.setInt(1, rol.getIdrol());
            ps.executeUpdate();
            for (int i = 0; i < rol.getPermisos().size(); i++) {
                ps = conn.prepareStatement("insert into permiso (idrol,idmenu) values(?,?)");
                ps.setInt(1, rol.getIdrol());
                ps.setInt(2, rol.getPermisos().get(i));
                ps.executeUpdate();
            }
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            r=false;
            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
}
