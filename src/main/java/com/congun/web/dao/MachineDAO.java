package com.congun.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.congun.web.model.DropDownMaster;
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
	
	@Transactional
	public Machines getMachineDetailsByModel(String model) {
		try{
			Criteria criteria = getSession().createCriteria(Machines.class);
			Machines machine= (Machines) criteria.add(Restrictions.eq("model",model)).list().get(0);
			System.out.println("Got machine with "+model+" from database");
			return machine;
			}catch(Exception e){
				System.out.println("Entered Exception Block : ");
				e.printStackTrace();
				return null;
			}
	}

	public List<Machines> getSearchResults(String value) {
		String input = "%"+value+"%";
		System.out.println(" input value :"+input);
		try{
			Criteria criteria = getSession().createCriteria(Machines.class);
			Criterion model = Restrictions.like("model", input);
			Criterion make = Restrictions.like("make",input);
			Criterion category = Restrictions.like("category",input);
			Criterion equipment = Restrictions.like("equipment",input);
			
			LogicalExpression orExp1 = Restrictions.or(model,make);
			LogicalExpression orExp2 = Restrictions.or(category,equipment);
			//criteria.add(orExp1);
			//criteria.add(orExp2);
			LogicalExpression finExp = Restrictions.or(orExp1, orExp2);
			criteria.add(finExp);
			List<Machines> list = criteria.list();
			return list;
			
		}catch(Exception e){
			System.out.println("Entered Exception Block : ");
			e.printStackTrace();
			return null;
		}
	}
	@Transactional
	public List<Machines> getDistinctModels() {
		try{
			Criteria criteria = getSession().createCriteria(Machines.class);
			criteria.setProjection(Projections.distinct(Projections.property("model")));
			List<Machines> list= criteria.list();
			return list;
			}catch(Exception e){
				System.out.println("Entered Exception Block : ");
				e.printStackTrace();
				return null;
			}
	}

}
