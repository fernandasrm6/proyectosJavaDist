<%-- 
    Document   : index
    Created on : 21/02/2018, 07:49:32 PM
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
        <form action="GetSugggestion" method="POST">
    
        <h1>Bienvenido a CineMAS!</h1>
        Permitenos recomendarte una peli
        
        <table border="1">
      
            <tbody>
                <tr>
                    <td>Zona:</td>
                    <td><select name="zona">
                            <option>Centro</option>
                            <option>Sur</option>
                            <option>Poniente</option>
                        </select></td>
                </tr>
                <tr>
                    <td>Tipo de película: </td>
                    <td><select name="tipo">
                            <option>Comedia Romántica</option>
                            <option>Terror</option>
                            <option>Suspenso</option>
                        </select></td>
                </tr>
                <tr>
                    <td><input type="reset" value="Limpiar" /></td>
                    <td><input type="submit" value="Ver recomendación" /></td>
                </tr>
            </tbody>
        </table>
       </form>
    </body>
</html>
