package com.me.myapp.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.myapp.dao.OwnerDAO;
import com.me.myapp.dao.UserDAO;
import com.me.myapp.pojo.Owner;
import com.me.myapp.pojo.User;



@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = "/userlogin", method = RequestMethod.POST)
	public ModelAndView register(Locale locale, Model model,HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		ModelAndView mv = null;
		
        HttpSession session = request.getSession();
		
        String username = request.getParameter("userName");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
       
        
//        if (session.getAttribute("invalidUser") == null) {
//            session.setAttribute("invalidUser", "Invalid credentials");
//        } else if (session.getAttribute("invalidUser").toString().equalsIgnoreCase("Invalid credentials")) {
//            session.removeAttribute("invalidUser");
//        } else {
//            session.setAttribute("invalidUser", "Invalid credentials");
//        }
//      
        if(!username.isEmpty()&&!password.isEmpty()) {
        if(role.equalsIgnoreCase("user")){
        	User loggedUser=null;
        	UserDAO user = new UserDAO();
        	
        	try {
        		loggedUser= user.authenticateUser(username, password);
        	}catch(Exception e) {
        		mv = new ModelAndView("index");
        		///session.setAttribute("invalidUser", "Invalid credentials");
        		model.addAttribute("invalidUser","Invalid credentials");
        	}
        	
        	if(loggedUser!=null){
        		session.setAttribute("sessionUser", loggedUser);
        		model.addAttribute("msg", "Login Successfull");
                mv = new ModelAndView("UserDashboard","message","Welcome "+loggedUser.getName()+" to The Adda");
            }
            else{
            	mv = new ModelAndView("index");
//            	session.setAttribute("invalidUser", "Invalid credentials");
            	model.addAttribute("invalidUser","Invalid credentials");
            	
            }
        }else if(role.equalsIgnoreCase("owner")) {
        	Owner loggedUser=null;
        	OwnerDAO owner = new OwnerDAO();
        	try {
        		loggedUser= owner.authenticateOwner(username, password);
        	}catch(Exception e) {
        		mv = new ModelAndView("index","invalidUser", "Invalid credentials");
        	}
        	
        	if(loggedUser!=null){
        		session.setAttribute("sessionUser", loggedUser);
        		model.addAttribute("msg", "Login Successfull");
                mv = new ModelAndView("OwnerDashboard","message","Welcome "+loggedUser.getName()+" to The Adda");
            }
            else{
            	mv = new ModelAndView("index","invalidUser", "Invalid credentials");
            }
        	
        }
        
        }else {
        	mv = new ModelAndView("index");
            model.addAttribute("invalidUser","Please enter credential");
        }
        
        
        //System.out.print(loggedUser.getName());
        
       
        
        return mv;
	}
	
	
	
}
