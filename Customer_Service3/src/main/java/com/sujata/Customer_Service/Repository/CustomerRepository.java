package com.sujata.Customer_Service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sujata.Customer_Service.Model.Customer;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Long>, CrudRepository<Customer, Long>
{

		Customer findByCustomerId(Long customerId);

}
