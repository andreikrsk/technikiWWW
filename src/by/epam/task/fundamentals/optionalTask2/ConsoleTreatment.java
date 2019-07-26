package by.epam.task.fundamentals.optionalTask2;

public class ConsoleTreatment {
    private String [] sotrStrings(String [] args){
        String[] sortedStringValue = args;
        for (int i = sortedStringValue.length-1; i >0 ; i--) {
            for (int j = 0; j < i; j++) {
                if (Integer.parseInt( sortedStringValue[j] ) > Integer.parseInt( sortedStringValue[j + 1] )) {
                    String tmp = sortedStringValue[j];
                    sortedStringValue[j] = sortedStringValue[j + 1];
                    sortedStringValue[j + 1] = tmp;
                }
            }
        }
        return sortedStringValue;
    }

    public void searchMinAndMaxNumber(String[] args) {
        String [] sotedStringValue = sotrStrings(args);
        System.out.println("Short number  " + sotedStringValue[0] + " consist of  " + sotedStringValue[0].length() + " numbers "
         + "\nLong number " + sotedStringValue[sotedStringValue.length-1] + " consist of  " + sotedStringValue[sotedStringValue.length-1].length() + " numbers");
    }

    public void outputNumberInAscendingAndDescendingOrder(String [] args){
        String [] sortedStringValue = sotrStrings(args);
        System.out.print("List in ascending order:");
        for (int i = 0; i < sortedStringValue.length ; i++) {
            System.out.print(" " + sortedStringValue[i]);
        }
        System.out.print("\nList in descending order:");
        for (int i = sortedStringValue.length-1; i >=0 ; i--) {
            System.out.print(" " + sortedStringValue[i]);
        }
    }

    public void outputNumberLessThanTheAverageLength(String[] args){
        int averageLengthList = 0;
        for (String value: args ) {
            averageLengthList+=value.length();
        }
        averageLengthList = Math.round((float)averageLengthList/args.length);
        System.out.print("\nOutput numbers larger than average length:");
        for (String value: args) {
            if(value.length()<averageLengthList){
                continue;
            }else{
                System.out.print(" " +value);
            }
        }
        System.out.print("\nOutput numbers less than average length:");
        for (String value: args) {
            if(value.length()>=averageLengthList){
                continue;
            }else{
                System.out.print(" "+value);
            }
        }
    }
}
