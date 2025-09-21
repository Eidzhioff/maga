
public class Polygon implements Figure{

    private Point[] vertices;

    //Провенрка на выпуклость https://otvet.mail.ru/question/25994147
    public Polygon(Point[] vertices){
        if (!Convex(vertices)) {
            throw new RuntimeException("Многоугольник не выпуклый");
        }
        this.vertices = vertices;
    }

    @Override
    public double perimeter() {
        double perimeter = 0;

        for (int i = 0; i < vertices.length; i++){
            Point p1 = vertices[i];
            Point p2 = vertices[(i + 1) % (vertices.length)];
            perimeter += p1.x() * p2.y() - p2.x() * p1.y();
        }
        return perimeter;
    }

    //По формуле Гаусса https://ru.wikipedia.org/wiki/Выпуклый_многоугольник
    @Override
    public double square() {
        double square = 0;
        for (int i = 0; i < vertices.length; i++) {
            Point p1 = vertices[i];
            Point p2 = vertices[(i + 1) % (vertices.length)];
            square += p1.x() * p2.y() - p2.x() * p1.y();
        }
        return square/2;
    }

    private double vecMultiplication(Point p1, Point p2, Point p3) {
        return (p2.x() - p1.x()) * (p3.y() - p1.y()) -
                (p2.y() - p1.y()) * (p3.x() - p1.x());
    }

    private boolean Convex(Point[] vertices){
        if (vertices.length < 3) {
            return false;
        }

        boolean sequentially = false;
        boolean negativeSequentially = false;

        for (int i = 0; i < vertices.length; i++) {
            Point p1 = vertices[i];
            Point p2 = vertices[(i + 1) % vertices.length];
            Point p3 = vertices[(i + 2) % vertices.length];

            double crossProduct = vecMultiplication(p1, p2, p3);

            if (crossProduct > 0) {
                sequentially = true;
            } else if (crossProduct < 0) {
                negativeSequentially = true;
            }

            if (sequentially && negativeSequentially) {
                return false;
            }
        }

        return true;
    }
}
