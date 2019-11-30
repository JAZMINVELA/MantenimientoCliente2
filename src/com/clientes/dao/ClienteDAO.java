package com.clientes.dao;

import java.util.List;

import com.clientes.bean.ClienteBean;

public interface ClienteDAO {

	    public int insertar(ClienteBean cliente);
	    public List<ClienteBean> buscar(String razSocial);
	    public ClienteBean obtenerPorId(int id);
	    public int actualizar(ClienteBean cliente);
	    public int eliminar(int id);

}
