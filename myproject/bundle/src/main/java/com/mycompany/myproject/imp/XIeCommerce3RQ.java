package com.mycompany.myproject.imp;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.paymetric.sdk.models.AccessTokenResponse.AccessTokenResponsePacket;
import com.paymetric.sdk.AccessTokenUtility;
import com.paymetric.sdk.models.PostPacket.PostPacket;
import com.paymetric.sdk.Utility;

import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;

@SlingServlet(paths = "/services/XIeCommerce3RQ", methods = "POST")
public class XIeCommerce3RQ extends SlingAllMethodsServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3905270709919636168L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Override
	protected void doGet(SlingHttpServletRequest request,SlingHttpServletResponse response) throws ServletException,IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(SlingHttpServletRequest request,SlingHttpServletResponse response) throws ServletException,IOException {
		
		PrintWriter out=response.getWriter();// to print on browser window
		
		String strGUID = "3986ffef-8762-4708-93c7-ddc49743edf6";
		String strPSK = "Yi8}7P+bn*2N?Rt5C3/p$y6Qf9B_Gj-4";
		
		String strURL = "https://qaapp02.xisecurenet.com/diecomm";
		//String strURL = "https://prdapp02.xisecurenet.com/diecomm";
		
		
		
		logger.info("strGUID - " + strGUID);
		logger.info("strPSK - " + strPSK);
		logger.info("strURL - " + strURL);
		
		String site = request.getScheme() + "://" + request.getServerName();
		if(request.getServerPort() != 80) {
		    site = site + ":" + request.getServerPort();
		}
		
		site = site + request.getRequestURI();
		site = site.replace(request.getServletPath(),"");

		request.setAttribute("XIeCommURL", strURL); 
		request.setAttribute("MerchantGuid", strGUID);
		request.setAttribute("site", site);
		
		PostPacket postPacket = new PostPacket(site + "/services/XIeCommerce3RS");
		logger.info("***********************************************"+postPacket);
		
		out.println(postPacket.toString());
		out.println("Hello World : Jenkins auto deploymen 333");
		
	    AccessTokenResponsePacket accessTokenResponsePacket = AccessTokenUtility.GetPostAccessToken(strGUID, strPSK, strURL, postPacket);
	   if (!(accessTokenResponsePacket.getRequestError() + "").equals("null")) {
	        String error = accessTokenResponsePacket.getRequestError().toString();
	        if (error.indexOf("com.paymetric.sdk.models.AccessTokenResponse.AccessTokenResponsePacket") != -1) {
	            request.setAttribute("errormessage", "The access token could not be procured. Could your merchant guid or shared key be misconfigured?");
	        } else {
	            request.setAttribute("errormessage", error);
	        }
	        request.setAttribute("errorstyle", "class='errorMsg'");
	    } else {
	        
	    }
	    
	    
	    //logger.info("Access Token"+"***********"+accessTokenResponsePacket.getResponsePacket().getAccessToken());
	    //out.print(accessTokenResponsePacket.getResponsePacket().getAccessToken());*/
	}
	    
}
