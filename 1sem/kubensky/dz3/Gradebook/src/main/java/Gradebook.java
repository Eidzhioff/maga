import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Gradebook {
//  Проверка на null
    private void checkStudent(String firstName, String lastName, String group) {
        if (firstName == null || lastName == null || group == null) {
            throw new IllegalArgumentException("Аргументы не должны быть null");
        }
        if (firstName.isEmpty() || lastName.isEmpty() || group.isEmpty()) {
            throw new IllegalArgumentException("Аргументы не должны быть null");
        }
    }
//  Проверка на диапазон оценок
    private void checkGrade(int grade) {
        if (grade < 2 || grade > 5) {
            throw new IllegalArgumentException("Оценка должна быть в диапазоне [2;5]");
        }
    }

    private Map<Student, Map<String, Integer>> students = new HashMap<>();

    public Student addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Аргументы не должны быть null");
        }
        if (!students.containsKey(student)) {
            students.put(student, new HashMap<>());
        }
        return student;
    }

    public Student addStudent(String firstName, String lastName, String group) {
        checkStudent(firstName, lastName, group);
        Student student = new Student(firstName, lastName, group);
        return addStudent(student);
    }

    public List<Student> listStudents() {
        return new ArrayList<>(students.keySet());
    }

    public Student findStudent(String firstName, String lastName, String group) {
        checkStudent(firstName, lastName, group);
        for (Student s : students.keySet()) {
            if (s.firstName().equals(firstName) && s.lastName().equals(lastName) && s.group().equals(group)){
                return s;
            }
        }
        return null;
    }

    public void addGrade(Student s, String subject, int grade) {
        checkGrade(grade);
        if (students.containsKey(s)) {
            students.get(s).put(subject, grade);
        }
    }

    public void removeGrade(Student s, String subject) {
        if (students.containsKey(s)) {
            students.get(s).remove(subject);
        }
    }

    public List<Student> getStudents5() {
        List<Student> studentList = new ArrayList<>();
        for (Map.Entry<Student, Map<String, Integer>> entry : students.entrySet()) {
            boolean onlyFives = true;
            for (int grade : entry.getValue().values()) {
                if (grade != 5) {
                    onlyFives = false;
                    break;
                }
            }
            if (onlyFives && !entry.getValue().isEmpty()) {
                studentList.add(entry.getKey());
            }
        }
        return studentList;
    }

    public List<Student> getStudents4() {
        List<Student> studentList = new ArrayList<>();
        for (Map.Entry<Student, Map<String, Integer>> entry : students.entrySet()) {
            boolean hasFour = false;
            boolean threshold = true;
            for (int grade : entry.getValue().values()) {
                if (grade == 4) {
                    hasFour = true;
                }
                if (grade < 4) {
                    threshold = false;
                    break;
                }
            }
            if (threshold && hasFour) {
                studentList.add(entry.getKey());
            }
        }
        return studentList;
    }

    public List<Student> getStudents3() {
        List<Student> studentList = new ArrayList<>();
        for (Map.Entry<Student, Map<String, Integer>> entry : students.entrySet()) {
            boolean hasThree = false;
            boolean threshold = true;
            for (int grade : entry.getValue().values()) {
                if (grade == 3) {
                    hasThree = true;
                }
                if (grade < 3) {
                    threshold = false;
                    break;
                }
            }
            if (threshold && hasThree) {
                studentList.add(entry.getKey());
            }
        }
        return studentList;
    }

    public List<Student> getStudents2() {
        List<Student> studentList = new ArrayList<>();
        for (Map.Entry<Student, Map<String, Integer>> entry : students.entrySet()) {
            for (int grade : entry.getValue().values()) {
                if (grade == 2) {
                    studentList.add(entry.getKey());
                    break;
                }
            }
        }
        return studentList;
    }

    public List<Student> getStudents0() {
        List<Student> studentList = new ArrayList<>();
        for (Map.Entry<Student, Map<String, Integer>> entry : students.entrySet()) {
            if (entry.getValue().isEmpty()) {
                studentList.add(entry.getKey());
            }
        }
        return studentList;
    }
}
