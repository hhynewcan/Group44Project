package Entities;

import java.util.ArrayList;
import java.util.LinkedList;

public class GroceryList
{
    ArrayList<Ingredient> currentList;

    public GroceryList()
    {
        this.currentList = new ArrayList<>();

    }

    public ArrayList<Ingredient> getGroceryList()
    {
        return currentList;
    }

    public void remove(int index)
    {
        currentList.remove(index);
    }

    public void addIngredient(Ingredient addMe)
    {
        this.currentList.add(addMe);
    }

    public void addListOfIngredients(LinkedList<Ingredient> desiredIngredients)
    {
        this.currentList.addAll(desiredIngredients);
    }

}
