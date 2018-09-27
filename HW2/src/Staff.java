import java.util.ArrayList;

public class Staff extends Person {

    private double Money = 0.0;

    public Staff()
    {
    }

    public Staff(String f, String l, String[] attr)
    {
        super(f, l, attr);
    }

    public double getMoney() {
        return Money;
    }

    public void setMoney(double amount)
    {
        Money = amount;
    }

    public int getSalary(int salIDX)
    {
        String sal = getAttribute(salIDX);
        int Salary = 0;
        if(sal == null)
            return 0;
        Salary = Integer.parseInt(sal);
        return Salary;

    }

    public void getPaid(int salIDX)
    {
        Money = getSalary(salIDX) + Money;
    }
}

