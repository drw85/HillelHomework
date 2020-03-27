package Lesson7.Animals;

public class GuideDog extends PetAnimal {
    private boolean isTrained;

    public GuideDog(final int age, final float wight, final String color,
                    final String name, final boolean isVaccinated, final boolean isTrained) {
        super(age, wight, color, name, isVaccinated);
        this.isTrained = isTrained;
    }

    @Override
    public String speak() {
        if (isTrained) {
            return super.speak() + " I can take you home.";
        } else {
            return super.speak();
        }
    }

    public void takeHome() {
        System.out.println("Ok. I will take you home!");
    }
}
