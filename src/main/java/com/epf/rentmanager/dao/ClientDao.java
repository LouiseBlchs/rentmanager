package com.epf.rentmanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;


@Repository
public class ClientDao {


    private ClientDao() {
    }


    private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
    private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
    private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
    private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
    private static final String COUNT_CLIENTS_QUERY = "SELECT COUNT(id) AS count FROM Client;";
    private static final String EDIT_CLIENT_QUERY = "UPDATE Client SET nom = ?, prenom = ?, email = ?, naissance = ? WHERE id = ?;";

    public long create(Client client) throws DaoException {

        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE_CLIENT_QUERY, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, client.getNom());
            statement.setString(2, client.getPrenom());
            statement.setString(3, client.getEmail());
            statement.setDate(4, Date.valueOf(client.getNaissance()));
            statement.execute();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                return id;
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
        return 0;
    }

    public long delete(Client client) throws DaoException {
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_CLIENT_QUERY);
            statement.setLong(1, client.getClient_id());
            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }

        return 0;

    }

    public Optional<Client> findById(long id) throws DaoException {

        try {
            Client client;
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_CLIENT_QUERY);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();

            rs.next();
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String email = rs.getString("email");
            LocalDate naissance = rs.getDate("naissance").toLocalDate();

            client = new Client(id, nom, prenom, email, naissance);
            statement.close();
            connection.close();
            return Optional.of(client);


        } catch (SQLException e) {
            e.printStackTrace();

        }

        return Optional.empty();
    }

    public List<Client> findAll() throws DaoException {

        List<Client> clients = new ArrayList<>();
        try {
            Connection connection = ConnectionManager.getConnection();
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(FIND_CLIENTS_QUERY);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String email = rs.getString("email");
                LocalDate naissance = rs.getDate("naissance").toLocalDate();

                clients.add(new Client(id, nom, prenom, email, naissance));


            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }

        return clients;


    }

    public static int CountClient() {
        int n = 0;
        try {

            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(COUNT_CLIENTS_QUERY);

            ResultSet rs = statement.executeQuery();
            rs.next();
            n = rs.getInt("count");

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return n;

    }

    public long edit(Client client) throws DaoException {

        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(EDIT_CLIENT_QUERY);
            statement.setString(1, client.getNom());
            statement.setString(2, client.getPrenom());
            statement.setString(3, client.getEmail());
            statement.setDate(4, Date.valueOf(client.getNaissance()));
            statement.setLong(5, client.getClient_id());
            statement.executeUpdate();


            statement.close();
            connection.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }


}
