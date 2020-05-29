/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Detalle;
import modelo.Producto;
import modelo.Venta;

/**
 *
 * @author usuario
 */
public class VentaControl {
    public Detalle ConsultaProducto(String codigo){
        Detalle det=new Detalle();
        try {
            Connection conn=new ConexionControl().getConexion();
            PreparedStatement ps=conn.prepareStatement("select idproducto,descripcion,codigo,precio,existencia from producto where codigo=?");
            ps.setString(1, codigo);
            ResultSet res=ps.executeQuery();
            while(res!=null&&res.next()){
                Producto pro=new Producto();
                pro.setIdproducto(res.getInt("idproducto"));
                pro.setDescripcion(res.getString("descripcion"));
                pro.setCodigo(res.getString("codigo"));
                pro.setPrecio(res.getInt("precio"));
                pro.setExistencia(res.getInt("existencia"));
                det.setProducto(pro);
                det.setPrecio(pro.getPrecio());
                det.setSubtotal(pro.getPrecio());
                det.setCantidad(1);
            }
            res.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(VentaControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return det;
    }
    public Venta AgregaProducto(Venta venta,Detalle nuevo){        
        int existe=0;
        for(int i=0;i<venta.getDetalle().size();i++){
            Detalle det=venta.getDetalle().get(i);
            if(nuevo.getProducto().getIdproducto()==det.getProducto().getIdproducto()){
                det.setCantidad(det.getCantidad()+1);
                det.setSubtotal(det.getSubtotal()+det.getPrecio());
                i=venta.getDetalle().size();
                existe=1;
            }
        }
        if(existe==0){
            venta.getDetalle().add(nuevo);
        }
        venta.setTotal(venta.getTotal()+nuevo.getProducto().getPrecio());
        return venta;
    }
    public boolean RealizarVenta(Venta venta){
        boolean r=true;
        try {
            Connection conn=new ConexionControl().getConexion();
            PreparedStatement ps=conn.prepareStatement("insert into venta(total,fecha) values(?,now())");
            ps.setFloat(1,venta.getTotal());
            ps.executeUpdate();
            ps=conn.prepareStatement("select last_insert_id() as ultimo");
            ResultSet res=ps.executeQuery();
            if(res!=null&&res.next()){
                venta.setIdventa(res.getInt("ultimo"));
            }
            res.close();
            for(int i=0;i<venta.getDetalle().size();i++){
                Detalle det=venta.getDetalle().get(i);
                ps=conn.prepareStatement("insert into detalle (idventa,idproducto,precio,cantidad,subtotal) values(?,?,?,?,?)");
                ps.setInt(1,venta.getIdventa());
                ps.setInt(2,det.getProducto().getIdproducto());
                ps.setFloat(3, det.getPrecio());
                ps.setFloat(4,det.getCantidad());
                ps.setFloat(5,det.getSubtotal());
                ps.executeUpdate();
            }
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            r=false;
            Logger.getLogger(VentaControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    public String ComboProductos(String sel){
        String cad="";
        try {
            Connection conn=new ConexionControl().getConexion();
            PreparedStatement ps=conn.prepareStatement("select idproducto,descripcion from producto");
            ResultSet res=ps.executeQuery();
            while(res!=null&&res.next()){
                cad=cad+"<option value='"+res.getInt("idproducto")+"'>"+res.getString("descripcion")+"</option>";
            }
            res.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(VentaControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cad;
    }
}
