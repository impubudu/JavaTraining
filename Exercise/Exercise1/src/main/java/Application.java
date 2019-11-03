package main.java;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Application {
    private Statement statement;

    public static void main(String[] args) {
        PostgreSqlConnect postgreSqlConnect = PostgreSqlConnect.getDbCon();
        Connection conn = postgreSqlConnect.getConn();

        String create = "CREATE TABLE COMPANY " +
                "(id INT PRIMARY KEY     NOT NULL," +
                " first_name     VARCHAR(50)    NOT NULL, " +
                " last_name      VARCHAR(50)    NOT NULL, " +
                " birth_day      DATE    , " +
                " age            INT     , " +
                " address        VARCHAR(50), " +
                " salary         FLOAT,"+
                " marital_status BOOLEAN,"+
                " created_date   TIMESTAMPTZ NOT NULL DEFAULT NOW())";

        String insert = "INSERT INTO COMPANY (id,first_name,last_name,birth_day,age,address,salary,marital_status) VALUES"
                + "(1, 'Pubudu','Prasanna','1994.08.01', 25,'Tangalle', 20000.00,false),"
                + "(2,'Nimal','Perera','1984.09.01',45,'Colombo',120000.0,true),"
                + "(3,'Gayan','Nuwan','1990.09.01',28,'Colombo',220000.0,false),"
                + "(4,'Kasun','Rajitha','1984.09.01',32,'Hambanthota',20000.0,false),"
                + "(5,'Nuwan','Madhushanka','1995.05.21',24,'Negambo',20000.0,true),"
                + "(6,'Charith','Rasika','1995.05.21',24,'Mathara',200000.0,false),"
                + "(7,'Nipun','Thilakshan','1982.05.21',44,'Mathara',200000.0,false),"
                + "(8,'Lalith','Bhanuka','1990.11.21',34,'Badulla',500000.0,true),"
                + "(9,'Nimantha','Piumal','1988.01.26',30,'Galle',100000.0,false),"
                + "(10,'Saman','Nilanka','1972.10.11',55,'Galle',50000.0,true);";

        String query = "SELECT * FROM COMPANY;";

        Application app = new Application();
        app.query(conn,create);
        app.query(conn,insert);
        ResultSet resultSet = app.select(conn,query);

        List<Employee> employees = app.getEmployees(resultSet);

        employees.stream().filter(s->s.getAge()<=30).collect(Collectors.toMap(Employee::getId,employee -> employee))
                .forEach((k,v)->{
                    System.out.println("Employee ID: "+v.getId());
                    System.out.println("First Name: "+v.getFirstName());
                    System.out.println("Last Name: "+v.getLastName());
                    System.out.println("Birth Day: "+v.getBirthDay());
                    System.out.println("Age: "+v.getAge());
                    System.out.println("Address: "+v.getAddress());
                    System.out.println("Salary: "+v.getSalary());
                    System.out.println("Marital Status: "+v.isMaritalStatus());
                    System.out.println("Created Date: "+new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(v.getCreatedDate()));
                    System.out.println("Calculated Date: "+ new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(Timestamp.valueOf(v.getCreatedDate().toLocalDateTime().minusDays(6).minusMonths(2))));
                    System.out.println("---------------------------");
                });
    }


    public int query(Connection conn, String query)  {
        int result=0;
        try {
            statement = conn.createStatement();
            result = statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public ResultSet select(Connection conn, String query) {
        ResultSet res=null;
        try {
            statement = conn.createStatement();
            res = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public List<Employee> getEmployees(ResultSet resultSet){
        List<Employee> employees = new ArrayList<>();
        try {
            while (resultSet.next()){
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setBirthDay(resultSet.getDate("birth_day"));
                employee.setAge(resultSet.getInt("age"));
                employee.setAddress(resultSet.getString("address"));
                employee.setSalary(resultSet.getFloat("salary"));
                employee.setMaritalStatus(resultSet.getBoolean("marital_status"));
                employee.setCreatedDate(resultSet.getTimestamp("created_date"));

                employees.add(employee);
            }
        }catch (SQLException e){}
        return employees;
    }
}
