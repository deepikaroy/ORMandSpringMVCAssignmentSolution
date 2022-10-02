package com.greatlearning.customer.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greatlearning.customer.entity.Customer;
@Repository
public class CustomerServiceImpl implements CustomerService
{
	private final SessionFactory sessionFactory;
	private Session session;

	@Autowired
	public CustomerServiceImpl(SessionFactory sessionFactory)
	{
		super();
		this.sessionFactory = sessionFactory;
		try
		{
			session = sessionFactory.getCurrentSession();
		}
		catch(HibernateException ex)
		{
			session = sessionFactory.openSession();
		}
	}

	@Transactional
	public void display()
	{
	}

	@Transactional
	public Customer findById(int id)
	{
		Transaction transaction = session.beginTransaction();
		Customer customer = session.get(Customer.class, id);
		transaction.commit();

		return customer;
	}

	@Transactional
	public List<Customer> findAll()
	{
		Transaction transaction = session.beginTransaction();
		List<Customer> customers = session.createQuery("from Customer").list();		
		transaction.commit();

		return customers;
	}
	
	@Transactional
	public void deleteById(int id)
	{
		Transaction transaction = session.beginTransaction();
		Customer customer = session.get(Customer.class, id);
		session.delete(customer);
		transaction.commit();
	}	

	@Transactional
	public void save(Customer customer)
	{
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(customer);		
		transaction.commit();
	}


}
