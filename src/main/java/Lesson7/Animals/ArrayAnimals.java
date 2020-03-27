package Lesson7.Animals;

public class ArrayAnimals {
    Animal[] animals = new Animal[0];

    public void add(Animal animal) {
        Animal[] newArr = new Animal[animals.length + 1];
        animal.setId(animals.length);
        if (animals.length == 0) {
            newArr[0] = animal;
        } else {
            for (int i = 0; i < animals.length; i++) {
                newArr[i] = animals[i];
            }
            newArr[animals.length] = animal;
        }
        animals = newArr;
    }

    public Animal get(int index) {
        if (index >= 0 && index < animals.length) {
            return animals[index];
        } else {
            System.out.println("WRONG INDEX");
            return null;
        }
    }

    public int getSize() {
        return animals.length;
    }

    public void remove(Animal animalToRemove) {
        for (int i = 0; i < animals.length; i++) {
            if (animals[i].equals(animalToRemove)) {
                remove(i);
            }
        }
    }

    public void remove(int indexToRemove) {
        int indexCounter = 0;
        Animal[] newArr = new Animal[animals.length - 1];
        for (int i = 0; i < animals.length; i++) {
            if (i != indexToRemove) {
                newArr[indexCounter] = animals[i];
                indexCounter++;
            }
        }
        animals = newArr;
    }

    public int countNotPredators() {
        int counter = 0;
        for (Animal animal :
                animals) {
            if (animal instanceof WildAnimal) {
                if (!((WildAnimal) animal).isPredator()) {
                    counter++;
                }
            }
        }
        return counter;
    }
}
