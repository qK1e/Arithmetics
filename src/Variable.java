import java.util.HashMap;



public class Variable implements Expression
{
    private String val;

    Variable(String val)
    {
        this.val = val;
    }

    Variable(char val)
    {
        this.val = Character.toString(val);
    }

    @Override
    public String toString()
    {
        return val;
    }

    @Override
    public Expression clone()
    {
        return new Variable(new String(val));
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
        throw new NotANumberException();
    }

    @Override
    public int countVariables()
    {
        return 1;
    }

    @Override
    public HashMap<String, Variable> getVariables(HashMap<String, Variable> vars)
    {
        vars.put(val, this);
        return vars;
    }

    @Override
    public HashMap<String, Variable> getVariables()
    {
        HashMap<String,Variable> vars = new HashMap<String, Variable>();
        vars.put(val, this);
        return vars;
    }

    @Override
    public Expression Differentiate(String var)
    {
        if (val.equals(this.val))
        {
            return new Num(1);
        }
        return new Num(0);
    }

    @Override
    public Expression Differentiate(Variable var)
    {
        if (var.val.equals(this.val))
        {
            return new Num(1);
        }
        return new Num(0);
    }

    @Override
    public Expression count(String var, double val)
    {
        if (this.val.equals(var))
        {
            return new Num(val);
        }

        return this.clone();
    }

    public String getVal()
    {
        return val;
    }
}
