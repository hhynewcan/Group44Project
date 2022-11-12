package Entities;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class RecipeRepository
{
    public static LinkedList<Recipe> allRecipes;

    public RecipeRepository()
    {
        allRecipes = generateTestRecipes();
    }

    /*
    PURPOSE:         To create and return an array of randomly generated recipes.
    PRE-CONDITIONS:  None.
    POST-CONDITIONS: Calls random();
    RETURN:          An array of randomly generated recipes.
     */
    public LinkedList<Recipe> generateTestRecipes()
    {
        //Ingredient Creation
        Ingredient[] ingredientArray = new Ingredient[7];
        ingredientArray[0] = new Ingredient("Flour");
        ingredientArray[1] = new Ingredient("Baking Soda");
        ingredientArray[2] = new Ingredient("Baking Powder");
        ingredientArray[3] = new Ingredient("Butter");
        ingredientArray[4] = new Ingredient("White Sugar");
        ingredientArray[5] = new Ingredient("Egg");
        ingredientArray[6] = new Ingredient("Vanilla Extract");

        //Making one full proper recipe.
        Recipe sugarCookies = new Recipe("Sugar Cookies",1);
        sugarCookies.addIngredient(ingredientArray[0],"2 + 3/4 cups");
        sugarCookies.addIngredient(ingredientArray[1],"1 teaspoon");
        sugarCookies.addIngredient(ingredientArray[2],"1/2 teaspoon");
        sugarCookies.addIngredient(ingredientArray[3],"1 cup, softened");
        sugarCookies.addIngredient(ingredientArray[4],"1 + 1/2 cups");
        sugarCookies.addIngredient(ingredientArray[5],"1");
        sugarCookies.addIngredient(ingredientArray[6],"1 teaspoon");

        sugarCookies.addTag(new Tag("Deserts"));

        sugarCookies.setInstructions(" Step 1\nPreheat oven to 375 degrees F (190 degrees C). In a small bowl, stir together flour, baking soda, and baking powder. Set aside. \nStep 2\nIn a large bowl, cream together the butter and sugar until smooth. Beat in egg and vanilla. Gradually blend in the dry ingredients. Roll rounded teaspoonfuls of dough into balls, and place onto ungreased cookie sheets./nStep 3Bake 8 to 10 minutes in the preheated oven, or until golden. Let stand on cookie sheet two minutes before removing to cool on wire racks.");

        //Declare a new recipe.
        Recipe mashedPotatoes = new Recipe("Mashed Potatoes",0);
        Ingredient potato = new Ingredient("Potatoes");
        Tag delicious = new Tag("Delicious");
        Tag carbDense = new Tag("carbDense");
        potato.addTag(delicious);
        potato.addTag(carbDense);
        //Fill in its attributes.
        mashedPotatoes.addIngredient(potato,"3");



        //Recipe[] recipes = new Recipe[20];


        LinkedList<Recipe> allRecipes = new LinkedList<>();

        //Generate 18 recipes with random tags, sequential names, and random ingredients.
        for (int i = 0; i < 18; i++)
        {
            Recipe newTestRecipe = new Recipe(("test" + i),i+2);
            Random randomInt = new Random();
            //int random = rand.nextInt(6);
            Tag testTag = new Tag("testable" + randomInt.nextInt(6));
            Tag testTag2 = new Tag("testable" + randomInt.nextInt(6));
            Tag testTag3 = new Tag("testable" + randomInt.nextInt(6));

            newTestRecipe.addTag(testTag);
            newTestRecipe.addTag(testTag2);
            newTestRecipe.addTag(testTag3);

            newTestRecipe.setDifficultyLevel(randomInt.nextInt(11));
            newTestRecipe.setTimeRequired(randomInt.nextInt(330)+30);



            newTestRecipe.addIngredient((ingredientArray[randomInt.nextInt(7)]),(randomInt.nextInt(7) + " cups"));
            newTestRecipe.addIngredient((ingredientArray[randomInt.nextInt(7)]),(randomInt.nextInt(7) + " cups"));
            newTestRecipe.addIngredient((ingredientArray[randomInt.nextInt(7)]),(randomInt.nextInt(7) + " cups"));
            allRecipes.add(newTestRecipe);
        }

        allRecipes.add(sugarCookies);
        allRecipes.add(mashedPotatoes);


        return allRecipes;
    }

    /*
    PURPOSE:         To create a string of the attributes for a given recipe.
    PRE-CONDITIONS:  givenRecipe -> A Recipe with attributes already filled in.
    POST-CONDITIONS: None.
    RETURN:          A printable string containing the relevant information for the given recipe.
    */
    public String printRecipe(Recipe givenRecipe)
    {
        // Iterator to walk along each ingredient, appending the name to a string.
        Iterator<IngredientPair> ingredientIterator = givenRecipe.getIngredients().iterator();
        StringBuilder ingredientString = new StringBuilder(ingredientIterator.next().ingredient.toString());
        while (ingredientIterator.hasNext())
        {
            ingredientString.append(", ").append(ingredientIterator.next().ingredient.toString());
        }
        ingredientString.append(".");

        return "Recipe name: " + givenRecipe.getName() + "\t\t\t Recipe ID: "
                + givenRecipe.getRecipeID() + "\n\t\t\t Tags: "
                + givenRecipe.getTags().toString() + "\n\t\t\t Ingredients: "
                + ingredientString;
    }



    public static void main(String[] args)
    {

    }
}