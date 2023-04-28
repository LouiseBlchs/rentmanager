package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RentServiceTest {
    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private ReservationDao reservationDao;
    @Mock
    private Reservation reservation;
    @Test
    void findAll_should_fail_when_dao_throws_exception() throws DaoException {
        // When
        when(this.reservationDao.findAll()).thenThrow(DaoException.class);

        // Then
        assertThrows(ServiceException.class, () -> reservationService.findAll());

    }


    @Test
    void findById_should_fail_when_dao_throws_exception() throws DaoException {
        // When
        when(this.reservationDao.findResaById(reservation.getReservation_id())).thenThrow(DaoException.class);

        // Then
        assertThrows(ServiceException.class, () -> reservationService.findResaById(reservation.getReservation_id()));

    }

    @Test
    void create_should_fail_when_dao_throws_exception() throws DaoException {
        // When
        when(this.reservationDao.create(reservation)).thenThrow(DaoException.class);

        // Then
        assertThrows(ServiceException.class, () -> reservationService.create(reservation));

    }

}
