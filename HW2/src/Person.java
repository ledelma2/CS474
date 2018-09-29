import java.util.ArrayList;
import java.util.Arrays;

public class Person implements Student{

    private String FirstName;
    private String LastName;
    private ArrayList<String> Attributes;

    public Person()
    {
    }

    public Person(String f, String l, String[] attr)
    {
        FirstName = new String(f);
        LastName = new String(l);
        Attributes = new ArrayList<String>(Arrays.asList(attr));
    }

    public Person(String f, String l, ArrayList<String> attr)
    {
        FirstName = new String(f);
        LastName = new String(l);
        Attributes = new ArrayList<String>(attr);
    }

    public String getFirstName() {

        return FirstName;
    }

    public String getLastName() {

        return LastName;
    }

    public void addEnrolledCourse(Course c)
    {
        if(!(this.Enrolled.contains(c)))
            this.Enrolled.add(c);
    }

    public void printEnrolledCourses()
    {
        for (Course c: Enrolled) {
            System.out.println(c.getCourseName());
        }
    }

    public String getAttribute(int attrIDX)
    {

        return Attributes.get(attrIDX);
    }
}

