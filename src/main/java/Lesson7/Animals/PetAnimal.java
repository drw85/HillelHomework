package Lesson7.Animals;

public abstract class PetAnimal extends Animal {
    private String name;
    private boolean isVaccinated;

    public PetAnimal(final int age, final float wight, final String color, final String name, final boolean isVaccinated) {
        super(age, wight, color);
        this.name = name;
        this.isVaccinated = isVaccinated;
    }

    public String speak(){
        return super.speak()+"my name is " + name+".";
    }
}
