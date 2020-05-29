
<%@page import="modelo.Rol"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Usuario"%>
<% if (session.getAttribute("US") != null && ((Usuario) session.getAttribute("US")).getRol().getPermisos().contains(4)) {  %>

<%
    ArrayList<Rol> lista = session.getAttribute("listaroles") != null ? (ArrayList<Rol>) session.getAttribute("listaroles") : new ArrayList();
%>

<script>
    $(document).ready(function () {
        $(".editarrol").click(function () {
            $.ajax({
                type: 'post',
                url: 'admin/modificarol.jsp',
                data: {accion: "Modifica_Rol", indice: $(this).attr("indice")},
                success: function (txtHtml) {
                    $("#main").html(txtHtml);
                }
            })
        });
    });
</script>


<table class='table table-hover'><tr class='table-secondary'><th>Nombre<th>Activo<th>Acciones
            <%
                for (int i = 0; i < lista.size(); i++) {
                    Rol rol = lista.get(i);
                    out.println(i % 2 == 0 ? "<tr class='table-info'>" : "<tr class='table-warning'>");
                    out.println("<td>" + rol.getNombre());
                    out.println("<td>" + (rol.getActivo() == 1 ? "Si" : "No"));
            %>
        <td><button type="button" class="btn btn-warning editarrol" indice="<%= i%>">Editar</button>  
            <%

                }
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

<% }%>