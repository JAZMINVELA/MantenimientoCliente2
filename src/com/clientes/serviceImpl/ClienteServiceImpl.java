/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clientes.serviceImpl;

import com.clientes.bean.ClienteBean;
import com.clientes.dao.ClienteDAO;
import com.clientes.daoImpl.ClienteDAOImpl;
import com.clientes.services.ClienteService;
import java.util.List;

/**
 *
 * @author GC
 */
public class ClienteServiceImpl implements ClienteService{
    private ClienteDAO clienteDAO; //declaramos una variable del cliente DAO
    
    @Override
    public int insertar(ClienteBean cliente) {
        clienteDAO = new ClienteDAOImpl();
        return clienteDAO.insertar (cliente);
    }

    @Override
    public List<ClienteBean> buscar(String razSocial) {
        clienteDAO = new ClienteDAOImpl();
        return clienteDAO.buscar(razSocial);
    }

    @Override
    public ClienteBean obtenerPorId(int id) {
        clienteDAO = new ClienteDAOImpl();
        return clienteDAO.obtenerPorId(id);
    }

    @Override
    public int actualizar(ClienteBean cliente) {
        clienteDAO = new ClienteDAOImpl();
        return clienteDAO.actualizar(cliente);
    }

    @Override
    public int eliminar(int id) {
        clienteDAO = new ClienteDAOImpl();
        return clienteDAO.eliminar(id);
    }
}
