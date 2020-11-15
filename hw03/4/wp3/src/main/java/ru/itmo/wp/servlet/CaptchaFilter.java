package ru.itmo.wp.servlet;

import ru.itmo.wp.util.ImageUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;

public class CaptchaFilter extends HttpFilter {
    private static final Random random = new Random();
    private static final String captchaSuccessStatus = "OK";

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if ("GET".equals(request.getMethod())) {
            HttpSession session = request.getSession();
            String expectedCaptcha = (String) session.getAttribute("captcha");
            String foundCaptcha = request.getParameter("captcha");
            if (expectedCaptcha == null) {
                sendNewCaptcha(request, response);
            } else if (expectedCaptcha.equals(captchaSuccessStatus)) {
                chain.doFilter(request, response);
            } else if (foundCaptcha == null) {
                reSendCaptcha(request, response, expectedCaptcha);
            } else if (foundCaptcha.equals(expectedCaptcha)) {
                session.setAttribute("captcha", captchaSuccessStatus);
                chain.doFilter(request, response);
            } else {
                sendNewCaptcha(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }
    private void sendNewCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processCaptcha(request, response, null);
    }
    private void reSendCaptcha(HttpServletRequest request, HttpServletResponse response, String previousCaptcha) throws IOException, ServletException {
        processCaptcha(request, response, previousCaptcha);
    }
    private void processCaptcha(HttpServletRequest request, HttpServletResponse response, String previousCaptcha) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String captchaKey = (previousCaptcha == null ? Integer.toString(random.nextInt(900) + 100) : previousCaptcha);
        byte[] img = Base64.getEncoder().encode(ImageUtils.toPng(captchaKey));
        try (PrintWriter writer = response.getWriter()) {
            writer.print(getCaptchaForm(img));
            writer.flush();
            session.setAttribute("captcha", captchaKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String getCaptchaForm(byte[] img) {
        return "<!DOCTYPE html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Captcha</title>\n" +
                "    <div>Send Captcha</div>\n" +
                "</head>\n" +
                "<body/>" +
                "<img src=\"data:image/png;base64," + new String(img, StandardCharsets.UTF_8) + "\"></img>\n" +
                "<form>\n" +
                "    <input type=\"text\" name=\"captcha\" id=\"captcha\">\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>";
    }
}
