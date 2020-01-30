package exception;

import java.util.ArrayList;

public class Subject {
    private String name;
    private static int serialId=1;
    private int id;

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                '}';
    }

    private ArrayList<Integer> studentIds=new ArrayList<>();
    public Subject(String name) {
        this.name = name;
        id=serialId;
        serialId++;
    }
    public void addStudent(int studentId){
        studentIds.add(studentId);
    }

    public int getId() {
        return id;
    }

    public ArrayList<Integer> getStudentIds() {
        return studentIds;
    }
}
