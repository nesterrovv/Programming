/**
 * Main class of this lab work. Allows to fill two-dimensional array and print
 * it to standard output thread in table representation. Values for formulas
 * are given from static methods of this class. Check readme file for more information.
 * @author Ivan Nesterov
 * @version 1.0
 * @since 14.07.2021
 */
public class Main {

    /** Constant value for saving length of first array. */
    private static final int LENGTH_OF_FIRST_ARRAY = 10;
    /** Constant value for saving length of second array. */
    private static final int LENGTH_OF_SECOND_ARRAY = 16;

    /** Method for filling two-dimensional array and printing it
     * to standard output thread in table representation.
     * @param args command line arguments. Not used here.
     * */
    public static void main(String[] args) {
        long[] firstArray = fillFirstArray(LENGTH_OF_FIRST_ARRAY);
        double[] secondArray = fillSecondArray(LENGTH_OF_SECOND_ARRAY);
        double[][] twoDimensionalArray = new double[LENGTH_OF_FIRST_ARRAY][LENGTH_OF_SECOND_ARRAY];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 16; j++) {
                switch ((int) firstArray[i]) {
                    case 20:
                        twoDimensionalArray[i][j] = realizeFirstFormula(secondArray[j]);
                        break;
                    case 6:
                    case 12:
                    case 14:
                    case 16:
                    case 18:
                        twoDimensionalArray[i][j] = realizeSecondFormula(secondArray[j]);
                        break;
                    default:
                        twoDimensionalArray[i][j] = realizeThirdFormula(secondArray[j]);
                        break;
                }
            }
        }
        printFormattedArray(twoDimensionalArray);
    }

    /**
     * Method for filling first array.
     * @param length of fillable array.
     * @return filled array with long-type values.
     */
    public static long[] fillFirstArray(int length) {
        long[] array = new long[length];
        for (int index = 0; index < length; index++) {
            array[index] = 20L - 2L * index;
        }
        return array;
    }

    /**
     * Method for filling second array.
     * @param length of fillable array.
     * @return filled array with double-type values.
     */
    public static double[] fillSecondArray(int length) {
        double[] array = new double[length];
        for(int index = 0; index < length; index++) {
             array[index] = Math.random() * (5.0 - (-11.0) + 1) - 11.0;
        }
        return array;
    }

    /**
     * Method for realizing first mathematical formula from the assignment to laboratory work.
     * @param x argument of the function being implemented.
     * @return counted value.
     */
    public static double realizeFirstFormula(double x) {
        return Math.asin(1 / (Math.exp(Math.pow((Math.sqrt(Math.abs(x))), (Math.pow(((0.25 + Math.asin((x - 3) /
                16.0))/(Math.pow(x, (x * (x - Math.PI))))), 3))))));
    }

    /**
     * Method for realizing second mathematical formula from the assignment to laboratory work.
     * @param x argument of the function being implemented.
     * @return counted value.
     */
    public static double realizeSecondFormula(double x) {
        return Math.cbrt(Math.cbrt(Math.pow((x / 12), x)));
    }

    /**
     * Method for realizing third mathematical formula from the assignment to laboratory work.
     * @param x argument of the function being implemented.
     * @return counted value.
     */
    public static double realizeThirdFormula(double x) {
        return Math.asin(Math.pow(Math.exp(Math.cbrt(-(Math.abs(x)))), 2));
    }

    /**
     * Method for printing to standard output formatted two dimensional array in table representation.
     * @param array printable array.
     */
    public static void printFormattedArray(double[][] array) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.format("%-10.2f", array[i][j]);
            }
            System.out.println(" ");
        }
    }
}
