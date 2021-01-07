<%-- 
    Document   : LoginExitoso
    Created on : 6 ene 2021, 11:51:56
    Author     : Ivan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" >
    </head>
    <body>
        <body background="Imagenes/intercambio.jpg">
        <nav class="navbar navbar-dark bg-dark">
            <img src="Imagenes/Regalo.png" width="30" height="30" class="d-inline-block align-top" alt="" loading="lazy">
            <a class="navbar-brand" href="#"> Bienvenido <c:out value="${Usuario}"/> </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Crear intercambio<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Ingresar a intercambio<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Ver mi perfil<span class="sr-only">(current)</span></a>
                    </li>
                </ul>
            </div>
        </nav>
            
        <table class="table table-striped table-dark text-light">
                <thead>
                    <tr>
                        <th>Id De Intercambio</th>
                        <th>Nombre de Intercambio</th>
                        <th>Tema</th>
                        <th>Monto Maximo</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach 
                        var="Intercambios" 
                        items="${ListaDeIntercambios}">
                        <tr>
                            <td>
                                <c:out value="${Intercambios.entidad.idIntercambio}"/> 
                            </td>
                            
                            <td>
                                <c:out value="${Intercambios.entidad.nombreIntercambio}"/> 
                            </td>

                            <td>
                                <c:out value="${Intercambios.entidad.tema}"/> 
                            </td>
                            
                            <td>
                                $<c:out value="${Intercambios.entidad.montoMax}"/> 
                            </td>

                            <td>
                                <a class="btn btn-danger btn-xs"
                                   href="#">
                                    Participar
                                </a>
                            </td>

                            <td>
                                <a class="btn btn-success btn-xs"
                                   href="#">
                                    Ver Intercambio
                                </a>
                            </td>
                            
                    </c:forEach>
                </tbody>
            </table>
        
         
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
    </body>
</html>
