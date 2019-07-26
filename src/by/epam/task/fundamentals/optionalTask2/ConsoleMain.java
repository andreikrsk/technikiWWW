package by.epam.task.fundamentals.optionalTask2;

public class ConsoleMain {
    public static void main(String[] args) {
        new ConsoleTreatment() {
            {
                this.searchMinAndMaxNumber( args );
                this.outputNumberInAscendingAndDescendingOrder( args );
                this.outputNumberLessThanTheAverageLength( args );
            }
        };
    }
}
