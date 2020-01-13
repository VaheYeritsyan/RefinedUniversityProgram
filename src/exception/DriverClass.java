package exception;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DriverClass {
    public static void main(String[] args) {
        University university = new University("YSU");
        Faculty informatics = new Faculty("Informatics");
        university.addFaculty(informatics);
        Group security = new Group("Security");
        university.addGroup(informatics, security);
        Group mathematics = new Group("Mathematics");
        university.addGroup(informatics, mathematics);
        Student student1 = new Student("Vahe");
        university.addStudent(security, student1);
        Student student2 = new Student("Armen");
        university.addStudent(mathematics, student2);
        Subject java = new Subject("Java");
        Subject sql = new Subject("SQL");
        university.addSubject(java);
        university.addSubject(sql);
        university.addSubjectForStudent(student1, java);
        university.addSubjectForStudent(student2, java);
        university.addSubjectForStudent(student2, sql);
        student2.setSubjectMark(sql, 8);
        student2.setSubjectMark(java, 3);
        System.out.println("Test");
        System.out.println("Student2 mark for sql: " + student2.getSubjectMark(sql));
        university.checkForInadequacies();
        System.out.println("Student2 GPA: " + student2.getGpa());
//        University university2=new University("Hyusisayin");
//        university2.checkForInadequacies();
//        Faculty irav = new Faculty("Irav");
//        university.addFaculty(irav);
//
//        Group myGroup = new Group("asd");
//        university.addGroup(irav, security);

    }
}
