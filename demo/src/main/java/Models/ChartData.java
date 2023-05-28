package Models;

    public class ChartData {
        private String date;
        private double averageSalary;

        public ChartData(String date, double averageSalary) {
            this.date = date;
            this.averageSalary = averageSalary;
        }

        public String getDate() {
            return date;
        }

        public double getAverageSalary() {
            return averageSalary;
        }
    }

