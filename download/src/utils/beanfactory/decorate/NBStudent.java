package utils.beanfactory.decorate;

public class NBStudent extends Student {
    public Student student;

    public NBStudent() {
    }

    public NBStudent(Student student) {
        this.student = student;
    }

    @Override
    public void study() {
        student.study();
        System.out.println("chinese");
        System.out.println("English");
        System.out.println("science");
    }

    public void study(Student student) {
        student.study();
        System.out.println("chinese");
        System.out.println("English");
        System.out.println("science");
    }
}