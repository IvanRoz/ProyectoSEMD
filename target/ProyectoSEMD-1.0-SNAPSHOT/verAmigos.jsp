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
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    </head>
    <body>
        <body background="Imagenes/amigosBack.jpg">
            <nav class="navbar navbar-dark bg-dark">
                <img src="Imagenes/Regalo.png" width="30" height="30" class="d-inline-block align-top" alt="" loading="lazy">
                
                <c:choose>
                        <c:when test="${agregar==1}">
                             <a class="navbar-brand" href="#"> Lista de amigos </a> 
                             <ul class="navbar-nav">
                                    <li class="nav-item active">
                                        <a class="nav-link" href="IntercambioServlet?accion=listaIntercambios"> Regresar a la lista de intercambios </a>
                                    </li>
                                </ul>
                        </c:when>    
                        <c:otherwise>
                            <a class="navbar-brand" href="#"> Lista de amigos </a> 
                            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                                <span class="navbar-toggler-icon"></span>
                            </button>
                            <div class="collapse navbar-collapse" id="navbarNav">
                                <ul class="navbar-nav">
                                    <li class="nav-item active">
                                        <a class="nav-link" href="BuscarAmigo.jsp"> Buscar amigo </a>
                                    </li>
                                    <li class="nav-item active">
                                        <a class="nav-link" href="IntercambioServlet?accion=listaIntercambios"> Regresar a la lista de intercambios </a>
                                    </li>
                                </ul>
                            </div>
                        </c:otherwise>
                    </c:choose>
                
                
                
            </nav>
            <div class="container">      
                <div class="card-body">      
                    <table class="table table-striped table-dark text-light">
                        <thead>
                            <tr>
                                <th>Nombre de Amigo</th>
                            </tr>
                        </thead>
                        <tbody>
                           <c:forEach 
                                var="Amigos" 
                                items="${ListaAmigos}">
                                <tr>

                                    <td>
                                        <c:out value="${Amigos.entidad.nombreAmigo}"/> 
                                    </td>

                                    <td>
                                        <c:choose>
                                            <c:when test="${agregar==1}">
                                                <a class="btn btn-success btn-xs"
                                                   href="IntercambioServlet?accion=nuevoParticipante&id=<c:out value="${Amigos.entidad.idAmigo}"/>&idIntercambio=<c:out value="${idIntercambio}"/>&nombre=<c:out value="${Amigos.entidad.nombreAmigo}"/> ">
                                                    Añadir al intercambio
                                                </a>
                                            </c:when>    
                                            <c:otherwise>
                                                <a class="btn btn-danger btn-xs"
                                                   href="UsuarioServlet?accion=eliminarAmigo&id=<c:out value="${Amigos.entidad.idAmigo}"/>">
                                                    Eliminar amigo
                                                </a>
                                            </c:otherwise>
                                        </c:choose>
                                        
                                    </td>

                                </c:forEach>
                        </tbody>
                    </table>
                </div> 
            </div>    
         
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script> 
        <script languaje="JavaScript">
            if(${alerta} == 1){
               swal("Error","Este usuario ya esta en el intercambio", "error");
           }
        </script> 
    </body>
</html>
