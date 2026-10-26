package org.example.lab3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CounterServlet", value = "/CounterServlet")
public class CounterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer counterGet = (Integer)
                request.getSession().getAttribute("CounterGetName");
        if (counterGet == null) {
            counterGet = 0;
        }
        String increment = request.getParameter("inc");
        String decrement = request.getParameter("dec");
        if (increment != null && !increment.trim().isEmpty()) {
            try{
                int valInc = Integer.parseInt(increment);
                counterGet += valInc;
            }catch (NumberFormatException e){

            }
        }
        if(decrement != null && !decrement.trim().isEmpty()){
            try{
                int valDec = Integer.parseInt(decrement);
                counterGet -= valDec;
            }catch(NumberFormatException e){

            }
        }
        request.getSession().setAttribute("CounterGetName", counterGet);

        if(counterGet == 0){
            request.getSession().setAttribute("WarningGet", true);
        }else{
            request.getSession().removeAttribute("WarningGet");
        }

        status(request, response);


    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer counterPost = (Integer)
                request.getSession().getAttribute("CounterPostName");
        if (counterPost == null) {
            counterPost = 0;
        }
        String increment = request.getParameter("inc");
        String decrement = request.getParameter("dec");
        if (increment != null && !increment.trim().isEmpty()) {
            try{
                int valInc = Integer.parseInt(increment);
                counterPost += valInc;
            }catch (NumberFormatException e){

            }
        }
        if(decrement !=null && !decrement.trim().isEmpty()){
            try{
                int valDec = Integer.parseInt(decrement);
                counterPost -= valDec;
            }catch(NumberFormatException e){

            }
        }
        request.getSession().setAttribute("CounterPostName", counterPost);

        if(counterPost == 0){
            request.getSession().setAttribute("WarningPost", true);
        }else{
            request.getSession().removeAttribute("WarningPost");
        }

        status(request, response);
    }

    private void status(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        printWriter.println("<h2>Welcome to Counter Servlet</h2>");
        Integer counterGet = (Integer)
                request.getSession().getAttribute("CounterGetName");
        printWriter.println("<br> Counter for GET: " + counterGet);
        Integer counterPost = (Integer)
                request.getSession().getAttribute("CounterPostName");
        printWriter.println("<br> Counter for POST: " + counterPost);

        Boolean warningGet = (Boolean) request.getSession().getAttribute("WarningGet");
        Boolean warningPost = (Boolean) request.getSession().getAttribute("WarningPost");

        printWriter.println("<br><br>");

        if (warningGet != null && warningGet) {
            printWriter.println("<strong style='color:red;'>WARNING GET</strong><br>");
        }

        if (warningPost != null && warningPost) {
            printWriter.println("<strong style='color:red;'>WARNING POST</strong><br>");
        }

    }
}