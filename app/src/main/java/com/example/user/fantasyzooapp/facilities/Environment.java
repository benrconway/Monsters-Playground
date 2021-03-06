package com.example.user.fantasyzooapp.facilities;

import com.example.user.fantasyzooapp.animals.Animal;
import com.example.user.fantasyzooapp.animals.Carnivore;
import com.example.user.fantasyzooapp.animals.Herbivore;
import com.example.user.fantasyzooapp.animals.Omnivore;
import com.example.user.fantasyzooapp.animals.Size;
import com.example.user.fantasyzooapp.facilities.Building;
import com.example.user.fantasyzooapp.food.Edible;
import com.example.user.fantasyzooapp.food.Flesh;
import com.example.user.fantasyzooapp.food.Vegetation;
import com.example.user.fantasyzooapp.people.Customer;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by user on 22/09/2017.
 */

public class Environment extends Building {
    private Random dm;
    private ArrayList<Flesh> meatLocker;
    private ArrayList<Vegetation> larder;
    private ArrayList<Animal> animals;
    private Enum<Size> size;

    public Environment(int value, int viewingCapacity, int staffCapactity, Enum<Size> size){
        super(value, viewingCapacity, staffCapactity);
        this.size = size;
        this.animals = new ArrayList<>();
        this.meatLocker = new ArrayList<>();
        this.larder = new ArrayList<>();
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public Enum<Size> getSize() {
        return size;
    }

    public ArrayList<Flesh> getMeatLocker() {
        return meatLocker;
    }

    public ArrayList<Vegetation> getLarder() {
        return larder;
    }

    public void takeMeatFromStocks(Flesh meat){
        meatLocker.add(meat);
    }

    public void takeVegFromStocks(Vegetation food){
        larder.add(food);
    }

    public Animal animal(int animalIndex) {
        return animals.get(animalIndex);
    }

    public Customer getCustomer(int index){
        return getFloorSpace().get(index);
    }

    public void feedCarnivore(Carnivore carnivore){
        Flesh meatForFeeding = meatLocker.get(0);
        carnivore.eat(meatForFeeding);
        //Test hunger and happiness to see if Carnivore tries to eat Staff
    }

    public void feedHerbivore(Herbivore herbivore){
        Vegetation vegForFeeding = larder.get(0);
        herbivore.eat(vegForFeeding);
        //Test to see if they will eat while staff are around... maybe they won't eat.
        // or they might try to attack staff.
    }

    public void feedOmnivore(Omnivore omnivore) {
        Edible food = checkFoodSupplies();
        if(food !=null) {
            omnivore.eat(food);
        }
        //Make it so that nulls make the animal hungry...?
    }


    public void takeIn(Animal animal){
        ArrayList<Animal> animalsToCompare = collectAnimalsForComparison(animal);

       if((areAnimalsCompatible(animalsToCompare))  &&
               (isEnvironmentCorrectSize(animal.getSize()))) {
            animals.add(animal);
        }
    }

    public void remove(Animal animal) {
        animals.remove(animal);
    }

    private boolean isEnvironmentCorrectSize(Enum<Size> animalSize){
        boolean isBigEnough = false;
            if(animalSize.ordinal() <= size.ordinal()){
                isBigEnough = true;
            }
        return isBigEnough;
    }

    private ArrayList<Animal> collectAnimalsForComparison(Animal animal){
        ArrayList<Animal> animalsForComparison = new ArrayList<>();
        for(Animal animalPresent: animals){
            animalsForComparison.add(animalPresent);
        }
        animalsForComparison.add(animal);
        return animalsForComparison;
    }

    private boolean areAnimalsCompatible(ArrayList<Animal> animalsToBeChecked){
        boolean areCompatible = false;
        ArrayList<Animal> carnivores = new ArrayList<>();
        ArrayList<Animal> others = new ArrayList<>();
        for (Animal animal: animalsToBeChecked){
            if (animal instanceof Carnivore){
                carnivores.add(animal);
            }else{ others.add(animal);}
        }
        if((others.size() == 0) || (carnivores.size() == 0)){
            areCompatible = true;
        }
        return areCompatible;
    }

    private Edible checkFoodSupplies() {
        Edible food = null;
        if(larder.size() > 0) {
            food = (Edible) larder.get(0);
        } else { food = (Edible) meatLocker.get(0);}
        return food;
    }

    public void addToMeatLocker(Flesh meat) {
        meatLocker.add(meat);
    }

    public void addToLarder(Vegetation vegetable) {
        larder.add(vegetable);
    }

    public void feedAnimals() {
        if(animal(0) instanceof Carnivore) {
            for(Animal animal: animals) {
                Carnivore carnivore = (Carnivore) animal;
                feedCarnivore(carnivore);
            }
        }else {
            for(Animal animal: animals)
                if(animal instanceof Herbivore){
                    Herbivore herbivore = (Herbivore) animal;
                    feedHerbivore(herbivore);
                }else { if(animal instanceof Omnivore){
                    Omnivore omnivore = (Omnivore) animal;
                    feedOmnivore(omnivore);

                }
            }
        }
    }

    //Customers annoy animals
    public void annoyTheAnimal(int customerIndex) {
//        if(dm.nextInt(40)+ getCustomer(customerIndex).getSkill() > 75) {
            for(Animal animal: animals){
                animal.adjustHappiness(-10);
            }
//        }
    }
}
