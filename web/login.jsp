<% if(session.getAttribute("US")==null){  %>
<script>
    $(document).ready(function () {
        $("#ingresar").click(function () {
            $.ajax({
                type: 'post',
                url: 'UsuarioServlet',
                data: {accion: "Acceso", usuario: $("#Usuario").val(), password: $("#Password").val()},
                success: function () {
                   location.reload();
                }
            })
        });
        $("#Usuario").keypress(function () {
            $("#mensaje").empty();
        });
        $("#Password").keypress(function () {
            $("#mensaje").empty();
        });
        $("#Usuario").focus();
    });
</script>
<form>
    <fieldset>
        <legend>Bienvenido al Sistema Integral de Control de tu Tiendita</legend>
        <div class="form-group">
            <label for="Usuario">Usuario</label>
            <input type="text" class="form-control" id="Usuario" name="Usuario"  placeholder="Escribe tu Usuario">
        </div>
        <div class="form-group">
            <label for="Password">Password</label>
            <input type="password" class="form-control" id="Password" name="Password" placeholder="Password">
        </div>
        <button type="button" id="ingresar" name="ingresar" class="btn btn-primary">Ingresar</button>
    </fieldset>
</form>
 <div class="container" id="mensaje" style="padding: 10px;">  
        
  <%
        if (session.getAttribute("Mensaje") != null) {
    %>
        <%=session.getAttribute("Mensaje").toString()%>
    <%
            session.removeAttribute("Mensaje");
        }
    %> 
</div>
    
<% }  %>