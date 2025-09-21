public interface Figure {
    double perimeter();
    double square();

    public static void main(String[] args){

        Circle crl = new Circle(5);

        Square sqr = new Square(5);

        Rectangle rct = new Rectangle(4,5);

        Point[] polyPoints = {
                new Point(1, 1),
                new Point(3, 1),
                new Point(4, 2),
                new Point(3, 3),
                new Point(1, 3),
                new Point(0, 2)
        };
        Figure poly = new Polygon(polyPoints);

        System.out.println("--------------------------------------");
        System.out.println("Периметр круга " + crl.perimeter());
        System.out.println("Площадь круга " + crl.square());
        System.out.println("--------------------------------------");
        System.out.println("Периметр квадрата " + sqr.perimeter());
        System.out.println("Площадь квадрата " + sqr.square());
        System.out.println("--------------------------------------");
        System.out.println("Периметр прямоугольника " + rct.perimeter());
        System.out.println("Площадь прямоугольника " + rct.square());
        System.out.println("--------------------------------------");
        System.out.println("Периметр многоугольника " + poly.perimeter());
        System.out.println("Площадь многоугольника " + poly.square());
        System.out.println("--------------------------------------");
        //Для проверки на выпуклость необходимо раскоммитить код снизу

//        Point[] polyPoints2 = {
//                new Point(0, 0),
//                new Point(5, 0),
//                new Point(5, 2),
//                new Point(3, 4),
//                new Point(1, 2),
//                new Point(0, 4)
//        };
//        Figure poly2 = new Polygon(polyPoints2);
//        System.out.println("Периметр многоугольника " + poly2.perimeter());
//        System.out.println("Площадь многоугольника " + poly2.square());
    }
}
