package by.epam.task.fundamentals.optionalTask1;

import java.util.Arrays;

public class HelloUser {
    public static void welcomes(String[] args) {
        System.out.println("Hello " + Arrays.toString( args ).replaceAll( "]|\\[" ,""));
    }

    public static void showReverseOrder(String[] args) {
        StringBuilder stringBuilder = new StringBuilder(  );
        for (int j = 0; j < args.length; j++) {
            for (int i = args[j].toCharArray().length-1; i >=0 ; i--) {
                stringBuilder.append( args[j].charAt( i ) );
            }
            stringBuilder.append( " " );
        }
        System.out.println(stringBuilder.toString().trim());
    }

    public static void outputRandomNumbers(String [] numberOfRandomNumbers) throws IllegalArgumentException{
        if(numberOfRandomNumbers.length>1){
            throw new IllegalArgumentException( "Переданно более двух параметров" );
        }
        try {
        for (int i = 0; i < Integer.parseInt( numberOfRandomNumbers[0] ); i++) {
            System.out.println( (int) (Math.random() * 100) );
            }
        }catch (IllegalArgumentException ex){
            System.out.println("Передан не правильный параметр");
        }
    }

    public static void calculateTheAmountOfArguments(String [] args){
        try {
            if(args.length>1){
                int sum=0;
                for (int i = 0; i <args.length; i++) {
                    sum+=Integer.parseInt( args[i]);
                }
                System.out.println(sum);
            }
        }catch (IllegalArgumentException ex){
            System.out.println("Передан не правильный параметр");
        }
    }
}
