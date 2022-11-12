package Search;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Objects;

import Entities.*;

public class SearchByTags
{


    /*
    PURPOSE:
    PRE-CONDITIONS:
    POST-CONDITIONS:
    RETURN:
    */
    public LinkedList<Recipe> search(String[] tags, LinkedList<Recipe> allRecipes)
    {
        //Check if given an empty recipeRepository
        if (allRecipes.size() == 0)
        {
            return null;
        }

        LinkedList<Recipe> matchingRecipes = new LinkedList<>();

        for (Recipe recipe : allRecipes) // For each recipe
        {
            for (String target : tags) // Looking for each target given in params
            {
                for (Tag tag : recipe.getTags()) // Search through the current recipe's tags.
                {
                    if (tag.getName().toLowerCase().contains(target.toLowerCase())) //If we found a match, add it.
                    {
                        matchingRecipes.add(recipe);
                    }
                }
            }
        }

        if (matchingRecipes.size() == 0) return null; // No matches were found
        return matchingRecipes;
    }


    public static void main(String[] args)
    {

        RecipeRepository repo = new RecipeRepository();

        SearchByTags searchByTags = new SearchByTags();
        LinkedList<Recipe> recipes = new LinkedList<>();
        Tag dessertTag = new Tag("dessert");
        Tag veg = new Tag("vegetarian");
        Tag mexican = new Tag("mexican");
        Tag keto = new Tag("keto");
        Tag indian = new Tag("indian");

        //Tag[] tags = {dessertTag, veg, mexican, keto, indian};
        //String[] tags = {"testable033", "testable1","wontFindMe"};
        String[] tags = {"wontFindMe"};


        //String[] tags = {"dessertTag", "veg", "mexican", "keto", "indian"};

        if (searchByTags.search(new String[]{"wontFindMe"}, repo.allRecipes) != null)
            System.out.println("TEST FAILED");
        else System.out.println("TEST passed");

        if (searchByTags.search(new String[]{"testable1"}, repo.allRecipes) == null)
            System.out.println("TEST FAILED");
        else System.out.println("TEST passed");



        if (searchByTags.search(new String[]{"noReturn","also no return",
                "testable3", "definitely no return"}, repo.allRecipes) == null)
            System.out.println("TEST FAILED");
        else System.out.println("TEST passed");
    }
}

