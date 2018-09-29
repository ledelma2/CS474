import java.util.ArrayList;

public interface Student{

    ArrayList<Course> Enrolled = new ArrayList<Course>();

    public void addEnrolledCourse(Course c);
    public void printEnrolledCourses();
    public String getFirstName();
    public String getLastName();
    public String getAttribute(int attrIDX);
}
