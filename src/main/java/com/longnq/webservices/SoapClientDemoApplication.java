package com.longnq.webservices;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.longnq.customers.Customer;
import com.longnq.customers.CustomerPortType;
import com.longnq.customers.CustomerPortTypeImplService;
import com.longnq.customers.Customers;

@SpringBootApplication
public class SoapClientDemoApplication {

	public static void main(String[] args) throws MalformedURLException {
		SpringApplication.run(SoapClientDemoApplication.class, args);
		
		CustomerPortTypeImplService service = 
				new CustomerPortTypeImplService(new URL("http://localhost:8080/my-customers/services/customerService?wsdl"));
		
		CustomerPortType portType = service.getCustomerPortTypeImplPort();
		
		Customers customers = portType.getCustomers();
		for(Customer customer: customers.getCustomer()) {
			System.out.println(customer);
		}
		
	}

}
