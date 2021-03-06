package exception;

import java.util.ArrayList;

public class Student {
    private String fullName;
    private int id;
    private ArrayList<Integer> subjectIds=new ArrayList<>();
    private ArrayList<Integer> grades=new ArrayList<>();
    private int groupId;
    private static int serialId=1;
    public Student(String name) {
        this.fullName = name;
        id=serialId;
        serialId++;
    }
    public String getFullName() {
        return fullName;
    }

    void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public ArrayList<Integer> getSubjectIds() {
        return new ArrayList<>(subjectIds);
    }
    void addSubject(int subjectId){
        subjectIds.add(subjectId);
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "fullName='" + fullName + '\'' +
                '}';
    }

    public void setSubjectMark(Subject subject, int mark){
        if(!subjectIds.contains(subject.getId())){
            throw new IllegalArgumentException("Student doesn't have this subject");
        }
        if(mark<0||mark>10){
            throw new IllegalArgumentException("Mark should be in range 0 to 10");
        }
        for(int i=grades.size();i<subjectIds.size();++i){
            grades.add(0);
        }
        int correspondingIndex=subjectIds.indexOf(subject.getId());
        grades.set(correspondingIndex,mark);
    }
    public int getSubjectMark(Subject subject){
        return grades.get(subjectIds.indexOf(subject.getId()));
    }
    public double getGpa(){
        double sum=0;
        for (int mark:grades){
            sum+=mark;
        }
        return sum/grades.size();
    }

    public int getGroupId() {
        return groupId;
    }
}
