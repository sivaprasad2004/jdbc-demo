package preparedstatement;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatementExample {

	public static void main(String[] args) {
		   String url = "jdbc:mysql://localhost:3306/student";
	        String username = "root";
	        String password = "groot";
	        try (Connection con = DriverManager.getConnection(url, username, password)) {

	            String query = "INSERT INTO dents(sid,sname) VALUES (?, ?)";
	            PreparedStatement pstmt = con.prepareStatement(query);
	            pstmt.setInt(1, 245);
	            pstmt.setString(2, "sivaga");
	            int rowsAffected = pstmt.executeUpdate();
	            System.out.println("Rows affected: " + rowsAffected);

	        
	         
	           
	            pstmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}

}
