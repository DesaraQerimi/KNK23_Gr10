package Repositories;

import Services.ConnectionUtil;
import Models.ChartData;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomeRepository {
    private static Connection connect;

    public HomeRepository() throws SQLException {
        connect = ConnectionUtil.getConnection();
    }
    public List<ChartData> getChartData() throws SQLException {
        String sql = "SELECT MONTH(start_date) AS month, COUNT(*) AS employee_count FROM employees GROUP BY MONTH(start_date) ORDER BY MONTH(start_date)";
        List<ChartData> chartDataList = new ArrayList<>();

        try (PreparedStatement prepare = connect.prepareStatement(sql);
             ResultSet result = prepare.executeQuery()) {

            while (result.next()) {
                String month = result.getString("month");
                int employeeCount = result.getInt("employee_count");

                ChartData chartData = new ChartData(month, employeeCount);
                chartDataList.add(chartData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return chartDataList;
    }

    public static class HelpRepository {
        public void saveQuestion(String question) throws SQLException {
            // Your code to save the question to the database
            // Use the appropriate database operations or framework, e.g., JDBC, JPA, etc.
            // Example code using JDBC:
            try (
                 PreparedStatement statement = connect.prepareStatement("INSERT INTO questions (question) VALUES (?)")) {
                statement.setString(1, question);
                statement.executeUpdate();
            }
        }
    }


    public int homeTotalEmployees() throws SQLException {
        String sql = "SELECT COUNT(id) FROM employees";

        int countData = 0;
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            while (result.next()) {
                countData = result.getInt("COUNT(id)");
            }

            return countData;

        } catch (Exception e) {
            e.printStackTrace();
        }

return 0;
    }
}
