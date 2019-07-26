package by.epam.task.fundamentals.optionalTask4;

import  java.util.*;

public class Matrix {
    private int [][] matrix=null;
    private void setMatrix(int matrixDimension){
        matrix = new int[matrixDimension][matrixDimension];
        for (int i = 0; i <matrix.length; i++) {
            for (int j = 0; j <matrix[i].length ; j++) {
                matrix[i][j] = new Random().nextInt();
            }
        }
    }

    private int[] sotrStrings(int [] unsortedString){
        for (int i = unsortedString.length-1; i >0 ; i--) {
            for (int j = 0; j < i; j++) {
                if (unsortedString[j] > unsortedString[j + 1] ) {
                    int tmp = unsortedString[j];
                    unsortedString[j] = unsortedString[j + 1];
                    unsortedString[j + 1] = tmp;
                }
            }
        }
        return unsortedString;
    }

    private void outputAnArrayOfRows(){
        for (int i = 0; i <matrix.length ; i++) {
            sotrStrings( matrix[i] );
        }
    }

    private void outputAnArrayOfColumn(){
        transposeMatrix();
        outputAnArrayOfRows();
        transposeMatrix();
    }

    private void transposeMatrix(){
        for (int i = 0; i <matrix.length ; i++) {
            for (int j = i+1; j <matrix.length ; j++) {
                int temporaryItem = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i]=temporaryItem;
            }

        }
    }

    private void displayArray() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print( "\t" + matrix[i][j]);
            }
            System.out.println();
        }
    }

    public void setParametrs(int matrixDimension){
        setMatrix( matrixDimension );
        System.out.println("Начальная матрица: ");
        displayArray();
        outputAnArrayOfRows( );
        System.out.println("\nОтсортированная по строкам матрица:");
        displayArray();
        outputAnArrayOfColumn();
        System.out.println("\nОтсортированная по стролбцам матрица:");
        displayArray();
    }

    private int sumElementMatrix(){
        int sum=0;
        for (int i = 0; i <matrix.length ; i++) {
            sum+=sumOfStringValues(matrix[i]);
        }
        return sum;
    }

    private int sumOfStringValues(int[] matrix) {
        int sumRows=0;
        for (int i = 0;i<matrix.length;i++) {
            if(matrix[i]>0 && i<matrix.length-1){
                i++;
                do{
                    if(i<matrix.length-1) {
                        sumRows += matrix[i++];
                    }
                    if(matrix[i]<0 && matrix.length-1==i){
                        sumRows=0;
                        break;
                    }
                    if(matrix[i]>0){
                        break;
                    }
                }while(true);
            }
        }
        return sumRows;
    }

    public void exemple(int matrixDimension){
        setMatrix( matrixDimension );
        System.out.println("Тест");
        displayArray();
        System.out.println("Сумма элементов: "+ sumElementMatrix());
    }
}