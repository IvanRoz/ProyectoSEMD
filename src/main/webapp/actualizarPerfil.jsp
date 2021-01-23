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
            <a class="navbar-brand" href="#"> Modificar Datos </a>
        </nav>   
        
        <div class="container">
            <div class="card-body">   
            <div class="card bg-light border-success">
                
            <h5 class="p-3 mb-2 bg-primary text-white">Modifica tus datos</h5> 
        
            <form action="UsuarioServlet?accion=update" method="post" name="frmCategoriaForm" id="frmCategoriaForm">
                <input type="hidden" name="idUsuario" id="idUsuario" value="<c:out value="${User.entidad.idUsuario}"/>"
                                       class="form-control" />
                <div class="card-body">
                    <ul class="list-group border-success">
                        
                        <li class="list-group-item"> 
                            <div class="col-sm-3">
                                Alias
                                <input type="text" name="alias" id="alias" 
                                       maxlength="50" required="required" placeholder="Alias"
                                       value="<c:out value="${User.entidad.alias}"/>"
                                       class="form-control" />
                            </div>
                        </li>
                        
                        <li class="list-group-item"> 
                             <div class="col-sm-3">
                                Nombre 
                                <input type="text" name="nombre" id="nombre" 
                                       maxlength="50" required="required" placeholder="nombre"
                                       value="<c:out value="${User.entidad.nombre}"/>"
                                       class="form-control"  /> 
                            </div>
                        </li>
                        
                        <li class="list-group-item"> 
                             <div class="col-sm-3">
                                 Correo
                                <input type="text" name="email" id="email" 
                                       maxlength="50" required="required" placeholder="Email"
                                       value="<c:out value="${User.entidad.correo}"/>"
                                       class="form-control" />
                            </div>
                        </li>
                        
                        <li class="list-group-item"> 
                            <div class="col-sm-3">
                                Clave
                                <input type="text" name="claveUsuario" id="claveUsuario" 
                                       maxlength="50" required="required" placeholder="Clave Usuario"
                                       value="<c:out value="${User.entidad.clave}"/>"
                                       class="form-control" />
                            </div>
                        </li>
                        
                    </ul>
                
                    
                </div>
                <div class=" card-body">
                    <input type="submit" class="btn btn-primary" value="Actualizar">
                    <a href="UsuarioServlet?accion=verUsuario" class="btn btn-danger"> Regresar </a> 
                </div>
                
            </form>   
            </div> 
            </div>    
           
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
    </body>
</html>