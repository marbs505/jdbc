package Database;

public class Student extends Person {
    public String course;

    public Student(String firstName, String lastName, int age, String course) {
        super(firstName, lastName, age);
        this.course = course;
    }
}
