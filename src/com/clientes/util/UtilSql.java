package com.clientes.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class UtilSql {
	public Connection getConnection(){
		Connection con = null;
		
		try{
			//1: Carga el driver
			//Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			
			
			Class.forName("com.mysql.jdbc.Driver");
			
			
			//2: Crea la conexión la URL de la Base de Datos, el usuario y la clave "ventas"
			//con = DriverManager.getConnection("jdbc:derby:E:\\Derby\\VentasBD;create=true", 
			//		"ventas", "ventas");


			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ventasbd", "root", "12345678");
		

			
			
		} catch (Throwable e){
			e.printStackTrace();
		}
		
		return con;
	}
	
	/*
	// A partir de Java 7 ya no es necesario usar estos dos métodos
	public void closeConnection(Connection con){
		try{
			//1: Cierra conexión a la base de datos
			con.close();			
		} catch (Throwable e){
			e.printStackTrace();
		}
	}

	public void closeStatement(Statement stm){
		try{
			//1: Cierra statement
			stm.close();			
		} catch (Throwable e){
			e.printStackTrace();
		}
	}
	*/
}	
