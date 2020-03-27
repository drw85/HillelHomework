package Lesson7.Animals;

public abstract class Animal {
    private int id;
    private int age;
    private float wight;
    private String color;

    public Animal(int age, float wight, String color) {
        this.age = age;
        this.wight = wight;
        this.color = color;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String speak() {
        return "Hello, ";
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
