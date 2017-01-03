package com.crud.rest.beans;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name="ticket")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })//very imp
public class Ticket {
	@Id
	@GeneratedValue
	@Column
	private int ticketID;
	@Column
	private int ticket_detailID;
	@Column
	private int typeID;
	@Column
	@NotNull
	private String name;
	public int getTicket_detailID() {
		return ticket_detailID;
	}
	public void setTicket_detailID(int ticket_detailID) {
		this.ticket_detailID = ticket_detailID;
	}
	
	public int getTicketID() {
		return ticketID;
	}
	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	
}
