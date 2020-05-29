
<%@page import="modelo.Usuario"%>
<% if (session.getAttribute("US") != null && ((Usuario) session.getAttribute("US")).getRol().getPermisos().contains(4)) {  %>


<script>
    $(document).ready(function () {
        $("#filtrar").click(function () {
            $.ajax({
                type: 'post',
                url: 'AdminServlet',
                data: {accion: "Consulta_Roles", filtro: $("#caja").val()},
                success: function () {
                    $("#lista").load("admin/listaroles.jsp");
                }
            })
        });
         $("#borrar").click(function () {
            $.ajax({
                type: 'post',
                url: 'AdminServlet',
                data: {accion: "Consulta_Roles"},
                success: function () {
                    $("#caja").val("");
                    $("#lista").load("admin/listaroles.jsp");
                }
            })
        });
        $("#nuevorol").click(function () {
            $.ajax({
                type: 'post',
                url: 'admin/nuevorol.jsp',
                success: function (txtHtml) {
                    $("#filtros").empty();
                    $("#lista").empty();
                    $("#nuevo").html(txtHtml);
                }
            })
        });
        $.ajax({
            type: 'post',
            url: 'AdminServlet',
            data: {accion: "Consulta_Roles"},
            success: function () {
                $("#lista").load("admin/listaroles.jsp");
            }
        });
    });
</script>
<div class="page-header">
    <h1 id="navbars">Roles</h1>
</div>
<div class="container" style="padding: 10px;" id="filtros">  
    <label for="caja" class="col-form-label">Nombre:</label>
    <input type="text" name="caja" id="caja" value="">
    <button type="button" class="btn btn-success"  name="filtrar" id="filtrar" >Filtrar</button> 
    <button type="button" class="btn btn-info"  name="borrar" id="borrar" >Borrar Filtros</button> 
     <button type="button" class="btn btn-primary"  name="nuevorol" id="nuevorol" >Nuevo Rol</button> 
</div>
<div class="container" id="lista" style="padding: 0px;">  
</div>
<div class="container" id="nuevo" style="padding: 0px;">  
</div>

    
<% } %>