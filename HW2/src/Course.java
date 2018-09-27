import java.util.ArrayList;

public class Course {

    private String CourseName;
    private ArrayList<TA> TAs;
    private ArrayList<Person> Students;
    private Instructor Professor;

    public Course(String n) {

        CourseName = new String(n);
    }

    public String getCourseName() {

        return CourseName;
    }

    public void setCourseName(String courseName) {

        CourseName = new String(courseName);
    }

    public void addTA(TA t)
    {
        if(!(TAs.contains(t)))
            TAs.add(t);
    }

    public void addStudent(Person s)
    {
        if(!(Students.contains(s)))
            Students.add(s);
    }

    public void addInstructor(Instructor i)
    {
        if(Professor != null)
            Professor = i;
    }

    public Instructor getProfessor() {
        return Professor;
    }
    
    public void printStudents()
    {
        for (Person s: Students)
        {
            System.out.println(s.getFirstName() + s.getLastName());
        }
    }
}
