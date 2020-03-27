package Lesson7.Animals;

public class Cat extends PetAnimal {

    public Cat(final int age, final float wight, final String color, final String name, final boolean isVaccinated) {
        super(age, wight, color, name, isVaccinated);
    }

    @Override
    public String speak() {
        return super.speak() + " Meow";
    }
}
