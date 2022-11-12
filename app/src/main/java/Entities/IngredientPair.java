package Entities;

public class IngredientPair
{
	public Ingredient ingredient;
	public String amount;

	public IngredientPair(Ingredient i, String a)
	{
		this.ingredient = i;
		this.amount = a;
	}

	public String ingredientString()
	{
		String output = "";

		output = output + amount + " of ";
		output = output + this.ingredient.toString();
		return output;
	}
}