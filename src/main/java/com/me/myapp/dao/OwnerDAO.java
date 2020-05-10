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
import com.me.myapp.pojo.Owner;
import com.me.myapp.pojo.User;
import com.me.myapp.pojo.Venue;


public class OwnerDAO extends DAO {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	public int addOwner(String name, String email, String password) throws Exception{
	    int result = 0;
	    try {
	        Owner u = new Owner();
	        u.setEmail(email);
	        u.setName(name);
	        u.setPassword(password);
	        begin();
	        getSession().save(u);
	        commit();
	        result = 1;
	    } catch (HibernateException e) {
	        e.printStackTrace();
	        rollback();
	        throw e;
	    } finally {
	        close();
	    }
	    return result;
	}
	    
	    public Owner authenticateOwner(String email,String password) {
	        Owner u  = new Owner();
	        try {
	            begin();
	            //Query q = getSession().createQuery("from Message where userName= :username");
	            Query q = getSession().createQuery("from Owner where email= :username and password= :password");
	            q.setString("username", email);
	            q.setString("password", password);
	            u = (Owner) q.uniqueResult();
	            commit();
	        } catch (HibernateException e) {
	            e.printStackTrace();
	            rollback();
	        } finally {
	            close();
	        }
	        return u;
	    }
	    
	    public int update(Owner owner, Venue v) {
	    	int ret = 0;
	    	 try {
	             begin();
	             logger.info("In Update before size"+owner.getEmail()+" Set"+owner.getVenues().size());
//	             Set<Venue> venues = getVenues(owner.getEmail());
//	             logger.info("Size Vaibhav"+venues.size());
//	 			
//	 			venues.add(v);	
//	 			owner.setVenues(venues);
//	             v.setOwner(owner);
	             owner.addVenue(v);
	             //getSession().update(owner);
	             System.out.println("updated from here");
	             ret=1;
	             //commit();
	         } catch (HibernateException e) {
	        	 e.printStackTrace();
		         rollback();
	         }finally {
		            close();
		        }
	    	 return ret;
	    }
	    
	    
		public Set<Venue> getVenues(String email){
			Set<Venue> v  = new HashSet<Venue>();
	        try {
	            begin();
	            //Query q = getSession().createQuery("from Message where userName= :username");
	            Query q = getSession().createQuery("from Owner where email= :username");
	            q.setString("username", email);
	            
	            v = (Set<Venue>) q.uniqueResult();
	            commit();
	        } catch (HibernateException e) {
	            e.printStackTrace();
	            rollback();
	        } finally {
	            close();
	        }
	        return v;		
			
		}
		
		
		
		
		 public Owner getOwnerbyid(int oid) throws Exception{
				Owner v = new Owner();
		        try {
		            begin();
		            //Query q = getSession().createQuery("from Message where userName= :username");
		            Query q = getSession().createQuery("from Owner where id=:oid");
		            
		            q.setInteger("oid", oid);
		            v = (Owner) q.uniqueResult();
		            
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
		 
		 public int updateOwner(Owner owner) {
		    	int ret = 0;
		    	 try {
		             begin();
		             logger.info("In Update before size"+owner.getEmail()+" Set"+owner.getVenues().size());
//		             Set<Venue> venues = getVenues(owner.getEmail());
//		             logger.info("Size Vaibhav"+venues.size());
//		 			
//		 			venues.add(v);	
//		 			owner.setVenues(venues);
//		             v.setOwner(owner);
		             
		             getSession().update(owner);
		             System.out.println("updated from here");
		             ret=1;
		             commit();
		         } catch (HibernateException e) {
		        	 e.printStackTrace();
			         rollback();
		         }finally {
			            close();
			        }
		    	 return ret;
		    }
		 
		 public int deleteOwner(Owner owner) {
		    	int ret = 0;
		    	 try {
		             begin();      
		             getSession().delete(owner);
		             System.out.println("deleted from here");
		             ret=1;
		             commit();
		             System.out.println("After delete commit");
		         } catch (HibernateException e) {
		        	 e.printStackTrace();
			         rollback();
		         }finally {
			            close();
			        }
		    	 return ret;
		    }
		 
		 
		 
}
