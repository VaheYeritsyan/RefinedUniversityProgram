package exception;

import java.util.ArrayList;

public class University {
    private String name;
    private ArrayList<Faculty> faculties = new ArrayList<>();
    private ArrayList<Group> groups = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Subject> subjects = new ArrayList<>();

    public University(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasGroup(Group group) {
        return groups.contains(group);
    }

    public void addGroup(Faculty faculty, Group group) {
        if (hasGroup(group)) {
            throw new RuntimeException("Group " + group.getName() +
                    "already exists");
        }
        if (!faculties.contains(faculty)) {
            throw new RuntimeException("No faculty named " + faculty.getName()+
                    " found");
        }
        groups.add(group);
        faculties.get(faculties.indexOf(faculty))
                .addGroupId(group.getId());

    }

    public void addFaculty(Faculty faculty) {
        if (faculties.contains(faculty)) {
            throw new RuntimeException("Faculty " +faculty.getName()+
                    "already exists");
        }
        faculties.add(faculty);
    }

    public void addStudent(Group group, Student student) {
        if (!groups.contains(group)) {
            throw new RuntimeException("The university has no group named " +
                    group.getName());
        }
        if (students.contains(student)) {
            throw new RuntimeException("Student " + student.getFullName() +
                    " already exists");
        }
        groups.get(groups.indexOf(group))
                .addStudent(student.getId());
        students.add(student);
        if (student.getSubjectIds() != null) {
            for (int i = 0; i < student.getSubjectIds().size(); i++) {
                for (Subject subject : subjects) {
                    if (subject.getId() == student.getSubjectIds().get(i)) {
                        subject.addStudent(student.getId());
                    }
                }

            }
        }
    }

    public void addSubjectForStudent(Student student, Subject subject) {
        if (!subjects.contains(subject)) {
            throw new RuntimeException("No subject named " + subject.getName() +
                    " available in the university");
        }
        if (!students.contains(student)) {
            throw new RuntimeException("No student named " + student.getFullName() +
                    " in the university");
        }
        student.getSubjectIds().add(subject.getId());
        subject.addStudent(student.getId());
    }

    public void addSubject(Subject subject) {
        if (subjects.contains(subject)) {
            throw new RuntimeException("Subject " + subject.getName() +
                    " already exists");
        }
        subjects.add(subject);
    }

    public void checkForInadequacies() {
        for (Student student : students) {
            if (student.getSubjectIds().isEmpty()) {
                throw new RuntimeException("Student " + student.getFullName() +
                        " does not have any active classes");
            }
        }
        for (Group group : groups) {
            if (group.getStudentIds().isEmpty()) {
                throw new RuntimeException("Group " + group.getName() +
                        " does not have any students");
            }
        }
        for (Faculty faculty : faculties) {
            if (faculty.getGroupIds().isEmpty()) {
                throw new RuntimeException("Faculty " + faculty.getName() +
                        " does not have any groups");
            }
        }
        if (faculties.isEmpty()) {
            throw new RuntimeException("The university " + name +
                    " does not have any faculties");
        }
    }
}
