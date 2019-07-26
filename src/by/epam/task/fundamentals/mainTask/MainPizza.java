package by.epam.task.fundamentals.mainTask;

public class MainPizza {
    public static void main(String[] args) {
        try {
            Order order = new Order( Order.getPizza( new Pizza( 10001, 7717, true, 2, "Margarita" ) {
                {
                    this.addIngredient( Ingredients.BACON );
                    this.addIngredient( Ingredients.CHEESE );
                    this.addIngredient( Ingredients.GARLIC );
                    this.addIngredient( Ingredients.PEPERONI );
                    this.setPizzaAttributes();
                }
            }, new Pizza( 10001, 7717, false, 3 , "Mar") {
                {
                    this.addIngredient( Ingredients.BACON );
                    this.addIngredient( Ingredients.BACON );
                    this.addIngredient( Ingredients.GARLIC );
                    this.addIngredient( Ingredients.PEPERONI );
                    this.setPizzaAttributes();
                }
            } ) );
            System.out.println( order.toString() );
        }catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
    }
}
