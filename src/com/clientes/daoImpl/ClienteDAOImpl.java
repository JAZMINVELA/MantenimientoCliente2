/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clientes.daoImpl;

import com.clientes.bean.ClienteBean;
import com.clientes.dao.ClienteDAO;
//import com.clientes.util.Conexion;

import com.clientes.util.UtilSql;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;


public class ClienteDAOImpl implements ClienteDAO{
    private static Logger logger = Logger.getLogger(ClienteDAOImpl.class.getName());
    private UtilSql con;
    private Connection cn;
    private ResultSet rs;
    private PreparedStatement ps;
    private CallableStatement cs;
    private String sql;
    private int flgOperacion = 0;   
        
    @Override
    public int insertar(ClienteBean cliente) {
        logger.info("insertar");
      // sql = "call p_insertar_cliente(?,?,?,?,?)";
       // PreparedStatement pstmt=null;
        sql = "insert into cliente(cli_id,cli_ruc,cli_raz_social,"
        	   + "cli_direccion,cli_telefono)" + 
                "values(?,?,?,?,?)";
  
        System.out.println(sql + "consulta ");
        try{
            con = new UtilSql();
            cn = con.getConnection();
            cn.setAutoCommit(false);
           // cs = cn.prepareCall (sql);
            
            ps = cn.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            ps.setString(2, cliente.getRuc());
            ps.setString(3, cliente.getRazSocial());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getTelefono());
            
              flgOperacion = ps.executeUpdate();
              
            if(flgOperacion > 0){
                cn.commit();
                  System.out.println(sql + "comitttt ");
            }else{
                cn.rollback();
                  System.out.println(sql + "roblbackkk ");
            }

        }catch(Exception e){
            	e.printStackTrace();
            logger.error("insertar: " + e.getMessage());
        }finally{
           // con.cerrarConexion(cn);
        }
        return flgOperacion;
    }

    @Override
    public List<ClienteBean> buscar(String razSocial) {
        logger.info("buscar");
        sql = "select cli_id, cli_ruc, cli_raz_social, "
                + "cli_direccion, cli_telefono "
                + "from cliente "
               + "where cli_raz_social like('% ? %')";
        
        List<ClienteBean> lstCliente = null;
        ClienteBean cliente = null;
        try{
            con = new UtilSql();
            cn = con.getConnection();
            cn.setAutoCommit(false);
            ps = cn.prepareStatement(sql);
            
            ps.setString(1, razSocial);
            
            rs = ps.executeQuery();
            lstCliente = new ArrayList<ClienteBean>();
            while(rs.next()){
                cliente = new ClienteBean();
                cliente.setId(rs.getInt("cli_id"));
                cliente.setRuc(rs.getString("cli_ruc"));
                cliente.setRazSocial(rs.getString("cli_raz_social"));
                cliente.setDireccion(rs.getString("cli_direccion"));
                cliente.setTelefono(rs.getString("cli_telefono"));
                
                lstCliente.add(cliente);
                System.out.println("lista de clientes : " + lstCliente);
            }
        }catch(Exception e){
            logger.error("buscar: " + e.getMessage());
        }finally{
            //con.cerrarConexion(cn);
        }
        return lstCliente;
        
        
    }

    @Override
    public ClienteBean obtenerPorId(int id) {
        logger.info("buscarPorId");
        sql = "select cli_id, cli_ruc, cli_raz_social, "
                + "cli_direccion, cli_telefono "
                + "from cliente "
                + "where cli_id = ?";
        ClienteBean cliente = null;
        try{
            con = new UtilSql();
            cn = con.getConnection();
            cn.setAutoCommit(false);
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                cliente = new ClienteBean();
                cliente.setId(rs.getInt("cli_id"));
                cliente.setRuc(rs.getString("cli_ruc"));
                cliente.setRazSocial(rs.getString("cli_raz_social"));
                cliente.setDireccion(rs.getString("cli_direccion"));
                cliente.setTelefono(rs.getString("cli_telefono"));
            }
        }catch(Exception e){
            logger.error("buscarPorId: " + e.getMessage());
        }finally{
           // con.cerrarConexion(cn);
        }
        return cliente;
    }

    @Override
    public int actualizar(ClienteBean cliente) {
        logger.info("actualizar");
        //sql = "call p_actualizar_cliente(?,?,?,?,?,?)";
        
         sql="update cliente set cli_ruc=?,cli_raz_social=?,"
         		+ "cli_direccion=?,cli_telefono=?"
        		+ "where cli_id = ?";
        
        try{
            con = new UtilSql();
            cn = con.getConnection();
            cn.setAutoCommit(false);
          //  cs = cn.prepareCall(sql);
              
            ps = cn.prepareStatement(sql);
  
            ps.setInt(1, cliente.getId());
            ps.setString(2, cliente.getRuc());
            ps.setString(3, cliente.getRazSocial());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getTelefono());
            
          flgOperacion = ps.executeUpdate();
              
          
            if(flgOperacion > 0){
                cn.commit();
            }else{
                cn.rollback();
            }
        }catch(Exception e){
            logger.error("actualizar: " + e.getMessage());
        }finally{
           // con.cerrarConexion(cn);
        }
        return flgOperacion;
    }

    @Override
    public int eliminar(int id) {
        logger.info("eliminar");
        sql = "delete from cliente where cli_id = ?";
        try{
            con = new UtilSql();
            cn = con.getConnection();
            cn.setAutoCommit(false);
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            flgOperacion = ps.executeUpdate();
            if(flgOperacion > 0){
                cn.commit();
            }else{
                cn.rollback();
            }
        }catch(Exception e){
            logger.error("eliminar: " + e.getMessage());
        }finally{
           // con.cerrarConexion(cn);
        }
        return flgOperacion;
    }
}
