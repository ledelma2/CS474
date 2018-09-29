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
        String[] attr = new String[i.getAttributes().size()];
        i.getAttributes().toArray(attr);
        Professor = new Instructor(i.getFirstName(), i.getLastName(), attr);
    }

    public Instructor getProfessor() {
        return Professor;
    }
    
    public ArrayList<Student> getStudents()
    {
        return Students;
    }

    public ArrayList<TA> getTAs() {
        return TAs;
    }
}
