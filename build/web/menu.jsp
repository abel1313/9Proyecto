
<%@page import="modelo.Usuario"%>
<% if (session.getAttribute("US") != null) {%>

<script>
    $(document).ready(function () {
        $("#ventas").click(function () {
            $(".navbar-collapse").removeClass("show");
            $("#main").load("ventas/venta.jsp");
        });
        $("#productos").click(function () {
            $("#main").load("productos.jsp");
        });
        $("#roles").click(function () {
            $(".navbar-collapse").removeClass("show");
            $("#main").load("admin/roles.jsp");
        });
        $("#salir").click(function () {
            $.ajax({
                type: 'post',
                url: 'UsuarioServlet',
                data: {accion: "Cerrar_Sesion"},
                success: function () {
                    location.reload();
                }
            })
        });
    });
</script>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand" href="#"><%= ((Usuario) session.getAttribute("US")).getNombre().toUpperCase() %></a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarColor01">
        <ul class="navbar-nav mr-auto">
            <% if (((Usuario) session.getAttribute("US")).getRol().getPermisos().contains(1)) {  %>
            <button class="btn btn-primary" type="button" id="ventas" name="ventas">Ventas</button>
            <% }  %>
            <% if (((Usuario) session.getAttribute("US")).getRol().getPermisos().contains(2)) {  %>
            <button type="button" class="btn btn-primary"  name="productos" id="productos" >Productos</button> 
            <% }  %>
            <% if (((Usuario) session.getAttribute("US")).getRol().getPermisos().contains(3)) {  %>
            <button type="button" class="btn btn-primary"  name="reportes" id="reportes" >Reportes</button> 
            <% }  %>
            <% if (((Usuario) session.getAttribute("US")).getRol().getPermisos().contains(4)) {  %>
            <button type="button" class="btn btn-primary"  name="roles" id="roles" >Roles</button> 
            <% }  %>
            <% if (((Usuario) session.getAttribute("US")).getRol().getPermisos().contains(5)) {  %>
            <button type="button" class="btn btn-primary"  name="usuarios" id="usuarios" >Usuarios</button> 
            <% }  %> 
            <button class="btn btn-info" type="button" id="salir" name="salir">Salir</button>
        </ul>
    </div>
</nav>
<% }%>
