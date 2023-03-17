package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.VehicleDao;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

	private VehicleDao vehicleDao;
	
	private VehicleService(VehicleDao vehicleDao) {
		this.vehicleDao = vehicleDao;
	}

	public long create(Vehicle vehicle) throws ServiceException {

		try{
			return this.vehicleDao.create(vehicle);}
		catch (DaoException e) {

			throw new ServiceException();
		}

	}


	public long delete(Vehicle vehicle) throws ServiceException {


		try{
			return vehicleDao.delete(vehicle);}
		catch (DaoException e) {

			throw new ServiceException();
		}


	}

	public Vehicle findById(long id) throws ServiceException {

		try{
			return vehicleDao.findById(id).get();}
		catch (DaoException e) {

			throw new ServiceException();
		}

	}

	public List<Vehicle> findAll() throws ServiceException {

		try{
			return vehicleDao.findAll();}
		catch (DaoException e) {

			throw new ServiceException();
		}

	}

	public int CountVehicle() {

		return this.vehicleDao.CountVehicle();

	}
	
}
