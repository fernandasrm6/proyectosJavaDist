/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fernandasramirezm
 */
public class GetSugggestion extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try  {
             PrintWriter out = response.getWriter();
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GetSugggestion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Nuestras recomendaciones son: </h1>");
            out.println("<h2> Los cines más cercanos a tu ubicación son: </h2>");
              if(request.getParameter("zona")!=null ){
                   String nextJSP = "/CinemaSuggestions";
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP); 
                    dispatcher.include(request, response); //si esta bien sigue a welcome.jsp
                     
                }
              else{
                  out.println("Te fallo");
              }
                
            out.println("<h2> Las películas del género que te gusta son: </h2>");            
           // if(request.getParameter("tipo")!=null ){
                   String nextJSP = "/MovieSuggestion";
                    RequestDispatcher dispatcher2 = getServletContext().getRequestDispatcher(nextJSP); 
                    dispatcher2.include(request, response); //si esta bien sigue a welcome.jsp
                     
              //  }
            out.println("</body>");
            out.println("</html>");
        }
        catch (Exception e) {
                e.printStackTrace();
            }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
