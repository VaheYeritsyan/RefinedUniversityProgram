package database;

import exception.Faculty;
import exception.Group;
import exception.Student;
import exception.University;

import javax.print.DocFlavor;
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
    public static final String COLUMN_STUDENT_GROUP_ID ="groupID";

    public static final String TABLE_GROUPS="groups";
    public static final String COLUMN_GROUP_ID="_id";
    public static final String COLUMN_GROUP_NAME="name";
    public static final String COLUMN_GROUP_FACULTY_ID="facultyID";

    public static final String TABLE_FACULTIES="faculties";
    public static final String COLUMN_FACULTY_ID="_id";
    public static final String COLUMN_FACULTY_NAME="name";


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

    private void createTableStudents(){
        try(Statement statement=connection.createStatement()){
            String query=String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER UNIQUE,%s TEXT,%s INTEGER)",
                                        TABLE_STUDENTS,COLUMN_STUDENT_ID,COLUMN_STUDENT_NAME, COLUMN_STUDENT_GROUP_ID);
            statement.execute(query);
        }catch (SQLException e){
            System.out.printf("Connection failed: %s\n", e.getMessage());
            e.printStackTrace();
        }
    }

    private void createTableGroups(){
        try(Statement statement=connection.createStatement()) {
            String query=String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER UNIQUE,%s TEXT,%s INTEGER)",
                                    TABLE_GROUPS,COLUMN_GROUP_ID,COLUMN_GROUP_NAME,COLUMN_GROUP_FACULTY_ID);
            statement.execute(query);
        }catch (SQLException e){
            System.out.printf("Connection failed: %s\n", e.getMessage());
            e.printStackTrace();
        }
    }

    private void createTableFaculties(){
        try(Statement statement=connection.createStatement()){
            String query= String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER UNIQUE,%S TEXT)",
                    TABLE_FACULTIES,COLUMN_FACULTY_ID,COLUMN_FACULTY_NAME);
            statement.execute(query);
        }catch (SQLException e) {
            System.out.printf("Connection failed: %s\n", e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean addStudent(Student student){
        createTableStudents();
        try(Statement statement=connection.createStatement()) {
            String query=String.format("INSERT INTO %s values(%d,'%s',%d)",
                    TABLE_STUDENTS,student.getId(),student.getFullName(),student.getGroupId());
            statement.execute(query);
            return true;
        }catch (SQLException e) {
            System.out.printf("Connection failed: %s\n", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean addGroup(Group group){
        createTableGroups();
        try(Statement statement=connection.createStatement()) {
            String query=String.format("INSERT INTO %s VALUES(%d,'%s',%d)",
                    TABLE_GROUPS,group.getId(),group.getName(),group.getFacultyId());
            statement.execute(query);
            return  true;
        }catch (SQLException e){
            System.out.printf("Connection failed: %s\n", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean addFaculty(Faculty faculty) {
        createTableFaculties();
        try (Statement statement = connection.createStatement()) {

            String query = String.format("INSERT INTO %s values(%d,'%s')",
                    TABLE_FACULTIES, faculty.getId(), faculty.getName());
            statement.execute(query);
            return true;

        } catch (SQLException e) {
            System.out.printf("Connection failed: %s\n", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void addEntireUniversity(University university){
        for (Faculty faculty : university.getFaculties()) {
            addFaculty(faculty);
        }
        for (Group group : university.getGroups()) {
            addGroup(group);
        }
        for (Student student : university.getStudents()) {
            addStudent(student);
        }
    }
}
