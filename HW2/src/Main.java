import com.opencsv.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static Course courseInList(ArrayList<Course> total, String coursename)
    {
        for(Course t: total)
            if(t.getCourseName().equals(coursename))
                return t;
        return null;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, InputMismatchException
    {
        String path = System.getProperty("user.dir");
        String response;
        Scanner in = new Scanner(System.in);
        File f;
        ArrayList<String> headers = new ArrayList<String>();
        ArrayList<Course> courses = new ArrayList<Course>();
        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<Staff> staff = new ArrayList<Staff>();
        ArrayList<Instructor> instructors = new ArrayList<Instructor>();
        ArrayList<TA> tas = new ArrayList<TA>();
        while(true)
        {
            in.reset();
            System.out.print("Enter action number [0, 1, 2, 3, 4, 5, 6, or 7]: ");
            response = in.nextLine();
            if(response.equals("0"))
            {
                System.out.print("Goodbye");
                break;
            }
            else if(response.equals("1"))
            {
                System.out.print("Enter name of .csv file: ");
                f = new File(path + "\\" + in.nextLine());
                if(!(f.exists() && f.canRead())){
                    System.out.println("File does not exist or is not readable...");
                    continue;
                }else{
                    CSVReader cr = new CSVReaderBuilder(new FileReader(f)).build();
                    String[] line = cr.readNext();
                    if(line == null)
                    {
                        System.out.println("File is empty...");
                        cr.close();
                        continue;
                    }
                    headers = new ArrayList<String>(Arrays.asList(line));
                    int crTakeingIDX = headers.indexOf("Courses Taking");
                    int crTeachingIDX = headers.indexOf("Courses Teaching");
                    int first = headers.indexOf("First Name");
                    int last = headers.indexOf("Last Name");
                    while((line = cr.readNext()) != null)
                    {
                        if(!(line[crTakeingIDX].isEmpty()) && !(line[crTeachingIDX].isEmpty()))
                        {
                            //TA
                            TA ta = new TA(line[first], line[last], line);
                            String[] taking = line[crTakeingIDX].split(",");
                            String[] teaching = line[crTeachingIDX].split(",");
                            for(String p: teaching) {
                                String s = p.trim();
                                Course c = courseInList(courses, s);
                                if (c == null) {
                                    c = new Course(s);
                                    courses.add(c);
                                }
                                ta.addTeachingCourse(c);
                                c.addTA(ta);
                            }
                            for(String p: taking) {
                                String s = p.trim();
                                Course c = courseInList(courses, s);
                                if (c == null) {
                                    c = new Course(s);
                                    courses.add(c);
                                }
                                ta.addEnrolledCourse(c);
                                c.addStudent(ta);
                            }
                            tas.add(ta);
                        }
                        else if(!(line[crTakeingIDX].isEmpty()) && (line[crTeachingIDX].isEmpty()))
                        {
                            //Student
                            Student st = new Person(line[first], line[last], line);
                            String[] taking = line[crTakeingIDX].split(",");
                            for(String p: taking) {
                                String s = p.trim();
                                Course c = courseInList(courses, s);
                                if (c == null) {
                                    c = new Course(s);
                                    courses.add(c);
                                }
                                st.addEnrolledCourse(c);
                                c.addStudent(st);
                            }
                            students.add(st);
                        }
                        else if((line[crTakeingIDX].isEmpty()) && !(line[crTeachingIDX].isEmpty()))
                        {
                            //Instructor
                            Instructor i = new Instructor(line[first], line[last], line);
                            String[] teaching = line[crTeachingIDX].split(",");
                            for(String p: teaching) {
                                String s = p.trim();
                                Course c = courseInList(courses, s);
                                if (c == null) {
                                    c = new Course(s);
                                    courses.add(c);
                                }
                                i.addTeachingCourse(c);
                                c.addInstructor(i);
                            }
                            instructors.add(i);
                        }
                        else if((line[crTakeingIDX].isEmpty()) && (line[crTeachingIDX].isEmpty()))
                        {
                            //Staff
                            Staff st = new Staff(line[first], line[last], line);
                            staff.add(st);
                        }
                    }
                }
            }
            else if(response.equals("2"))
            {
                System.out.println("Please enter a name to add to the directory [First Last]: ");
                String name = in.nextLine();
                System.out.println("Please enter a role for the person [Student, TA, Instructor, Staff]: ");
                String role = in.nextLine();
            }
            else if(response.equals("3"))
            {

            }
            else if(response.equals("4"))
            {

            }
            else if(response.equals("5"))
            {

            }
            else if(response.equals("6"))
            {

            }
            else if(response.equals("7"))
            {

            }
            else
            {
                System.out.println("Invalid command, try again");
            }
            /*System.out.println("Courses:");
            for(Course c: courses)
                System.out.println("\t" + c.getCourseName());
            System.out.println("Students:");
            for(Student s: students)
                System.out.println("\t" + s.getFirstName() + " " + s.getLastName());
            System.out.println("Staff:");
            for(Staff s: staff)
                System.out.println("\t" + s.getFirstName() + " " + s.getLastName());
            System.out.println("Instructors:");
            for(Instructor i: instructors)
                System.out.println("\t" + i.getFirstName() + " " + i.getLastName());
            System.out.println("TA's:");
            for(TA t: tas)
                System.out.println("\t" + t.getFirstName() + " " + t.getLastName());*/
        }
    }
}
