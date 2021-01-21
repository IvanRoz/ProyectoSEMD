<%-- 
    Document   : verPerfil
    Created on : 21 ene 2021, 10:36:50
    Author     : Ivan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    </head>
    <body>
        <body background="Imagenes/userBack.jpeg">
        
        <!-- Image and text -->
        <nav class="navbar navbar-dark bg-dark">
            <img src="Imagenes/Regalo.png" width="30" height="30" class="d-inline-block align-top" alt="" loading="lazy">
            <a class="navbar-brand" href="#"> Datos del Usuario </a>
        </nav>   
        
        <div class="container">
            <div class="card-body">   
            <div class="card bg-light border-success">
                
                     <h5 class="p-3 mb-2 bg-primary text-white">Datos del Usuario</h5> 
        

                <div class="card-body">
                    <ul class="list-group border-success">
                        <li class="list-group-item"> Alias de Usuario : <c:out value="${User.entidad.alias}"/>  </li>
                        <li class="list-group-item"> Nombre de Usuario : <c:out value="${User.entidad.nombre}"/> </li>
                        <li class="list-group-item"> Correo de Usuario : <c:out value="${User.entidad.correo}"/> </li>
                    </ul>
                </div>
                <div class=" card-body">
                    <a href="IntercambioServlet?accion=listaIntercambios" class="btn btn-danger"> Regresar a la lista de intercambios </a>
                    <a href="UsuarioServlet?accion=actualizarUsuario" class="btn btn-primary"> Modificar Datos </a>                 
                </div>
                
               
            </div> 
            </div>    
           
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
    </body>
</html>
