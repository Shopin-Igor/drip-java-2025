package Task1;

public record Multiplication(Expr number1, Expr number2) implements Expr {
    @Override
    public double evaluate() {
        return number1.evaluate() * number2.evaluate();
    }
}
