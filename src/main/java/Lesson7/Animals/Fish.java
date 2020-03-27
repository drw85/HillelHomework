package Lesson7.Animals;

public class Fish extends PetAnimal {

    public Fish(final int age, final float wight, final String color, final String name, final boolean isVaccinated) {
        super(age, wight, color, name, isVaccinated);
    }

    @Override
    public String speak() {
        return "....";
    }
}
