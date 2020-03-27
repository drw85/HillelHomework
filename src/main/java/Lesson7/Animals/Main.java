package Lesson7.Animals;

public class Main {
    public static void main(String[] args) {
        ArrayAnimals animalsArr = new ArrayAnimals();
        animalsArr.add(new Fish(1, 0.5f, "GOLD", "GOLDIE", false));
        animalsArr.add(new Dog(3, 4.5f, "BROWN", "ROCKY", true));
        animalsArr.add(new Cat(3, 8, "GREY", "MONYA", true));
        animalsArr.add(new Wolf(4, 50, "GREY", true));
        animalsArr.add(new Lion(9, 170, "YELLOW", true));
        animalsArr.add(new Giraffe(17, 1000, "YELLOW", false));
        animalsArr.add(new Crocodile(70, 300, "GREEN", true));
        animalsArr.add(new Hamster(1, 1, "WHITE", "KHOMA", true));
        animalsArr.add(new GuideDog(4, 15, "BLACK", "TUZIK", true, true));

        for (int i = 0; i < animalsArr.getSize(); i++) {
            System.out.println(animalsArr.get(i).speak());
        }

        System.out.println("Let's feed the predators!");
        System.out.println("Getting two more giraffes.");
        animalsArr.add(new Giraffe(17, 1000, "YELLOW", false));
        animalsArr.add(new Giraffe(17, 1000, "YELLOW", false));
        System.out.println("NUMBER OF NON-PREDATORY ANIMALS: "+animalsArr.countNotPredators());
        for (int i = 0; i < animalsArr.getSize(); i++) {
            Animal animal = animalsArr.get(i);
            if (animal instanceof WildAnimal) {
                if (((WildAnimal) animal).isPredator()) {
                    for (int j = 0; j < animalsArr.getSize(); j++) {
                        Animal animal2 = animalsArr.get(j);
                        if (animal2 instanceof WildAnimal) {
                            if (!((WildAnimal) animal2).isPredator()) {
                                ((WildAnimal) animal).eat(animal2, animalsArr);
                                System.out.println("NUMBER OF NON-PREDATORY ANIMALS: "+animalsArr.countNotPredators());
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}