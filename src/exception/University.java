package exception;

import java.util.ArrayList;

public class University {
    private String name;
    private ArrayList<Faculty> faculties=new ArrayList<>();
    private ArrayList<Group> groups=new ArrayList<>();
    private ArrayList<Student> students=new ArrayList<>();
    private ArrayList<Subject> subjects=new ArrayList<>();
    public University(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean hasGroup(Group group){
        return groups.contains(group);
    }
    public void addGroup(Faculty faculty,Group group){
        if(hasGroup(group)){
            throw new RuntimeException("Group already exists");
        }
        if(!faculties.contains(faculty)){
            throw new RuntimeException("No such faculty");
        }
        groups.add(group);
        faculties.get(faculties.indexOf(faculty))
                .addGroupId(group.getId());



    }
    public void addFaculty(Faculty faculty){
        if(faculties.contains(faculty)){
            throw new RuntimeException("Faculty already exists");
        }
        faculties.add(faculty);
    }
    public void addStudent(Group group,Student student){
        if(!groups.contains(group)){
            throw new RuntimeException("No such group");
        }
        if(students.contains(student)){
            throw new RuntimeException("Student already exists");
        }
        groups.get(groups.indexOf(group))
                .addStudent(student.getId());
        students.add(student);
        if(student.getSubjectIds()!=null) {
            for (int i = 0; i < student.getSubjectIds().size(); i++) {
                for (Subject subject : subjects) {
                    if (subject.getId() == student.getSubjectIds().get(i)) {
                        subject.addStudent(student.getId());
                    }
                }

            }
        }
    }
    public void addSubjectForStudent(Student student,Subject subject){
        if(!subjects.contains(subject)){
            throw new RuntimeException("No such subject available in the university");
        }
        if(!students.contains(student)){
            throw new RuntimeException("No such student found");
        }
        student.getSubjectIds().add(subject.getId());
        subject.addStudent(student.getId());
    }
    public void addSubject(Subject subject){
        if(subjects.contains(subject)){
            throw new RuntimeException("Subject already exists");
        }
        subjects.add(subject);
    }
}
