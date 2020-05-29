<%-- 
    Document   : index
    Created on : 17/05/2020, 11:29:58 PM
    Author     : NoelCS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sistema de Ventas</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link type="text/css" href="css/bootstrap.css" rel="Stylesheet" />  
        <script type="text/javascript" src="js/jquery-3.5.1.js" ></script>
        <script type="text/javascript" src="js/jquery.validate.js" ></script>
        <script type="text/javascript" src="js/bootstrap.js"></script>
        <script>
            $(document).ready(function () {
                jQuery.extend(jQuery.validator.messages, {
                    required: "Este campo es obligatorio.",
                    email: "Por favor, escribe una dirección de correo válida",
                    url: "Por favor, escribe una URL válida.",
                    date: "Por favor, escribe una fecha válida.",
                    dateISO: "Por favor, escribe una fecha (ISO) válida.",
                    number: "Por favor, escribe un número entero válido.",
                    digits: "Por favor, escribe sólo dígitos.",
                    creditcard: "Por favor, escribe un número de tarjeta válido.",
                    equalTo: "Por favor, escribe el mismo valor de nuevo.",
                    accept: "Por favor, escribe un valor con una extensión aceptada.",
                    maxlength: jQuery.validator.format("Por favor, no escribas más de {0} caracteres."),
                    minlength: jQuery.validator.format("Por favor, no escribas menos de {0} caracteres."),
                    rangelength: jQuery.validator.format("Por favor, escribe un valor entre {0} y {1} caracteres."),
                    range: jQuery.validator.format("Por favor, escribe un valor entre {0} y {1}."),
                    max: jQuery.validator.format("Por favor, escribe un valor menor o igual a {0}."),
                    min: jQuery.validator.format("Por favor, escribe un valor mayor o igual a {0}."),
                    extension: "Por favor, selecciona un archivo con una extensión jpg.",
                });
                $("#top").load("menu.jsp");
                $("#main").load("ventas/venta.jsp");
                $("#login").load("login.jsp");
            });
        </script>
        <style type="text/css">
            .error {
              color: red;
             }
         </style>
    </head>

    <body>
        <div class="container" id="top" style="padding: 10px;">    
        </div>
        <div class="container" id="main" style="padding: 10px;">  
        </div>
        <div class="container" id="login" style="padding: 10px;">  
        </div>

    </body>
</html>
