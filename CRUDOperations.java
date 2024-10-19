package preparedstatement;
import java.sql.*;
import java.util.Scanner;

public class CRUDOperations {

	 public static final String URL = "jdbc:mysql://localhost:3306/student";
	 public static final String USERNAME = "root";
	 public static final String PASSWORD = "groot";
    
    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Scanner scanner = new Scanner(System.in)) {
            
            while (true) {
                System.out.println("Select operation:");
                System.out.println("1. Create");
                System.out.println("2. Read");
                System.out.println("3. Update");
                System.out.println("4. Delete");
                System.out.println("5. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                switch (choice) {
                    case 1:
                        // Create
                        System.out.println("Enter student ID:");
                        int createId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.println("Enter student name:");
                        String createName = scanner.nextLine();
                        createRecord(con, createId, createName);
                        break;
                    
                    case 2:
                        // Read
                        System.out.println("Enter student ID to read:");
                        int readId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        readRecord(con, readId);
                        break;
                    
                    case 3:
                        // Update
                        System.out.println("Enter student ID to update:");
                        int updateId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.println("Enter new student name:");
                        String updateName = scanner.nextLine();
                        updateRecord(con, updateId, updateName);
                        break;
                    
                    case 4:
                        // Delete
                        System.out.println("Enter student ID to delete:");
                        int deleteId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        deleteRecord(con, deleteId);
                        break;
                    
                    case 5:
                        // Exit
                        System.out.println("Exiting...");
                        return;
                    
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void createRecord(Connection con, int id, String name) throws SQLException {
        String insertQuery = "INSERT INTO dents (sid, sname) VALUES (?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(insertQuery)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            int rowsInserted = pstmt.executeUpdate();
            System.out.println("Rows inserted: " + rowsInserted);
        }
    }
    
    public static void readRecord(Connection con, int id) throws SQLException {
        String selectQuery = "SELECT * FROM dents WHERE sid = ?";
        try (PreparedStatement pstmt = con.prepareStatement(selectQuery)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("ID: " + rs.getInt("sid"));
                    System.out.println("Name: " + rs.getString("sname"));
                } else {
                    System.out.println("No record found with ID " + id);
                }
            }
        }
    }
    
    public static void updateRecord(Connection con, int id, String newName) throws SQLException {
        String updateQuery = "UPDATE dents SET sname = ? WHERE sid = ?";
        try (PreparedStatement pstmt = con.prepareStatement(updateQuery)) {
            pstmt.setString(1, newName);
            pstmt.setInt(2, id);
            int rowsUpdated = pstmt.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);
        }
    }
    
    public static void deleteRecord(Connection con, int id) throws SQLException {
        String deleteQuery = "DELETE FROM dents WHERE sid = ?";
        try (PreparedStatement pstmt = con.prepareStatement(deleteQuery)) {
            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            System.out.println("Rows deleted: " + rowsDeleted);
        }
    }
}
