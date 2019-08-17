/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion;

import java.io.*;
import java.sql.*;
/**
 *
 * @author uth
 */
public class gestorDB {

    /**
     * Retorna una conexion a la base de datos derby local
     * @return objeto conexion
     */
    public static Connection getConexion() {
        try {
            String usuario = "Relojes";
            String contrasenia = "Flakito2019";
            String url = "jdbc:derby://localhost:1527/VENTA VIRTUAL";
            
            return DriverManager.getConnection(url, usuario, contrasenia);
        } catch (Exception e) {
            System.out.println("Error getConexion: " + e.toString());
        }
        return null;
    }
    
    public static void guardarReloj(
            int Id, 
            double Precio,
            String Marca,
            String Descripcion
                ) {
        try {
            //1.- Obtener la conexion
            Connection con = getConexion();

            //2.- SQL de insert
            String sql = "INSERT INTO UTH.RELOJ" +
                         "(Id, Precio, Marca, Descripcion)" +
                         "VALUES (?, ?, ?, ?)";
           
            
            //3. Preparar el queryc
            PreparedStatement ps=con.prepareStatement(sql);
            
            //4. Asignar valores a los signos de interrogacion
            ps.setInt   (1, Id);
            ps.setDouble(2, Precio);
            ps.setString(3, Marca);
            ps.setString(4, Descripcion);
            
                       
            //5. Ejecutar el query
            ps.execute();
            
            //6. Cerrar la conexion
            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error guardarReloj: "+e.toString());
        }
    }
}