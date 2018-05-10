import java.util.HashMap;


public interface Expression
{
    public String toString();

    public Expression clone();

    public default Boolean isNum()
    {
        return Boolean.FALSE;
    }

    public Expression count();

    public double count(double val) throws TooManyVariablesException;

    public double getNumber() throws NotANumberException;

    public int countVariables();

    public HashMap<String, Variable> getVariables(HashMap<String, Variable> vars);

    public HashMap<String, Variable> getVariables();

    public Expression Differentiate(String var);

    public Expression Differentiate(Variable var);

    public Expression count(String var, double val);

}
