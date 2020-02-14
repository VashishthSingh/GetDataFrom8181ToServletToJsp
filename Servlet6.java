package com.testexample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/Servlet6")
public class Servlet6 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Servlet6(){super();}
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		  Map<String,String> map=null;
		  URL url = new URL("http://localhost:8181/ClientGetProg/getResource1/getdata");
	      HttpURLConnection con = (HttpURLConnection) url.openConnection();
	      con.setRequestMethod("GET");
	      con.setRequestProperty("Accept", "application/json");
	      BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	      String inputLine;
	      StringBuffer response1=new StringBuffer();
	      while ((inputLine = in.readLine()) != null) {
	    	response1.append(inputLine);
	      }
	      System.out.println(response1);
	      in.close();
	      con.disconnect();
	      JSONObject myresponse=null;
	      int lengthOfJsonObject=0;
	      try {
	        myresponse=new JSONObject(response1.toString());
	        lengthOfJsonObject=myresponse.names().length();
	      }
	      catch(Exception e) {
	    	  System.out.println("Exce : "+e);
	      }
	      RequestDispatcher rd;
	      for(int i=0;i<lengthOfJsonObject;i++){
	     	 JSONObject myresponse1=new JSONObject(myresponse.getJSONObject(i+"").toString());
	     	 double ramUsed=myresponse1.getDouble("ramUsed");
	     	 double diskUsed=myresponse1.getDouble("diskUsed");
	     	 double cpuUtilization=myresponse1.getDouble("cpuUtilization");
	     	 String readDateTime=myresponse1.getString("readDateTime");
	     	 map=new LinkedHashMap<String, String>();
	     	 map.put("readDateTime", readDateTime);
	     	 map.put("ramUti", ramUsed+"");
	     	 map.put("diskUti", diskUsed+"");
	     	 map.put("cpukUti", cpuUtilization+"");
	     	 list.add(map);
	      }
	      request.setAttribute("list",list);
	      rd=request.getRequestDispatcher("jspfile17.jsp");
		  rd.include(request, response);
	}
}
