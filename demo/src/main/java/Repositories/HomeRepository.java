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
    private Connection connect;

    public HomeRepository() throws SQLException {
        connect = ConnectionUtil.getConnection();
    }
    public List<ChartData> getChartData() throws SQLException {
        String sql = "SELECT date, AVG(salary) FROM employee GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 7";
        List<ChartData> chartDataList = new ArrayList<>();

        try (PreparedStatement prepare = connect.prepareStatement(sql);
             ResultSet result = prepare.executeQuery()) {

            while (result.next()) {
                String date = result.getString("date");
                double averageSalary = result.getDouble("AVG(salary)");

                ChartData chartData = new ChartData(date, averageSalary);
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
            try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement("INSERT INTO questions (question) VALUES (?)")) {
                statement.setString(1, question);
                statement.executeUpdate();
            }
        }
    }


    public int homeTotalEmployees() throws SQLException {
        String sql = "SELECT COUNT(id) FROM employee";

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
