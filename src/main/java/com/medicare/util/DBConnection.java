package com.medicare.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
  private static final String URL =
      setting(
          "medicare.db.url", "MEDICARE_DB_URL",
          "jdbc:mysql://localhost:3306/medicare_db?useSSL=false&allowPublicKeyRetrieval=true");
  private static final String USER =
      setting("medicare.db.user", "MEDICARE_DB_USER", "root"); // ඔයාගේ MySQL Username එක
  private static final String PASSWORD =
      setting(
          "medicare.db.password", "MEDICARE_DB_PASSWORD", ""); // ඔයාගේ MySQL Password එක (තිබේ නම් ඇතුළත් කරන්න)

  private static String setting(String property, String environment, String fallback) {
    String value = System.getenv(environment);
    return System.getProperty(property, value == null ? fallback : value);
  }

  static {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
  }
}
