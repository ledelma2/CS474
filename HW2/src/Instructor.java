import java.util.ArrayList;

public class Instructor extends Staff{

    private ArrayList<Course> Teaching;

    public Instructor()
    {
    }

    public Instructor(String f, String l, String[] attr)
    {
        super(f, l, attr);
        Teaching = new ArrayList<Course>();
    }

    public void addTeachingCourse(Course c)
    {
        if(!(Teaching.contains(c)))
            Teaching.add(c);
    }

    public ArrayList<Course> getTeaching() {
        return Teaching;
    }

    public void getPaid(int salIDX)
    {

        setMoney(getMoney() + (getSalary(salIDX) * Teaching.size()));
    }
}

