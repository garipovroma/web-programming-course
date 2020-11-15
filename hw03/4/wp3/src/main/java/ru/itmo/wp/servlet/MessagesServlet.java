package ru.itmo.wp.servlet;

import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessagesServlet extends HttpServlet {
    private List<Message> messages = Collections.synchronizedList(new ArrayList<>());
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        response.setContentType("application/json");
        switch (uri) {
            case "/message/auth": {
                if (request.getParameter("user") == null) {
                    session.setAttribute("user", "");
                }
                session.setAttribute("user", request.getParameter("user"));
                String user = new Gson().toJson(request.getParameter("user"));
                printJson(response, user);
                break;
            }
            case "/message/findAll": {
                String json = new Gson().toJson(messages);
                printJson(response, json);
                break;
            }
            case "/message/add": {
                String user = (String) request.getSession().getAttribute("user");
                String text = request.getParameter(("text"));
                messages.add(new Message(user, text));
                break;
            }
        }
    }
    private void printJson(HttpServletResponse response, String json) throws IOException {
        try (PrintWriter writer = response.getWriter()) {
            writer.print(json);
            writer.flush();
        }
    }
    private static class Message {
        private final String user, text;

        public Message(String user, String text) {
            this.user = user;
            this.text = text;
        }

        @Override
        public String toString() {
            return user + ":" + text;
        }
    }
}
