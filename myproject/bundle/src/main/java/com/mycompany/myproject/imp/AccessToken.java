package com.mycompany.myproject.imp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;


@SlingServlet(paths = "/bin/paymetric/token", methods = "POST")
public class AccessToken extends SlingAllMethodsServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7572776734698629682L;
	
	
	
	@Override
	protected void doPost(SlingHttpServletRequest request,SlingHttpServletResponse response) throws ServletException,IOException {
		String merchantGuid="29a8ce17-1838-4f54-a49b-8d6cbff9ff24";
	    String sessionRequestType="3";
	    String signature="ODEwODZhN2NlZjZhNjk0YjVjYjI2MjIwOGQ5OTQzOGIyNGQyMmNmNDRmMTIxMDY3ZTIzMjExYTYyYTg3MzRjNQ";
	    String merchantDevelopmentEnvironment="java";
		PrintWriter out=response.getWriter();
		int status=0;
	    try{
		HttpClient client=new HttpClient();
		
		out.println("*****************************");
		PostMethod post=new PostMethod("http://qaapp02.XiSecurenet.com/DIeComm/AccessToken");
		//PostMethod post=new PostMethod("http://login.salesforce.com/services/oauth2/token");
		out.print("*****************************");
		post.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

		post.setParameter("MerchantGuid", merchantGuid);
		post.setParameter("SessionRequestType", sessionRequestType);
		post.setParameter("Signature", signature);
		post.setParameter("MerchantDevelopmentEnvironment", merchantDevelopmentEnvironment);
		post.setParameter("Packet", "%3C%3Fxml+version%3D%221.0%22%3F%3E%0D%0A%3CPostPacketModel+xmlns%3Axsd%3D%22http%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema%22+xmlns%3Axsi%3D%22http%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema-instance%22%3E%0D%0A%3CRedirectUri%3Ehttp%3A%2F%2Flocalhost%3A4502%2Fservices%2FXIeCommerce3RS%3C%2FRedirectUri%3E%0D%0A%3C%2FPostPacketModel%3E");
		
		status=client.executeMethod(post);
		out.write("status :"+status);
	    }
	    catch(Exception e){
	    	
	    	out.print(e);
	    }
	}

	}
	
	

