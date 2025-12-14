package Task1;

public record Constant(Expr number) implements Expr {
    @Override
    public double evaluate() {
        return number.evaluate();
    }
}