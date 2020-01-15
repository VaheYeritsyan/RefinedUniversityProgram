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

    public boolean addGroup(Faculty faculty, Group group) {
        if (hasGroup(group)) {
            return false;
        }
        if (!faculties.contains(faculty)) {
            throw new RuntimeException("No faculty named " + faculty.getName() +
                    " found");
        }
        groups.add(group);
        faculties.get(faculties.indexOf(faculty))
                .addGroupId(group.getId());
        return true;

    }

    public boolean addFaculty(Faculty faculty) {
        if (faculties.contains(faculty)) {
            return true;
        }
        faculties.add(faculty);
        return false;
    }

    public boolean addStudent(Group group, Student student) {
        if (!groups.contains(group)) {
            throw new RuntimeException("The university has no group named " +
                    group.getName());
        }
        if (students.contains(student)) {
            return false;
        }
        groups.get(groups.indexOf(group))
                .addStudent(student.getId());
        students.add(student);
        student.setGroupId(group.getId());
        //get student's subjects and add to them student's id
        if (student.getSubjectIds() != null) {
            for (int i = 0; i < student.getSubjectIds().size(); i++) {
                for (Subject subject : subjects) {
                    if (subject.getId() == student.getSubjectIds().get(i)) {
                        subject.addStudent(student.getId());
                    }
                }

            }
        }
        return true;
    }

    public boolean addSubjectForStudent(Student student, Subject subject) {
        if (!subjects.contains(subject)) {
            throw new RuntimeException("No subject named " + subject.getName() +
                    " available in the university");
        }
        if (!students.contains(student)) {
            throw new RuntimeException("No student named " + student.getFullName() +
                    " in the university");
        }
        student.addSubject(subject.getId());
        subject.addStudent(student.getId());
        return true;
    }

    public boolean addSubject(Subject subject) {
        if (subjects.contains(subject)) {
            return false;
        }
        subjects.add(subject);
        return true;
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

    public double getAverageMarkForSubject(Subject subject) {

        if (!subjects.contains(subject)) {
            throw new RuntimeException("No subject named " + subject.getName() +
                    " found in the university");
        }
        checkForInadequacies();
        double sum = 0;
        for (Student student : students) {
            for (int learnerId : subject.getStudentIds()) {
                if (student.getId() == learnerId) {
                    sum += student.getSubjectMark(subject);
                }
            }
        }
        return sum / subject.getStudentIds().size();

    }

    public double getAverageMarkForSubject(Subject subject, Group group) {
        if (!subjects.contains(subject)) {
            throw new RuntimeException("No subject named " + subject.getName() +
                    " found in the university");
        }
        if (!groups.contains(group)) {
            throw new RuntimeException("No group named " + group.getName() +
                    " found in the university");
        }
        double sum = 0;
        int learnerCount = 0;

        for (Student student : students) {
            if (student.getGroupId() == group.getId() &&subject.getStudentIds()
                        .contains(student.getId())) {
                sum += student.getSubjectMark(subject);
                learnerCount++;
            }
        }
        return sum / learnerCount;
    }
    public double getAverageMarkForSubject(Subject subject, Faculty faculty) {
        if (!subjects.contains(subject)) {
            throw new RuntimeException("No subject named " + subject.getName() +
                    " found in the university");
        }
        if (!faculties.contains(faculty)) {
            throw new RuntimeException("No faculty named " + faculty.getName() +
                    " found in the university");
        }
        double sum = 0;
        int learnerCount = 0;

        for (Student student : students) {
            if(faculty.getGroupIds().contains(student.getGroupId())){
                learnerCount++;
                sum+=student.getSubjectMark(subject);
            }
        }
        return sum / learnerCount;
    }

}
