package Search;

import java.util.LinkedList;
import Entities.*;

public class SearchByName
{
    /**
     * searches the list of recipes for the recipes that have similar names
     * @param name name of recipes that user typed in
     * @return list  of recipes that matches with the name
     */


    /*
    PURPOSE:         To search for a recipe that matches the name
    PRE-CONDITIONS:
    POST-CONDITIONS:
    RETURN:
     */
    public LinkedList<Recipe> search(String name, LinkedList<Recipe> recipeList)
    {
        LinkedList<Recipe> recipesResult = new LinkedList<>();
        for(Recipe recipe : recipeList)
        {
            if( recipe.getName().toLowerCase().contains(name.toLowerCase()))
            {
                recipesResult.add(recipe);
            }
        }
        return recipesResult;
    }


    public static void main(String[] args)
    {
        SearchByName searchByName  = new SearchByName();
        RecipeRepository repository = new RecipeRepository();

        //01 test
        LinkedList<Recipe> resultList = searchByName.search("Potatoes", repository.allRecipes);
        if(resultList == null){
                    System.out.println("test 1 failed Recipe, nothing was returned" );
        }

        //02 test small letters
        resultList = searchByName.search("cookies", repository.allRecipes);
        if(resultList == null){
            System.out.println("test 2 failed Recipe, nothing was returned" );
        }

        //03 test Capital letters
        resultList = searchByName.search("COOKIES", repository.allRecipes);
        if(resultList == null){
            System.out.println("test 3 failed Recipe, nothing was returned" );
        }


        //04 testing for recipes with name that contains same subStrings
        resultList = searchByName.search("test", repository.allRecipes);
        if(resultList == null || resultList.size()==0){
            System.out.println("test 4 failed Recipe, nothing was returned" );
        }

        //05 test
        resultList = searchByName.search("1234", repository.allRecipes);
        if(resultList.size() != 0){
            System.out.println("test 5 failed Recipe return for this case is supposed to be null");
        }
    }
}
