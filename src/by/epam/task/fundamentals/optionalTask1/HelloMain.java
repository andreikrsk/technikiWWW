package by.epam.task.fundamentals.optionalTask1;


class HelloMain {
    public static void main(String[] args) {
        HelloUser.welcomes(args);
        HelloUser.showReverseOrder(args);
        try {
        HelloUser.outputRandomNumbers( args);
        }catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
        HelloUser.calculateTheAmountOfArguments( args );
    }
}
