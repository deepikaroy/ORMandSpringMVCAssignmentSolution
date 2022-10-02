package com.greatlearning.customer.service;

import java.util.List;

import com.greatlearning.customer.entity.Customer;

public interface CustomerService
{
	public void display();
	public Customer findById(int id);
	public List<Customer> findAll();
	public void deleteById(int id);
	public void save(Customer customer);
}
