package exception;

import database.Datasource;

import java.lang.reflect.Array;
import java.util.ArrayList;
public class DriverClass {
    public static void main(String[] args) {
        testTheProgram();
    }

    public static void testTheProgram(){
        University ysu=new University("YSU");
        //faculties
        Faculty informatics=new Faculty("Informatics");
        Faculty history=new Faculty("History");
        ysu.addFaculty(informatics);
        ysu.addFaculty(history);
        
        //groups for informatics
        Group mathematics=new Group("Mathematics");
        Group security=new Group("Security");
        ysu.addGroup(informatics,mathematics);
        ysu.addGroup(informatics,security);
        
        //groups for history
        Group art=new Group("Art");
        ysu.addGroup(history,art);
        
        //subjects
        Subject sql=new Subject("SQL");
        Subject java=new Subject("Java");
        Subject php=new Subject("Php");
        Subject js=new Subject("JS");
        ysu.addSubject(sql);
        ysu.addSubject(java);
        ysu.addSubject(php);
        ysu.addSubject(js);

        //students for mathematics
        Student vahe=new Student("Vahe");
        ysu.addStudent(mathematics,vahe);
        ysu.addSubjectForStudent(vahe,sql);
        ysu.addSubjectForStudent(vahe,java);
        vahe.setSubjectMark(sql,3);
        vahe.setSubjectMark(java,2);
        Student vazgen=new Student("Vazgen");
        ysu.addStudent(mathematics,vazgen);
        ysu.addSubjectForStudent(vazgen,java);
        ysu.addSubjectForStudent(vazgen,php);
        vazgen.setSubjectMark(php,9);
        vazgen.setSubjectMark(java,4);
        
        //students for security
        Student artur=new Student("Artur");
        ysu.addStudent(security,artur);
        ysu.addSubjectForStudent(artur,js);
        ysu.addSubjectForStudent(artur,java);
        artur.setSubjectMark(js,2);
        artur.setSubjectMark(java,7);
        
        //students for art
        Student arman=new Student("Arman");
        ysu.addStudent(art,arman);
        ysu.addSubjectForStudent(arman,java);
        ysu.addSubjectForStudent(arman,php);
        arman.setSubjectMark(php,8);
        arman.setSubjectMark(java,5);

        //checking the results
        System.out.println("Average mark for java in group mathematics: "+
                ysu.getAverageMarkForSubject(java,mathematics));

        System.out.println("Average mark for java in group security: "+
                ysu.getAverageMarkForSubject(java,security));

        System.out.println("Average mark for java in group art: "+
                ysu.getAverageMarkForSubject(java,art));

        System.out.println("Average mark for java in faculty informatics: "+
                ysu.getAverageMarkForSubject(java,informatics));

        System.out.println("Average mark for java in faculty history: "+
                ysu.getAverageMarkForSubject(java,history));

        System.out.println("Average mark for java in ysu: "+
                ysu.getAverageMarkForSubject(java));

        System.out.println("Vahe's GPA is "+vahe.getGpa());
        ysu.checkForInadequacies();

        Datasource datasource=new Datasource();
        //this line should add al the information to the database
        datasource.addEntireUniversity(ysu);
//        datasource.addStudent(vahe);
//        datasource.addFaculty(informatics);
//        datasource.createTableFaculties();
//        datasource.addGroup(mathematics);
//        datasource.createTableStudents();
//        datasource.addStudent(artur);





        //checking the exceptions

        //University emptyUniversity=new University("I have no faculties");
        //emptyUniversity.checkForInadequacies();
        //Faculty emptyFaculty=new Faculty("I have no groups");
        //ysu.addFaculty(emptyFaculty);
        //Group emptyGroup=new Group("I have no students");
        //Student lazyStudent=new Student("I have no classes");
        //ysu.addStudent(mathematics,lazyStudent);
        //ysu.checkForInadequacies()
    }
}
