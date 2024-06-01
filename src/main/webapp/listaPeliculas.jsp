<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab6_20182048.Beans.Pelicula" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Películas</title>
  </head>
  <body>
    <h1>Lista de Películas</h1>
    <form method="get" action="Pelicula">
      <input type="text" name="titulo" placeholder="Buscar por título">
      <input type="submit" value="Buscar">
    </form>
    <table border="1">
      <tr>
        <th>Título</th>
        <th>Director</th>
        <th>Año de Publicación</th>
        <th>Rating</th>
        <th>Box Office</th>
        <th>Género</th>
        <th>Actores</th>
      </tr>
      <%
        ArrayList<Pelicula> peliculas = (ArrayList<Pelicula>) request.getAttribute("peliculas");
        for (Pelicula pelicula : peliculas) {
      %>
      <tr>
        <td><a href="<%=request.getContextPath()%>/Pelicula?a=<%= pelicula.getIdPelicula() %>"><%= pelicula.getTitulo() %></a></td>
        <td><%= pelicula.getDirector() %></td>
        <td><%= pelicula.getAnoPublicacion() %></td>
        <td><%= pelicula.getRating() %></td>
        <td><%= pelicula.getBoxOffice() %></td>
        <td><%= pelicula.getGenero().getNombre() %></td>
        <td><a href="<%=request.getContextPath()%>/Actor?a=lista&id=<%= pelicula.getIdPelicula() %>">Actores</a></td>
      </tr>
      <%
        }
      %>
    </table>
  </body>
</html>

