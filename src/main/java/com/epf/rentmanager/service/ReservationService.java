package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.dao.ReservationDao;

public class ReservationService {

    private ReservationDao reservationDao;
    public static ReservationService instance;

    private ReservationService() {
        this.reservationDao = ReservationDao.getInstance();
    }

    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }

        return instance;
    }




    public long create(Reservation reservation) throws ServiceException {


        try{
            return reservationDao.getInstance().create(reservation);}
        catch (DaoException e) {

            throw new ServiceException();
        }
    }


    public long delete(Reservation reservation) throws ServiceException {


        try{
            return ReservationDao.getInstance().delete(reservation);}
        catch (DaoException e) {

            throw new ServiceException();
        }


    }
    public List<Reservation> findAll() throws ServiceException {

        try{
            return reservationDao.getInstance().findAll();}
        catch (DaoException e) {

            throw new ServiceException();
        }

    }

    public List<Reservation> findResaByVehicleId(long vehicleId) throws ServiceException {

        try{
            return reservationDao.getInstance().findResaByVehicleId(vehicleId);}
        catch (DaoException e) {

            throw new ServiceException();
        }

    }

    public List<Reservation> findResaByClientId(long clientId) throws ServiceException {

        try{
            return reservationDao.getInstance().findResaByClientId(clientId);}
        catch (DaoException e) {

            throw new ServiceException();
        }

    }


}

