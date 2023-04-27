package com.epf.rentmanager.util;

import com.epf.rentmanager.model.Client;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class UsersTest {
    @Test
    void isLegal_should_return_true_when_age_is_over_18() {
        // Given
        Client legalUser = new Client("John", "Doe", "john.doe@ensta.fr", LocalDate.of(2000, 1, 8));

        // Then
        assertTrue(Users.isLegal(legalUser));
    }

    @Test
    void isLegal_should_return_false_when_age_is_under_18() {
        // Given
        Client illegaUser = new Client("John", "Doe", "john.doe@ensta.fr",LocalDate.of(2005, 1, 8));

        // Then
        assertFalse(Users.isLegal(illegaUser));
    }
}

