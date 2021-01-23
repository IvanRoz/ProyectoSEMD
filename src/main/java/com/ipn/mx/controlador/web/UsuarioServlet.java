/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador.web;

import com.ipn.mx.modelo.dao.AmigoDAO;
import com.ipn.mx.modelo.dao.IntercambioDAO;
import com.ipn.mx.modelo.dao.UsuarioDAO;
import com.ipn.mx.modelo.dto.AmigoDTO;
import com.ipn.mx.modelo.dto.IntercambioDTO;
import com.ipn.mx.modelo.dto.UsuarioDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ivan
 */
public class UsuarioServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        if (accion.equals("registrar")){
            registrarUsuario(request,response);
          }else if (accion.equals("login")){
              loginUsuario(request,response);
            }else if(accion.equals("verUsuario")){
                verUsuario(request,response);
              } else if(accion.equals("actualizarUsuario")){
                   actualizarUsuario(request,response);
                 }else if(accion.equals("update")){
                       updateUser(request,response);
                   }else if(accion.equals("verAmigos")){
                         verAmigos(request,response); 
                      }else if(accion.equals("eliminarAmigo")){
                          eliminarAmigo(request,response);
                         }else if(accion.equals("buscarAmigo")){
                             buscarAmigo(request,response);
                            }else if(accion.equals("agregarAmigo")){
                               agregarAmigo(request,response);
                                 }else if(accion.equals("agregarParticipantes")){
                                        agregarParticipantes(request,response);
                                      }   
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private void registrarUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDTO dto = new UsuarioDTO();
        UsuarioDAO dao = new UsuarioDAO(); 
        
        dto.getEntidad().setAlias(request.getParameter("nombreUsuario"));
        dto.getEntidad().setNombre(request.getParameter("nombre"));
        dto.getEntidad().setCorreo(request.getParameter("email"));
        dto.getEntidad().setClave(request.getParameter("claveUsuario"));
        
        try {
            dao.create(dto);
            RequestDispatcher rd = request.getRequestDispatcher("Login.html");
            rd.forward(request,response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loginUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDTO dto = new UsuarioDTO();
        UsuarioDAO dao = new UsuarioDAO();
        IntercambioDAO daoI = new IntercambioDAO();
        HttpSession sesion = request.getSession();
        
        dto.getEntidad().setAlias(request.getParameter("nombreUsuario"));
        dto.getEntidad().setClave(request.getParameter("claveUsuario"));

        try {
            dto = dao.login(dto);
            List lista = daoI.readAll();
            if (dto != null) {
                  sesion.setAttribute("Usuario",dto.getEntidad().getAlias());
                  sesion.setAttribute("idUsuario",dto.getEntidad().getIdUsuario());
                  RequestDispatcher rd = request.getRequestDispatcher("IntercambioServlet?accion=listaIntercambios");
                  rd.forward(request, response);            
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("Login.html");
                rd.forward(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        
    }

    private void verUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDTO dto = new UsuarioDTO();
        UsuarioDAO dao = new UsuarioDAO();
        HttpSession sesion = request.getSession();
        
        dto.getEntidad().setIdUsuario(Integer.parseInt(sesion.getAttribute("idUsuario").toString()));
        
        try {
            dto = dao.Read(dto);
            request.setAttribute("User", dto);
            RequestDispatcher rd = request.getRequestDispatcher("verPerfil.jsp");
            rd.forward(request, response); 
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDTO dto = new UsuarioDTO();
        UsuarioDAO dao = new UsuarioDAO();
        HttpSession sesion = request.getSession();
        
        dto.getEntidad().setIdUsuario(Integer.parseInt(sesion.getAttribute("idUsuario").toString()));
        
        try {
            dto = dao.Read(dto);
            request.setAttribute("User", dto);
            RequestDispatcher rd = request.getRequestDispatcher("actualizarPerfil.jsp");
            rd.forward(request, response); 
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDTO dto = new UsuarioDTO();
        UsuarioDAO dao = new UsuarioDAO();
        HttpSession sesion = request.getSession();
        
        dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));
        dto.getEntidad().setAlias(request.getParameter("alias"));
        dto.getEntidad().setNombre(request.getParameter("nombre"));
        dto.getEntidad().setCorreo(request.getParameter("email"));
        dto.getEntidad().setClave(request.getParameter("claveUsuario"));
        
        try {
            dao.update(dto);
            sesion.setAttribute("Usuario",dto.getEntidad().getAlias());
            RequestDispatcher rd = request.getRequestDispatcher("UsuarioServlet?accion=verUsuario");
            rd.forward(request, response); 
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void verAmigos(HttpServletRequest request, HttpServletResponse response) {
        AmigoDAO dao = new AmigoDAO();
        AmigoDTO dto = new AmigoDTO();
        HttpSession sesion = request.getSession();
        
        dto.getEntidad().setIdUsuario(Integer.parseInt(sesion.getAttribute("idUsuario").toString()));
        
        try {
            List lista = dao.readAll(dto);
            request.setAttribute("ListaAmigos", lista);
            RequestDispatcher rd = request.getRequestDispatcher("verAmigos.jsp");
            rd.forward(request, response); 
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarAmigo(HttpServletRequest request, HttpServletResponse response) {
        AmigoDAO dao = new AmigoDAO();
        AmigoDTO dto = new AmigoDTO();
        dto.getEntidad().setIdAmigo(Integer.parseInt(request.getParameter("id")));
        
        try {
            dao.delete(dto);
            RequestDispatcher rd = request.getRequestDispatcher("UsuarioServlet?accion=verAmigos");
            rd.forward(request, response); 
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

    private void buscarAmigo(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDTO dto = new UsuarioDTO();
        UsuarioDAO dao = new UsuarioDAO();

        dto.getEntidad().setAlias(request.getParameter("alias"));
        
        try {
            dto = dao.ReadByName(dto);
            if(dto!=null ){
                request.setAttribute("User", dto);
                request.setAttribute("amigo", 1);
                RequestDispatcher rd = request.getRequestDispatcher("verPerfil.jsp");
                rd.forward(request, response);
            }else{
                request.setAttribute("alerta", 1);
                RequestDispatcher rd = request.getRequestDispatcher("BuscarAmigo.jsp");
                rd.forward(request, response);
            }
        } catch (SQLException | ServletException | IOException ex) {
            
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarAmigo(HttpServletRequest request, HttpServletResponse response) {
        AmigoDAO dao = new AmigoDAO();
        AmigoDTO dto = new AmigoDTO();
        HttpSession sesion = request.getSession();
        
        dto.getEntidad().setIdUsuario(Integer.parseInt(sesion.getAttribute("idUsuario").toString()));
        dto.getEntidad().setIdAmigo(Integer.parseInt(request.getParameter("id")));
        dto.getEntidad().setNombreAmigo(request.getParameter("nombre"));
        
        try {
            dao.create(dto);
            RequestDispatcher rd = request.getRequestDispatcher("UsuarioServlet?accion=verAmigos");
            rd.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    private void agregarParticipantes(HttpServletRequest request, HttpServletResponse response) {
        AmigoDAO dao = new AmigoDAO();
        AmigoDTO dto = new AmigoDTO();
        HttpSession sesion = request.getSession();
        
        dto.getEntidad().setIdUsuario(Integer.parseInt(sesion.getAttribute("idUsuario").toString()));
        
        try {
            List lista = dao.readAll(dto);
            request.setAttribute("ListaAmigos", lista);
            request.setAttribute("agregar", 1);
            request.setAttribute("idIntercambio", request.getParameter("idIntercambio"));
            RequestDispatcher rd = request.getRequestDispatcher("verAmigos.jsp");
            rd.forward(request, response); 
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
