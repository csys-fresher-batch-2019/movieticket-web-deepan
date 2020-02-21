package com.movieapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movieapp.DbException;
import com.movieapp.dao.TicketBookingDAO;
import com.movieapp.dao.impl.TicketBookingDAOImpl;


@WebServlet("/CancelTicketServlet")
public class CancelTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String bookedId = request.getParameter("bookedId");
		System.out.println(bookedId);

		TicketBookingDAO impl = new TicketBookingDAOImpl();
		try {
			impl.cancelTicket(bookedId);
		} catch (DbException e) {
			e.printStackTrace();
		}
		
		PrintWriter out=response.getWriter();
		out.print("Your Ticeket is successfully cancelled");
		response.sendRedirect("HomeMovies.jsp");
	}

}
