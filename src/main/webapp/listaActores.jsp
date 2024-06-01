<%@ page import="com.example.lab6_20182048.Beans.Actor" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    ArrayList<Actor> actores = (ArrayList<Actor>) request.getAttribute("actores");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title><%= request.getAttribute("tituloPelicula") %></title>
    </head>
    <body>
        <h1><%= request.getAttribute("tituloPelicula") %></h1>
        <table border="1">
            <tr>
                <th>idActor</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Año de Nacimiento</th>
                <th>Ganador Premio Oscar</th>
            </tr>
            <%
                for (Actor actor : actores) {
            %>
            <tr>
                <td><%= actor.getIdActor() %></td>
                <td><%= actor.getNombre() %></td>
                <td><%= actor.getApellido() %></td>
                <td><%= actor.getAnoNacimiento() %></td>
                <td><%= actor.isPremioOscar() ? "True" : "False" %></td>
            </tr>
            <%
                }
            %>
        </table>
    </body>
</html>

