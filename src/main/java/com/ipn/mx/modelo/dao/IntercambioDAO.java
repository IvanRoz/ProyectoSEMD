
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.IntercambioDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
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
public class IntercambioDAO {
    
     private static final String SQL_SELECT_ALL="select * from intercambio";
     private static final String SQL_SELECT="select * from intercambio where idIntercambio = ?";
     private static final String SQL_INSERT="insert into intercambio(nombreIntercambio, tema, fechaIntercambio, montoMax) values (?,?,?,?)";
    
    private Connection con;
    
    public Connection obtenerConexion() {
        String user = "vbsatyzmpujkhq";   //Usuario de la BD
        String pass = "b8d488f9b0b3ae5f776b4d051a5e5a1f123b5551e8d82007eebbbdb90aad87e9";   //Contra del usuario
        String url = "jdbc:postgresql://ec2-54-156-73-147.compute-1.amazonaws.com:5432/d60vss2u2939fr"; //Url de la base de datos
        String driverMySql = "org.postgresql.Driver";  //Lugar donde esta el driver de mysql
        try {
             Class.forName(driverMySql);
            con = DriverManager.getConnection(url, user, pass);
        //    System.out.println("Logre la coneccion");
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
    public void create(IntercambioDTO dto) throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
        try{
            cs = con.prepareCall(SQL_INSERT);
            cs.setString(1,dto.getEntidad().getNombreIntercambio());
            cs.setString(2,dto.getEntidad().getTema());
            cs.setDate(3, dto.getEntidad().getFechaIntercambio());
            cs.setInt(4,dto.getEntidad().getMontoMax());
            cs.executeUpdate();
        }finally{
            if(cs != null)
                  cs.close();
            if(con != null)
                  con.close();
        }
    }
    
    public IntercambioDTO read(IntercambioDTO dto)throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
        ResultSet rs = null;
        try{
            cs = con.prepareCall(SQL_SELECT);
            cs.setInt(1, dto.getEntidad().getIdIntercambio());
            rs = cs.executeQuery();
            List resultados = obtenerReusltados(rs);
            if(resultados.size() > 0){
                return (IntercambioDTO) resultados.get(0);
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
    
    public List readAll()throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
        ResultSet rs = null;
        try{
            cs = con.prepareCall(SQL_SELECT_ALL);
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
    
     private List obtenerReusltados(ResultSet rs) throws SQLException {
        List resultados = new ArrayList();
        
        while(rs.next()){
            IntercambioDTO dto = new IntercambioDTO();
            dto.getEntidad().setIdIntercambio(rs.getInt("idIntercambio"));
            dto.getEntidad().setNombreIntercambio(rs.getString("nombreIntercambio"));
            dto.getEntidad().setTema(rs.getString("tema"));
            dto.getEntidad().setFechaIntercambio(rs.getDate("fechaIntercambio"));
            dto.getEntidad().setMontoMax(rs.getInt("montoMax"));
            resultados.add(dto);
        }
        return resultados;
    }
     
//     public static void main(String[] args) {
//        IntercambioDAO dao = new IntercambioDAO();
//        IntercambioDTO dto = new IntercambioDTO();
//        
//        dto.getEntidad().setNombreIntercambio("Intercambio desde netbeans");
//        dto.getEntidad().setTema("Ordenadores");
//        dto.getEntidad().setFechaIntercambio(Date.valueOf("2021-08-21"));
//        dto.getEntidad().setMontoMax(15300);
//        dao.create(dto);
//        
//    }
    
}
