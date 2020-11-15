package ru.itmo.wp.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;

public class StaticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        String[] uriArray = uri.split("\\+");
        ByteArrayOutputStream filesConcatStream = new ByteArrayOutputStream();
        for (String currentUri : uriArray) {
            File firstFile = new File(getServletContext().getRealPath("/static/" + currentUri));
            File secondFile = new File("/home/roma/work/itmo/web/hw03/2/wp3/src/main/webapp/static/" + currentUri);
            if (firstFile.isFile()) {
                processFile(filesConcatStream, firstFile);
            } else if (secondFile.isFile()) {
                processFile(filesConcatStream, secondFile);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
            }
        }
        if (response.getStatus() != HttpServletResponse.SC_NOT_FOUND) {
            response.setContentType(getContentTypeFromName(uriArray[0]));
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(filesConcatStream.toByteArray());
            outputStream.flush();
        }
    }

    private void processFile(ByteArrayOutputStream filesConcatStream, File file) throws IOException {
        byte[] fileBytes = Files.readAllBytes(file.toPath());
        filesConcatStream.write(fileBytes);
        filesConcatStream.flush();
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
