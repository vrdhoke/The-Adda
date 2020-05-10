package com.me.myapp.dao;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.me.myapp.controller.MainController;
import com.me.myapp.pojo.Event;
import com.me.myapp.pojo.Owner;
import com.me.myapp.pojo.User;
import com.me.myapp.pojo.Venue;

public class EventDAO extends DAO{
	private static final Logger logger = LoggerFactory.getLogger(EventDAO.class);
	
	public Event addEvent(String eventname,String details, Date event_date,int capacity,Venue venue,User organizer) throws Exception{
		Event ev=null;
	    try {
	    	ev = new Event();
	    	ev.setEventname(eventname);
	        ev.setLocation(venue.getLocation());
	        ev.setCapacity(capacity);
	        ev.setDetails(details);
	        ev.setEvent_date(event_date);
	        ev.setOrganizer(organizer);
	        ev.setSeats_available(capacity);
	        ev.setVenue(venue);
	        ev.setStatus("Scheduled");
	        begin();
	        getSession().save(ev);
	        commit();
	    } catch (HibernateException e) {
	        e.printStackTrace();
	        rollback();
	        throw e;
	    } finally {
	        close();
	    }
	    return ev;
	}
	
	
	public List<Event> getEvents(User user){
		List<Event> v = new ArrayList<Event>();
        try {
            begin();
            //Query q = getSession().createQuery("from Message where userName= :username");
            Query q = getSession().createQuery("from Event where organizer=:user");
            
            q.setEntity("user", user);
            v = (List<Event>) q.list();
            logger.info("Vaibhav"+v.size());
            System.out.println("Size in sout"+v.size());
            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
        } finally {
            close();
        }
        return v;		
	}
	
	
	public List<Event> getAllEvents(){
		List<Event> v = new ArrayList<Event>();
        try {
            begin();
            //Query q = getSession().createQuery("from Message where userName= :username");
            Query q = getSession().createQuery("from Event");
            
            v = (List<Event>) q.list();
            logger.info("Vaibhav"+v.size());
            System.out.println("Size in sout"+v.size());
            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
        } finally {
            close();
        }
        return v;		
	}
	
	
	
	public Event getEventbyid(int eid){
		Event v = new Event();
        try {
            begin();
            //Query q = getSession().createQuery("from Message where userName= :username");
            Query q = getSession().createQuery("from Event where id=:eid");
            
            q.setInteger("eid", eid);
            v = (Event) q.uniqueResult();
            
            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
        } finally {
            close();
        }
        return v;		
	}
	
	
	public int updateEvent(Event event) throws Exception{
    	int ret = 0;
    	 try {
             begin();
            
//             Set<Venue> venues = getVenues(owner.getEmail());
//             logger.info("Size Vaibhav"+venues.size());
// 			
// 			venues.add(v);	
// 			owner.setVenues(venues);
//             v.setOwner(owner);
             
             getSession().update(event);
             System.out.println("updated from here");
             ret=1;
             commit();
         } catch (HibernateException e) {
        	 e.printStackTrace();
	         rollback();
	         throw e;
         }finally {
	            close();
	        }
    	 return ret;
    }
	
	public int deleteEvent(Event event) throws Exception{
    	int ret = 0;
    	 try {
             begin();
            
//             Set<Venue> venues = getVenues(owner.getEmail());
//             logger.info("Size Vaibhav"+venues.size());
// 			
// 			venues.add(v);	
// 			owner.setVenues(venues);
//             v.setOwner(owner);
             
             getSession().delete(event);
             System.out.println("deleted from here");
             ret=1;
             commit();
         } catch (HibernateException e) {
        	 e.printStackTrace();
	         rollback();
	         throw e;
         }finally {
	            close();
	        }
    	 return ret;
    }
	
}
