import java.util.HashMap;

public class Division implements Expression
{
    private Expression FirstOper, SecondOper;

    @Override
    public String toString() {
        return "(" + FirstOper.toString() + ")" + "/" + "(" + SecondOper.toString() + ")";
    }

    @Override
    public Expression clone()
    {
        return new Division(FirstOper.clone(), SecondOper.clone());
    }

    @Override
    public Expression count()
    {
        if (FirstOper.count().isNum() && SecondOper.count().isNum())
        {
            try
            {
                double ret = this.getNumber();
            }
            catch (NotANumberException e)
            {
                return this;
            }
        }

        return this;
    }

    @Override
    public double count(double val) throws TooManyVariablesException
    {
        if(this.countVariables() < 2)
        {
            return FirstOper.count(val) + SecondOper.count(val);
        }
        throw new TooManyVariablesException();
    }

    @Override
    public HashMap<String, Variable> getVariables()
    {
        HashMap<String, Variable> vars = new HashMap<String, Variable>();
        FirstOper.getVariables(vars);
        SecondOper.getVariables(vars);
        return vars;
    }

    @Override
    public Expression Differentiate(String var)
    {
        Multiplication mult1 = new Multiplication(FirstOper.Differentiate(var), SecondOper);
        Multiplication mult2 = new Multiplication(SecondOper.Differentiate(var), FirstOper);
        Multiplication mult3 = new Multiplication(mult2, new Num(-1));
        Sum sum = new Sum(mult1, mult3);
        Power pow = new Power(SecondOper, new Num(2));
        Division div = new Division(sum, pow);
        return div;
    }

    @Override
    public Expression Differentiate(Variable var)
    {
        Multiplication mult1 = new Multiplication(FirstOper.Differentiate(var), SecondOper);
        Multiplication mult2 = new Multiplication(SecondOper.Differentiate(var), FirstOper);
        Multiplication mult3 = new Multiplication(mult2, new Num(-1));
        Sum sum = new Sum(mult1, mult3);
        Power pow = new Power(SecondOper, new Num(2));
        Division div = new Division(sum, pow);
        return div;
    }

    @Override
    public Expression count(String var, double val)
    {
        Division ret = (Division) this.clone();

        ret.FirstOper = this.FirstOper.count(var, val);
        ret.SecondOper = this.SecondOper.count(var, val);

        return ret.count();
    }

    @Override
    public int countVariables()
    {
        HashMap<String, Variable> vars = FirstOper.getVariables();
        SecondOper.getVariables(vars);

        return vars.size();
    }

    @Override
    public HashMap<String, Variable> getVariables(HashMap<String, Variable> vars)
    {
        FirstOper.getVariables(vars);
        SecondOper.getVariables(vars);

        return vars;
    }

    @Override
    public double getNumber() throws NotANumberException
    {
        try
        {
            double ret = FirstOper.getNumber()/SecondOper.getNumber();
            return ret;
        }
        catch (NotANumberException e)
        {
            throw e;
        }
    }

    Division(Expression a, Expression b)
    {
        FirstOper = a;
        SecondOper = b;
    }
}
