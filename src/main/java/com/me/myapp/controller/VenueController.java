package com.me.myapp.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.me.myapp.dao.VenueDAO;
import com.me.myapp.pojo.Event;
import com.me.myapp.pojo.Owner;
import com.me.myapp.pojo.Venue;


@Controller
public class VenueController {

	
private static final Logger logger = LoggerFactory.getLogger(VenueController.class);
	
//	@RequestMapping(value = "/addvenue", method = RequestMethod.POST)
//	public ModelAndView addVenue(Locale locale, Model model,HttpServletRequest request) {
//		logger.info("Welcome home! The client locale is {}.", locale);
//		
//		ModelAndView mv = new ModelAndView();
//		String addvenue = request.getParameter("addvenue");
//		String allvenues = request.getParameter("allvenues");
//		
//		HttpSession session = request.getSession();
//		Owner usersess = (Owner)session.getAttribute("sessionUser");
//		Set<Venue> venues = null;
//		
//		if(addvenue!=null) {
//			mv = new ModelAndView("AddVenue");
//		}else if(allvenues!=null) {
//			OwnerDAO od = new OwnerDAO();
//			try {
//				venues = usersess.getVenues();
//			}catch(Exception e) {
//				mv = new ModelAndView("OwnerDashboard", "error","Error while fecting venues");
//			}
//			logger.info("Size of Set be printing");
//			logger.info("Size of Set", venues.size());
//			mv = new ModelAndView("allvenues","venues",venues);
//		}
//		
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		
//		String formattedDate = dateFormat.format(date);
//		
//		model.addAttribute("serverTime", formattedDate);
//		
//		return mv;
//	}
	
	
	
	@RequestMapping(value = "/addvenuedetails", method = RequestMethod.POST)
	public ModelAndView registerVenue(Locale locale, Model model,HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		ModelAndView mv = null;
		String location = request.getParameter("location");
		String availability= request.getParameter("availability");
		String transport = request.getParameter("transport");
		HttpSession session = request.getSession();
		Owner usersess = (Owner)session.getAttribute("sessionUser");
		VenueDAO vd = new VenueDAO();
		OwnerDAO od = new OwnerDAO();
		try{
			
			Venue venue = vd.addVenue(location, transport, availability, usersess);
//			Venue venue = new Venue();
//			venue.setLocation(location);
//			venue.setTransportation(transport);
//			venue.setRooms(availability);
			
			od.update(usersess,venue);
			mv= new ModelAndView("OwnerDashboard", "error", "Venue Successfully added");
			EmailController.sendEmail("theadda2020@gmail.com", "vaibhavdhoke1@gmail.com", "Venue Added Successfully and Location is "
					+venue.getLocation()+" and Accessibility is "+venue.getTransportation(), "Venue Added");
		}catch(Exception e) {
			mv = new ModelAndView("OwnerDashboard","error", "Error occured while adding Venue");
		}
		 
		
		return mv;
	}
	
	@RequestMapping(value = "/OwnerDashboard", method = RequestMethod.GET)
	public String ownerdashboard(Locale locale, Model model,HttpServletRequest request) {
		
		return "OwnerDashboard";
	}
	
	@RequestMapping(value = "/UserDashboard", method = RequestMethod.GET)
	public String userdashboard(Locale locale, Model model,HttpServletRequest request) {
		
		return "UserDashboard";
	}
	
	@RequestMapping(value = "/allvenues", method = RequestMethod.GET)
	public ModelAndView allvenuesforevents(Locale locale, Model model,HttpServletRequest request) {
		
		ModelAndView mv = null;
		
		
		VenueDAO vd = new VenueDAO();
		List<Venue> v= new ArrayList<Venue>();
		v = vd.getVenues();
		mv = new ModelAndView("venuesforevents", "venues",v);
		return mv;
	}
	
	@RequestMapping(value = "/ownervenues", method = RequestMethod.GET)
	public ModelAndView ownervenues(Locale locale, Model model,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		Owner usersess = (Owner)session.getAttribute("sessionUser");
		List<Venue> venues = null;
		VenueDAO vd = new VenueDAO();
		OwnerDAO od = new OwnerDAO();
		try {
			venues = vd.getVenuebyOwner(usersess);
		}catch(Exception e) {
			mv = new ModelAndView("OwnerDashboard", "error","Error while fecting venues");
		}
		logger.info("Size of Set be printing");
		logger.info("Size of Set", venues.size());
		mv = new ModelAndView("allvenues","venues",venues);
		
		return mv;
	}
	
	
	@RequestMapping(value = "/addvenue", method = RequestMethod.GET)
	public ModelAndView addvenue(Locale locale, Model model,HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView();
		mv = new ModelAndView("AddVenue");
		return mv;
	}
	
	
	@RequestMapping(value = "/updateVenue", method = RequestMethod.POST)
	public ModelAndView updatevenue(Locale locale, Model model,HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		Owner usersess = (Owner)session.getAttribute("sessionUser");
		int vid = Integer.parseInt(request.getParameter("venueid"));
		
		VenueDAO vd = new VenueDAO();
		Venue venue = vd.getVenuebyId(vid);
		
		
		
		mv = new ModelAndView("updatevenue","venue",venue);
		return mv;
	}
	
	@RequestMapping(value = "/updatevenuedetails", method = RequestMethod.POST)
	public ModelAndView updatevenuedetails(Locale locale, Model model,HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		Owner usersess = (Owner)session.getAttribute("sessionUser");
		int vid = Integer.parseInt(request.getParameter("venueid"));
		
		String vloc = request.getParameter("venueloc");
		String vroom = request.getParameter("vroom");
		String venuetrans = request.getParameter("venuetrans");
		VenueDAO vd = new VenueDAO();
		Venue venue = vd.getVenuebyId(vid);
		EventDAO ed = new EventDAO();
		
		String action = request.getParameter("submit");
		if(action.equalsIgnoreCase("Update")) {
			venue.setLocation(vloc);
			venue.setRooms(vroom);
			venue.setTransportation(venuetrans);
			try {
				vd.update(venue);
				mv = new ModelAndView("updatevenue","venue",venue);
				model.addAttribute("msg", "Venue Updated Successfully");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mv = new ModelAndView("updatevenue","msg","Venue can not be updated at this time");
			}
			
		}else if(action.equalsIgnoreCase("Delete")) {
			
			Set<Event> events = venue.getEvents();
			for(Event event:events) {
				try {
					ed.deleteEvent(event);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					mv = new ModelAndView("updatevenue","msg","Something went wrong");
				}
			}
			try {
				vd.deleteVenue(venue);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mv = new ModelAndView("updatevenue","msg","Something went wrong");
			}
			List<Venue> venues= vd.getVenuebyOwner(usersess);
			mv = new ModelAndView("allvenues","venues",venues);
			model.addAttribute("msg","Venue Deleted Successfully");
		}
		
		return mv;
	}
	
	
}
