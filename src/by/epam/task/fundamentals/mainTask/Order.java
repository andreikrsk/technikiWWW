package by.epam.task.fundamentals.mainTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Order {
    private final int CLIENT_ID=0;
    private final int ORDER_ID=1;
    private final int NAME_PIZZA=2;
    private final int NUMBER_OF_PIZZA=3;
    private final int THE_AMOUNT_OF_PIZZA=4;
    private List<Pizza> pizza;

    public Order(List<Pizza> pizza) {
        this.pizza = pizza;
    }

    public static List<Pizza> getPizza(Pizza... anotherPizzaInOrder) throws IllegalArgumentException {
        List<Pizza> pizzaList = new ArrayList<>();
        for (Pizza pizza : anotherPizzaInOrder) {
            pizzaList.add( pizza );
        }
        return pizzaList;
    }

    @Override
    public String toString() throws IllegalArgumentException {
        float totalAmount=0.0f;
        StringBuilder outputString = new StringBuilder( "********************************\n" );
        outputString.append( "Заказ: " + pizza.get(0).getPizzaAttributes().get( CLIENT_ID ) + "\n" ).append( "Клиент: " + pizza.get(0).getPizzaAttributes().get( ORDER_ID ) + "\n" );
        for (int i = 0; i < pizza.size(); i++) {
            totalAmount +=(float) pizza.get( i ).getPizzaAttributes().get( THE_AMOUNT_OF_PIZZA ) ;
            outputString.append( "Название: " + ((pizza.get( i ).getPizzaAttributes().get( NAME_PIZZA ).toString().length()>=4 &&
                    pizza.get( i ).getPizzaAttributes().get( NAME_PIZZA ).toString().length()<=20) ? pizza.get( i ).getPizzaAttributes().get( NAME_PIZZA ):
                    pizza.get(0).getPizzaAttributes().get( CLIENT_ID )+"_"+ (i+1)) + "\n" );
            outputString.append( "--------------------------------" + "\n" );
            for (Map.Entry<String, Float> ingregient : pizza.get( i ).getIngredientSet().entrySet()) {
                outputString.append(  ingregient.getKey() +
                        ((ingregient.getKey().length()>8) ? "\t\t\t\t":(ingregient.getKey().length()>7)?"\t\t\t\t\t":"\t\t\t\t\t\t")  + ingregient.getValue() + " €\n" );
                }
                outputString.append( "--------------------------------" + "\n" );
                outputString.append("Всего: \t\t\t\t\t\t" + pizza.get(i).getPizzaAttributes().get( THE_AMOUNT_OF_PIZZA )+" €\n");
                outputString.append("Кол-во: \t\t\t\t\t\t" + pizza.get(i).getPizzaAttributes().get( NUMBER_OF_PIZZA )  + "\n");
                outputString.append( "--------------------------------" + "\n" );
            }
            outputString.append( "Общая сумма: " + "\t\t\t\t" +  totalAmount +" €\n" );
            outputString.append( "********************************" );
        return outputString.toString();
        }
}
