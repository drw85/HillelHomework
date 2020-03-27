package Lesson7.Animals;

public abstract class WildAnimal extends Animal {
    private boolean isPredator;

    public WildAnimal(final int age, final float wight, final String color, final boolean isPredator) {
        super(age, wight, color);
        this.isPredator = isPredator;
    }

    @Override
    public String speak() {
        if (this.isPredator) {
            return super.speak() + "I am a wild animal and I am angry";
        } else {
            return super.speak() + "I am a wild animal";
        }
    }

    public boolean isPredator() {
        return isPredator;
    }

    public boolean eat(Animal food, ArrayAnimals animalsArr) {
        if (isPredator) {
            if (food instanceof WildAnimal) {
                if (!((WildAnimal) food).isPredator) {
                    System.out.println(toString() + " eating " + food.toString());
                    animalsArr.remove(food);
                    return true;
                } else {
                    System.out.println("Predator not eating predator");
                    return false;
                }
            } else {
                System.out.println("Not allowed to eat pets");
                return false;
            }
        } else {
            System.out.println("Not predator");
            return false;
        }
    }
}
