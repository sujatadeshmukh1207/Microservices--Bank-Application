package com.sujata.Customer_Service.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sujata.Customer_Service.Model.Customer;
import com.sujata.Customer_Service.Repository.CustomerRepository;
import com.sujata.Customer_Service.ValueObject.Account;
import com.sujata.Customer_Service.ValueObject.ResponseTemplateVO;


@Service
public class CustomerService
{
    @Autowired
	private CustomerRepository customerRepository;

    @Autowired
    private RestTemplate restTemplate;
    
	
    // Save Customer Details
    public Customer saveCustomer(Customer customer) 
	{
	   return customerRepository.save(customer);
	}
    
    // Delete Customer
	public void deleteCustomer(Long customerId) {
		customerRepository.deleteById(customerId);
	}

	// get Customer Details with Account Details
	public ResponseTemplateVO getCustomerWithAccount(Long customerId)
	{
	   ResponseTemplateVO vo= new ResponseTemplateVO();
	   Customer customer = customerRepository.findByCustomerId(customerId);
       
	   //Calling Account microservice to get account details
	   Account account = 
			   restTemplate.getForObject("http://ACCOUNT-SERVICE/accounts/" + customer.getAccountId(),Account.class);
	   
	   //To set Customer and Account in VO 
	   vo.setCustomer(customer);
	   vo.setAccount(account);
	   
       return vo;	
	}
   
}
