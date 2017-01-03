package com.crud.rest.beans;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name="ticket_detail")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })//very imp
public class Ticket_detail implements Serializable {
	 
	private static final long serialVersionUID = -5527566248002296042L;
	@Id
	@GeneratedValue
	@Column
	private int ticket_detailID;
	@Column
	private int flightID;
	@Column
	private int customerID;

	@Column
	private int totalclient;
	@Column
	private String totalprice;
	@Column
	private String totaltax;
	@Column
	private boolean confirm;
	@Column
	private String time;
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="ticket_detailID")
	@OrderBy("ticket_detailID")
	@LazyCollection(LazyCollectionOption.FALSE)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Ticket> tickets;
	


	

	public List<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	
	
	
	public Ticket_detail(int ticket_detailID, int flightID, int customerID, int totalclient, String totalprice,
			String totaltax, boolean confirm, String time, List<Ticket> tickets) {
		super();
		this.ticket_detailID = ticket_detailID;
		this.flightID = flightID;
		this.customerID = customerID;
		this.totalclient = totalclient;
		this.totalprice = totalprice;
		this.totaltax = totaltax;
		this.confirm = confirm;
		this.time = time;
		this.tickets = tickets;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public boolean isConfirm() {
		return confirm;
	}
	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}
	public int getFlightID() {
		return flightID;
	}
	public void setFlightID(int flightID) {
		this.flightID = flightID;
	}
	public int getTicket_detailID() {
		return ticket_detailID;
	}
	public void setTicket_detailID(int ticket_detailID) {
		this.ticket_detailID = ticket_detailID;
	}

	public Ticket_detail() {
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public int getTotalclient() {
		return totalclient;
	}
	public void setTotalclient(int totalclient) {
		this.totalclient = totalclient;
	}
	public String getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}
	public String getTotaltax() {
		return totaltax;
	}
	public void setTotaltax(String totaltax) {
		this.totaltax = totaltax;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
