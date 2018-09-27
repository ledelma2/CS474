public class TA extends Instructor{

    public TA()
    {
    }

    public TA(String f, String l, String[] attr)
    {
        super(f, l, attr);
    }

    public int getHoursWorked(int hourWorkIDX)
    {

        String hours = getAttribute(hourWorkIDX);
        int hoursworked = 0;
        if(hours == null)
            return 0;
        hoursworked = Integer.parseInt(hours);
        return hoursworked;
    }

    public void getPaid(int salIDX, int hourWorkIDX)
    {

        setMoney(getMoney() + (getSalary(salIDX) * getHoursWorked(hourWorkIDX)));
    }
}
