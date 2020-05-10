package com.me.myapp.dao;

import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.myapp.pojo.Event;
import com.me.myapp.pojo.Owner;
import com.me.myapp.pojo.User;
import com.me.myapp.pojo.Venue;

public class UserDAO extends DAO{

	
	
	public int addUser(String name, String email, String password) throws Exception{
	    int result = 0;
	    try {
	        User u = new User();
	        u.setEmail(email);u.setName(name);
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
	    
	
	    public User authenticateUser(String email,String password) throws Exception{
	        User u  = new User();
	        try {
	            begin();
	            //Query q = getSession().createQuery("from Message where userName= :username");
	            Query q = getSession().createQuery("from User where email= :username and password= :password");
	            q.setString("username", email);
	            q.setString("password", password);
	            u = (User) q.uniqueResult();
	            commit();
	        } catch (HibernateException e) {
	            e.printStackTrace();
	            rollback();
	            throw e;
	        } finally {
	            close();
	        }
	        return u;
	    }
	    
	    public int update(User user) throws Exception{
	    	int ret = 0;
	    	 try {
	             begin();
	            
//	             Set<Venue> venues = getVenues(owner.getEmail());
//	             logger.info("Size Vaibhav"+venues.size());
//	 			
//	 			venues.add(v);	
//	 			owner.setVenues(venues);
//	             v.setOwner(owner);
	             
	             getSession().update(user);
	             System.out.println("updated user from here");
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
	    
	    public User getUserbyid(int uid) throws Exception{
			User v = new User();
	        try {
	            begin();
	            //Query q = getSession().createQuery("from Message where userName= :username");
	            Query q = getSession().createQuery("from User where id=:uid");
	            
	            q.setInteger("uid", uid);
	            v = (User) q.uniqueResult();
	            
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
	    
	    public int delete(User user) throws Exception{
	    	int ret = 0;
	    	 try {
	             begin();
	             
	             getSession().delete(user);
	             System.out.println("deleted user from here");
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
