package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.model.Vehicle;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private ReservationDao reservationDao;
    //public static ReservationService instance;

    /*private ReservationService() {
        this.reservationDao = ReservationDao.getInstance();
    }*/



    private ReservationService(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }
    /*public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }

        return instance;
    }*/




    public long create(Reservation reservation) throws ServiceException {


        try{
            return reservationDao.create(reservation);}
        catch (DaoException e) {

            throw new ServiceException();
        }
    }


    public long delete(Reservation reservation) throws ServiceException {


        try{
            return reservationDao.delete(reservation);}
        catch (DaoException e) {

            throw new ServiceException();
        }


    }
    public List<Reservation> findAll() throws ServiceException {

        try{
            return reservationDao.findAll();}
        catch (DaoException e) {

            throw new ServiceException();
        }

    }

    public List<Reservation> findResaByVehicleId(long vehicleId) throws ServiceException {

        try{
            return reservationDao.findResaByVehicleId(vehicleId);}
        catch (DaoException e) {

            throw new ServiceException();
        }

    }


    public Reservation findResaById(long reservationId) throws ServiceException {

        try{
            return reservationDao.findResaById(reservationId);}
        catch (DaoException e) {

            throw new ServiceException();
        }

    }
    public List<Reservation> findResaByClientId(long clientId) throws ServiceException {

        try{
            return reservationDao.findResaByClientId(clientId);}
        catch (DaoException e) {

            throw new ServiceException();
        }

    }

    public List<Vehicle> findVehiclesByClientId(long clientId) throws ServiceException {

        try{
            return reservationDao.findVehiclesByClientId(clientId);}
        catch (DaoException e) {

            throw new ServiceException();
        }

    }
    public int CountReservation() {

        return this.reservationDao.CountReservation();

    }


}

