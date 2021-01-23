
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.AmigoDTO;
import com.ipn.mx.modelo.dto.IntercambioDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
public class AmigoDAO {
    
    private static final String SQL_SELECT_ALL="select * from amigo where idUsuario = ?";
    private static final String SQL_INSERT="insert into amigo values (?,?,?)";
    private static final String SQL_DELETE="delete from amigo where idAmigo = ?";
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
    
    public void create(AmigoDTO dto) throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
        try{
            cs = con.prepareCall(SQL_INSERT);
            cs.setInt(1,dto.getEntidad().getIdUsuario());
            cs.setInt(2,dto.getEntidad().getIdAmigo());
            cs.setString(3, dto.getEntidad().getNombreAmigo());
            cs.executeUpdate();
        }finally{
            if(cs != null)
                  cs.close();
            if(con != null)
                  con.close();
        }
    }
    
    public List readAll(AmigoDTO dto)throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
        ResultSet rs = null;
        try{
            cs = con.prepareCall(SQL_SELECT_ALL);
            cs.setInt(1, dto.getEntidad().getIdUsuario());
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
    
    public void delete(AmigoDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = con.prepareCall(SQL_DELETE);
            ps.setInt(1, dto.getEntidad().getIdAmigo());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
     private List obtenerReusltados(ResultSet rs) throws SQLException {
        List resultados = new ArrayList();
        
        while(rs.next()){
            AmigoDTO dto = new AmigoDTO();
            dto.getEntidad().setIdUsuario(rs.getInt("idUsuario"));
            dto.getEntidad().setIdAmigo(rs.getInt("idAmigo"));
            dto.getEntidad().setNombreAmigo(rs.getString("nombreAmigo"));
            resultados.add(dto);
        }
        return resultados;
    }
//     
//     public static void main(String[] args) {
//        AmigoDAO dao = new AmigoDAO();
//        AmigoDTO dto = new AmigoDTO();
//        
//        dto.getEntidad().setIdUsuario(1);
//        
//        System.out.println(dao.readAll(dto));
//    }
     
    
}
