<%-- 
    Document   : index
    Created on : 4/04/2018, 08:00:54 PM
    Author     : fernandasramirezm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <%
        
        
        
        %>
    <%-- start web service invocation --%><hr/>
    <%
    try {
	webservicesclient.MyWebService_Service service = new webservicesclient.MyWebService_Service();
	webservicesclient.MyWebService port = service.getMyWebServicePort();
	 // TODO initialize WS operation arguments here
	int i = 8;
	int j = 8;
	// TODO process result here
	int result = port.add(i, j);
	out.println("Result = "+result);
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%><hr/>
    </body>
</html>
