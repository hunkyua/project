package com.hunky;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hunky on 06.11.2015.
 */
public class DAO {
    private static String url = "jdbc:mysql://localhost:3306/test";
    private static String username = "root";
    private static String password = "1336";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(url, username, password);
    }

    public static List<Post> getPosts() throws SQLException, ClassNotFoundException {
        try (Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement("SELECT id, txt from posts");
        ResultSet resultSet = ps.executeQuery();) {

            List<Post> posts = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String txt = resultSet.getString(2);
                posts.add(new Post(id, txt));
            }
            return posts;
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println(getPosts());
    }

    public static void deletePost (int id) throws SQLException, ClassNotFoundException {
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE from posts WHERE id=?");
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public static void addPost(String txt) throws SQLException, ClassNotFoundException {
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement("INSERT INTO posts (txt) VALUES (?)");
        ) {
            ps.setString(1, txt);
            ps.executeUpdate();
        }
    }
}
