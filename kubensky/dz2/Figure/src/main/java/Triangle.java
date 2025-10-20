public class Triangle implements Figure {
    private final double side;

    public Triangle(double side) { this.side = side; }

    @Override
    public double perimeter() { return 3*side; }

    @Override
    public double square() { return side*side*Math.sqrt(3.0)/4; }

    public double intRadius() { return side*Math.sqrt(3.0)/6; }

    public double extRadius() { return side/Math.sqrt(3.0); }
}