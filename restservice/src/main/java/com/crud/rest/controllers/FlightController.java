package com.crud.rest.controllers;

import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.crud.rest.beans.Flight;
import com.crud.rest.service.FlightService;


@RestController // combination of @Controller and @ResponseBody annotations
public class FlightController {
	@Autowired
	private FlightService flightService;
	//setter for customerService
	public void setFlightService(FlightService flightService) {
		this.flightService = flightService;
	}
	
	// Add Flight
		@RequestMapping(value = "/flight/new", method = RequestMethod.POST)
		public ResponseEntity<Void> addCustomer(@RequestBody Flight flight, UriComponentsBuilder ucb) {
			if (flightService.isFlightExist(flight)) {
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			} else {

				flightService.saveFlight(flight);
				HttpHeaders headers = new HttpHeaders();
				headers.setLocation(ucb.path("/flight/{flightID}").buildAndExpand(flight.getFlightID()).toUri());
				return new ResponseEntity<Void>(HttpStatus.CREATED);
			}
		}

		// Get Single Flight
		@RequestMapping(value = "/flight/{flightID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Flight> getFlight(@PathVariable("flightID") int flightID) {

			Flight flight = flightService.findById(flightID);
			if (flight == null) {
				return new ResponseEntity<Flight>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Flight>(flight, HttpStatus.OK);
		}
		//
		@RequestMapping(value = "/flight/search/{seat}/{seat1}/{origin}/{destination}/{departTime}", method = RequestMethod.GET)
		public ResponseEntity<List<Flight>> searchFlight(@PathVariable("seat") Integer seat,@PathVariable("seat1") Integer seat1,@PathVariable("origin") String origin,@PathVariable("destination") String destination,@PathVariable("departTime") Date departTime) {
			List<Flight> flight = flightService.getListdk(seat,seat1, origin, destination, departTime);
			if (flight.isEmpty()) {
				return new ResponseEntity<List<Flight>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<Flight>>(flight, HttpStatus.OK);
		}
		// Get All Flights
		@RequestMapping(value = "/flights", method = RequestMethod.GET)
		public ResponseEntity<List<Flight>> listAllFlights() {
			List<Flight> flights = flightService.findAllFlights();
			if (flights.isEmpty()) {
				return new ResponseEntity<List<Flight>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Flight>>(flights, HttpStatus.OK);
		}

		// Update Customer
		@RequestMapping(value = "/flight/{flightID}", method = RequestMethod.PUT)
		public ResponseEntity<Flight> updateFlight(@PathVariable("flightID") int flightID, @RequestBody Flight fli) {

			Flight flight = flightService.findById(flightID);
			if (flight == null) {
				return new ResponseEntity<Flight>(HttpStatus.NOT_FOUND);
			}

			flight.setDepartTime(fli.getDepartTime());
			flight.setOrigin(fli.getOrigin());
			flight.setDestination(fli.getDestination());
			flight.setNumberOfSeats(fli.getNumberOfSeats());
			flight.setPrice(fli.getPrice());
			
			flightService.updateFlight(flight);
			return new ResponseEntity<Flight>(flight, HttpStatus.OK);
		}

		// Delete Customer
		@RequestMapping(value = "/flight/{flightID}", method = RequestMethod.DELETE)
		public ResponseEntity<Flight> deleteFlight(@PathVariable("flightID") int flightID) {

			Flight flight = flightService.findById(flightID);
			if (flight == null) {
				return new ResponseEntity<Flight>(HttpStatus.NOT_FOUND);
			}

			flightService.deleteFlightById(flightID);
			return new ResponseEntity<Flight>(HttpStatus.NO_CONTENT);
		}


}
