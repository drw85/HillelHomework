package Lesson7.Animals;

public class Dog extends PetAnimal {

    public Dog(final int age, final float wight, final String color, final String name, final boolean isVaccinated) {
        super(age, wight, color, name, isVaccinated);
    }

    public String speak() {
        return super.speak() + " Woof";
    }
}
