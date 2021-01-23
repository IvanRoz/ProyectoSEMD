<%-- 
    Document   : BuscarInter
    Created on : 11 ene 2021, 18:36:58
    Author     : Ivan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" >
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    </head>
    <body>
        <body background="Imagenes/ver.jpg">
        <nav class="navbar navbar-dark bg-dark">
            <img src="Imagenes/Regalo.png" width="30" height="30" class="d-inline-block align-top" alt="" loading="lazy">
            <a class="navbar-brand" href="#">Buscar un Amigo</a>
           
        </nav>
        
        <form action="UsuarioServlet?accion=buscarAmigo" method="post" name="frmLogin" id="frmLogin">

            <div class="form-group">
                <div class="col-sm-6">
                    <label class="col-sm-4 col-form-lsbel badge badge-dark  text-light">Ingresa el Alias de tu amigo</label>
                </div>
                <div class="col-sm-3">
                    <input type="text" name="alias" id="alias" 
                           maxlength="50" required="required" placeholder="Alias"
                           class="form-control"  /> 
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-6">
                    <input type="submit" value="Buscar Amigo" class="btn btn-success"/>
                    <a class="btn btn-danger btn-xs" href="UsuarioServlet?accion=verAmigos"/>Regresar</a>
                </div>
            </div>    

        </form>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
        <script languaje="JavaScript">
            if(${alerta} == 1){
               swal("Error","No se encontro a ningun usuario con ese alias", "error");
           }
        </script>   
    </body>
</html>
