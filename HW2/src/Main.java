import com.opencsv.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        String path = System.getProperty("user.dir");
        int response = -1;
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
            System.out.print("Enter action number [0, 1, 2, 3, 4, 5, 6, or 7]: ");
            response = in.nextInt();
            if(response == 0)
            {
                System.out.print("Goodbye");
                break;
            }
            else if(response == 1)
            {
                System.out.print("Enter name of .csv file: ");
                f = new File(path + "\\" + in.next());
                if(!(f.exists() && f.canRead())){
                    System.out.println("File does not exist or is not readable...");
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
                    while((line = cr.readNext()) != null)
                    {
                        if(line[crTakeingIDX] != null && line[crTeachingIDX] != null)
                        {
                            //TA

                        }
                        else if(line[crTakeingIDX] != null && line[crTeachingIDX] == null)
                        {
                            //Student
                        }
                        else if(line[crTakeingIDX] == null && line[crTeachingIDX] != null)
                        {
                            //Instructor
                        }
                        else if(line[crTakeingIDX] == null && line[crTeachingIDX] == null)
                        {
                            //Staff
                        }
                    }
                }

            }
            else if(response == 2)
            {

            }
            else if(response == 3)
            {

            }
            else if(response == 4)
            {

            }
            else if(response == 5)
            {

            }
            else if(response == 6)
            {

            }
            else if(response == 7)
            {

            }
            else
            {

            }
        }
    }
}
