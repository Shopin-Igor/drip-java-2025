package Task1;

public sealed interface Expr permits Constant, Negate, Exponent, Addition, Multiplication {
    public double evaluate();
}
