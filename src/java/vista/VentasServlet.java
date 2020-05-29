/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import control.VentaControl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Detalle;
import modelo.Usuario;
import modelo.Venta;

/**
 *
 * @author NoelCS
 */
public class VentasServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("US") != null&&((Usuario)request.getSession().getAttribute("US")).getRol().getPermisos().contains(1)) {
            if (request.getParameter("accion") != null) {
                if (request.getParameter("accion").equals("Busca_Producto") && request.getParameter("codigo") != null) {
                    String codigo = request.getParameter("codigo");
                    Detalle det = new VentaControl().ConsultaProducto(codigo);
                    if (det.getProducto().getIdproducto() != 0) {
                        Venta venta = (Venta) request.getSession().getAttribute("venta");
                        if (venta.getIdventa() == 0) {
                            new VentaControl().AgregaProducto(venta, det);
                        }
                    }
                }
                if (request.getParameter("accion").equals("Limpiar_Venta")) {
                    request.getSession().setAttribute("venta", new Venta());
                }
                if (request.getParameter("accion").equals("Realizar_Venta")) {
                    Venta venta = (Venta) request.getSession().getAttribute("venta");
                    if (new VentaControl().RealizarVenta(venta)) {
                        request.getSession().setAttribute("Mensaje", "La venta se realizo exitosamente");
                    }
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
