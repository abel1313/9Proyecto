
<%@page import="modelo.Usuario"%>
<% if(session.getAttribute("US") != null&&((Usuario)session.getAttribute("US")).getRol().getPermisos().contains(1)) {  
        Usuario us=(Usuario)session.getAttribute("US");
%> 

<%@page import="control.VentaControl"%>
<%@page import="modelo.Detalle"%>
<%@page import="modelo.Venta"%>
<%
    if (session.getAttribute("venta") == null) {
        session.setAttribute("venta", new Venta());
    }
%>

<script>
    $(document).ready(function () {
        $("#caja").keyup(function () {
            if ($("#caja").val().length >= 3) {
                $.ajax({
                    type: 'post',
                    url: 'VentasServlet',
                    data: {accion: "Busca_Producto", codigo: $("#caja").val()},
                    success: function () {
                        $("#caja").val("");
                        $("#detalle").load("ventas/detalle.jsp");
                    }
                })
            }
        });

        $("#limpiar").click(function () {
            $.ajax({
                type: 'post',
                url: 'VentasServlet',
                data: {accion: "Limpiar_Venta"},
                success: function () {
                    $("#caja").val("");
                    $("#detalle").load("ventas/detalle.jsp");
                }
            })

        });
        $("#detalle").load("ventas/detalle.jsp");
        $("#caja").focus();
    });
</script>
<div class="page-header">
    <h1 id="navbars">Ventas</h1>
</div>
<div class="container" id="buscar" style="padding: 10px;">  
    <label for="caja" class="col-form-label">Código del producto:</label>
    <input type="text" name="caja" id="caja" value="">
</div>
<div class="container" id="detalle" style="padding: 0px;">  
</div>

    
<% } %>