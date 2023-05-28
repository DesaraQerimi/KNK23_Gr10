package Models;

    public class ChartData {

        private String date;
        private int employeeCount;

        public ChartData(String date, int employeeCount) {
            this.date = date;
            this.employeeCount = employeeCount;
        }

        public String getDate() {
            return date;
        }

        public int getEmployeeCount() {
            return employeeCount;
        }

        private double averageSalary;

        public ChartData(String date, double averageSalary) {
            this.date = date;
            this.averageSalary = averageSalary;
        }



        public double getAverageSalary() {
            return averageSalary;
        }
    }

