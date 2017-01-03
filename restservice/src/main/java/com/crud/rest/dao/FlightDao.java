package com.crud.rest.dao;

import java.sql.Date;
import java.util.List;

import com.crud.rest.beans.Flight;

public interface FlightDao {

	Flight findById(int flightID);

	List<Flight> findByLocal(String origin,String destination);
	Flight findByLocalandTime(String origin,String destination,String departTime);
	List<Flight> getList();
	List<Flight> getListdk(Integer seat,Integer seat1,String origin, String destination, Date departTime);
	void saveFlight(Flight flight);

	void updateFlight(Flight flight);

	void deleteFlightById(int flightID);

	List<Flight> findAllFlights();

	void deleteAllFlights();

	boolean isFlightExist(Flight flight);
}

