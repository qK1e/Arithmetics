import java.util.HashMap;


public class Power implements Expression
{
    private Expression bottom, top;

    @Override
    public String toString()
    {
        return "(" + bottom.toString() + ")"  + "^" + "(" + top.toString() + ")";
    }

    @Override
    public Expression clone()
    {
        return new Power(bottom.clone(), top.clone());
    }

    @Override
    public Expression count()
    {
        if (bottom.count().isNum() && top.count().isNum())
        {
            try
            {
                double ret = Math.pow(bottom.getNumber(), top.getNumber());
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
    public double count(double val) throws TooManyVariablesException {
        if(this.countVariables() < 2)
        {
            return bottom.count(val) + top.count(val);
        }
        throw new TooManyVariablesException();
    }

    @Override
    public double getNumber() throws NotANumberException
    {
        try
        {
            double ret = Math.pow(bottom.getNumber(), top.getNumber());
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
        bottom.getVariables(vars);
        top.getVariables(vars);
        return vars;
    }

    @Override
    public Expression Differentiate(String var)
    {
        if (!top.getVariables().containsKey(var))
        {
            Sum sum = new Sum(top, new Num(-1));
            Power pow = new Power(bottom, sum);
            Multiplication mult1 = new Multiplication(top, pow);
            return mult1;
        }
        System.out.println("Differentiation of Power is not supported: need Ln()");
        return this.clone();
    }

    @Override
    public Expression Differentiate(Variable var)
    {
        if (!top.getVariables().containsKey(var.getVal()))
        {
            Sum sum = new Sum(top, new Num(-1));
            Power pow = new Power(bottom, sum);
            Multiplication mult1 = new Multiplication(top, pow);
            return mult1;
        }
        System.out.println("Differentiation of Power is not supported: need Ln()");
        return this.clone();
    }

    @Override
    public Expression count(String var, double val)
    {
        Power ret = (Power) this.clone();

        ret.bottom = this.top.count(var, val);
        ret.top = this.top.count(var, val);

        return ret.count();
    }

    Power(Expression a, Expression b)
    {
        bottom = a;
        top = b;
    }

    @Override
    public int countVariables()
    {
        HashMap<String, Variable> vars = bottom.getVariables();
        top.getVariables(vars);

        return vars.size();
    }

    @Override
    public HashMap<String, Variable> getVariables(HashMap<String, Variable> vars)
    {
        bottom.getVariables(vars);
        top.getVariables(vars);

        return vars;
    }
}
