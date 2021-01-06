package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.UsuarioDTO;
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
public class UsuarioDAO {
    
    private static final String SQL_INSERT="insert into usuario (alias,nombre,correo,clave) values (?,?,?,?);";
    private static final String SQL_LOGIN="select * from usuario where alias = ? and clave = ?";
    
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
    
    public void create(UsuarioDTO dto) throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
        try{
            cs = con.prepareCall(SQL_INSERT);
            cs.setString(1,dto.getEntidad().getAlias());
            cs.setString(2,dto.getEntidad().getNombre());
            cs.setString(3,dto.getEntidad().getCorreo());
            cs.setString(4,dto.getEntidad().getClave());
            cs.executeUpdate();
        }finally{
            if(cs != null)
                  cs.close();
            if(con != null)
                  con.close();
        }
    }
    
    public UsuarioDTO login(UsuarioDTO dto)throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
        ResultSet rs = null;
        try{
            cs = con.prepareCall(SQL_LOGIN);
            cs.setString(1, dto.getEntidad().getAlias());
            cs.setString(2, dto.getEntidad().getClave());
            rs = cs.executeQuery();
            List resultados = obtenerReusltados(rs);
            if(resultados.size() > 0){
                return (UsuarioDTO) resultados.get(0);
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
            UsuarioDTO dto = new UsuarioDTO();
            dto.getEntidad().setIdUsuario(rs.getInt("idUsuario"));
            dto.getEntidad().setAlias(rs.getString("alias"));
            dto.getEntidad().setNombre(rs.getString("nombre"));
            dto.getEntidad().setCorreo(rs.getString("correo"));
            dto.getEntidad().setClave(rs.getString("clave"));
            resultados.add(dto);
        }
        return resultados;
    }
     
//    public static void main(String[] args) {
//        UsuarioDAO dao = new UsuarioDAO();
//        UsuarioDTO dto = new UsuarioDTO();
//        dto.getEntidad().setAlias("Ivan Rz");
//        dto.getEntidad().setClave("12345");
//        
//        System.out.println(dao.login(dto));
//    }
// 
     

}
