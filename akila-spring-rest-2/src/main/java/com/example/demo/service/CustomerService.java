package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Customer;


public interface CustomerService {
	
	public List<Customer> getAllCustomer();

	public Customer getCustomerById(Integer id);
	
	public Customer getCustomerByName(String Cusname);

	void save(Customer customer);

	public void updateCustomer(Customer customer);

	public void deleteCustomer(Integer id);


}
