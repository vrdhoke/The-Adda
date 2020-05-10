package com.me.myapp.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Table(name="usertable")
public class User implements Serializable{
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name="userId")
	private int id;
	

	@Column(name="username", nullable=false)
	@NotNull
	private String name;
	
	@Column(unique = true)
	private String email;
	
	
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usertable_eventtable", joinColumns = { @JoinColumn(name = "userid")}, inverseJoinColumns = { @JoinColumn(name = "eventId") })
	private Set<Event> events_registered = new HashSet<Event>();
	
	@OneToMany(mappedBy = "organizer",fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	private Set<Event> events_created = new HashSet<Event>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Event> getEvents_registered() {
		return events_registered;
	}

	public void setEvents_registered(Set<Event> events_registered) {
		this.events_registered = events_registered;
	}

	public Set<Event> getEvents_created() {
		return events_created;
	}

	public void setEvents_created(Set<Event> events_created) {
		this.events_created = events_created;
	}

	public void addCreatedEvent(Event e) {
		events_created.add(e);
	}
	
	public void removeCreatedEvent(Event e) {
		events_created.remove(e);
	}
	
	public void addRegisteredEvent(Event e) {
		events_registered.add(e);
	}
	
	public void removeRegisteredEvent(Event e) {
		events_registered.remove(e);
	}
}
