package com.epf.rentmanager.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;



public class ClientService {

	private ClientDao clientDao;
	public static ClientService instance;
	
	private ClientService() {
		this.clientDao = ClientDao.getInstance();
	}
	
	public static ClientService getInstance() {
		if (instance == null) {
			instance = new ClientService();
		}
		
		return instance;
	}


	
	
	public long create(Client client) throws ServiceException {


		try{
			return clientDao.getInstance().create(client);}
		catch (DaoException e) {

			throw new ServiceException();
		}


	}

	public long delete(Client client) throws ServiceException {


		try{
			return clientDao.getInstance().delete(client);}
		catch (DaoException e) {

			throw new ServiceException();
		}


	}

	public Client findById(long id) throws ServiceException {
		// TODO: récupérer un client par son id
		try{
			return clientDao.getInstance().findById(id);}
		catch (DaoException e) {

			throw new ServiceException();
		}
	}

	public List<Client> findAll() throws ServiceException {
		// TODO: récupérer tous les clients
		try{
			return clientDao.getInstance().findAll();}
		catch (DaoException e) {

			throw new ServiceException();
		}
	}
	
}
