package Task1;

public record Exponent(Expr number, Expr exponent) implements Expr {
    @Override
    public double evaluate() {
        return Math.pow(number.evaluate(), exponent.evaluate());
    }
}
