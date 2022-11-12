package Search;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import Entities.*;

public class Search
{
    SearchByName searchByName;
    SearchByID searchByID;
    SearchByTags searchByTags;
    SearchByIngredients searchByIngredients;
    RecipeRepository recipeList;

    /*
    PURPOSE:         Constructor for the entirety of all search capabilities in the system.
    PRE-CONDITIONS:  None.
    POST-CONDITIONS: The Search() and its sub-dependencies are all instantiated.
    RETURN:          None.
     */
    public Search()
    {
        this.searchByName = new SearchByName();
        this.searchByID = new SearchByID();
        this.searchByIngredients =new SearchByIngredients();
        this.searchByTags = new SearchByTags();
        this.recipeList = new RecipeRepository();
    }


    /*
    PURPOSE:         Parses the given object (String, Tag, ID[int], etc) as a search query, and
                     calls the appropriate subroutine to perform the search.
    PRE-CONDITIONS:  Param keyword: A generic object type, currently supports:
                            String, String[], int object types.
    POST-CONDITIONS: Will call the relevant subroutine to perform the search.
    RETURN:          Null --> No matching results found.
                     Otherwise, a LinkedList of recipes that match the given search target.
     */
    public <T> LinkedList<Recipe> search(T keyword)
    {
        LinkedList<Recipe> searchResults = new LinkedList<>();
        if (keyword instanceof String) //Given recipe name.
        {
            System.out.println("You want to find recipe name: " + keyword);
            searchResults = this.searchByName.search((String) keyword,this.recipeList.allRecipes);
        }

        if (keyword instanceof String[]) //Given tags.
        {
            searchResults.addAll(this.searchByTags.search((String[])keyword,this.recipeList.allRecipes));
            System.out.println("You want to find recipes with tags: " + Arrays.toString((String[])keyword));
        }

        if (keyword instanceof Integer) //Given recipe ID.
        {
            searchResults.add(this.searchByID.search(keyword.toString(),this.recipeList.allRecipes));
            System.out.println("You want to find recipe with ID " + keyword);
        }
//
//        if (keyword instanceof Tag[])
//        {
//            //TODO: Handle this.
//            System.out.println("Yes, I am an ingredient.");
//        }
//
//        if (keyword instanceof Tag)
//        {
//            //TODO: handle this.
//        }
//
//        if (keyword instanceof Ingredient)
//        {
//            //TODO: handle this.
//            System.out.println("Yes, I am an ingredient.");
//        }
        if (searchResults.size() == 0) return null;
        else if (searchResults.peekFirst() == null) return null;
        return searchResults;
    }



    public static void main(String[] args)
    {
        Search newSearch = new Search();

        //01 testing for recipes with name that contains same subStrings
        LinkedList<Recipe> recipes = newSearch.search("test");
        if(recipes == null || recipes.size()==0){
            System.out.println("test 1 failed Recipe, nothing was returned" );
        }
        else{
            for(Recipe recipe : recipes){
                if(!recipe.getName().contains("test")){
                    System.out.println("Returned a recipe that does not contains the keyword in the name");
                }
            }
        }

        //02 testing if search calls  id given an array of string tags
        recipes = newSearch.search(new String[]{"Deserts", "Delicious"});
        if(recipes == null || recipes.size()==0){
            System.out.println("test 2 failed Recipe, nothing was returned" );
        }


        //03 giving an array fo just one tag
        recipes = newSearch.search(new String[]{"Delicious"});
//        for(Recipe recipe : recipes){
//            System.out.println(recipe.printRecipe());
//        }
        int counter =1;
        if(recipes == null || recipes.size()==0){
            System.out.println("test 3 failed Recipe, nothing was returned" );
        }

//        else{
//
//            for(Recipe recipe : recipes){
//                for(Tag tag : recipe.getTags()){
//                    if(tag.getName().equals("Delicious")){
//                        break;
//                    }
//                }
//                counter++;
//            }
//        }
//        if(counter==recipes.size()){
//            System.out.println("Recipes that were returned does not contain");
//        }
    }
}
