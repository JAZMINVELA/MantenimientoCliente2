/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clientes.servlets;

import com.clientes.bean.ClienteBean;
import com.clientes.services.ClienteService;
import com.clientes.serviceImpl.ClienteServiceImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;


@WebServlet(name = "ClienteServlet", 

urlPatterns = {"/ClienteServlet"})

public class ClienteServlet extends HttpServlet {
	
    private static Logger logger = 
    Logger.getLogger(ClienteServlet.class.getName());
    
    private ClienteService clienteService;
    private ClienteBean cliente;
    private int flgOperacion = 0;
    private String mensaje = null;
    private HttpSession sesion;

    @Override
    protected void doGet(HttpServletRequest request, 
            HttpServletResponse response)
          throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) 
          throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException,
            IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        
        logger.info("processRequest: " + accion);
        
        if (accion != null) {
            if (accion.equals("insertar")) {
                insertar(request, response);
                return;
            }
            if (accion.equals("buscar")) {
                buscar(request, response);
                return;
            }
            if (accion.equals("obtenerPorId")) {
                obtenerPorId(request, response);
                return;
            }
            if (accion.equals("actualizar")) {
                actualizar(request, response);
                return;
            }
            if (accion.equals("eliminar")) {
                eliminar(request, response);
            }
        }
    }

    protected void insertar(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        logger.info("insertar");
        String ruc = request.getParameter("ruc") == null ? "" : 
                request.getParameter("ruc");
        String razSocial = request.getParameter("razSocial") == null ? "" : 
                request.getParameter("razSocial");
        String direccion = request.getParameter("direccion") == null ? "" : 
                request.getParameter("direccion");
        String telefono = request.getParameter("telefono") == null ? "" : 
                request.getParameter("telefono");
        try{
            cliente = new ClienteBean();
            cliente.setRuc(ruc);
            cliente.setRazSocial(razSocial);
            cliente.setDireccion(direccion);
            cliente.setTelefono(telefono);
            
            clienteService = new ClienteServiceImpl();
            
            flgOperacion = clienteService.insertar(cliente);
            
            if(flgOperacion > 0){
                mensaje = "Cliente insertado correctamente.";
            }else{
                mensaje = "Error al insertar cliente.";
            }
            
            sesion = request.getSession();
            sesion.removeAttribute("msgPostOperacion");
            sesion.removeAttribute("listaClientes");
            sesion.removeAttribute("msgListado");
            sesion.removeAttribute("clienteActualizar");
            sesion.setAttribute("msgPostOperacion", mensaje);
            buscar(request, response);
        }catch(Exception e){
            logger.error("insertar: " + e.getMessage());
        }
    }

    protected void buscar(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
       logger.info("buscar");
String razSocial = request.getParameter("razSocial") == null ? "" : request.getParameter("razSocial");      
     
        try{
            sesion = request.getSession();
            sesion.removeAttribute("listaClientes");
            sesion.removeAttribute("msgListado");
            sesion.removeAttribute("clienteActualizar");
            if(sesion.getAttribute("msgPostOperacion") != null){
                razSocial = "";
            }
            clienteService = new ClienteServiceImpl();
            List<ClienteBean> lstCliente = clienteService.buscar(razSocial);
            if(lstCliente.size() > 0){
                sesion.setAttribute("listaClientes", lstCliente);
            }else{
                mensaje = "No existen clientes.";
                sesion.setAttribute("msgListado", mensaje);
            }
            response.sendRedirect("clienteLst.jsp");
            
        }catch(Exception e){
        	
            logger.error("buscar: " + e.getMessage());
        }
    }

    protected void obtenerPorId(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        logger.info("obtenerPorId");
        int id = Integer.parseInt(request.getParameter("id") == null ? "0" : 
                request.getParameter("id"));
        try{
            clienteService = new ClienteServiceImpl();
            
            cliente = clienteService.obtenerPorId(id); //
            sesion = request.getSession();
            sesion.removeAttribute("msgPostOperacion");
            sesion.removeAttribute("listaClientes");
            sesion.removeAttribute("msgListado");
            sesion.removeAttribute("clienteActualizar");
            sesion.setAttribute("clienteActualizar", cliente);
            response.sendRedirect("clienteMnt.jsp");
        }catch(Exception e){
            logger.error("obtenerPorId: " + e.getMessage());
        }
    }

    protected void actualizar(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        logger.info("actualizar");
        int id = Integer.parseInt(request.getParameter("id") == null ? "0" : 
                request.getParameter("id"));
        String ruc = request.getParameter("ruc") == null ? "" : 
                request.getParameter("ruc");
        String razSocial = request.getParameter("razSocial") == null ? "" : 
                request.getParameter("razSocial");
        String direccion = request.getParameter("direccion") == null ? "" : 
                request.getParameter("direccion");
        String telefono = request.getParameter("telefono") == null ? "" : 
                request.getParameter("telefono");
        try{
            cliente = new ClienteBean();
            cliente.setId(id);
            cliente.setRuc(ruc);
            cliente.setRazSocial(razSocial);
            cliente.setDireccion(direccion);
            cliente.setTelefono(telefono);
            
            clienteService = new ClienteServiceImpl();
            
            flgOperacion = clienteService.actualizar(cliente);
            if(flgOperacion == 1){
                mensaje = "Cliente actualizado correctamente.";
            }else if(flgOperacion == 0){
                mensaje = "Error al actualizar cliente.";
            }else{
                mensaje = "RUC o Razón Social de cliente duplicados.";
            }
            sesion = request.getSession();
            sesion.removeAttribute("msgPostOperacion");
            sesion.removeAttribute("listaClientes");
            sesion.removeAttribute("msgListado");
            sesion.removeAttribute("clienteActualizar");
            sesion.setAttribute("msgPostOperacion", mensaje);
            buscar(request, response);
        }catch(Exception e){
            logger.error("actualizar: " + e.getMessage());
        }
    }

    protected void eliminar(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        logger.info("eliminar");
        int id = Integer.parseInt(request.getParameter("id") == null ? "0" : 
                request.getParameter("id"));
        try{
            clienteService = new ClienteServiceImpl(); // implementacion del ciente services
            flgOperacion = clienteService.eliminar(id);//
            
            if(flgOperacion > 0){
                mensaje = "Cliente eliminado correctamente.";
            }else{
                mensaje = "Error al eliminar cliente.";
            }
            sesion = request.getSession();
            sesion.removeAttribute("msgPostOperacion");
            sesion.removeAttribute("listaClientes");
            sesion.removeAttribute("msgListado");
            sesion.removeAttribute("clienteActualizar");
            sesion.setAttribute("msgPostOperacion", mensaje);
            buscar(request, response);
        }catch(Exception e){
            logger.error("eliminar: " + e.getMessage());
        }
    }
}
