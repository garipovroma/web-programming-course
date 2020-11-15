package ru.itmo.wp.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class StaticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        File firstAttemptFile = new File("/home/roma/work/itmo/web/hw03/1/wp3/src/main/webapp/static/" + uri);
        File secondAttemptFile = new File(getServletContext().getRealPath("/static" + uri));
        if (firstAttemptFile.isFile()) {
            processFile(response, uri, firstAttemptFile);
        } else if (secondAttemptFile.isFile()) {
            processFile(response, uri, secondAttemptFile);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void processFile(HttpServletResponse response, String uri, File file) throws IOException {
        response.setContentType(getContentTypeFromName(uri));
        OutputStream outputStream = response.getOutputStream();
        Files.copy(file.toPath(), outputStream);
        outputStream.flush();
    }

    private String getContentTypeFromName(String name) {
        name = name.toLowerCase();

        if (name.endsWith(".png")) {
            return "image/png";
        }

        if (name.endsWith(".jpg")) {
            return "image/jpeg";
        }

        if (name.endsWith(".html")) {
            return "text/html";
        }

        if (name.endsWith(".css")) {
            return "text/css";
        }

        if (name.endsWith(".js")) {
            return "application/javascript";
        }

        throw new IllegalArgumentException("Can't find content type for '" + name + "'.");
    }
}
