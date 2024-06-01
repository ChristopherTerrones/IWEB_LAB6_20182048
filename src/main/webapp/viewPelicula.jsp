<%@ page import="com.example.lab6_20182048.Beans.Pelicula" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title><%= ((Pelicula) request.getAttribute("pelicula")).getTitulo() %></title>
        <style>
            .center-text {
                text-align: center;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <%
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
            currencyFormat.setMaximumFractionDigits(0);
            Pelicula pelicula = (Pelicula) request.getAttribute("pelicula");
        %>
        <h1><%= pelicula.getTitulo() %></h1>
        <table border="1">
            <tr>
                <td class="center-text">idPelicula</td>
                <td><%= pelicula.getIdPelicula() %></td>
            </tr>
            <tr>
                <td class="center-text">Director</td>
                <td><%= pelicula.getDirector() %></td>
            </tr>
            <tr>
                <td class="center-text">Año Publicacion</td>
                <td><%= pelicula.getAnoPublicacion() %></td>
            </tr>
            <tr>
                <td class="center-text">Rating</td>
                <td><%= pelicula.getRating() %>/10</td>
            </tr>
            <tr>
                <td class="center-text">BoxOffice</td>
                <td><%= currencyFormat.format(pelicula.getBoxOffice()) %></td>
            </tr>
            <tr>
                <td class="center-text">Genero</td>
                <td><%= pelicula.getGenero().getNombre() %></td>
            </tr>
            <tr>
                <td class="center-text">Actores</td>
                <td><a href="<%=request.getContextPath()%>/Actor?a=ver&id=<%= pelicula.getIdPelicula() %>"> Ver Actores</a></td>
            </tr>
        </table>
    </body>
</html>

