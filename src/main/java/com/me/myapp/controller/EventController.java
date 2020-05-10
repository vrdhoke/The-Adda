package com.me.myapp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.myapp.dao.EventDAO;
import com.me.myapp.dao.OwnerDAO;
import com.me.myapp.dao.UserDAO;
import com.me.myapp.dao.VenueDAO;
import com.me.myapp.pojo.Event;
import com.me.myapp.pojo.Owner;
import com.me.myapp.pojo.User;
import com.me.myapp.pojo.Venue;

import javassist.Loader.Simple;

@Controller
public class EventController {

private static final Logger logger = LoggerFactory.getLogger(EventController.class);

	@RequestMapping(value = "/createevent", method = RequestMethod.POST)
	public ModelAndView register(Locale locale, Model model,HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		ModelAndView mv = null;
		
		String venueid = request.getParameter("venueId");
		
		int vid = Integer.parseInt(venueid); 
		VenueDAO ed = new VenueDAO();
		Venue venue = ed.getVenuebyId(vid);
		
		mv = new ModelAndView("createevent", "venue",venue);
		
		return mv;
	}


	@RequestMapping(value = "/addeeventdetails", method = RequestMethod.POST)
	public ModelAndView addevent(Locale locale, Model model,HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		HttpSession session = request.getSession();
		User usersess = (User)session.getAttribute("sessionUser");
		
		
		
		
		ModelAndView mv = null;
		Date eventdate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat tf = new SimpleDateFormat("yyyy-mm-dd");
		String eventname = request.getParameter("eventname");
		String details = request.getParameter("details");
		int capacity = Integer.parseInt(request.getParameter("capacity"));
		try {
			eventdate = sdf.parse(request.getParameter("eventdate"));
			System.out.println("Date from request"+eventdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
        java.sql.Date sqlDate = new java.sql.Date(eventdate.getTime());
        System.out.println("Date after conversion"+sqlDate);
		String venueid = request.getParameter("venueid");
		int vid = Integer.parseInt(venueid);
		
		VenueDAO vd = new VenueDAO();
		Venue venue = vd.getVenuebyId(vid);
		
		
		Set<Date> bookeddates = venue.getBookedDates();
		boolean flag = true;
		for(Date d:bookeddates) {
			
			String dt = tf.format(d);
			System.out.println("BookedDate "+d+" Event Date "+eventdate);
			if(dt.equals(tf.format(eventdate))) {
				flag = false;
				break;
			}
		}
		
		
		if(flag) {
		EventDAO ed = new EventDAO();
		Event event = null;
		List<Event> events = new ArrayList<Event>();
		try {
			event = ed.addEvent(eventname,details, eventdate, capacity, venue, usersess);
			usersess.addCreatedEvent(event);
			
			venue.addBookedDate(eventdate);
			vd.update(venue);
			events = ed.getEvents(usersess);
			mv = new ModelAndView("usercreatedevents", "events",events);
			model.addAttribute("success", "Event Created Successfully");
			EmailController.sendEmail("theadda2020@gmail.com", "vaibhavdhoke1@gmail.com", "Event Added Successfully by "
			+usersess.getName()+" at "+venue.getLocation()+"and Event Name is "+eventname+" , Event Date is "+event.getEvent_date(), "Event Added");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mv = new ModelAndView("usercreatedevents", "events",events);
			model.addAttribute("msg", "Event can not be added at this time");
		}
		}else {
		
		
			mv = new ModelAndView("createevent","booked", "Venue is not available at this date");
			//model.addAttribute("Message", "Event Created Successfully");
		}
		return mv;
	}
	
	@RequestMapping(value = "/usercreatedevents", method = RequestMethod.GET)
	public ModelAndView usercreatedevents(Locale locale, Model model,HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		ModelAndView mv = null;
		
		
		
		HttpSession session = request.getSession();
		User usersess = (User)session.getAttribute("sessionUser");
		EventDAO ed = new EventDAO();
		
		List<Event> events = new ArrayList<Event>();
		events = ed.getEvents(usersess);
		mv = new ModelAndView("usercreatedevents", "events",events);
		
		return mv;
	}
	
	
	@RequestMapping(value = "/viewallevents", method = RequestMethod.GET)
	public ModelAndView viewallevents(Locale locale, Model model,HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		ModelAndView mv = null;
		
		HttpSession session = request.getSession();
		User usersess = (User)session.getAttribute("sessionUser");
		EventDAO ed = new EventDAO();
		
		List<Event> events = new ArrayList<Event>();
		events = ed.getAllEvents();
		
		List<Event> sevents = new ArrayList<Event>(); 
		
		for(Event e:events) {
			System.out.println("status "+e.getStatus());
			if(e.getStatus().equalsIgnoreCase("Scheduled")) {
				sevents.add(e);
			}
		}
		mv = new ModelAndView("allevents", "events",sevents);
		
		return mv;
	}
	
	
	@RequestMapping(value = "/registerevent", method = RequestMethod.POST)
	public ModelAndView registerforevent(Locale locale, Model model,HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		ModelAndView mv = null;
		
		HttpSession session = request.getSession();
		User usersess = (User)session.getAttribute("sessionUser");
		
		
		
		int eventid = Integer.parseInt(request.getParameter("eventid"));
		UserDAO ud = new UserDAO();
		EventDAO ed = new EventDAO();
		
		
		
		
		Event event = ed.getEventbyid(eventid);
		Set<Event> events = new HashSet<Event>();
		events = usersess.getEvents_registered();
		System.out.println("Value of If statement ="+events.contains(event));
		boolean flag = false;
		for(Event e:events) {
			if(e.getId()==eventid) {
				flag = true;
			}
		}
		if(flag) {
			mv = new ModelAndView("registeredevents", "events",events);
			model.addAttribute("registered", "Event is already registered");
		}else {
			event.setSeats_available(event.getSeats_available()-1);
			
			usersess.addRegisteredEvent(event);
			event.addUser(usersess);
		
		try {
			ud.update(usersess);
			ed.updateEvent(event);
			System.out.println("Size of registered set"+usersess.getEvents_registered().size());
			
			events = usersess.getEvents_registered();
		  	mv = new ModelAndView("registeredevents", "events",events);
		  	model.addAttribute("registered", "Event registered successfully");
		  	EmailController.sendEmail("theadda2020@gmail.com", "vaibhavdhoke1@gmail.com", "Event Registered Successfully by "
		  	+usersess.getName()+" for "+event.getEventname()+" at "+event.getLocation(), "Event Registered");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			events = usersess.getEvents_registered();
			e.printStackTrace();
			mv = new ModelAndView("registeredevents", "events",events);
			model.addAttribute("registered", "Event is already registered");
		}
		
		}
//		ed.updateEvent(event);
		
		
		
		return mv;
	}
	
	@RequestMapping(value = "/userregisteredevents", method = RequestMethod.GET)
	public ModelAndView userregisteredevents(Locale locale, Model model,HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		ModelAndView mv = null;
		
		HttpSession session = request.getSession();
		User usersess = (User)session.getAttribute("sessionUser");
		EventDAO ed = new EventDAO();
		
		Set<Event> events = new HashSet<Event>();
		events = usersess.getEvents_registered();
		//List<Event> events = ed.getEvents(usersess);
		
		
		mv = new ModelAndView("registeredevents", "events",events);
		
		return mv;
	}
	
	
	
	@RequestMapping(value = "/updateevent", method = RequestMethod.POST)
	public ModelAndView updateevent(Locale locale, Model model,HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		ModelAndView mv = null;
		
		int eid = Integer.parseInt(request.getParameter("eventid"));
		
		EventDAO ed = new EventDAO();
		Event event = ed.getEventbyid(eid);
		
		SimpleDateFormat tf = new SimpleDateFormat("MM/dd/yyyy");
		String d = tf.format(event.getEvent_date());
		mv = new ModelAndView("updateevent", "event",event);
		model.addAttribute("evdate", d);
		
		return mv;
	}
	
	
	@RequestMapping(value = "/updateeventdetails", method = RequestMethod.POST )
	public ModelAndView updateeventdetails(Locale locale, Model model,HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		ModelAndView mv = null;
		
		HttpSession session = request.getSession();
		User usersess = (User)session.getAttribute("sessionUser");
		String action = request.getParameter("submit");
		int eid = Integer.parseInt(request.getParameter("eventid"));
		EventDAO ed = new EventDAO();
		VenueDAO vd = new VenueDAO();
		
		if(action.equalsIgnoreCase("Update")) {
			
			try {
			System.out.println("Before Param");
			String ename = request.getParameter("eventname");
			String details = request.getParameter("details");
			int capacity = Integer.parseInt(request.getParameter("capacity"));
			System.out.println("After capacity");
			String status = request.getParameter("status");
			System.out.println("After status");
			
			
			System.out.println("After Param");
			Date eventdate = null;
			Date oldeventdate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			eventdate = sdf.parse(request.getParameter("eventdate"));
			oldeventdate = sdf.parse(request.getParameter("oldeventdate"));
			java.sql.Date sqlDate = new java.sql.Date(eventdate.getTime());
			System.out.println("After Date");
			
			Event event = ed.getEventbyid(eid);
			event.setEventname(ename);
			event.setDetails(details);
			event.setCapacity(capacity);
			event.setEvent_date(eventdate);
			event.setStatus(status);
			Venue venue = vd.getVenuebyId(event.getVenue().getId());
			
			Calendar c = Calendar.getInstance(); 
			c.setTime(oldeventdate); 
			c.add(Calendar.DATE, 1);
			oldeventdate = c.getTime();
			
			if(venue.getBookedDates().contains(eventdate)) {
				mv = new ModelAndView("updateevent", "event",event);
				model.addAttribute("booked", "Venue is not available at this date");
				
			}else {
					venue.removeBookedDate(oldeventdate);
					venue.addBookedDate(eventdate);
					System.out.println("Before Update");
					ed.updateEvent(event);
					System.out.println("After Event Update");
					vd.update(venue);
					System.out.println("After Venue Update");
					
					mv = new ModelAndView("updateevent", "event",event);
					model.addAttribute("msg", "Event Updated Successfully");
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mv = new ModelAndView("usercreatedevents", "msg", "Event can not be updated at this time");
			}
			
		}else if(action.equalsIgnoreCase("Delete")) {
			
			try {
			Event event = ed.getEventbyid(eid);
			Venue venue = vd.getVenuebyId(event.getVenue().getId());
			Date oldeventdate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			
			oldeventdate = sdf.parse(request.getParameter("oldeventdate"));
			
			Calendar c = Calendar.getInstance(); 
			c.setTime(oldeventdate); 
			c.add(Calendar.DATE, 1);
			oldeventdate = c.getTime();
			System.out.println("Old DAte "+oldeventdate);
			venue.removeBookedDate(oldeventdate);
			vd.update(venue);
			Set<User> users = event.getUsers();
			
//			for(User u:users) {
//				u.removeRegisteredEvent(event);
//			}
//			
//			usersess.removeCreatedEvent(event);
				
				ed.deleteEvent(event);
				mv = new ModelAndView("usercreatedevents");
				model.addAttribute("msg", "Event deleted successfully");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mv = new ModelAndView("usercreatedevents", "msg", "Event can not be deleted at this time");
			}
			
		}
		return mv;
	}
	
	
	
	
	@RequestMapping(value = "/deregister", method = RequestMethod.POST)
	public ModelAndView deregister(Locale locale, Model model,HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		ModelAndView mv = null;
		
		HttpSession session = request.getSession();
		User usersess = (User)session.getAttribute("sessionUser");
		
		
		int eventid = Integer.parseInt(request.getParameter("eventid"));
		UserDAO ud = new UserDAO();
		EventDAO ed = new EventDAO();
		
		Event event = ed.getEventbyid(eventid);
		
		event.setSeats_available(event.getSeats_available()+1);
		usersess.removeRegisteredEvent(event);
		//event.removeUser(usersess);
		
		
		try {
//			ed.updateEvent(event);
			ud.update(usersess);
			
			System.out.println("After all update");
			mv = new ModelAndView("registeredevents","events",usersess.getEvents_registered());
			model.addAttribute("msg", "Event Successfully Deregistered");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("inside catch");
			mv = new ModelAndView("registeredevents","events",usersess.getEvents_registered());
			model.addAttribute("msg", "Something went wrong");
		}
		
		
		
		return mv;
	}
	
	
}
