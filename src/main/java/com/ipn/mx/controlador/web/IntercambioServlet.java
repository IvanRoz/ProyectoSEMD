/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador.web;

import com.ipn.mx.modelo.dao.IntercambioDAO;
import com.ipn.mx.modelo.dao.ParticipanteDAO;
import com.ipn.mx.modelo.dao.UsuarioDAO;
import com.ipn.mx.modelo.dto.IntercambioDTO;
import com.ipn.mx.modelo.dto.ParticipanteDTO;
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
public class IntercambioServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        if (accion.equals("listaIntercambios")){
              listaIntercambios(request,response);
            }else if (accion.equals("registrar")){
                registrarIntercambio(request,response);
               }else if (accion.equals("ver")){
                verIntercambio(request,response);
                     }else if(accion.equals("participar")){
                         participarIntercambio(request,response);
                        }else if(accion.equals("dejarIntercambio")){
                           dejarIntercambio(request, response);
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

    private void listaIntercambios(HttpServletRequest request, HttpServletResponse response) {
        try {
            IntercambioDAO dao = new IntercambioDAO();
            HttpSession sesion = request.getSession();
            
            sesion.setAttribute("ListaDeIntercambios", dao.readAll());
            RequestDispatcher rd = request.getRequestDispatcher("LoginExitoso.jsp");   
            rd.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(IntercambioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void registrarIntercambio(HttpServletRequest request, HttpServletResponse response) {
        IntercambioDTO dto = new IntercambioDTO();
        IntercambioDAO dao = new IntercambioDAO();
        HttpSession sesion = request.getSession();
        String fecha = request.getParameter("fecha");
        dto.getEntidad().setNombreIntercambio(request.getParameter("nombreIntercambio"));
        dto.getEntidad().setTema(request.getParameter("tema"));
        dto.getEntidad().setFechaIntercambio(Date.valueOf(fecha));
        dto.getEntidad().setMontoMax(Integer.parseInt(request.getParameter("montoMax")));
        
        try {
            dao.create(dto);
            sesion.setAttribute("ListaDeIntercambios",dao.readAll());
            RequestDispatcher rd = request.getRequestDispatcher("LoginExitoso.jsp");
            rd.forward(request, response);  
            
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void verIntercambio(HttpServletRequest request, HttpServletResponse response) {
        IntercambioDTO dto = new IntercambioDTO();
        IntercambioDAO dao = new IntercambioDAO();
        ParticipanteDTO dtoP = new ParticipanteDTO();
        ParticipanteDAO daoP = new ParticipanteDAO();
        HttpSession sesion = request.getSession();
        int idIntercambio = Integer.parseInt(request.getParameter("id"));
        
        dto.getEntidad().setIdIntercambio(Integer.parseInt(request.getParameter("id")));
        
        dtoP.getEntidad().setIdUsuario(Integer.parseInt(sesion.getAttribute("idUsuario").toString()));
        dtoP.getEntidad().setIdIntercambio(idIntercambio);
       
        
        try {
            List lista = daoP.readAll(dtoP);
            request.setAttribute("ListaDeParticipantes", lista);
            dtoP = daoP.read(dtoP);
            if (dtoP != null) {
                request.setAttribute("Participa", 1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IntercambioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            dto = dao.read(dto);
            if(dto!= null){
                sesion.setAttribute("Intercambio",dto);
                RequestDispatcher rd = request.getRequestDispatcher("verIntercambio.jsp");
                rd.forward(request, response);  
            }else{
                request.setAttribute("alerta", 1);
                RequestDispatcher rd = request.getRequestDispatcher("BuscarInter.jsp");
                rd.forward(request, response); 
            }
            
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(IntercambioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void participarIntercambio(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDTO dto = new UsuarioDTO();
        UsuarioDAO dao = new UsuarioDAO();
        ParticipanteDTO dtoP = new ParticipanteDTO();
        ParticipanteDAO daoP = new ParticipanteDAO();
        HttpSession sesion = request.getSession();
        
        dto.getEntidad().setIdUsuario(Integer.parseInt(sesion.getAttribute("idUsuario").toString()));
        try {
            dto = dao.Read(dto);
            dtoP.getEntidad().setIdIntercambio(Integer.parseInt(request.getParameter("id")));
            dtoP.getEntidad().setIdUsuario(dto.getEntidad().getIdUsuario());
            dtoP.getEntidad().setNombreParticipante(dto.getEntidad().getNombre());
            dtoP.getEntidad().setParticipa(true);
            daoP.create(dtoP);
            verIntercambio(request, response);
            
        } catch (SQLException ex) {
            Logger.getLogger(IntercambioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

    private void dejarIntercambio(HttpServletRequest request, HttpServletResponse response) {
        ParticipanteDTO dto = new ParticipanteDTO();
        ParticipanteDAO dao = new ParticipanteDAO();
        HttpSession sesion = request.getSession();
        
        dto.getEntidad().setIdIntercambio(Integer.parseInt(request.getParameter("id")));
        dto.getEntidad().setIdUsuario(Integer.parseInt(sesion.getAttribute("idUsuario").toString()));
        
        try {
            dao.delete(dto);
            verIntercambio(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(IntercambioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

}
