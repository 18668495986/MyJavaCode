package beanfactory.decorate;

public class NBStudent extends Student{ //获取到的类的对象必须得是public的 所以需要单独建一个类
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