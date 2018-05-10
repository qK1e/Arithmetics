import java.util.HashMap;



public class Sum implements Expression
{
    private Expression FirstOper;
    private Expression SecondOper;

    @Override
    public String toString()
    {
       return "(" + FirstOper.toString() + ")" + "+" + "(" + SecondOper.toString() + ")";
    }

    @Override
    public Expression clone()
    {
        return new Sum(FirstOper.clone(), SecondOper.clone());
    }

    @Override
    public Expression count()
    {
        if (FirstOper.count().isNum() && SecondOper.count().isNum())
        {
            try
            {
                Num ret = new Num(this.getNumber());
                return ret;
            }
            catch (NotANumberException e)
            {
                return this;
            }
        }

        return this;
    }

    @Override
    public double count(double val) throws TooManyVariablesException {
        if(this.countVariables() < 2)
        {
            return FirstOper.count(val) + SecondOper.count(val);
        }
        throw new TooManyVariablesException();
    }

    @Override
    public double getNumber() throws NotANumberException
    {
        try
        {
            double ret = FirstOper.getNumber() + SecondOper.getNumber();
            return ret;
        }
        catch (NotANumberException e)
        {
            throw e;
        }

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
    public HashMap<String, Variable> getVariables()
    {
        HashMap<String, Variable> vars = new HashMap<String, Variable>();
        FirstOper.getVariables(vars);
        SecondOper.getVariables(vars);
        return vars;
    }

    @Override
    public Expression Differentiate(String var) {
        return new Sum(FirstOper.Differentiate(var), SecondOper.Differentiate(var));
    }

    @Override
    public Expression Differentiate(Variable var) {
        return new Sum(FirstOper.Differentiate(var), SecondOper.Differentiate(var));
    }

    @Override
    public Expression count(String var, double val)
    {
        Sum ret = (Sum) this.clone();

        ret.FirstOper = this.FirstOper.count(var, val);
        ret.SecondOper = this.SecondOper.count(var, val);

        return ret.count();
    }

    Sum(Expression a, Expression b)
    {
        FirstOper = a.clone();
        SecondOper = b.clone();
    }

}
