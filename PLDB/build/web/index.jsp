<%-- 
    Document   : index
    Created on : 5/03/2018, 07:23:04 PM
    Author     : fernandasramirezm
--%>


<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Staff!</h1>
        <%
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con
                    = DriverManager.getConnection(
                            "jdbc:derby://localhost:1527/MyFirstDatabase",
                            "root",
                            "1234");
            Statement query = con.createStatement();
            String i = "";
            String n = "";
           String b = "";
            try{
                i = request.getParameter("id");
                n = request.getParameter("name");
                b = request.getParameter("balance");
            }catch(Exception e){
                e.getStackTrace();
            }

            if (request.getParameter("add") != null) {
                query.executeUpdate("INSERT INTO CUSTOMERS VALUES ("+ i +",'" + n + "',"+ b+ ")");
                con.commit();
            } else if (request.getParameter("delete") != null) {
                query.executeUpdate("DELETE FROM CUSTOMERS WHERE id="+i);
                con.commit();
            } else if(request.getParameter("update") != null){
                query.executeUpdate("UPDATE CUSTOMERS SET name='"+n+"', balance="+b+" WHERE id="+i);
                con.commit();
            }

            //Show resutls
            ResultSet rs = query.executeQuery("SELECT * FROM CUSTOMERS");
            
            while (rs.next()) {
                out.println(" Id: " + rs.getInt("id"));
                out.println(" | Name: " + rs.getString("name"));
                out.println(" | Balance: " + rs.getString("balance"));
                out.print("<br>");
            }
            
            con.close();

        %>
        <h2>Add a record</h2>
        <form action="">
            <table border="1">
                <tbody>
                    <tr>
                        <td>ID</td>
                        <td><input type="text" name="id" value="" /></td>
                        <td>Name</td>
                        <td><input type="text" name="name" value="" /></td>
                        <td>Balance</td>
                        <td><input type="text" name="balance" value="" /></td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="OK" name="add"/>
        </form>
        <h2>Delete a record</h2>
        <form action="">
            <table border="1">
                <tbody>
                    <tr>
                        <td>ID</td>
                        <td><input type="text" name="id" value="" /></td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="OK" name="delete" />
        </form>
        <h2>Update a</h2>
        <form action="">
            <table border="1">
                <tbody>
                    <tr>
                        <td>ID</td>
                        <td><input type="text" name="id" value="" /></td>
                        <td>Name</td>
                        <td><input type="text" name="name" value="" /></td>
                        <td>Balance</td>
                        <td><input type="text" name="balance" value="" /></td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="OK" name="update"/>
        </form>
    </body>
</html>
