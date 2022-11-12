package Search;

import java.util.LinkedList;

import Entities.*;

public class SearchByID
{

    /*
    PURPOSE:         To iterate through the given recipeList, comparing each recipe's ID to the
                     given target ID until it is found (if it exists).
    PRE-CONDITIONS:  id --> A string representing the target recipe.getRecipeID to be found.
                     recipeList --> A list of recipes to search through.
    POST-CONDITIONS: None.
    RETURN:          A recipe that matches the desired ID, or a null pointer if none exists.
     */
    public Recipe search(String id, LinkedList<Recipe> recipeList)
    {
        int value = parseIntOrNull(id);
        if(value >= 0 )
        {
            for (Recipe recipe : recipeList)
            {
                if (recipe.getRecipeID() == value)
                {
                    return recipe;
                }
            }
        }
        return null;
    }


    /*
    PURPOSE:         Handles possible exceptions when attempting to parse an integer from a string.
    PRE-CONDITIONS:  value --> The string to be converted to an integer.
    POST-CONDITIONS: None.
    RETURN:          -1 --> conversion to int failed.
                     Otherwise, returns the integer 'value' represents.
     */
    public Integer parseIntOrNull(String value)
    {
        try
        {
            return Integer.parseInt(value);
        }
        catch (NumberFormatException e)
        {
            return -1;
        }
    }


    public static void main(String[] args)
    {
        SearchByID searchById = new SearchByID();
        RecipeRepository repository = new RecipeRepository();

        //01 test
        Recipe result = searchById.search("1", repository.allRecipes);
        if(result == null)
        {
            System.out.println("Test 1 failed");
        }

        //02 test everything is right
        result = searchById.search("2", repository.allRecipes);
        if(result.getRecipeID() != 2){
            System.out.println("Test 2 failed");
        }

        //03 test (unexpected number -ve)
        result = searchById.search("-23", repository.allRecipes);
        if(result != null)
        {
            System.out.println("Test 3 failed");
        }



        //04 test (unexpected number alphabet)
        result = searchById.search("A", repository.allRecipes);
        if(result != null){
            System.out.println("Test 4 failed");
        }
    }
}
