import java.util.List;

public class TestGradebook {
    public static void main(String[] args){

        Gradebook journal = new Gradebook();
        Student ivanov = journal.addStudent("Иван", "Иванов", "ПМ-2541");
        Student ptitsun = journal.addStudent("Игорь", "Птицин", "ПМ-2541");
        Student zagitova = journal.addStudent("Екатерина", "Загитова", "ПМ-2541");
        Student smornov = journal.addStudent("Андрей", "Смирнов", "ПМ-2541");

        Student petrov = new Student("Петр", "Петров", "ПМ-2541");
        journal.addStudent(petrov); // добавляем в журнал

//      Добавляем оценки
        journal.addGrade(ivanov, "Математика", 5);
        journal.addGrade(ivanov, "Программирование", 4);
        journal.addGrade(petrov, "Математика", 5);
        journal.addGrade(petrov, "Программирование", 5);
        journal.addGrade(ptitsun, "Математика", 3);
        journal.addGrade(ptitsun, "Программирование", 4);
        journal.addGrade(zagitova, "Математика", 2);
        journal.addGrade(zagitova, "Программирование", 3);

//      Кто получил все пятёрки?
        List<Student> allFives = journal.getStudents5();  // [petrov]
//      Кто имеет четвёрки и нет оценок ниже?
        List<Student> onlyFours = journal.getStudents4(); // [ivanov]
//      Кто имеет тройки и нет оценок ниже?
        List<Student> onlyThree = journal.getStudents3(); // [ptitsun]
//      Кто имеет двойки?
        List<Student> onlyTwo = journal.getStudents2(); // [zagitova]
//      Кто не имеет оценок?
        List<Student> onlyZero = journal.getStudents0(); // [smornov]


//      Проверка
        System.out.println(journal.listStudents());
        System.out.println("Студент в журнале: " + journal.findStudent("Иван","Иванов","ПМ-2541"));
        System.out.println("Студент не в журнале: " + journal.findStudent("Петя","Иванов","ПМ-2541"));
        System.out.println("Все пятерки: " + allFives);
        System.out.println("Имеет четвёрки: " + onlyFours);
        System.out.println("Имеет тройки: " + onlyThree);
        System.out.println("Имеет двойки: " + onlyTwo);
        System.out.println("Нет оценок: " + onlyZero);

        Gradebook journal2 = new Gradebook();
        Student ivanov2 = journal2.addStudent("Иван", "Иванов","");
    }
}
