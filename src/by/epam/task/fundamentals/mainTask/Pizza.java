package by.epam.task.fundamentals.mainTask;

import java.util.*;

public class Pizza implements PizzaIngredients{
    private final int MAX_NUMBER_OF_PIZZA_INGREDIENTS=4;
    private int clientId;
    private int orderId;
    private String namePizza;
    private int amountPizza;
    private Map<String, Float> ingredientSet = new HashMap<>();
    private List<Object> pizzaAttributes=new ArrayList<>(  );
    private boolean isCalzone;

    Pizza(int clientId,int orderId, boolean isCalzone, int amountPizza, String namePizza) {
        this.namePizza=namePizza;
        this.clientId=clientId;
        this.orderId=orderId;
        this.isCalzone = isCalzone;
        this.amountPizza=amountPizza;
    }


    private void setTypePizza() {
        if(isCalzone){
            setTypeInIngridients( Ingredients.CALSONE );
        }else {
            setTypeInIngridients( Ingredients.BASE );
        }
    }

    private void setTypeInIngridients(Ingredients typePizza) throws IllegalArgumentException {
        switch (typePizza){
            case BASE:
                ingredientSet.put( Ingredients.BASE.name(),Ingredients.BASE.getIngredientPrice() );
                break;
            case CALSONE:
                ingredientSet.put( Ingredients.CALSONE.name(),Ingredients.CALSONE.getIngredientPrice() );
                break;
            default:
                throw new IllegalArgumentException( "Выберете основание Base или Calzone" );
        }
    }

    public List<Object> getPizzaAttributes() throws IllegalArgumentException{
        if(pizzaAttributes==null){
            throw new IllegalArgumentException( "Не заданы атрибуты пиццы.");
        }
        return pizzaAttributes;
    }

    public void setPizzaAttributes() {
        setTypePizza();
        pizzaAttributes.add( orderId );
        pizzaAttributes.add( clientId );
        pizzaAttributes.add( namePizza);
        pizzaAttributes.add( amountPizza );
        pizzaAttributes.add(calculateTheAmountOfTheOrder());
    }

    @Override
    public void addIngredient(Ingredients ingredients) throws IllegalArgumentException {
        if(ingredients==Ingredients.BASE || ingredients==Ingredients.CALSONE){
            throw new IllegalArgumentException( "Вы выбрали уже оснавание, выберете ингридиенты пиццы." );
        }
        if(!ingredientSet.containsKey(ingredients)&& ingredientSet.size()<MAX_NUMBER_OF_PIZZA_INGREDIENTS){
            ingredientSet.put(ingredients.name(), ingredients.getIngredientPrice());
        }else{
            throw new IllegalArgumentException( "Пицца заполнена, либо вы выбрали уже имеющийся ингридиент" );
        }
    }

    public Map<String, Float> getIngredientSet() {
        return ingredientSet;
    }
    private float calculateTheAmountOfTheOrder(){
        float theAmountOfPizza=0.0f;
        for (Map.Entry<String,Float> ingredient: ingredientSet.entrySet()) {
            theAmountOfPizza +=ingredient.getValue();
        }
        return theAmountOfPizza*amountPizza;
    }
}