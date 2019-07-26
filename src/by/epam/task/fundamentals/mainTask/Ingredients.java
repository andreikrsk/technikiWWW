package by.epam.task.fundamentals.mainTask;

public enum Ingredients {
    TOMATO_PASTE(1.0f), CHEESE(1.0f), SALAMI(1.5f), BACON(1.2f), GARLIC(0.3f),
    CORN(0.7f), PEPPER(0.6f), PEPERONI(1.5f), OLIVES(0.5f),
    BASE(1.0f),CALSONE(1.5f);
    private float ingredientPrice;
    Ingredients(float price){
        this.ingredientPrice=price;
    }
    public float getIngredientPrice(){
        return ingredientPrice;
    }
}