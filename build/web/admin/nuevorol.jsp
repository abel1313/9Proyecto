
<%@page import="control.AdminControl"%>
<%@page import="modelo.Menu"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Usuario"%>
<% if (session.getAttribute("US") != null && ((Usuario) session.getAttribute("US")).getRol().getPermisos().contains(4)) {
        ArrayList<Menu> menus = new AdminControl().CargaPermisos();
%>

<script>
    $(document).ready(function () {
        $("#frmrol").validate({
            rules: {
                nombrerol: {required: true, maxlength: 50, remote: { url:"AdminServlet",type:"post",data:{accion: "Valida_Rol"} } },
            },
            messages: {
                nombrerol: {
                    remote: "El Nombre del rol ya Existe.",
                },
            },
            submitHandler: function (form) {
                $.ajax({
                    type: 'post',
                    url: 'AdminServlet',             
                    data: $(form).serialize(),
                    cache: false,             
                    processData: false,     
                    success: function() {
                        $("#main").load("admin/roles.jsp");
                    }
                });
            }
        });
    });
</script>
<form id="frmrol" name="frmrol">
    <fieldset>
        <legend>Nuevo Rol</legend>
        <div class="form-group">
            <label for="nombre">Nombre del Rol:</label>
            <input type="text" class="form-control" id="nombrerol" name="nombrerol"  placeholder="Escribe el Nombre del Rol">
        </div>
        <div class="form-group">
            <label for="activo">Activo:</label>
            <select class="form-control" id="activo" name="activo">
                <option value=1>Activo</option>
                <option value=0>Inactivo</option>
            </select>
        </div>
        <legend>Permisos</legend>
        <%
            for (int i = 0; i < menus.size(); i++) {
                Menu menu = menus.get(i);
        %>
        <div class="form-check" >
            <label class="form-check-label">
                <input class="form-check-input" type="checkbox" value="<%= menu.getIdmenu()%>"  id="menu<%= menu.getIdmenu()%>" name="menu<%= menu.getIdmenu()%>">
                <%= menu.getNombre()%>
            </label>
        </div>
        <%
            }
        %>
        <fieldset class="form-group"> </fieldset>
        <input type="hidden" class="form-control" id="accion" name="accion"  value="Crea_Rol">
        <button type="submit" class="btn btn-primary">Crear Rol</button>
        <button type="button" id="cancela" name="cancela" class="btn btn-danger">Cancelar</button>
    </fieldset>
</form>

    
<% } %>