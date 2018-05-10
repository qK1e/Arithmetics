import java.util.HashMap;

public class Multiplication implements Expression
{
    private Expression FirstOper,SecondOper;

    @Override
    public String toString()
    {
        return "(" + FirstOper.toString() + ")" + "*" + "(" + SecondOper.toString() + ")";
    }

    @Override
    public Expression clone()
    {
        return new Multiplication(FirstOper.clone(), SecondOper.clone());
    }

    @Override
    public Expression count()
    {
        if (FirstOper.count().isNum() && SecondOper.count().isNum())
        {
            try
            {
                double ret = FirstOper.count().getNumber()*SecondOper.count().getNumber();
                return new Num(ret);
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
    public double getNumber() throws NotANumberException
    {
            try
            {
                double ret = FirstOper.count().getNumber() + SecondOper.count().getNumber();
                return ret;
            }
            catch (NotANumberException e)
            {
                throw e;
            }
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
        Multiplication Oper1 = new Multiplication(FirstOper.Differentiate(var), SecondOper);
        Multiplication Oper2 = new Multiplication(SecondOper.Differentiate(var), FirstOper);
        Sum ret = new Sum(Oper1, Oper2);

        return ret;
    }

    @Override
    public Expression Differentiate(Variable var)
    {
        Multiplication Oper1 = new Multiplication(FirstOper.Differentiate(var), SecondOper);
        Multiplication Oper2 = new Multiplication(SecondOper.Differentiate(var), FirstOper);
        Sum ret = new Sum(Oper1, Oper2);

        return ret;
    }

    @Override
    public Expression count(String var, double val)
    {
        Multiplication ret = (Multiplication) this.clone();

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

    Multiplication(Expression a, Expression b)
    {
        FirstOper = a;
        SecondOper = b;
    }
}
