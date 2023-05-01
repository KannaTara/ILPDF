/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//import com.itextpdf.text.BadElementException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Part;
import javax.servlet.http.*;
import javax.swing.JOptionPane;
//import org.apache.commons.io.output.DeferredFileOutputStream;
//import com.itextpdf.text.Image;
//import com.itextpdf.text.pdf.PdfWriter;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name="convertingfile", urlPatterns={"/convertingfile"})
@MultipartConfig(fileSizeThreshold = 1024*1024*2,
        maxFileSize=1024*1024*10, 
        maxRequestSize=1024*1024*50)

public class webptoimg extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet convertingfile</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet convertingfile at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
           
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
       
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest requ, HttpServletResponse resp)
            throws ServletException, IOException {
       try (PrintWriter out = resp.getWriter()) {
       
        String temppath = ("C:\\Users\\ELCOT\\Documents\\NetBeansProjects\\Computaria\\web\\uplofiles");  
     // for(Part s:requ.getParts()){ 
        Part s=requ.getPart("ip");
     // String[] i=requ.getParameterValues("ip");
     // long ii =s.getSize();
     // out.println("filesize "+ii);
     // out.println("files "+i);
        String inputImage = s.getSubmittedFileName();
        out.println("inputimg  "+inputImage);
        FileOutputStream fops;
        String imgpa,outputImage;
        String formatType=requ.getParameter("png");//"png";//requ.getParameter("demo");
        out.println("formatType  "+formatType);
        outputImage = "C:\\Users\\ELCOT\\Videos\\New folder\\file."+formatType;                                                                                                                                                                                                                         
      try{
            fops=new FileOutputStream(new File(temppath+File.separator+inputImage));
            out.println("Temp path file : "+fops);
            imgpa=temppath+File.separator+inputImage;
            InputStream is=s.getInputStream();
            byte [] ph= new byte[is.available()];
            is.read(ph);
            fops.write(ph);
            fops.flush();
            FileInputStream iis=new FileInputStream(imgpa);
            FileOutputStream os=new FileOutputStream(outputImage);
            BufferedImage ii =ImageIO.read(iis);
            boolean result = ImageIO.write(ii,formatType,os);
            os.close();
            iis.close();
         // boolean result = fileconvert.convertImg(inputImage, outputImage, formatType);
            if(result){
                System.out.println( "Success");
                out.println( "Success");
            }
            else{
                System.out.println("Failure");
                out.println("Failure");
             }
       
          
     /*      
                        Document con = new Document(); 
                        PdfWriter.getInstance(con, new FileOutputStream(outputImage));
                        con.open();
                        out.println("format  "+con.getRole());
                        Image c;
                        c = Image.getInstance(imgpa);
                        con.add(c);
                        con.close();
                        out.println("Successfully Converted JPG to PDF in iText");*/
      }     
           catch(IOException e){
       System.out.println( "error "+e.getMessage());
       out.println("Error msg "+e.getMessage());
       }              
    }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
