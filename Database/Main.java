package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final String URL = "jdbc:postgresql://localhost:5432/TestingLang";
    private static final String USER = "postgres";
    private static final String PASSWORD = "passwordkosapostgresql";

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            List<Student> students = Arrays.asList(
                    new Student("Marvin", "Fabricante", 20, "BSCS"),
                    new Student("Alex", "Pacaldo", 23, "BSCS"),
                    new Student("Karol", "Tabio", 21, "Computer Science"),
                    new Student("Jeremi", "Garces", 35, "Tumigil na siya")
            );

            insertStudents(connection, students);

            connection.close();
            System.out.println("Data inserted successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection failed.");
            e.printStackTrace();
        }
    }

    private static void insertStudents(Connection connection, List<Student> students) {
        String insertSQL = "INSERT INTO Users (firstname, lastname, course, age) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            for (Student student : students) {
                preparedStatement.setString(1, student.firstName);
                preparedStatement.setString(2, student.lastName);
                preparedStatement.setString(3, student.course);
                preparedStatement.setInt(4, student.age);
                preparedStatement.executeUpdate();
                System.out.println("Inserted: " + student.firstName + " " + student.lastName);
            }
        } catch (SQLException e) {
            System.err.println("Error inserting students: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
