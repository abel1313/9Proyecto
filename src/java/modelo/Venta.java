/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class Venta {
    int idventa=0;
    float total=0;
    String fecha="";
    ArrayList<Detalle> detalle=new ArrayList();

    public int getIdventa() {
        return idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Detalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(ArrayList<Detalle> detalle) {
        this.detalle = detalle;
    }
        
}
