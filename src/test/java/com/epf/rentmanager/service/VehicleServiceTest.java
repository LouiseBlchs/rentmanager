package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VehicleServiceTest {
    @InjectMocks
    private VehicleService vehicleService;

    @Mock
    private VehicleDao vehicleDao;
    @Mock
    private Vehicle vehicle;
    @Test
    void findAll_should_fail_when_dao_throws_exception() throws DaoException {
        // When
        when(this.vehicleDao.findAll()).thenThrow(DaoException.class);

        // Then
        assertThrows(ServiceException.class, () -> vehicleService.findAll());

    }


    @Test
    void findById_should_fail_when_dao_throws_exception() throws DaoException {
        // When
        when(this.vehicleDao.findById(vehicle.getVehicle_id())).thenThrow(DaoException.class);

        // Then
        assertThrows(ServiceException.class, () -> vehicleService.findById(vehicle.getVehicle_id()));

    }

    @Test
    void create_should_fail_when_dao_throws_exception() throws DaoException {
        // When
        when(this.vehicleDao.create(vehicle)).thenThrow(DaoException.class);

        // Then
        assertThrows(ServiceException.class, () -> vehicleService.create(vehicle));

    }

}
