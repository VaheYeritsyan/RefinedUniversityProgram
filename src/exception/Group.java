package exception;

import java.util.ArrayList;

public class Group {
    private String name;
    private int id;
    private ArrayList<Integer> studentIds=new ArrayList<>();
    private static int serialId=1;
    private int facultyId;
    public Group(String name) {
        this.name = name;
        id=serialId;
        serialId++;
    }
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
    void addStudent(int studentId){
        studentIds.add(studentId);
    }

    public ArrayList<Integer> getStudentIds() {
        return new ArrayList<>(studentIds);
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                '}';
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }
}
