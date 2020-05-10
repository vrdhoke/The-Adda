package com.me.myapp.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.me.myapp.controller.MainController;
import com.me.myapp.pojo.Event;
import com.me.myapp.pojo.Owner;
import com.me.myapp.pojo.Venue;


public class VenueDAO extends DAO{

	private static final Logger logger = LoggerFactory.getLogger(VenueDAO.class);
	public Venue addVenue(String location, String transportation, String rooms,Owner owner) throws Exception{
		Venue v=null;
	    try {
	    	v = new Venue();
	        v.setLocation(location);
	        v.setOwner(owner);
	        v.setRooms(rooms);
	        v.setTransportation(transportation);
	        begin();
	        getSession().save(v);
	        commit();
	       
	    } catch (HibernateException e) {
	        e.printStackTrace();
	        rollback();
	        throw e;
	    } finally {
	        close();
	    }
	    return v;
	}
	
	
	
	
	public List<Venue> getVenues(){
		List<Venue> v = new ArrayList<Venue>();
        try {
            begin();
            //Query q = getSession().createQuery("from Message where userName= :username");
            Query q = getSession().createQuery("from Venue");
            
            v = (List<Venue>) q.list();
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
	
	
	
	
	public Venue getVenuebyId(int venueId){
		Venue v = null;
        try {
            begin();
            //Query q = getSession().createQuery("from Message where userName= :username");
            Query q = getSession().createQuery("from Venue where id= :venueid");
            
            q.setInteger("venueid",venueId);
            v = (Venue) q.uniqueResult();
            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
        } finally {
            close();
        }
        return v;		
	}
	 
	
	
	public Venue update(Venue v) throws Exception{
	    try {
	    	
	        begin();
	        getSession().update(v);
	        commit();
	       
	    } catch (HibernateException e) {
	        e.printStackTrace();
	        rollback();
	        throw e;
	    } finally {
	        close();
	    }
	    return v;
	}
	
	public int deleteVenue(Venue venue) throws Exception{
    	int ret = 0;
    	 try {
             begin();
            
//             Set<Venue> venues = getVenues(owner.getEmail());
//             logger.info("Size Vaibhav"+venues.size());
// 			
// 			venues.add(v);	
// 			owner.setVenues(venues);
//             v.setOwner(owner);
             
             getSession().delete(venue);
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
	
	
	public List<Venue> getVenuebyOwner(Owner owner){
		List<Venue> v = null;
		
        try {
            begin();
            //Query q = getSession().createQuery("from Message where userName= :username");
            Query q = getSession().createQuery("from Venue where owner= :own");
            
            q.setEntity("own",owner);
            v = (List<Venue>) q.list();
            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
        } finally {
            close();
        }
        return v;		
	}
	
}
