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

    public static void addStudent(String[] line, int fIDX, int lIDX, int tIDX, ArrayList<Course> courses, ArrayList<Student> students)
    {
        Student st = new Person(line[fIDX], line[lIDX], line);
        String[] taking = line[tIDX].split(",");
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

    public static void addTA(String[] line, int fIDX, int lIDX, int taIDX, int teIDX, ArrayList<Course> courses, ArrayList<TA> tas)
    {
        TA ta = new TA(line[fIDX], line[lIDX], line);
        String[] taking = line[taIDX].split(",");
        String[] teaching = line[teIDX].split(",");
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

    public static void addInstructor(String[] line, int fIDX, int lIDX, int teIDX, ArrayList<Course> courses, ArrayList<Instructor> instructors)
    {
        Instructor i = new Instructor(line[fIDX], line[lIDX], line);
        String[] teaching = line[teIDX].split(",");
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

    public static void addStaff(String[] line, int fIDX, int lIDX, ArrayList<Staff> staff)
    {
        Staff st = new Staff(line[fIDX], line[lIDX], line);
        staff.add(st);
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
                            addTA(line, first, last, crTakeingIDX, crTeachingIDX, courses, tas);
                        }
                        else if(!(line[crTakeingIDX].isEmpty()) && (line[crTeachingIDX].isEmpty()))
                        {
                            //Student
                            addStudent(line, first, last, crTakeingIDX, courses, students);
                        }
                        else if((line[crTakeingIDX].isEmpty()) && !(line[crTeachingIDX].isEmpty()))
                        {
                            //Instructor
                            addInstructor(line, first, last, crTeachingIDX, courses, instructors);
                        }
                        else if((line[crTakeingIDX].isEmpty()) && (line[crTeachingIDX].isEmpty()))
                        {
                            //Staff
                            addStaff(line, first, last, staff);
                        }
                    }
                }
            }
            else if(response.equals("2"))
            {
                System.out.println("Please enter a name to add to the directory [First Last]: ");
                String name = in.nextLine();
                name = name.trim();
                String[] firstlast = name.split(" ");
                System.out.println("Please enter a role for the person [Student, TA, Instructor, Staff]: ");
                String role = in.nextLine();
                if(headers.isEmpty())
                {
                    headers.add("First Name");
                    headers.add("Last Name");
                    headers.add("Role");
                    headers.add("Courses Taking");
                    headers.add("Courses Teaching");
                    headers.add("Courses Taken");
                }
                int crTakeingIDX = headers.indexOf("Courses Taking");
                int crTeachingIDX = headers.indexOf("Courses Teaching");
                int first = headers.indexOf("First Name");
                int last = headers.indexOf("Last Name");
                if(role.equals("Student"))
                {
                    ArrayList<String> attr = new ArrayList<>();
                    for(int i = 0; i < headers.size(); i++)
                    {
                        String curAttr = headers.get(i);
                        if(curAttr.equals("First Name"))
                        {
                            attr.add(firstlast[0]);
                        }
                        else if(curAttr.equals("Last Name"))
                        {
                            attr.add(firstlast[1]);
                        }
                        else if (curAttr.equals("Role"))
                        {
                            attr.add("Student");
                        }
                        else if(curAttr.equals("Courses Teaching"))
                        {
                            attr.add("");
                        }
                        else {
                            System.out.println("Please enter the person's " + curAttr + " : ");
                            attr.add(in.nextLine());
                        }
                    }
                    String[] line = new String[headers.size()];
                    attr.toArray(line);
                    addStudent(line, first, last, crTakeingIDX, courses, students);
                }
                else if(role.equals("TA"))
                {
                    ArrayList<String> attr = new ArrayList<>();
                    for(int i = 0; i < headers.size(); i++)
                    {
                        String curAttr = headers.get(i);
                        if(curAttr.equals("First Name"))
                        {
                            attr.add(firstlast[0]);
                        }
                        else if(curAttr.equals("Last Name"))
                        {
                            attr.add(firstlast[1]);
                        }
                        else if (curAttr.equals("Role"))
                        {
                            attr.add("TA");
                        }
                        else {
                            System.out.println("Please enter the person's " + curAttr + " : ");
                            attr.add(in.nextLine());
                        }
                    }
                    String[] line = new String[headers.size()];
                    attr.toArray(line);
                    addTA(line, first, last, crTakeingIDX, crTeachingIDX, courses, tas);
                }
                else if(role.equals("Instructor"))
                {
                    ArrayList<String> attr = new ArrayList<>();
                    for(int i = 0; i < headers.size(); i++)
                    {
                        String curAttr = headers.get(i);
                        if(curAttr.equals("First Name"))
                        {
                            attr.add(firstlast[0]);
                        }
                        else if(curAttr.equals("Last Name"))
                        {
                            attr.add(firstlast[1]);
                        }
                        else if (curAttr.equals("Role"))
                        {
                            attr.add("Student");
                        }
                        else if(curAttr.equals("Courses Taking") || curAttr.equals("Courses Taken"))
                        {
                            attr.add("");
                        }
                        else {
                            System.out.println("Please enter the person's " + curAttr + " : ");
                            attr.add(in.nextLine());
                        }
                    }
                    String[] line = new String[headers.size()];
                    attr.toArray(line);
                    addInstructor(line, first, last, crTeachingIDX, courses, instructors);
                }
                else if(role.equals("Staff"))
                {
                    ArrayList<String> attr = new ArrayList<>();
                    for(int i = 0; i < headers.size(); i++)
                    {
                        String curAttr = headers.get(i);
                        if(curAttr.equals("First Name"))
                        {
                            attr.add(firstlast[0]);
                        }
                        else if(curAttr.equals("Last Name"))
                        {
                            attr.add(firstlast[1]);
                        }
                        else if (curAttr.equals("Role"))
                        {
                            attr.add("Student");
                        }
                        else if(curAttr.equals("Courses Taking") || curAttr.equals("Courses Taken") || curAttr.equals("Courses Teaching"))
                        {
                            attr.add("");
                        }
                        else {
                            System.out.println("Please enter the person's " + curAttr + " : ");
                            attr.add(in.nextLine());
                        }
                    }
                    String[] line = new String[headers.size()];
                    attr.toArray(line);
                    addStaff(line, first, last, staff);
                }
                else
                {
                    System.out.println(role + "is not a valid role...");
                }
            }
            else if(response.equals("3"))
            {

            }
            else if(response.equals("4"))
            {
                boolean printed = false;
                System.out.print("Enter a course name: ");
                String coursename = in.nextLine();
                for(Course c: courses)
                {
                    if(c.getCourseName().equals(coursename))
                    {
                        System.out.println("Course: " + c.getCourseName());
                        System.out.println("Instructor:");
                        System.out.println("\t" + c.getProfessor().getFirstName() + " " + c.getProfessor().getLastName());
                        System.out.println("TA's:");
                        for(TA t: c.getTAs())
                            System.out.println("\t" + t.getFirstName() + " " + t.getLastName());
                        System.out.println("Students:");
                        for(Student s: c.getStudents())
                            System.out.println("\t" + s.getFirstName() + " " + s.getLastName());
                        printed = true;
                        break;
                    }
                }
                if(!printed)
                    System.out.println("Course doesn't exist...");
            }
            else if(response.equals("5"))
            {
                System.out.println("Courses without an instructor:");
                for(Course c: courses)
                {
                    if(c.getProfessor() == null)
                        System.out.println("\t" + c.getCourseName());
                }
                System.out.println("Instructors with Courses:");
                for(Instructor i: instructors)
                {
                    System.out.println("\t" + i.getFirstName() + " " + i.getLastName() + ":");
                    for(Course c: i.getTeaching())
                    {
                        System.out.println("\t\t" + c.getCourseName());
                    }
                }
            }
            else if(response.equals("6"))
            {
                int hoursIndex = headers.indexOf("Hours Worked");
                int salaryIndex = headers.indexOf("Salary");
                if(hoursIndex != -1 && salaryIndex != -1) {
                    for(TA t: tas)
                    {
                        t.getPaid(salaryIndex, hoursIndex);
                        //System.out.println(t.getMoney());
                    }
                }
                if(salaryIndex != -1)
                {
                    for(Staff s: staff) {
                        s.getPaid(salaryIndex);
                        //System.out.println(s.getMoney());
                    }
                    for(Instructor i: instructors) {
                        i.getPaid(salaryIndex);
                        //System.out.println(i.getMoney());
                    }
                }
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
