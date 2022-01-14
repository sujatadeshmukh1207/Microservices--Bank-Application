package com.sujata.Customer_Service.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sujata.Customer_Service.Model.Customer;
import com.sujata.Customer_Service.Repository.CustomerRepository;
import com.sujata.Customer_Service.Service.CustomerService;
import com.sujata.Customer_Service.ValueObject.ResponseTemplateVO;

@RestController
@RequestMapping("/customers")
public class CustomerController 
{
   @Autowired	
   private CustomerService customerService;
   
   @Autowired
   private CustomerRepository customerRepository;
   
   // Add New Customer Details 
   @PostMapping("/")
   public Customer saveCustomer(@RequestBody Customer customer)
   {
	  return customerService.saveCustomer(customer);   
   }
   
// Get Customer Details
   @GetMapping("/{id}")
   public  ResponseEntity<ResponseTemplateVO> getCustomerWithAccount(@PathVariable("id") Long customerId)
   {
	   Customer customer=customerRepository.findByCustomerId(customerId); 
	   if(customer==null) 
	    { 
	      //return new ResponseEntity<ResponseTemplateVO>("Customer not Found", HttpStatus.NOT_FOUND);
		   
		   return ResponseEntity.notFound().build();
		    } 
		   else 
		   { 
		   ResponseTemplateVO vo = customerService.getCustomerWithAccount(customerId); 
		    return new ResponseEntity<ResponseTemplateVO>(vo,HttpStatus.OK);
	   }
   }
   
 /*  // Get Customer Details
   @GetMapping("/{id}")
   public ResponseTemplateVO getCustomerWithAccount(@PathVariable("id") Long customerId)
   {
     return customerService.getCustomerWithAccount(customerId);    	   
   }
 */  
   
   // Delete Customer
   @DeleteMapping("/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Long customerId) {
	   Customer customer = customerRepository.findByCustomerId(customerId);
		if(customer== null)
		{	
		  return new ResponseEntity<String>("Customer Id: "+customerId +" not found. ",HttpStatus.BAD_REQUEST);
	    }
		else
		{
		 customerService.deleteCustomer(customerId);
		 return new ResponseEntity<String>("Customer Deleted Successfully !!",HttpStatus.OK);
		}
	}

   
}
