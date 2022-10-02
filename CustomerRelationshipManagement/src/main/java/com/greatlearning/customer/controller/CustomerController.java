package com.greatlearning.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.customer.entity.Customer;
import com.greatlearning.customer.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController
{
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/display")
	public String homePage(Model model)
	{ 
		List<Customer> customer = customerService.findAll();
		model.addAttribute("Customers", customer);
		
		return "display-customers";		
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model model)
	{ 	
		Customer customer = new Customer();
		model.addAttribute("Customer", customer);
		return "add-customer";		
	}	
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id") int id, Model model)
	{ 		
		Customer customer = customerService.findById(id);		
		model.addAttribute("Customer", customer);
		return "add-customer";		
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") int id) 
	{
		customerService.deleteById(id);		

		return "redirect:/customer/display";
	}
	@PostMapping("/save")
	public String saveCustomer(@RequestParam("id") int id, 
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("email") String email)
	{
		System.out.println(id);
		Customer customer;

		if (id != 0) 
		{
			customer = customerService.findById(id);
			customer.setEmail(email);
			customer.setFirstName(firstName);
			customer.setLastName(lastName);
		}
		else
		{
			customer = new Customer(firstName, lastName, email);
		}

		// save the customer
		customerService.save(customer);

		// use a redirect to prevent duplicate submissions
		return "redirect:/customer/display";

	}

}
