import json

class Recipe:
	id = 0
	name = ""
	ingredients = []
	tags = []
	prep = 0
	cook = 0
	servings = 0
	kcal = 0
	instructions = ""
	source = ""

class Ingredient:
	id = 0
	name = ""
	tags = []

	def __init__(self, id):
		self.id = id

def importIngredients(fileName, id=0):
	resource = [line.strip() for line in open(fileName, "r")]
	ingredients = []
	
	index = 0

	while resource[index] != "end":
		if resource[index][0:5] == "name":
			id += 1
			ingredients.append(Ingredient(id))
			ingredients[id-1].


	return ingredients

def importRecipes(fileName):
	resource = open(fileName, "r")
	resource = [x.splitlines() for x in resource]
	recipes = []
	id = 0
	for line in resource:
		if line[0] != "\t":	# New ingredient
			recipes.append(Recipe(id))
			id += 1
			recipes[-1].name = line.strip()
		else:	# Tags
			recipes[-1].tags.append(line.strip())
			

	return recipes