<%-- 
    Document   : verIntercambio
    Created on : 11 ene 2021, 17:08:08
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
        <body background="Imagenes/Back3.jpg">
        
        <!-- Image and text -->
        <nav class="navbar navbar-dark bg-dark">
            <img src="Imagenes/Regalo.png" width="30" height="30" class="d-inline-block align-top" alt="" loading="lazy">
            <a class="navbar-brand" href="#"> Datos del intercambio </a>
        </nav>   
        
        <div class="container">
            <div class="card-body">   
            <div class="card bg-light border-success">
                
                     <h5 class="p-3 mb-2 bg-success text-white">Datos del intercambio</h5> 
        

                <div class="card-body">
                    <ul class="list-group border-success">
                        <li class="list-group-item"> Nombre del intercambio : <c:out value="${Intercambio.entidad.nombreIntercambio}"/></li>
                        <li class="list-group-item"> Tema : <c:out value="${Intercambio.entidad.tema}"/> </li>
                        <li class="list-group-item"> Fecha del intercambio : <c:out value="${Intercambio.entidad.fechaIntercambio}"/> </li>
                        <li class="list-group-item"> Monto maximo : $<c:out value="${Intercambio.entidad.montoMax}"/></li>
                        <li class="list-group-item overflow-auto"> Participantes: 
                            <c:forEach 
                                var="Participantes" 
                                items="${ListaDeParticipantes}">
                                <br/>
                                <c:out value="${Participantes.entidad.nombreParticipante}"/> 
                        </c:forEach>
                        </li>
                    </ul>
                </div>
                <div class=" card-body">
                    <c:choose>
                        <c:when test="${Participa==1}">
                            <a href="IntercambioServlet?accion=dejarIntercambio&id=<c:out value="${Intercambio.entidad.idIntercambio}"/>" class="btn btn-success"> Abandonar intercambio </a>          
                            <a href="UsuarioServlet?accion=agregarParticipantes&idIntercambio=<c:out value="${Intercambio.entidad.idIntercambio}"/>" class="btn btn-success"> Añadir amigos al intercambio</a>
                        </c:when>    
                        <c:otherwise>
                           <a href="IntercambioServlet?accion=participar&id=<c:out value="${Intercambio.entidad.idIntercambio}"/>" class="btn btn-success"> Participar </a>          
                        </c:otherwise>
                    </c:choose>
                    <a href="IntercambioServlet?accion=listaIntercambios" class="btn btn-danger"> Regresar a la lista de intercambios </a>
                    
                </div>
                
               
            </div> 
            </div>    
           
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
    </body>
</html>
