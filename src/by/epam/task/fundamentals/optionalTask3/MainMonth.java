package by.epam.task.fundamentals.optionalTask3;

import static org.junit.Assert.*;
import org.junit.Test;

public class MainMonth {
    @Test
    public void getMonthTestFirst(){
        assertEquals( "JANUARY" ,Month.callMonth( 1 ));
    }

    @Test
    public void getMonthTestSecond(){
        assertEquals( "SEPTEMBER" ,Month.callMonth( 9 ));
    }

    @Test
    public void nonexistentMonth() {
        try {
            Month.callMonth( 1323 );
        } catch (IllegalArgumentException ex) {
            assertEquals( "Введен номер не существующего месяца, повторите запрос от 1 до 12.", ex.getMessage() );
        }
    }
}
