package controller;


import controller.commands.Command;
import controller.commands.CommandFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.util.Constants.REDIRECT_TO;

@WebServlet(urlPatterns = "/hotel/*")
public class MainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {

        String commandAttribute = (String) request.getAttribute("command");
        Command command = CommandFactory.createCommand(commandAttribute);
        String page = command.execute(request, response);
        if (page.startsWith(REDIRECT_TO)) {
            request.setAttribute("command", page.replace(REDIRECT_TO, ""));
            processRequest(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
    }
}
