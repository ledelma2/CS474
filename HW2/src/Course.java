import java.util.ArrayList;

public class Course {

    private String CourseName;
    private ArrayList<TA> TAs;
    private ArrayList<Student> Students;
    private Instructor Professor;

    public Course()
    {}

    public Course(String n) {

        CourseName = new String(n);
        TAs = new ArrayList<>();
        Students = new ArrayList<>();
        Professor = new Instructor();
    }

    public String getCourseName() {

        return CourseName;
    }

    public void setCourseName(String courseName) {

        CourseName = new String(courseName);
    }

    public void addTA(TA t)
    {
        if(TAs.isEmpty())
            TAs.add(t);
        if(!(TAs.contains(t)))
            TAs.add(t);
    }

    public void addStudent(Student s)
    {
        if(Students.isEmpty())
            Students.add(s);
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
        if(Students.isEmpty())
        {
            System.out.println("Student array empty");
            return;
        }
        for (Student s: Students)
        {
            System.out.println(s.getFirstName() + s.getLastName());
        }
    }
}
