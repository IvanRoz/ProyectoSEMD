/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.IntercambioDTO;
import com.ipn.mx.modelo.dto.ParticipanteDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivan
 */
public class ParticipanteDAO {
    
    private static final String SQL_INSERT="insert into participante values (?,?,?)";
    private static final String SQL_SELECT="select * from participante where idIntercambio = ? and idUsuario = ?";
    private static final String SQL_SELECT_ALL="select * from participante where idIntercambio = ?";
    private static final String SQL_DELETE="delete from participante where idIntercambio = ? and idUsuario = ?";
    
    private Connection con;
    
    public Connection obtenerConexion() {
        String user = "vbsatyzmpujkhq";   //Usuario de la BD
        String pass = "b8d488f9b0b3ae5f776b4d051a5e5a1f123b5551e8d82007eebbbdb90aad87e9";   //Contra del usuario
        String url = "jdbc:postgresql://ec2-54-156-73-147.compute-1.amazonaws.com:5432/d60vss2u2939fr"; //Url de la base de datos
        String driverMySql = "org.postgresql.Driver";  //Lugar donde esta el driver de mysql
        try {
             Class.forName(driverMySql);
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
    public void create(ParticipanteDTO dto) throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
        try{
            cs = con.prepareCall(SQL_INSERT);
            cs.setInt(1,dto.getEntidad().getIdIntercambio());
            cs.setInt(2,dto.getEntidad().getIdUsuario());
            cs.setString(3,dto.getEntidad().getNombreParticipante());
            cs.executeUpdate();
        }finally{
            if(cs != null)
                  cs.close();
            if(con != null)
                  con.close();
        }
    }
    
    public ParticipanteDTO read(ParticipanteDTO dto)throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
        ResultSet rs = null;
        try{
            cs = con.prepareCall(SQL_SELECT);
            cs.setInt(1, dto.getEntidad().getIdIntercambio());
            cs.setInt(2, dto.getEntidad().getIdUsuario());
            rs = cs.executeQuery();
            List resultados = obtenerReusltados(rs);
            if(resultados.size() > 0){
                return (ParticipanteDTO) resultados.get(0);
            }else
                return null;
        }finally{
            if(rs != null)
                 rs.close();
            if(cs != null)
                 cs.close();
            if(con != null)
                 con.close();
        }
    }
    
    public List readAll(ParticipanteDTO dto)throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
        ResultSet rs = null;
        try{
            cs = con.prepareCall(SQL_SELECT_ALL);
            cs.setInt(1, dto.getEntidad().getIdIntercambio());
            rs = cs.executeQuery();
            List resultados = obtenerReusltados(rs);
            if(resultados.size() > 0){
                return resultados;
            }else
                return null;
        }finally{
            if(rs != null)
                 rs.close();
            if(cs != null)
                 cs.close();
            if(con != null)
                 con.close();
        }
    }
    
    public void delete(ParticipanteDTO dto) throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
        try{
            cs = con.prepareCall(SQL_DELETE);
            cs.setInt(1,dto.getEntidad().getIdIntercambio());
            cs.setInt(2,dto.getEntidad().getIdUsuario());
            cs.executeUpdate();
        }finally{
            if(cs != null)
                  cs.close();
            if(con != null)
                  con.close();
        }
    }
    
    private List obtenerReusltados(ResultSet rs) throws SQLException {
        List resultados = new ArrayList();
        while(rs.next()){
            ParticipanteDTO dto = new ParticipanteDTO();
            dto.getEntidad().setIdIntercambio(rs.getInt("idIntercambio"));
            dto.getEntidad().setIdUsuario(rs.getInt("idUsuario"));
            dto.getEntidad().setNombreParticipante(rs.getString("nombreParticipante"));
            dto.getEntidad().setParticipa(rs.getBoolean("participa"));
            resultados.add(dto);
        }
        return resultados;
    }
    
}
