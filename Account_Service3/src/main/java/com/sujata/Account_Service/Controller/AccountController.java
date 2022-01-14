package com.sujata.Account_Service.Controller;

import org.springframework.http.HttpStatus;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sujata.Account_Service.Model.Account;
import com.sujata.Account_Service.Service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	@Autowired
	private AccountService accountService;

	// Create new Account
	@PostMapping("/")
	public Account saveAccount(@RequestBody Account account) {
		return accountService.saveAccount(account);
	}

	// Get Account Details
	@GetMapping("/{accountId}")
	public ResponseEntity<Account> findAccountById(@PathVariable("accountId") int accountId) 
	 {
		
		/*
		  Account account=accountService.findAccountById(accountId); 
		   if(account==null) 
		    { 
		      return ResponseEntity.notFound().build();
  		    } 
  		   else 
  		   { 
  		    accountService.findAccountById(accountId); 
  		    return new ResponseEntity<Account>(account,HttpStatus.OK);
		   }
		*/
		
		return Optional
				.ofNullable( accountService.findAccountById(accountId) )
				.map(account -> ResponseEntity.ok().body(account))      //200 OK
				.orElseGet( () -> ResponseEntity.notFound().build());   //404 Not found
	 }
	
	
	
	// checkBalance
		
	@GetMapping("/{accountId}/balance")
	public int getBalance(@PathVariable("accountId") int accountId) {
		return accountService.getBalance(accountId);
	}
	
	 // depositAmount
		@PutMapping("/{accountId}/deposit/{amount}")
		public ResponseEntity<String> depositAmount(@PathVariable int accountId, @PathVariable int amount) {
	      
			Account account=accountService.findAccountById(accountId); 
			   if(account==null) 
			    { 
				   return new ResponseEntity<String>("Account Id not found :"+accountId, HttpStatus.BAD_REQUEST);
	  		    } 
	  		   else 
	  		   { 
	  		    accountService.depositAmount(accountId, amount); 
	  		    return new ResponseEntity<String>("Deposited " +amount +" Rs. Successfully !!", HttpStatus.OK);
			   }
		} 
 			
	// Withdraw Amount
	@PutMapping("/{accountId}/withdraw/{amount}")
	public ResponseEntity<String> withdrawAmount(@PathVariable int accountId, @PathVariable int amount) {
		
		Account account=accountService.findAccountById(accountId); 
		   if(account==null) 
		    { 
			   return new ResponseEntity<String>("Account Id not found :"+accountId, HttpStatus.BAD_REQUEST);
		    } 
		   else 
		   {
			 if(amount < account.getBalance())
			 {	 
		       accountService.withdrawAmount(accountId, amount);  
	   	       return new ResponseEntity<String>("Withdrawn "+amount+ " Rs. Successfully !!" , HttpStatus.OK);
		     }
			 else
			 {
				 return new ResponseEntity<String>("Cannot Withdraw Rs." +amount+ "\n"
			                                        +"Your Current Balance is:"+account.getBalance(), HttpStatus.BAD_REQUEST); 
			 }	   
		    
		   }
	}

	// deleteAccount
	@DeleteMapping("/{accountId}")
	public ResponseEntity<String> deleteAccount(@PathVariable("accountId") int accountId) {
    
	  Account account = accountService.findAccountById(accountId);
		if(account== null)
		{	
		  return new ResponseEntity<String>("Account Id not found :"+accountId,HttpStatus.BAD_REQUEST);
	    }
		else
		{
		 accountService.deleteAccount(accountId);
		 return new ResponseEntity<String>("Account Deleted Successfully",HttpStatus.OK);
		}
		
	}	

}
