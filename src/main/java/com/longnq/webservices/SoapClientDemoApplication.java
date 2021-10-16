package com.longnq.webservices;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
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
		Client client = ClientProxy.getClient(portType);
		Endpoint endpoint = client.getEndpoint();
		
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
		properties.put(WSHandlerConstants.USER, "user1");
		properties.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
		properties.put(WSHandlerConstants.PW_CALLBACK_CLASS, PasswordCallbackImpl.class.getName());
		
		WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(properties);
		endpoint.getOutInterceptors().add(wssOut);
		
		Customers customers = portType.getCustomers();
		for(Customer customer: customers.getCustomer()) {
			System.out.println(customer);
		}
		
	}

}
