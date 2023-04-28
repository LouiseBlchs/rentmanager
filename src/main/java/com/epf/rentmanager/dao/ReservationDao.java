package com.epf.rentmanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationDao {




	private final ClientDao clientDao;
	private final VehicleDao vehicleDao;

	private ReservationDao(ClientDao clientDao,VehicleDao vehicleDao) {
		this.clientDao=clientDao;
		this.vehicleDao=vehicleDao;
	}

	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, client_id,vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_VEHICLES_BY_CLIENT_ID_QUERY = "SELECT DISTINCT Vehicle.id,constructeur,modele,nb_places FROM Reservation INNER JOIN Client ON Reservation.client_id = Client.id INNER JOIN Vehicle ON Vehicle.id = Reservation.vehicle_id WHERE Client.id=?;";
	private static final String FIND_RESERVATION_BY_ID_QUERY = "SELECT id, client_id, vehicle_id,debut, fin FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
	private static final String COUNT_RESERVATIONS_QUERY = "SELECT COUNT(id) AS count FROM Reservation;";
	private static final String EDIT_RESERVATION_QUERY = "UPDATE Reservation SET client_id = ?, vehicle_id = ?, debut = ?, fin = ? WHERE id = ?;";
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
			statement.close();
			connection.close();
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
			statement.close();
			connection.close();

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
				int client_id= rs.getInt("client_id");
				int vehicle_id= rs.getInt("vehicle_id");
				LocalDate debut=rs.getDate("debut").toLocalDate();
				LocalDate fin=rs.getDate("fin").toLocalDate();

				reservations.add(new Reservation(id,client_id,vehicle_id,debut,fin));

			}
			statement.close();
			connection.close();
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
				int client_id= rs.getInt("client_id");
				int vehicle_id= rs.getInt("vehicle_id");
				LocalDate debut=rs.getDate("debut").toLocalDate();
				LocalDate fin=rs.getDate("fin").toLocalDate();

				reservations.add(new Reservation(id,client_id,vehicle_id,debut,fin));

			}
			statement.close();
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
		return reservations;
	}


	public List<Vehicle> findVehiclesByClientId(long clientId) throws DaoException {

		List<Vehicle> vehicles= new ArrayList<>();
		try{
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement= connection.prepareStatement(FIND_VEHICLES_BY_CLIENT_ID_QUERY);
			statement.setLong(1,clientId);
			ResultSet rs= statement.executeQuery();

			while (rs.next()){
				int id= rs.getInt("vehicle.id");
				String constructeur = rs.getString("constructeur");
				String modele = rs.getString("modele");
				int nb_places = rs.getInt("nb_places");

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

	public Reservation findResaById(long ReservationId) throws DaoException {


		Reservation reservation = null;
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(FIND_RESERVATION_BY_ID_QUERY);
			statement.setLong(1, ReservationId);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");

				int client_id = rs.getInt("client_id");
				int vehicle_id = rs.getInt("vehicle_id");
				LocalDate debut = rs.getDate("debut").toLocalDate();
				LocalDate fin = rs.getDate("fin").toLocalDate();

				reservation = new Reservation(id, client_id, vehicle_id, debut, fin);

			}
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return reservation;
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
				LocalDate debut=rs.getDate("debut").toLocalDate();
				LocalDate fin=rs.getDate("fin").toLocalDate();

				reservations.add(new Reservation(id,client_id,vehicle_id,debut,fin));

			}
			statement.close();
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
		return reservations;
	}


	public static int CountReservation(){
		int n = 0;
		try {

			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(COUNT_RESERVATIONS_QUERY);

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

	public long edit(Reservation reservation) throws DaoException {

		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(EDIT_RESERVATION_QUERY);

			statement.setLong(1,reservation.getClient_id());
			statement.setLong(2,reservation.getVehicle_id());
			statement.setDate(3, Date.valueOf(reservation.getDebut()));
			statement.setDate(4, Date.valueOf(reservation.getFin()));
			statement.setLong(5,reservation.getReservation_id());
			statement.executeUpdate();
			statement.close();
			connection.close();

		}catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
		return 0;
	}


}
