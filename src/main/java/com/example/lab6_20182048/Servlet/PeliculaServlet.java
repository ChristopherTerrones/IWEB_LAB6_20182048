package com.example.lab6_20182048.Servlet;

import com.example.lab6_20182048.Beans.Pelicula;
import com.example.lab6_20182048.Daos.PeliculaDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

@WebServlet(name = "Pelicula", value = "/Pelicula")
public class PeliculaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("a") == null ? "listar" : request.getParameter("a");
        PeliculaDao peliculaDao = new PeliculaDao();

        switch (action) {
            case "listar" -> {
                ArrayList<Pelicula> listapeliculas = peliculaDao.obtenerPeliculas();

                request.setAttribute("peliculas", listapeliculas);
                request.getRequestDispatcher("listaPeliculas.jsp").forward(request, response);
            }
            case "view" -> {
                int idPelicula = Integer.parseInt(request.getParameter("id"));
                Pelicula pelicula = peliculaDao.buscarPorID(idPelicula);
                request.setAttribute("pelicula", pelicula);
                request.getRequestDispatcher("viewPelicula.jsp").forward(request, response);
            }
        }

    }
}

