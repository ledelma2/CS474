import java.util.ArrayList;

public interface Student{

    ArrayList<Course> Enrolled = new ArrayList<Course>();

    public void addEnrolledCourse(Course c);
    public void printEnrolledCourses();
}
