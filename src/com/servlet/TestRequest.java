package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.*;

@WebServlet("/testrequest")
public class TestRequest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpURLConnection conn = null;
        URL url;

        try {
            url = new URL("http://192.168.1.30:8080/api/test");

            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            //conn.setRequestProperty("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYXJ5bGwiLCJyb2xlcyI6WyJST0xFX1NVUEVSX0FETUlOIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hcGkvbG9naW4iLCJleHAiOjE2MzE4NTEzMDZ9.1wd4CNvIoAKdQ6JhCTUK0RTpT9DLxEaZC-lRSfx15yI");
            conn.setRequestProperty("Authorization", "Bearer "+request.getParameter("token"));
            conn.setRequestProperty("Cookie", "REFRESH_TOKEN=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYXJ5bGwiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvYXBpL2xvZ2luIiwiZXhwIjoxNjMxODUyNTA2fQ.iZju6NfZq_kdCYFN7cZg00uNN_a2PRCt-TTB3Qpm4uY");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            int statusCode = conn.getResponseCode();
            System.out.println(statusCode);

            InputStream connIStream = conn.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connIStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            connIStream.close();
            bufferedReader.close();

            out.println(stringBuilder.toString());

        } catch (MalformedURLException | ConnectException | SocketTimeoutException ne) {
            out.println(ne.toString());
        } catch (Exception e) {
            out.println(e.toString());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

    }
}
