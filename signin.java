/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.RequestDispatcher;

@WebServlet(urlPatterns = {"/UslogVal"})
public class signin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            
          String ema=request.getParameter("email");
          String pas=request.getParameter("pa");
          System.out.println("names1: "+ema+"     "+pas);
            
        try{
           Class.forName("org.apache.derby.jdbc.ClientDriver");
       }
        catch(java.lang.ClassNotFoundException cn){
       System.out.println(cn.getMessage());
       }
     try{          
        String url="jdbc:derby://localhost:1527/ILPDFlogin;create=true;user=TaRa;password=tara";
        Connection c=DriverManager.getConnection(url);
        PreparedStatement st=c.prepareStatement("Select EMAIL,PASSWORD from APP.SIGNUP where EMAIL = ?");
        st.setString(1, ema);
        ResultSet rs=st.executeQuery();
        try{
        while (rs.next())
        {
            String em=rs.getString(1);
            String pa=rs.getString(2);
            request.setAttribute("email", em);
            request.setAttribute("pw", pa);
          
           
            System.out.println("names2: "+em+"   "+pa);
     
        if(ema.equals(em)&&(pas.equals(pa))){ 
            
            RequestDispatcher rd; 
            rd = request.getRequestDispatcher("index.html");
            rd.forward(request, response);
        }
        
        else{
 
            out.println("   NEW USER   ");
            out.println("<br>");
            out.println("<br>");
            out.println("Click Here for Go to ");
            out.println("<a href='Loginhtml.html'>");
            out.println("Sign UP");
            out.println("</a>");
            out.println("  page");
            
             }

}

}
    catch(Exception ev){
    out.println("Cannot validate right now..."+ev.getMessage());

    }
     }
     catch(Exception e){
        out.println("Can't get the connection Something wrong Tara "+e.getMessage());
     }
            
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
