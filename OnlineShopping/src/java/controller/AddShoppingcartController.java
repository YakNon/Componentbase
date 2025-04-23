/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Shoppingcart;
import model.ShoppingcartPK;
import model.ShoppingcartTable;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "AddShoppingcartController", urlPatterns = {"/AddShoppingcartController"})
public class AddShoppingcartController extends HttpServlet {

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
            throws ServletException, IOException{
        HttpSession session = request.getSession();
        try{
        String[] selectedCheckboxes = request.getParameterValues("select");//[5,6]
        String[] qtySelected = request.getParameterValues("qty");//[,,,,2,5,]
        int cartId = getLastCartId();

        //if (selectedCheckboxes != null) {
            for (int i = 0; i<selectedCheckboxes.length;i++ ) {
                String movieIdstr = selectedCheckboxes[i];//.replaceAll("/", "");
                int movieId = Integer.parseInt(movieIdstr);
                if(!qtySelected[movieId-1].equals("")){
                       Shoppingcart shop = new Shoppingcart();
                        ShoppingcartPK spk = new ShoppingcartPK(cartId, movieId);
                        shop.setShoppingcartPK(spk);
                        Integer qty = Integer.parseInt(qtySelected[movieId-1]);
                        shop.setQuantity(qty);
                        int rowInserted = ShoppingcartTable.insertShoppingcart(shop);
                        request.setAttribute("rowInserted", rowInserted);
                }
            }
        //}
        //session.setAttribute("cartId", cartId);
        /*response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet testAdd</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet testAdd at " + request.getContextPath() + "</h1>");
            out.println("<h2>Servlet testAdd at " + Arrays.toString(selectedCheckboxes) + "</h2>");
            out.println("<h2>Servlet testAdd at " + Arrays.toString(qtySelected) + "</h2>");
            out.println("</body>");
            out.println("</html>");
        }*/
        
        session.setAttribute("cartId", cartId);
        request.getRequestDispatcher("shoppingcartPages.jsp").forward(request, response);
        }catch(Exception e){
            request.setAttribute("errMsg", e);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
    public int getLastCartId(){
        ShoppingcartTable st = new ShoppingcartTable();
        List<Shoppingcart> shop = st.findAllShoppingcart();
        Shoppingcart last = shop.get(shop.size()-1);
        int lasted = last.getShoppingcartPK().getCartId();
        return lasted+1;
    }
        /*protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //HttpSession session = request.getSession();
        String[] selectedCheckboxes = request.getParameterValues("select");
        int cartId = getLastCartId();
        //if (selectedCheckboxes != null) {
            for (String checkboxValue : selectedCheckboxes) {
                String movieIdstr = checkboxValue.replaceAll("/", "");
                if ((movieIdstr!= null) && (!movieIdstr.isEmpty())) {
                    try {
                        int movieId = Integer.parseInt(movieIdstr);
                        Shoppingcart shop = new Shoppingcart();
                        ShoppingcartPK spk = new ShoppingcartPK(cartId, movieId);
                        shop.setShoppingcartPK(spk);
                        Integer qty = Integer.parseInt(request.getParameter("qty"));
                        shop.setQuantity(qty);
                        int rowInserted = ShoppingcartTable.insertShoppingcart(shop);
                        request.setAttribute("rowInserted", rowInserted);
                    } catch (NumberFormatException e) {
                        
                    }
                }
            }
        //}
        //session.setAttribute("cartId", cartId);
        request.setAttribute("cartId", cartId);
        request.getRequestDispatcher("shoppingcartPages.jsp").forward(request, response);
    }*/

    

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
