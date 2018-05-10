import java.util.HashMap;


public class Num implements Expression
{
    private double val;

    Num(double val)
    {
        this.val = val;
    }

    @Override
    public String toString()
    {
        return Double.toString(val);
    }

    @Override
    public Expression clone()
    {
        return new Num(val);
    }

    @Override
    public Boolean isNum()
    {
        return Boolean.TRUE;
    }

    @Override
    public Expression count()
    {
        return this;
    }

    @Override
    public double count(double val)
    {
        return val;
    }

    @Override
    public double getNumber() throws NotANumberException
    {
        return val;
    }

    @Override
    public int countVariables()
    {
        return 0;
    }

    @Override
    public HashMap<String, Variable> getVariables(HashMap<String, Variable> vars)
    {
        return vars;
    }

    @Override
    public HashMap<String, Variable> getVariables()
    {
        HashMap<String, Variable> vars = new HashMap<String,Variable>();
        return vars;
    }

    @Override
    public Expression Differentiate(String var)
    {
        return new Num(0);
    }

    @Override
    public Expression Differentiate(Variable var)
    {
        return new Num(0);
    }

    @Override
    public Expression count(String var, double val)
    {
        return this.clone();
    }
}
