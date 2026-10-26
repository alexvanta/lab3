package ro.ulbs.ingprog.lab3;

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
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Integer counterGet = (Integer) request.getSession().getAttribute("CounterGetName");
            if (counterGet == null) {
                counterGet = 0;
            }

            String inc = request.getParameter("inc");
            if (inc != null && !inc.isEmpty()) {
                int valoareCrestere = Integer.parseInt(inc);
                counterGet = counterGet + valoareCrestere;
            }

            String dec = request.getParameter("dec");
            if (dec != null && !dec.isEmpty()) {
                int valoareScadere = Integer.parseInt(dec);
                counterGet = counterGet - valoareScadere;
            }

            request.getSession().setAttribute("CounterGetName", counterGet);
            if (counterGet == 0) {

                request.getSession().setAttribute("WarningGet", true);
            } else {
                request.getSession().setAttribute("WarningGet", false);
            }

            status(request, response);
        }
            @Override
                    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
                    Integer counterPost = (Integer) request.getSession().getAttribute("CounterPostName");
                    if(counterPost == null) {
                        counterPost = 0;
                    }

                    String inc = request.getParameter("inc");
                    if(inc != null && !inc.isEmpty()) {
                        int valoareCrestere = Integer.parseInt(inc);
                        counterPost = counterPost + valoareCrestere;
                    }

                    String dec = request.getParameter("dec");
                    if(dec != null && !dec.isEmpty()) {
                        int valoareScadere = Integer.parseInt(dec);
                        counterPost = counterPost - valoareScadere;
                    }

                    request.getSession().setAttribute("CounterPostName", counterPost);
                    if(counterPost == 0) {
                        request.getSession().setAttribute("WarningPost", true);
                    } else {
                        request.getSession().setAttribute("WarningPost", false);
                    }
                    status(request, response);
            }

        private void status(HttpServletRequest request, HttpServletResponse response) throws IOException {
            response.setContentType("text/html");
            PrintWriter printWriter = response.getWriter();
            printWriter.println("<h2>Welcome to Counter Servlet</h2>");

            Integer counterGet = (Integer) request.getSession().getAttribute("CounterGetName");
            printWriter.println("<br> Counter for GET: " + counterGet);

            Boolean warningGet = (Boolean) request.getSession().getAttribute("WarningGet");
            if(warningGet != null && warningGet == true) {
                printWriter.println(" <b style='color:red;'>WARNING GET</b>");
            }

            Integer counterPost = (Integer) request.getSession().getAttribute("CounterPostName");
            printWriter.println("<br> Counter for POST: " + counterPost);

            Boolean warningPost = (Boolean) request.getSession().getAttribute("WarningPost");
            if (warningPost != null && warningPost == true) {
                printWriter.println(" <b style='color:red;'>WARNING POST</b>");
            }
        }
    }

