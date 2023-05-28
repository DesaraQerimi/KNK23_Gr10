package Services;

import Repositories.HomeRepository;
import Models.ChartData;
import java.sql.SQLException;
import java.util.List;

public class HomeService {
    static HomeRepository homeRepository;

    static {
        try {
            homeRepository = new HomeRepository();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public class HelpService {
        private static HomeRepository.HelpRepository helpRepository = new HomeRepository.HelpRepository();

        public static void saveQuestion(String question) throws SQLException {
            helpRepository.saveQuestion(question);
        }
    }

    public static int homeTotalEmployees() throws SQLException {
        return homeRepository.homeTotalEmployees();
    }

    public static List<ChartData> getChartData() throws SQLException {
        return homeRepository.getChartData();
    }
}
