package was.v5.servlet;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.HttpServlet;

import java.io.IOException;

public class HomeServlet implements HttpServlet {
    @Override
    public void service(HttpRequest request, HttpResponse response) {
        response.writeBody("<h1> home </h1>");
        response.writeBody("<ul>");
        response.writeBody("<li> <a href='/site1'> SITE1 </a> </li>");
        response.writeBody("<li> <a href='/site2'> SITE2 </a> </li>");
        response.writeBody("<li> <a href='/search?q=hello'> SEARCH </a> </li>");
        response.writeBody("</ul>");
    }
}
