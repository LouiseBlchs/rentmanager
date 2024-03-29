package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.epf.rentmanager.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @InjectMocks
    private ClientService userService;

    @Mock
    private ClientDao userDao;
    @Mock
    private Client client;
    @Test
    void findAll_should_fail_when_dao_throws_exception() throws DaoException {
        // When
        when(this.userDao.findAll()).thenThrow(DaoException.class);

        // Then
        assertThrows(ServiceException.class, () -> userService.findAll());

    }


    @Test
    void findById_should_fail_when_dao_throws_exception() throws DaoException {
        // When
        when(this.userDao.findById(client.getClient_id())).thenThrow(DaoException.class);

        // Then
        assertThrows(ServiceException.class, () -> userService.findById(client.getClient_id()));

    }


    @Test
    void create_should_fail_when_dao_throws_exception() throws DaoException {
        // When
        when(this.userDao.create(client)).thenThrow(DaoException.class);

        // Then
        assertThrows(ServiceException.class, () -> userService.create(client));

    }
}
