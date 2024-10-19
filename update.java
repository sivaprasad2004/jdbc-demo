package preparedstatement;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class update {

	public static void main(String[] args) {
		   String url = "jdbc:mysql://localhost:3306/student";
	        String username = "root";
	        String password = "groot";
	        try (Connection con = DriverManager.getConnection(url, username, password)) {

	            String updateQuery = "UPDATE dents SET sname = ? WHERE sid = ?";
	            try (PreparedStatement pstmt = con.prepareStatement(updateQuery)) {
	                pstmt.setString(1, "maheshhhhh");
	                pstmt.setInt(2, 243);
	                int rowsUpdated = pstmt.executeUpdate();
	                System.out.println("Rows updated: " + rowsUpdated);
	            }
	     
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}

}
