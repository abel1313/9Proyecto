
<%@page import="modelo.Usuario"%>
<% if(session.getAttribute("US") != null&&((Usuario)session.getAttribute("US")).getRol().getPermisos().contains(1)) {  
        Usuario us=(Usuario)session.getAttribute("US");
%>

<%@page import="modelo.Detalle"%>
<%@page import="modelo.Venta"%>
<%
    Venta venta = (Venta) session.getAttribute("venta");
%>
<script>
    $(document).ready(function () {
        $("#limpiar").click(function () {
            $.ajax({
                type: 'post',
                url: 'VentasServlet',
                data: {accion: "Limpiar_Venta"},
                success: function () {
                    $("#detalle").load("ventas/detalle.jsp");
                }
            })
        });
        $("#vender").click(function () {
            $("#vender").prop( "disabled", true );
            $.ajax({
                type: 'post',
                url: 'VentasServlet',
                data: {accion: "Realizar_Venta"},
                success: function () {
                    $("#detalle").load("ventas/detalle.jsp");
                }
            })
        });
    });
</script>


<table class='table table-hover'><tr class='table-secondary'><th>Descripcion<th>Precio<th>Cantidad<th>Subtotal
            <%
                for (int i = 0; i < venta.getDetalle().size(); i++) {
                    Detalle det = venta.getDetalle().get(i);
                    out.println(i%2==0?"<tr class='table-info'>":"<tr class='table-light'>");
                    out.println("<td>" + det.getProducto().getDescripcion());
                    out.println("<td>$" + det.getPrecio());
                    out.println("<td>" + det.getCantidad());
                    out.println("<td>$" + det.getSubtotal());
        
                }
                out.println("<tr class='table-success'>");
                    out.println("<td><td><th>Total: <th>$" + venta.getTotal());
            %>
</table>
 
 <%
        if (session.getAttribute("Mensaje") != null) {
    %>
        </div>
    <div class="alert alert-dismissible alert-primary">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <strong> <%=session.getAttribute("Mensaje").toString()%></strong>
    </div>
    <%
            session.removeAttribute("Mensaje");
        }
    %>

    
<% if (venta.getDetalle().size() > 0 && venta.getIdventa() == 0) {%>
    <button type="button" class="btn btn-success"  name="vender" id="vender" >Realizar Venta</button>
<% } %>
<% if (venta.getDetalle().size() > 0) { %>
    <button type="button" class="btn btn-warning"  name="limpiar" id="limpiar" >Nueva Venta</button>
<% }%>
<% if (venta.getIdventa() != 0) { %>
    <button type="button" class="btn btn-primary"  name="imprimir" id="imprimir" >Imprimir Ticket</button>
<% }%>

      
 

    
<% } %>