package com.me.myapp.controller;

import java.text.DateFormat;
import java.util.Date;
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

import com.me.myapp.HomeController;
import com.me.myapp.dao.EventDAO;
import com.me.myapp.dao.OwnerDAO;
import com.me.myapp.dao.UserDAO;
import com.me.myapp.dao.VenueDAO;
import com.me.myapp.pojo.Event;
import com.me.myapp.pojo.Owner;
import com.me.myapp.pojo.User;
import com.me.myapp.pojo.Venue;


@Controller
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value = "/usersignup", method = RequestMethod.POST)
	public String home(Locale locale, Model model,HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "signup";
	}
	
	@RequestMapping(value = "/userregister", method = RequestMethod.POST)
	public ModelAndView register(Locale locale, Model model,HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		ModelAndView mv = null;
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		
        
        String name = request.getParameter("firstName");
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        
        if(role.equalsIgnoreCase("user")) {
        	UserDAO user = new UserDAO();
        	
        	int res=0;
        	try {
        		res = user.addUser(name,email,password);
        		if(res>0){
                    mv = new ModelAndView("registration-successful","message","Hi "+name+" Registration Successfull to The Adda");
                }else{
                    mv = new ModelAndView("error");
                }
        	}catch(Exception e) {
        		mv = new ModelAndView("signup","invalidUser" , "Please use different credentials");
        	}
        	
        }else if(role.equalsIgnoreCase("owner")) {
        	OwnerDAO owner = new OwnerDAO();
        	int res=0;
        	try {
        		res = owner.addOwner(name,email,password);
        		if(res>0){
                    mv = new ModelAndView("registration-successful","message","Hi "+name+" Registration Successfull to The Adda");
                }else{
                    mv = new ModelAndView("error");
                }
        	}catch(Exception e) {
        		mv = new ModelAndView("signup","invalidUser" , "Please use different credentials");
        	}
        }
        
		
		return mv;
	}
	
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public ModelAndView logoutUser(HttpServletRequest req,Model model) throws Exception
	{
		ModelAndView mv = new ModelAndView("index");
		HttpSession session= req.getSession();
		User userLoggedIn = (User)session.getAttribute("sessionUser");
		session.removeAttribute(userLoggedIn.getEmail());
		req.getSession().invalidate();
		return mv;
	}
	
	@RequestMapping(value="/ownerlogout", method=RequestMethod.GET)
	public ModelAndView ownerlogout(HttpServletRequest req,Model model) throws Exception
	{
		ModelAndView mv = new ModelAndView("index");
		HttpSession session= req.getSession();
		Owner userLoggedIn = (Owner)session.getAttribute("sessionUser");
		session.removeAttribute(userLoggedIn.getEmail());
		session.invalidate();
		
		return mv;
	}
	
	@RequestMapping(value="/edituser", method=RequestMethod.GET)
	public ModelAndView userview(HttpServletRequest req,Model model) throws Exception
	{
		
		HttpSession session= req.getSession();
		User userLoggedIn = (User)session.getAttribute("sessionUser");
		UserDAO ud = new UserDAO();
		User user = ud.getUserbyid(userLoggedIn.getId());
		
		ModelAndView mv = new ModelAndView("useredit","user",user);
		
		return mv;
	}
	
	
	@RequestMapping(value="/updateuser", method=RequestMethod.POST)
	public ModelAndView updateuser(HttpServletRequest request,Model model) throws Exception
	{
		ModelAndView mv = null;
		HttpSession session= request.getSession();
		User userLoggedIn = (User)session.getAttribute("sessionUser");
		
		String action = request.getParameter("submit");
		UserDAO ud = new UserDAO();
		int userid = Integer.parseInt(request.getParameter("userid"));
		
		if(action.equalsIgnoreCase("Update")) {
			String email = request.getParameter("email");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			User user = ud.getUserbyid(userid);
			user.setEmail(email);
			user.setName(username);
			user.setPassword(password);
			ud.update(user);
			
			mv = new ModelAndView("useredit","user",user);
			model.addAttribute("msg","Details Updated Sucessfully");
		}else if(action.equalsIgnoreCase("Delete")) {
			
			User user = ud.getUserbyid(userid);
			
			ud.delete(user);
			
			mv = new ModelAndView("index","msg","Account Deleted");
			session.removeAttribute(userLoggedIn.getEmail());
			session.invalidate();
		}
		
	
		
		return mv;
	}
	
	@RequestMapping(value="/editowner", method=RequestMethod.GET)
	public ModelAndView owneredit(HttpServletRequest req,Model model) throws Exception
	{
		
		HttpSession session= req.getSession();
		Owner userLoggedIn = (Owner)session.getAttribute("sessionUser");
		OwnerDAO ud = new OwnerDAO();
		Owner user = ud.getOwnerbyid(userLoggedIn.getId());
		
		ModelAndView mv = new ModelAndView("owneredit","user",user);
		
		return mv;
	}
	
	@RequestMapping(value="/updateowner", method=RequestMethod.POST)
	public ModelAndView updateowner(HttpServletRequest request,Model model) throws Exception
	{
		ModelAndView mv = null;
		HttpSession session= request.getSession();
		Owner userLoggedIn = (Owner)session.getAttribute("sessionUser");
		
		String action = request.getParameter("submit");
		OwnerDAO od = new OwnerDAO();
		VenueDAO vd = new VenueDAO();
		EventDAO ed = new EventDAO();
		UserDAO ud = new UserDAO();
		int ownerid = Integer.parseInt(request.getParameter("userid"));
		
		if(action.equalsIgnoreCase("Update")) {
			String email = request.getParameter("email");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			Owner owner = od.getOwnerbyid(ownerid);
			owner.setEmail(email);
			owner.setName(username);
			owner.setPassword(password);
			od.updateOwner(owner);
			
			mv = new ModelAndView("owneredit","user",owner);
			model.addAttribute("msg","Details Updated Sucessfully");
		}else if(action.equalsIgnoreCase("Delete")) {
			
			Owner owner = od.getOwnerbyid(ownerid);
			Set<Venue> venues = owner.getVenues();
			for(Venue v:venues) {
				Set<Event> events = v.getEvents();
				for(Event e:events) {
					ed.deleteEvent(e);
				}
				//vd.deleteVenue(v);
			}
			System.out.println("Before Owner Delete");
			od.deleteOwner(owner);
			
			mv = new ModelAndView("index","msg","Account Deleted");
			session.removeAttribute(userLoggedIn.getEmail());
			session.invalidate();
		}
		
		return mv;
	}
	
}
