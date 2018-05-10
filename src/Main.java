
public class Main
{
    public static void main(String[] args)
    {
        Variable x = new Variable("x");

        Num num1 = new Num(8);
        Sum sum2 = new Sum(x, num1);
        Expression ex = sum2.count("x", 4);

        System.out.println(ex.toString()+"\n\n");

        System.out.println(x.toString());
        Variable y = new Variable("y");
        System.out.println(y.toString());
        Sum sum1 = new Sum(x, y);
        System.out.println(sum1.toString());

        System.out.println("\n\n");

        Sum sum3 = new Sum(x, num1);
        Expression ex1 = sum3.count("x", 5);
        System.out.println(sum3.toString());
        System.out.println(ex1.toString());

        System.out.println("\n\n");

        Power pow1 = new Power(num1, new Num(3));
        System.out.println(pow1.toString());
        Expression ex2 = pow1.count();
        System.out.println(ex2.toString());

        System.out.println("\n\n");

        Power pow2 = new Power(x, new Num(3));
        System.out.println(pow2.toString());
        Expression ex3 = pow2.count("x", 2);
        System.out.println(ex3.toString());

        System.out.println("\n\n");

        Multiplication mul = new Multiplication(x, pow2);
        System.out.println(mul.toString());
        Expression ex4 = mul.count("x", 2);
        System.out.println(ex4.toString());

        System.out.println("\n\n");

        Sum sum4 = new Sum(x, y);
        System.out.println(sum4.toString());
        Multiplication mult1 = new Multiplication(y, new Num(-1));
        System.out.println(mult1.toString());
        Sum sum5 = new Sum(x, mult1);
        System.out.println(sum5.toString());
        Division div1 = new Division(sum4, sum5);
        System.out.println(div1.toString());
        Expression ex5 = div1.count("y", 7);
        System.out.println(ex5.toString());

        System.out.println("\n\n");

        Expression ex6 = div1.Differentiate(x);
        System.out.println(ex6.toString());

        System.out.println("\n\n");

        Power pow3 = new Power(sum3, y);
        Expression ex7 = pow3.Differentiate(x);
        System.out.println(ex7.toString());
    }
}
