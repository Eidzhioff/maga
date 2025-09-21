public class Rectangle implements Figure{

    private final double width;
    private final double height;

    public Rectangle(double width, double height){
        this.width = width;
        this.height = height;
    }

    @Override
    public double perimeter() {
        return 2 * (width + height);
    }

    @Override
    public double square() {
        return width * height;
    }
}
