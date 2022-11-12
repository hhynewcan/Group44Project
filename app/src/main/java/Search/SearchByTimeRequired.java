package Search;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

import Entities.*;

public class SearchByTimeRequired
{
    @RequiresApi(api = Build.VERSION_CODES.N)
    public LinkedList<Recipe> search(LinkedList<Recipe> recipeList)
    {
        Collections.sort(recipeList, Comparator.comparing(Recipe::getTimeRequired));
        return recipeList;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args)
    {
        SearchByTimeRequired newSearch = new SearchByTimeRequired();
        LinkedList<Recipe> sorted = newSearch.search(RecipeRepository.allRecipes);
        for (Recipe recipe : sorted)
        {
            System.out.println("Name: " + recipe.getName() + "\t\ttime required: "+ recipe.getTimeRequired());
        }

        System.out.println("hi");
    }


}
