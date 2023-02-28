package com.epf.rentmanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

public class ReservationDao {

	private static ReservationDao instance = null;
	private ReservationDao() {}
	public static ReservationDao getInstance() {
		if(instance == null) {
			instance = new ReservationDao();
		}
		return instance;
	}
	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
		
	public long create(Reservation reservation) throws DaoException {

		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(CREATE_RESERVATION_QUERY,Statement.RETURN_GENERATED_KEYS);

			statement.setLong(1,reservation.getClient_id());
			statement.setLong(2,reservation.getVehicle_id());
			statement.setDate(3, Date.valueOf(reservation.getDebut()));
			statement.setDate(4, Date.valueOf(reservation.getFin()));
			statement.execute();

			ResultSet resultSet = statement.getGeneratedKeys();

			if (resultSet.next()) {int id = resultSet.getInt(1); return id;}

		}catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
		return 0;
	}
	
	public long delete(Reservation reservation) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_RESERVATION_QUERY);
			statement.setLong(1, reservation.getReservation_id());
			statement.executeUpdate();


		}catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}

		return 0;
	}

	
	public List<Reservation> findResaByClientId(long clientId) throws DaoException {

		List<Reservation>reservations= new ArrayList<>();
		try{
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement= connection.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
			statement.setLong(1,clientId);
			ResultSet rs= statement.executeQuery();

			while (rs.next()){
				int id= rs.getInt("id");
				Client client;
				Vehicle vehicle;
				int vehicle_id= rs.getInt("vehicle_id");
				try {
					client= ClientService.instance.findById(clientId);
				} catch (ServiceException e) {
					throw new RuntimeException(e);
				}
				try {
					vehicle= VehicleService.instance.findById(vehicle_id);
				} catch (ServiceException e) {
					throw new RuntimeException(e);
				}
				LocalDate debut=rs.getDate("début").toLocalDate();
				LocalDate fin=rs.getDate("fin").toLocalDate();

				reservations.add(new Reservation(id,client,vehicle,debut,fin));

			}
		}catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
		return reservations;


	}
	
	public List<Reservation> findResaByVehicleId(long vehicleId) throws DaoException {

		List<Reservation>reservations= new ArrayList<>();
		try{
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement= connection.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
			statement.setLong(1,vehicleId);
			ResultSet rs= statement.executeQuery();

			while (rs.next()){
				int id= rs.getInt("id");
				Client client;
				Vehicle vehicle;
				int client_id= rs.getInt("client_id");
				try {
					client= ClientService.instance.findById(client_id);
				} catch (ServiceException e) {
					throw new RuntimeException(e);
				}
				try {
					vehicle= VehicleService.instance.findById(vehicleId);
				} catch (ServiceException e) {
					throw new RuntimeException(e);
				}
				LocalDate debut=rs.getDate("début").toLocalDate();
				LocalDate fin=rs.getDate("fin").toLocalDate();

				reservations.add(new Reservation(id,client,vehicle,debut,fin));

			}
		}catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
		return reservations;
	}

	public List<Reservation> findAll() throws DaoException {

		List<Reservation>reservations= new ArrayList<>();
		try{
			Connection connection = ConnectionManager.getConnection();
			Statement statement= connection.createStatement();

			ResultSet rs= statement.executeQuery(FIND_RESERVATIONS_QUERY);

			while (rs.next()){
				int id= rs.getInt("id");
				int client_id= rs.getInt("client_id");
				int vehicle_id= rs.getInt("vehicle_id");
				 Client client;
				Vehicle vehicle;
				try {
					client= ClientService.instance.findById(client_id);
				} catch (ServiceException e) {
					throw new RuntimeException(e);
				}

				try {
					vehicle= VehicleService.instance.findById(vehicle_id);
				} catch (ServiceException e) {
					throw new RuntimeException(e);
				}
				LocalDate debut=rs.getDate("début").toLocalDate();
				LocalDate fin=rs.getDate("fin").toLocalDate();

				reservations.add(new Reservation(id,client,vehicle,debut,fin));

			}
		}catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
		return reservations;
	}
}
