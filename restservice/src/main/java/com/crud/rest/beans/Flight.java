package com.crud.rest.beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name="flight")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })//very imp
public class Flight {
	@Id
	@GeneratedValue
	@Column
	private int flightID;
	@Column
	@NotEmpty
	private String departTime;
	@Column
	@NotEmpty
	private String origin;
	@Column
	@NotEmpty
	private String destination;
	@Column
	private int numberOfSeats;
	@Column
	private int price;
	@OneToMany(fetch=FetchType.EAGER)
	@OrderBy("flightID")
	@JoinColumn(name="flightID")
	@Fetch(value = FetchMode.SUBSELECT)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Ticket_detail> ticket_details;
	
	

	public Flight(int flightID, String departTime, String origin, String destination, int numberOfSeats, int price,
			List<Ticket_detail> ticket_details) {
		super();
		this.flightID = flightID;
		this.departTime = departTime;
		this.origin = origin;
		this.destination = destination;
		this.numberOfSeats = numberOfSeats;
		this.price = price;
		this.ticket_details = ticket_details;
	}

	public List<Ticket_detail> getTicket_details() {
		return ticket_details;
	}

	public void setTicket_details(List<Ticket_detail> ticket_details) {
		this.ticket_details = ticket_details;
	}

	public Flight() {}
	
	public int getFlightID() {
		return flightID;
	}
	public void setFlightID(int flightID) {
		this.flightID = flightID;
	}
	public String getDepartTime() {
		return departTime;
	}
	public void setDepartTime(String departTime) {
		this.departTime = departTime;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getNumberOfSeats() {
		return numberOfSeats;
	}
	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
