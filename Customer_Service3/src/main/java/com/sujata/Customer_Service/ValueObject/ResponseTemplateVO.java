package com.sujata.Customer_Service.ValueObject;

import com.sujata.Customer_Service.Model.Customer;

//this class is nothing but wrapper object which contains user and department both

// this class (ResponseTemplateVO) can be used as a return type, so that we can return user and its department

public class ResponseTemplateVO 
{
    private Customer customer;
    private Account account;
	
    public ResponseTemplateVO() {
		super();
	}

	public ResponseTemplateVO(Customer customer, Account account) {
		super();
		this.customer = customer;
		this.account = account;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
    
	
}
