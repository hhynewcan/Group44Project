package Entities;

import java.util.LinkedList;
import java.util.TreeSet;

public class Recipe
{

	private final String name;
	private final int recipeID;
	private LinkedList<IngredientPair> ingredients;
	private TreeSet<Tag> tags;
	private String instructions;
	private int difficulty;
	private int minutesRequired;


	public Recipe(String name, int recipeID) {
		this.name = name;
		this.recipeID = recipeID;
		this.tags = new TreeSet<>();
		this.ingredients = new LinkedList<>();
		//this.minutesRequired = -1;
		//this.difficulty = -1;

	}

	public void addIngredient(Ingredient ingredient, String amount)
	{
		this.ingredients.add(new IngredientPair(ingredient, amount));
		for (Tag t : ingredient.getTags())
		{
			this.addTag(t);
		}
	}

	public void setTimeRequired(int minutesRequired)
	{
		this.minutesRequired = minutesRequired;
	}

	public int getTimeRequired()
	{
		return this.minutesRequired;
	}

	public void setDifficultyLevel(int difficultyLevel)
	{
		this.difficulty = difficultyLevel;
	}

	public int getDifficultyLevel()
	{
		return this.difficulty;
	}

	public void addTag(Tag t)
	{
		this.tags.add(t);
	}

	public void removeTag(Tag t) {
		this.tags.add(t);
	}

	public String getName() {
		return name;
	}

	public int getRecipeID() {
		return recipeID;
	}

	public LinkedList<IngredientPair> getIngredients()
	{
		return ingredients;
	}

	public TreeSet<Tag> getTags() {
		return tags;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getInstructions() {
		return instructions;
	}
}
