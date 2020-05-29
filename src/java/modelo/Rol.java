/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author NoelCS
 */
public class Rol {
    int idrol=0;
    String nombre="";
    int activo=0;
    ArrayList<Integer> permisos=new ArrayList();

    public int getIdrol() {
        return idrol;
    }

    public void setIdrol(int idrol) {
        this.idrol = idrol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public ArrayList<Integer> getPermisos() {
        return permisos;
    }

    public void setPermisos(ArrayList<Integer> permisos) {
        this.permisos = permisos;
    }
    
    
}
