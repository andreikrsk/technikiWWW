package by.epam.task.fundamentals.optionalTask3;

public class Month {
    public static String callMonth(int monthNumber) throws IllegalArgumentException{
        switch (monthNumber){
            case 1:
                return MonthEnum.JANUARY.toString();
            case 2:
                return MonthEnum.FEBRUARY.toString();
            case 3:
                return MonthEnum.MARCH.toString();
            case 4:
                return MonthEnum.APRIL.toString();
            case 5:
                return MonthEnum.MAY.toString();
            case 6:
                return MonthEnum.JUNE.toString();
            case 7:
                return MonthEnum.JULE.toString();
            case 8:
                return MonthEnum.AUGUST.toString();
            case 9:
                return MonthEnum.SEPTEMBER.toString();
            case 10:
                return MonthEnum.OCTOBER.toString();
            case 11:
                return MonthEnum.NOVEMBER.toString();
            case 12:
                return MonthEnum.DECEMBER.toString();
            default:
                throw new IllegalArgumentException( "Введен номер не существующего месяца, повторите запрос от 1 до 12." );

        }
    }
}
