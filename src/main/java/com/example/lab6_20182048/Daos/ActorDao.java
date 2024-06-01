package com.example.lab6_20182048.Daos;

import com.example.lab6_20182048.Beans.Actor;
import com.example.lab6_20182048.Beans.Pelicula;

import java.sql.*;
import java.util.ArrayList;

public class ActorDao {
    private static String user = "root";
    private static String pass = "root";
    private static String url = "jdbc:mysql://localhost:3306/mydb";

    public ArrayList<Actor> obtenerActoresPorPelicula(int idPelicula) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        ArrayList<Actor> actores = new ArrayList<>();
        String sql = "SELECT a.idActor, a.nombre, a.apellido, a.anoNacimiento, a.premioOscar FROM Actor a JOIN Protagonistas p ON a.idActor = p.idActor WHERE p.idPelicula = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPelicula);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Actor actor = new Actor();
                    actor.setIdActor(rs.getInt("idActor"));
                    actor.setNombre(rs.getString("nombre"));
                    actor.setApellido(rs.getString("apellido"));
                    actor.setAnoNacimiento(rs.getInt("anoNacimiento"));
                    actor.setPremioOscar(rs.getBoolean("premioOscar"));
                    actores.add(actor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return actores;
    }
    public Pelicula obtenerPeliculaPorId(int idPelicula) {
        Pelicula pelicula = null;
        String sql = "SELECT p.idPelicula, p.titulo FROM Pelicula p WHERE p.idPelicula = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPelicula);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                pelicula = new Pelicula();
                pelicula.setIdPelicula(rs.getInt("idPelicula"));
                pelicula.setTitulo(rs.getString("titulo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pelicula;
    }
}
