package com.example.lab6_20182048.Servlet;

import com.example.lab6_20182048.Beans.Actor;
import com.example.lab6_20182048.Beans.Pelicula;
import com.example.lab6_20182048.Daos.ActorDao;
import com.example.lab6_20182048.Daos.PeliculaDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

@WebServlet(name = "Actor", value = "/Actor")
public class ActorServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("a") == null ? "listar" : request.getParameter("a");
        ActorDao actorDao = new ActorDao();
        switch (action) {
            case "listar" -> {
                int idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
                ArrayList<Actor> actores = actorDao.obtenerActoresPorPelicula(idPelicula);

                request.setAttribute("actores", actores);
                request.getRequestDispatcher("listaActores.jsp").forward(request, response);
            }
        }
    }
}

