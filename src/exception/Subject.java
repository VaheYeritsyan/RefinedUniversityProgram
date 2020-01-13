package exception;

import java.util.ArrayList;

public class Subject {
    private String name;
    private static int serialId=1;
    private int id;
    private ArrayList<Integer> studentIds=new ArrayList<>();
    public Subject(String name) {
        this.name = name;
        id=serialId;
        serialId++;
    }
    public void addStudent(int studentId){
        studentIds.add(studentId);
    }
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

}
