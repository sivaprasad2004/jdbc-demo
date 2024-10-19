package preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class delete {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/student";
        String username = "root";
        String password = "groot";
        try (Connection con = DriverManager.getConnection(url, username, password)) {
        	String deleteQuery="DELETE FROM dents WHERE sid=?";
        	try (PreparedStatement pstmt = con.prepareStatement(deleteQuery)){
        	pstmt.setInt(1, 243); 
            int rowsDeleted = pstmt.executeUpdate();
            System.out.println("Rows deleted: " + rowsDeleted);
        }
    } catch (SQLException e) {
        e.printStackTrace();

        }

	}

}
