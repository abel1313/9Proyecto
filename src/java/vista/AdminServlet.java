/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import control.AdminControl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Rol;
import modelo.Usuario;

/**
 *
 * @author NoelCS
 */
public class AdminServlet extends HttpServlet {

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
        if (request.getSession().getAttribute("US") != null&&((Usuario)request.getSession().getAttribute("US")).getRol().getPermisos().contains(4)) {
            if (request.getParameter("accion") != null) {
                if (request.getParameter("accion").equals("Consulta_Roles") && request.getParameter("filtro") == null) {
                    ArrayList lista=new AdminControl().ConsultaRoles("");
                    request.getSession().setAttribute("listaroles",lista);
                }
                if (request.getParameter("accion").equals("Consulta_Roles") && request.getParameter("filtro") != null) {
                    String filtro=request.getParameter("filtro");
                    ArrayList lista=new AdminControl().ConsultaRoles(filtro);
                    request.getSession().setAttribute("listaroles",lista);
                }   
                if (request.getParameter("accion").equals("Valida_Rol")) {
                    String nombre = request.getParameter("nombrerol");
                    if (new AdminControl().ExisteRol(nombre)){
                        response.setContentType("text/plain");
                        response.getWriter().write("false");
                    } else {
                        response.setContentType("text/plain");
                        response.getWriter().write("true");
                    }
                } 
                if (request.getParameter("accion").equals("Valida_RolMod")) {
                    String nombre = request.getParameter("nombrerol");
                    Rol rol =(Rol)request.getSession().getAttribute("RolMod");
                    if (new AdminControl().ExisteRol(nombre,rol.getIdrol())){
                        response.setContentType("text/plain");
                        response.getWriter().write("false");
                    } else {
                        response.setContentType("text/plain");
                        response.getWriter().write("true");
                    }
                }  
                if (request.getParameter("accion").equals("Crea_Rol")) {
                    Rol rol=new Rol();
                    rol.setNombre(request.getParameter("nombrerol"));
                    rol.setActivo(Integer.parseInt(request.getParameter("activo")));
                    Enumeration enumeration = request.getParameterNames();
                    while (enumeration.hasMoreElements()) {
                        String nombreparametro = (String) enumeration.nextElement();
                        if(nombreparametro.contains("menu"))rol.getPermisos().add(Integer.parseInt(request.getParameter(nombreparametro)));
                    }
                    if (new AdminControl().CreaRol(rol)) {
                        request.getSession().setAttribute("Mensaje", "El rol se creo exitosamente");
                    }
                }  
                if (request.getParameter("accion").equals("Actualiza_Rol")) {
                    Rol rol =(Rol)request.getSession().getAttribute("RolMod");
                    rol.setNombre(request.getParameter("nombrerol"));
                    rol.setActivo(Integer.parseInt(request.getParameter("activo")));
                    rol.setPermisos(new ArrayList());
                    Enumeration enumeration = request.getParameterNames();
                    while (enumeration.hasMoreElements()) {
                        String nombreparametro = (String) enumeration.nextElement();
                        System.out.println(nombreparametro);
                        if(nombreparametro.contains("menu"))rol.getPermisos().add(Integer.parseInt(request.getParameter(nombreparametro)));
                    }
                    if (new AdminControl().ActualizaRol(rol)) {
                        request.getSession().setAttribute("Mensaje", "El rol se actualizo exitosamente");
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
