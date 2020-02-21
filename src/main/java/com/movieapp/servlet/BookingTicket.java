package com.movieapp.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.movieapp.dao.impl.TicketBookingDAOImpl;
import com.movieapp.model.TicketBooking;

/**
 * Servlet implementation class TicketBooking
 */
@WebServlet("/BookingTicket")
public class BookingTicket extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int tot= 0;
		//1. GEt Form Data 
		String seats = request.getParameter("numberofseats");
		int movieTheatreId = Integer.parseInt(request.getParameter("Movietheatreid"));
		String showDate = request.getParameter("showdate");
		//String amount=request.getParameter("amount");
		System.out.println("Params:" + seats +"-" + movieTheatreId +"-" + showDate);
		PrintWriter out=response.getWriter();
		// Get data from session
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("USER_ID");
		System.out.println("UserId="+ userId);
		
		HttpSession ses = request.getSession();
		ses.setAttribute("show_date",showDate);
		
		HttpSession ses1 = request.getSession();
		ses1.setAttribute("no_of_seats",seats);
		
		
		
		try {
			
		
			//Store data in model obj			
			TicketBooking tb=new TicketBooking();
			TicketBookingDAOImpl impl=new TicketBookingDAOImpl();
			tb.setBookedSeats(Integer.parseInt(seats));
			tb.setUsersId(userId);
			tb.setMovieTheaterId(movieTheatreId);
			out.print(userId);

			out.print(movieTheatreId);
			tb.setShowDate(LocalDate.parse(showDate));
			
			int a=impl.getPrice(tb.getMovieTheaterId());
			out.print(a);
			tot  =tb.getBookedSeats()*a;
			tb.setAmount(tot);
			HttpSession sesion = request.getSession();
			sesion.setAttribute("tot_amt", tot);
    		impl.addBookingDetails(tb);
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		 response.sendRedirect("AmountPaid.jsp?totalAmount=" + tot);
		
	
       

	
	}

	
}
