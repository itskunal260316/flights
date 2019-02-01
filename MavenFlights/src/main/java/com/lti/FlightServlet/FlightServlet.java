package com.lti.FlightServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lti.Flight.model.Flight;

import travel_globe.ReservationDao;

/**
 * Servlet implementation class FlightServlet
 */
@WebServlet

public class FlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       List<Flight> fl= new ArrayList();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FlightServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String source = request.getParameter("Depart_From");
		String dest = request.getParameter("Destination");
		
	    ReservationDao dao = new ReservationDao();
	    List<Flight> fl= dao.Search(source, dest);
	   
	    
	    ObjectMapper mapper=new ObjectMapper(); //used for conversion from java to json and vice versa
	      
		String flJSON  = mapper.writeValueAsString(fl);
		System.out.println(source+" "+dest); 
		response.setContentType("application/json");
		
		//to restrict or allow the acess.
		
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		response.setHeader("Access-Control-Allow-Methods","GET");
		//one server cannot talk to another bowser directly
		
		PrintWriter out = response.getWriter();
		out.write(flJSON);
//		response.sendRedirect("/viewProducts.html");

	    
	}


}
