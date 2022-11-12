package Search;

import Entities.Recipe;
import Entities.RecipeRepository;


//The purpose of this class is to search through recipe data that would be stored in the form of a linked list
public class SearchForRecipe
{
    RecipeRepository recipeRepo = new RecipeRepository();


//    private LinkedList<Recipe> recipeList;
//
//    /**
//     * This function allows user to search by typing in names/keywords, this function will look for those keywords in names of the recipes
//     * @param desiredRecipeName The desired name the user put in the search box
//     */
//    Recipe[] searchByRecipeName(String desiredRecipeName)
//    {
//        //to access the recipe data this class would ask some other class eg. getRecipefromStorage, to get the data and the go through it
//    }
//
//    /**
//     * This function allows the user to search by a tag
//     * @param givenTag the tag user typed in
//     */
//    Recipe[] searchByTag(Tag givenTag)
//    {
//        Recipe[] list = new Recipe[20];
//    }
//
//    /**
//     * This function allows the user to search by multiple tags
//     * @param givenTags the tags user typed in
//     */
//    void searchByTags(Tag[] givenTags)
//    {
//
//    }
//    /**
//     * This function allows the user to search by a ingredients
//     * @param desiredIngredients list of ingredients
//     */
//    void searchByIngredient(LinkedList<Ingredient> desiredIngredients)
//    {
//
//    }
//
//    /**
//     * This function allows the developers to search by ID for internal purposes
//     * @param givenID ID given to each recipes
//     */
//    void searchByID(int givenID)
//    {
//
//    }
//
//    void searchType()
//    {
//        //Get user input
//        //Decide: Search by recipe name, search by tag, search by ID, search by ingredient.
//
////        return userInput;
//
//    }
//
//
//    /*
//    PURPOSE:         To create and return an array of randomly generated recipes.
//    PRE-CONDITIONS:  None.
//    POST-CONDITIONS: Calls random();
//    RETURN:          An array of randomly generated recipes.
//     */
//    public static Recipe[] generateTestRecipes()
//    {
//        //Ingredient Creation
//        Ingredient[] ingredientArray = new Ingredient[7];
//        ingredientArray[0] = new Ingredient("Flour");
//        ingredientArray[1] = new Ingredient("Baking Soda");
//        ingredientArray[2] = new Ingredient("Baking Powder");
//        ingredientArray[3] = new Ingredient("Butter");
//        ingredientArray[4] = new Ingredient("White Sugar");
//        ingredientArray[5] = new Ingredient("Egg");
//        ingredientArray[6] = new Ingredient("Vanilla Extract");
//
//        //Making one full proper recipe.
//        Recipe sugarCookies = new Recipe("Sugar Cookies",1);
//        sugarCookies.addIngredient(ingredientArray[0],"2 + 3/4 cups");
//        sugarCookies.addIngredient(ingredientArray[1],"1 teaspoon");
//        sugarCookies.addIngredient(ingredientArray[2],"1/2 teaspoon");
//        sugarCookies.addIngredient(ingredientArray[3],"1 cup, softened");
//        sugarCookies.addIngredient(ingredientArray[4],"1 + 1/2 cups");
//        sugarCookies.addIngredient(ingredientArray[5],"1");
//        sugarCookies.addIngredient(ingredientArray[6],"1 teaspoon");
//
//        sugarCookies.addTag(new Tag("Deserts"));
//        sugarCookies.setInstructions("""
//                Step 1
//                Preheat oven to 375 degrees F (190 degrees C). In a small bowl, stir together flour, baking soda, and baking powder. Set aside.
//
//                Step 2
//                In a large bowl, cream together the butter and sugar until smooth. Beat in egg and vanilla. Gradually blend in the dry ingredients. Roll rounded teaspoonfuls of dough into balls, and place onto ungreased cookie sheets.
//
//                Step 3
//                Bake 8 to 10 minutes in the preheated oven, or until golden. Let stand on cookie sheet two minutes before removing to cool on wire racks.""");
//
//        //Declare a new recipe.
//        Recipe mashedPotatoes = new Recipe("Mashed Potatoes",0);
//        Ingredient potato = new Ingredient("Potatoes");
//        Tag delicious = new Tag("Delicious");
//        Tag carbDense = new Tag("carbDense");
//        potato.addTag(delicious);
//        potato.addTag(carbDense);
//        //Fill in its attributes.
//        mashedPotatoes.addIngredient(potato,"3");
//
//        Recipe[] recipes = new Recipe[20];
//
//
//        for (int i = 0; i < 18; i++)
//        {
//            Random rand = new Random();
//            //int random = rand.nextInt(6);
//            Tag testTag = new Tag("testable" + rand.nextInt(6));
//            Tag testTag2 = new Tag("testable" + rand.nextInt(6));
//            Tag testTag3 = new Tag("testable" + rand.nextInt(6));
//
//            recipes[i] = new Recipe(("test" + i),i+2);
//            recipes[i].addTag(testTag);
//            recipes[i].addTag(testTag2);
//            recipes[i].addTag(testTag3);
//
//            recipes[i].addIngredient((ingredientArray[rand.nextInt(7)]),(rand.nextInt(7) + "cups"));
//            recipes[i].addIngredient((ingredientArray[rand.nextInt(7)]),(rand.nextInt(7) + "cups"));
//            recipes[i].addIngredient((ingredientArray[rand.nextInt(7)]),(rand.nextInt(7) + "cups"));
//        }
//
//
//        recipes[18] = sugarCookies;
//        recipes[19] = mashedPotatoes;
//        return recipes;
//    }
//
//    /*
//    PURPOSE:         To create a string of the attributes for a given recipe.
//    PRE-CONDITIONS:  givenRecipe -> A Recipe with attributes already filled in.
//    POST-CONDITIONS: None.
//    RETURN:          A printable string containing the relevant information for the given recipe.
//     */
//    public static String printRecipe(Recipe givenRecipe)
//    {
//        Iterator<IngredientPair> ingredientIterator = givenRecipe.getIngredients().iterator();
//        StringBuilder ingredientString = new StringBuilder(ingredientIterator.next().ingredient.toString());
//        while (ingredientIterator.hasNext())
//        {
//            ingredientString.append(", ").append(ingredientIterator.next().ingredient.toString());
//        }
//        ingredientString.append(".");
//        return "Recipe name: " + givenRecipe.getName() + "\t\t\t Recipe ID: "
//                + givenRecipe.getRecipeID() + "\n\t\t\t Tags: "
//                + givenRecipe.getTags().toString() + "\n\t\t\t Ingredients: "
//                + ingredientString;
//    }
//
//
//
//    public static void main(String[] args)
//    {
//
//        //Create random recipes.
//        Recipe[] allRecipies = generateTestRecipes();
//
//        //Print each.
//        for (Recipe recipe : allRecipies)
//        {
//            System.out.println(printRecipe(recipe) + "\n");
//        }

    void searchByID(RecipeRepository recipeList, int givenID)
    {
        for (Recipe allRecipes: recipeRepo.allRecipes)
        {
            return;
        }
    }

    void searchType()
    {
        //Get user input
        //Decide: Search by recipe name, search by tag, search by ID, search by ingredient.

        //return userInput;

    }



    public static void main(String[] args)
    {



        //Get user input

       // test();
    }
}