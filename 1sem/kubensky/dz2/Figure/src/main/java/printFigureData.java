import java.lang.reflect.Method;
public class printFigureData {

    public static void main(String[] args) {
        printFigureData("Triangle", 1);
        printFigureData("Square", 2);
        printFigureData("Circle", 3);
    }

    public static void printFigureData(String className, double param) {
        try {
            Class<?> clazz = Class.forName(className);
            Figure figure = (Figure) clazz.getConstructor(double.class).newInstance(param);

            Method[] methods = clazz.getDeclaredMethods();

            System.out.println(clazz.getSimpleName());

            //, чтобы вызывались только те методы, которые имеют атрибут public, возвращают вещественный результат и не имеют аргументов.
            for (Method method : methods) {
                if (method.isAccessible() || method.canAccess(figure)) {
                    method.setAccessible(true);

                    if (method.getReturnType() == double.class &&
                            method.getParameterCount() == 0) {

                        double result = (double) method.invoke(figure);
                        System.out.println(method.getName() + ": " + result);
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}