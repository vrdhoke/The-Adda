package com.me.myapp.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="venuetable")
public class Venue {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name="venueId")
	private int id;
	
	
	private String location;
	
	private String transportation;
	
	private String rooms;

	@ElementCollection(fetch = FetchType.EAGER)
	@Column(name="bookeddate")
	private Set<Date> bookedDates = new HashSet<Date>();
	
	@ManyToOne
//	@JoinColumn(name="ownerId", nullable=false)
//	@JoinColumn(name="email", nullable=false)
	private Owner owner;
	
	@OneToMany(mappedBy = "venue",fetch = FetchType.EAGER)
	private Set<Event> events = new HashSet<Event>();


	public String getTransportation() {
		return transportation;
	}


	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}


	public String getRooms() {
		return rooms;
	}


	public void setRooms(String rooms) {
		this.rooms = rooms;
	}
	
	public Owner getOwner() {
		return owner;
	}


	public void setOwner(Owner owner) {
		this.owner = owner;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}



	public Set<Event> getEvents() {
		return events;
	}


	public void setEvents(Set<Event> events) {
		this.events = events;
	}


	public Set<Date> getBookedDates() {
		return bookedDates;
	}


	public void setBookedDates(Set<Date> bookedDates) {
		this.bookedDates = bookedDates;
	}
	
	public void addBookedDate(Date d) {
		bookedDates.add(d);
	}
	
	public void removeBookedDate(Date d) {
		bookedDates.remove(d);
	}
	
	
}
