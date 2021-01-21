/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador.web;

import com.ipn.mx.modelo.dao.IntercambioDAO;
import com.ipn.mx.modelo.dao.UsuarioDAO;
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

}
