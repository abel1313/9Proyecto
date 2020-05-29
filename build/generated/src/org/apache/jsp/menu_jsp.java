package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import modelo.Usuario;

public final class menu_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write('\n');
      out.write('\n');
 if (session.getAttribute("US") != null) {
      out.write("\n");
      out.write("\n");
      out.write("<script>\n");
      out.write("    $(document).ready(function () {\n");
      out.write("        $(\"#ventas\").click(function () {\n");
      out.write("            $(\".navbar-collapse\").removeClass(\"show\");\n");
      out.write("            $(\"#main\").load(\"ventas/venta.jsp\");\n");
      out.write("        });\n");
      out.write("        $(\"#productos\").click(function () {\n");
      out.write("            $(\"#main\").load(\"productos.jsp\");\n");
      out.write("        });\n");
      out.write("        $(\"#roles\").click(function () {\n");
      out.write("            $(\".navbar-collapse\").removeClass(\"show\");\n");
      out.write("            $(\"#main\").load(\"admin/roles.jsp\");\n");
      out.write("        });\n");
      out.write("        $(\"#salir\").click(function () {\n");
      out.write("            $.ajax({\n");
      out.write("                type: 'post',\n");
      out.write("                url: 'UsuarioServlet',\n");
      out.write("                data: {accion: \"Cerrar_Sesion\"},\n");
      out.write("                success: function () {\n");
      out.write("                    location.reload();\n");
      out.write("                }\n");
      out.write("            })\n");
      out.write("        });\n");
      out.write("    });\n");
      out.write("</script>\n");
      out.write("\n");
      out.write("<nav class=\"navbar navbar-expand-lg navbar-dark bg-primary\">\n");
      out.write("    <a class=\"navbar-brand\" href=\"#\">");
      out.print( ((Usuario) session.getAttribute("US")).getNombre().toUpperCase() );
      out.write("</a>\n");
      out.write("\n");
      out.write("    <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarColor01\" aria-controls=\"navbarColor01\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n");
      out.write("        <span class=\"navbar-toggler-icon\"></span>\n");
      out.write("    </button>\n");
      out.write("\n");
      out.write("    <div class=\"collapse navbar-collapse\" id=\"navbarColor01\">\n");
      out.write("        <ul class=\"navbar-nav mr-auto\">\n");
      out.write("            ");
 if (((Usuario) session.getAttribute("US")).getRol().getPermisos().contains(1)) {  
      out.write("\n");
      out.write("            <button class=\"btn btn-primary\" type=\"button\" id=\"ventas\" name=\"ventas\">Ventas</button>\n");
      out.write("            ");
 }  
      out.write("\n");
      out.write("            ");
 if (((Usuario) session.getAttribute("US")).getRol().getPermisos().contains(2)) {  
      out.write("\n");
      out.write("            <button type=\"button\" class=\"btn btn-primary\"  name=\"productos\" id=\"productos\" >Productos</button> \n");
      out.write("            ");
 }  
      out.write("\n");
      out.write("            ");
 if (((Usuario) session.getAttribute("US")).getRol().getPermisos().contains(3)) {  
      out.write("\n");
      out.write("            <button type=\"button\" class=\"btn btn-primary\"  name=\"reportes\" id=\"reportes\" >Reportes</button> \n");
      out.write("            ");
 }  
      out.write("\n");
      out.write("            ");
 if (((Usuario) session.getAttribute("US")).getRol().getPermisos().contains(4)) {  
      out.write("\n");
      out.write("            <button type=\"button\" class=\"btn btn-primary\"  name=\"roles\" id=\"roles\" >Roles</button> \n");
      out.write("            ");
 }  
      out.write("\n");
      out.write("            ");
 if (((Usuario) session.getAttribute("US")).getRol().getPermisos().contains(5)) {  
      out.write("\n");
      out.write("            <button type=\"button\" class=\"btn btn-primary\"  name=\"usuarios\" id=\"usuarios\" >Usuarios</button> \n");
      out.write("            ");
 }  
      out.write(" \n");
      out.write("            <button class=\"btn btn-info\" type=\"button\" id=\"salir\" name=\"salir\">Salir</button>\n");
      out.write("        </ul>\n");
      out.write("    </div>\n");
      out.write("</nav>\n");
 }
      out.write('\n');
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
