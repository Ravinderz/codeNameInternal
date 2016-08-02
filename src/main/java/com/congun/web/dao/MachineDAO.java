package com.congun.web.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.congun.web.model.Machines;
import com.congun.web.model.User;
import com.congun.web.util.ResponseConstants;

@Repository
public class MachineDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Transactional
	public Machines getMachineDetails(String category){
		try{
			System.out.println("Getting details from DAOImpl :"+category+":");
			Criteria criteria = getSession().createCriteria(Machines.class);
		Machines machine= (Machines) criteria.add(Restrictions.eq("category",category)).list().get(0);
		System.out.println("Got machine with "+category+" from database");
		return machine;
		}catch(Exception e){
			System.out.println("Entered Exception Block : ");
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	public String insertMachineDetails(Machines machine){
		try{
			System.out.println("Inserting Machine to DB : "+machine.getCategory()+" :"+machine.model+" :"+machine.getMake());
			getSession().saveOrUpdate(machine);
			return ResponseConstants.MACHINE_SUCCESS_CODE;
		}catch(Exception e){
			e.printStackTrace();
			return ResponseConstants.MACHINE_EXCEPTION_CODE;
		}
	}

}
