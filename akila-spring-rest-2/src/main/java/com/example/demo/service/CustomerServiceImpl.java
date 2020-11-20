package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@Component
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	 @Override
	 public void save(Customer customer) {
	        customer.setEmail(customer.getEmail());
	        customer.setDescription(customer.getDescription());
	        customer.setName(customer.getName());
	        customerRepository.save(customer);
	    }

	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomerById(Integer id) {
		// TODO Auto-generated method stub
		return customerRepository.getOne(id);
	}

	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerRepository.save(customer);
		
	}

	@Override
	public void deleteCustomer(Integer id) {
		// TODO Auto-generated method stub
		customerRepository.deleteById(id);
	}

	@Override
	public Customer getCustomerByName(String customerName) {
		// TODO Auto-generated method stub
		return customerRepository.findByName(customerName);
	}

	}
