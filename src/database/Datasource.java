package database;

import exception.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Datasource {
    public static final String DB_NAME="students.db";
    public static final String CONNECTION_STRING="jdbc:sqlite:C:\\Programming\\EpamProjects\\RefinedUniversityProgram\\"+DB_NAME;

    public static final String TABLE_STUDENTS="students";
    public static final String COLUMN_STUDENT_ID="_id";
    public static final String COLUMN_STUDENT_NAME="name";
    public static final String COLUMN_STUDENT_GROUP="group";

    public static final String ADD_STUDENT=String.format("INSERT INTO %s values ()",TABLE_STUDENTS);

    private Connection connection;

    public boolean open(){
        try{
            connection= DriverManager.getConnection(CONNECTION_STRING);
            return true;
        }catch (SQLException e){
            System.out.printf("Couldn't connect to database: %s\n",e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void close(){
        try {
            if(connection!=null){
                connection.close();
            }
        }catch (SQLException e){
            System.out.printf("Couldn't close connection: %s\n", e.getMessage());
            e.printStackTrace();
        }
    }

    public Datasource() {
        open();
    }

    public void createTableStudents(){
        try(Statement statement=connection.createStatement()){
            statement.execute("CREATE TABLE IF NOT EXISTS students (_id INTEGER,name TEXT,groupID INTEGER)");
        }catch (SQLException e){
            System.out.printf("Connection failed: %s\n", e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean addStudent(Student student){
        createTableStudents();
        try(Statement statement=connection.createStatement()) {
            String query=String.format("INSERT INTO students values(%d,'%s',%d)",
                    student.getId(),student.getFullName(),student.getId());
            statement.execute(query);
            return true;
        }catch (SQLException e) {
            System.out.printf("Connection failed: %s\n", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
