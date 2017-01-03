package com.crud.rest.service;

import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.crud.rest.beans.Flight;
import com.crud.rest.dao.FlightDao;

public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightDao flightDao;
	//setter for customerDao
		public void setFlightDao(FlightDao flightDao) {
			this.flightDao = flightDao;
		}
	@Override
	public Flight findById(int flightID) {
		return flightDao.findById(flightID);
	}

	@Override
	public List<Flight> findByLocal(String origin, String destination) {
		return flightDao.findByLocal(origin, destination);
	}

	@Override
	public Flight findByLocalandTime(String origin, String destination, String departTime) {
		return flightDao.findByLocalandTime(origin, destination, departTime);
	}

	@Override
	public void saveFlight(Flight flight) {
		flightDao.saveFlight(flight);

	}

	@Override
	public void updateFlight(Flight flight) {
		flightDao.updateFlight(flight);

	}

	@Override
	public void deleteFlightById(int flightID) {
		flightDao.deleteFlightById(flightID);
	}

	@Override
	public List<Flight> findAllFlights() {
		return flightDao.findAllFlights();
	}

	@Override
	public void deleteAllFlights() {
		flightDao.deleteAllFlights();

	}

	@Override
	public boolean isFlightExist(Flight flight) {
		return flightDao.isFlightExist(flight);
	}
	@Override
	public List<Flight> getList() {
		// TODO Auto-generated method stub
		return flightDao.getList();
	}
	@Override
	public List<Flight> getListdk(Integer seat,Integer seat1,String origin, String destination, Date departTime) {
		// TODO Auto-generated method stub
		return flightDao.getListdk(seat,seat1, origin, destination, departTime);
	}

}
