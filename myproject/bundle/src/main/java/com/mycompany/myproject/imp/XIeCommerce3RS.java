package com.mycompany.myproject.imp;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.paymetric.sdk.AccessTokenUtility;


import java.io.IOException;



import javax.servlet.ServletException;

@SlingServlet(paths = "/services/XIeCommerce3RS", methods = "POST")
public class XIeCommerce3RS extends SlingAllMethodsServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3231279287641068608L;
	
	 private Logger logger=LoggerFactory.getLogger(getClass());
	 
	 protected void doGet(SlingHttpServletRequest request,SlingHttpServletResponse response) throws ServletException,IOException {
			doPost(request, response);
		}
	 
	 	 
	//----------------------------------DOPOST-------------------------------------------------------
	@Override
	protected void doPost(SlingHttpServletRequest request,SlingHttpServletResponse response) throws ServletException,IOException {
		String strGUID = "29a8ce17-1838-4f54-a49b-8d6cbff9ff24";
		String strPSK = "4y$NP-9n/xS3wZ2%5?Tos7*H8!Gc+q6K";
		String strURL = "https://qaapp02.xisecurenet.com/diecomm";
		//String strURL = "https://prdapp02.xisecurenet.com/diecomm";
		

		

		logger.info("strGUID - " + strGUID);
		logger.info("strPSK - " + strPSK);
		logger.info("strURL - " + strURL);
		
		
	    String tokencard = "";
	    String accessToken = request.getParameter("id");
	    logger.info("strURL #####- " + accessToken);
	    String xml = AccessTokenUtility.GetResponsePacket(strGUID.trim(), strPSK.trim(), strURL.trim(), accessToken);
	    request.setAttribute("xmlResponse", xml);

	    tokencard =  getElementValue(xml, "Value");
	    request.setAttribute("Token", tokencard);
	}
	
	//------------------------------------------------------------------------
	public String getElementValue(String strXml, String strElement)
	{
		String strResult = null;
		int iBeging = 0;
		int iEnd = 0;
		
		try
		{
			iBeging = strXml.indexOf("<" + strElement + ">");
			if(iBeging != -1)
			{
				iBeging += 2 + strElement.length();
				iEnd = strXml.indexOf("</" + strElement + ">", iBeging);
				strResult = strXml.substring(iBeging, iEnd);   
			}
			else strResult = "";
		}
		catch(Exception ex)
		{
			strResult = "";
		}

		return (strResult.length() != 0 ? strResult : "   ");
	}
	
	//------------------------------------------------------------------------

}
