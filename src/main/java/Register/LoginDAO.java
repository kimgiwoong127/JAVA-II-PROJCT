// LoginDAO.java
package Register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {
    public boolean login(LoginDTO loginDTO) {
        String query = "SELECT * FROM USER WHERE Id = ? AND Password = ?";
        try (Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, loginDTO.getId());
            preparedStatement.setString(2, loginDTO.getPassword());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // If there is a matching user, return true
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
