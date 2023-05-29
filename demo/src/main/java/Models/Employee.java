package Models;

import java.util.Date;

public class Employee {
        public static int id;
        private String firstName;
        private String lastName;
        private String department;
        private String email;
        private String phone;
        private Roli roli;
        private Date start_date;
        private Date end_date;

    public Employee(int id, String firstName, String lastName, String department, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.email = email;
        this.phone = phone;
    }

    public Employee(String firstName, String lastName, String department, String email, String phone) {

            this.firstName = firstName;
            this.lastName = lastName;
            this.department = department;
            this.email = email;
            this.phone = phone;
        }

    public Employee(int id, String firstName, String lastName, String department, String email, String phone, Roli roli, Date start_date, Date end_date) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.email = email;
        this.phone = phone;
        this.roli = roli;
        this.start_date = start_date;
        this.end_date = end_date;
    }

// Getters and setters for all fields

        public static int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }


    public Roli getRoli() {
        return roli;
    }

    public void setRoli(Roli roli) {
        this.roli = roli;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    // toString method for debugging and logging purposes
        @Override
        public String toString() {
            return "Employee{" +
                    "id=" + id +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", department='" + department + '\'' +
                    ", email='" + email + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }
    }



