package com.me.myapp.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.me.myapp.pojo.Event;
import com.me.myapp.pojo.User;

 
@Controller
public class PDFController {
		
	@RequestMapping(value = "/myapp/DownloadPDF", method=RequestMethod.GET)
	public ModelAndView downloadPDF(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception{
		HttpSession session = request.getSession();
		User usersess = (User)session.getAttribute("sessionUser");
		Set<Event> registeredevent = new HashSet();
		
		registeredevent = usersess.getEvents_registered();
		map.put("registeredevent", registeredevent);
				return new ModelAndView("pdfView", "map", map);
	}
//	@RequestMapping(value = "/myapp/DownloadPDF",method=RequestMethod.GET)
//	public ModelAndView showEventSummaryPDF(HttpServletRequest req, HttpServletResponse response, ModelMap map, Model model) throws Exception {
//				        
//        ModelAndView mv = null;
//        HttpSession session= req.getSession(false);
//		String sessionName = (String)session.getAttribute("userName");
//        if (sessionName !=null)
//        {
//        	UserDao u = new UserDao();
//            User user = u.checkCredentials(sessionName);
//        	
//        	if (user.getRole().equals("patient"))
//            {
//              model.addAttribute("authorizationFailed","You are not authorized to access this page");
//              mv = new ModelAndView("Login");
//            }
//            else if (user.getRole() == null)
//            {
//            	model.addAttribute("authorizationFailed","You are not authorized to access this page");
//                mv = new ModelAndView("Login");
//            }
//            else
//            {
//        	  mv = new ModelAndView("pdfHome");	
//			  List<Symptoms> listSymptoms = new ArrayList<Symptoms>();
//			  SymptomDao sDao = new SymptomDao();
//			  listSymptoms = sDao.getSymptomList(sessionName);
//			  map.put("listSymptoms", listSymptoms);
//  
//            }
//        }
//        else
//        {
//        	 model.addAttribute("authorizationFailed","You are not authorized to access this page");
//		     mv = new ModelAndView("Login");
//        }
//		return mv;
//	}																
}
