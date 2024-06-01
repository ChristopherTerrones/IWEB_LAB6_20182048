package com.example.lab6_20182048.Daos;

import com.example.lab6_20182048.Beans.Genero;
import com.example.lab6_20182048.Beans.Pelicula;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;

public class PeliculaDao {
    private static String user = "root";
    private static String pass = "root";
    private static String url = "jdbc:mysql://localhost:3306/mydb";

    public ArrayList<Pelicula> obtenerPeliculas() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        ArrayList<Pelicula> listapeliculas = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT p.idPelicula, p.titulo, p.director, p.anoPublicacion, p.rating, p.boxOffice, g.idGenero, g.nombre FROM Pelicula p JOIN Genero g ON p.idGenero = g.idGenero")){
            while (rs.next()){
                Pelicula pelicula = new Pelicula();

                pelicula.setIdPelicula(rs.getInt("idPelicula"));
                pelicula.setTitulo(rs.getString("titulo"));
                pelicula.setDirector(rs.getString("director"));
                pelicula.setAnoPublicacion(rs.getInt("anoPublicacion"));
                pelicula.setRating(rs.getDouble("rating"));
                pelicula.setBoxOffice(rs.getDouble("boxOffice"));

                Genero genero = new Genero();
                genero.setIdGenero(rs.getInt("idGenero"));
                genero.setNombre(rs.getString("nombre"));

                pelicula.setGenero(genero);
                listapeliculas.add(pelicula);
            }
        }catch (SQLException e) {
            System.out.println("No se pudo realizar la busqueda");
        }
        listapeliculas.sort(new Comparator<Pelicula>() {
            @Override
            public int compare(Pelicula p1, Pelicula p2) {
                int ratingComparison = Double.compare(p2.getRating(), p1.getRating());
                if (ratingComparison == 0) {
                    return Double.compare(p2.getBoxOffice(), p1.getBoxOffice());
                }
                return ratingComparison;
            }
        });

        return listapeliculas;

    }
    public Pelicula buscarPorID(int idPelicula) {
        Pelicula pelicula = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String sql = "SELECT p.idPelicula, p.titulo, p.director, p.anoPublicacion, p.rating, p.boxOffice, g.idGenero, g.nombre FROM Pelicula p JOIN Genero g ON p.idGenero = g.idGenero WHERE p.idPelicula = ?";

        try (Connection connection = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = connection.prepareStatement(sql);) {

            pstmt.setInt(1, idPelicula);

            try (ResultSet rs = pstmt.executeQuery();) {

                if (rs.next()) {
                    pelicula = new Pelicula();
                    pelicula.setIdPelicula(rs.getInt("idPelicula"));
                    pelicula.setTitulo(rs.getString("titulo"));
                    pelicula.setDirector(rs.getString("director"));
                    pelicula.setAnoPublicacion(rs.getInt("anoPublicacion"));
                    pelicula.setRating(rs.getDouble("rating"));
                    pelicula.setBoxOffice(rs.getDouble("boxOffice"));

                    Genero genero = new Genero();
                    genero.setIdGenero(rs.getInt("idGenero"));
                    genero.setNombre(rs.getString("nombre"));

                    pelicula.setGenero(genero);

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pelicula;

    }

}
