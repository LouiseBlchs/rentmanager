package com.epf.rentmanager.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleDao {
	

	private VehicleDao() {}

	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur,modele, nb_places) VALUES(?,?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur,modele, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur,modele, nb_places FROM Vehicle;";
	private static final String COUNT_VEHICLES_QUERY = "SELECT COUNT(id) AS count FROM Vehicle;";
	private static final String EDIT_VEHICLE_QUERY = "UPDATE Vehicle SET constructeur = ?, modele = ?, nb_places = ? WHERE id = ?;";
	public long create(Vehicle vehicle) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(CREATE_VEHICLE_QUERY,Statement.RETURN_GENERATED_KEYS);
			statement.setString(1,vehicle.getConstructeur());
			statement.setString(2,vehicle.getModele());
			statement.setInt(3,vehicle.getNb_places());

			statement.execute();

			ResultSet resultSet = statement.getGeneratedKeys();

			if (resultSet.next()) {int id = resultSet.getInt(1); return id;}

			statement.close();
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
		return 0;
	}

	public long delete(Vehicle vehicle) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_VEHICLE_QUERY);
			statement.setLong(1, vehicle.getVehicle_id());
			statement.executeUpdate();
			statement.close();
			connection.close();

		}catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}

		return 0;
	}

	public Vehicle findById(long id) throws DaoException {

		Vehicle vehicle = null;
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(FIND_VEHICLE_QUERY);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			rs.next();
			String constructeur = rs.getString("constructeur");
			String modele = rs.getString("modele");
			int nb_places = rs.getInt("nb_places");

			vehicle = new Vehicle(id, constructeur, modele, nb_places);
			statement.close();
			connection.close();


		} catch (SQLException e) {
			e.printStackTrace();

		}

		return vehicle;
	}

	public List<Vehicle> findAll() throws DaoException {
		List<Vehicle> vehicles= new ArrayList<>();
		try{
			Connection connection = ConnectionManager.getConnection();
			Statement statement= connection.createStatement();

			ResultSet rs= statement.executeQuery(FIND_VEHICLES_QUERY);

			while (rs.next()){
				int id= rs.getInt("id");
				String constructeur=rs.getString("constructeur");
				String modele=rs.getString("modele");
				int nb_places=rs.getInt("nb_places");


				vehicles.add(new Vehicle(id,constructeur,modele,nb_places));

			}
			statement.close();
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}

		return vehicles;
		
	}


	public static int CountVehicle(){
		int n = 0;
		try {

			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(COUNT_VEHICLES_QUERY);

			ResultSet rs = statement.executeQuery();
			rs.next();
			n = rs.getInt("count");
			statement.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return n;

	}

	public long edit(Vehicle vehicle) throws DaoException {

		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(EDIT_VEHICLE_QUERY);
			statement.setString(1,vehicle.getConstructeur());
			statement.setString(2,vehicle.getModele());
			statement.setInt(3,vehicle.getNb_places());
			statement.setLong(4, vehicle.getVehicle_id());
			statement.executeUpdate();
			statement.close();
			connection.close();


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

}
