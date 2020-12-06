public class Laba1 {
    public static void main(String[] args) {
        int num = 20;
        long[] d = new long[10]; // First array
        double[] x = new double[16]; // Second array
        double[][] answer = new double[10][16]; // Third array
        for (int i = 0; i < d.length; i++) {
            d[i] = num - 2 * i; // To stack the first array
        }
        double start = -11.0;
        for (int i = 0; i < x.length; i++) {
            x[i] = start + (Math.random() * 16); // To stack the second array
        }
        for (int i = 0; i < 10; i++) { // To stack the third array
            for (int j = 0; j < 16; j++) {
                switch ((int) d[i]) {
                    case 20:
                        double uwful_u = 0.25 + Math.asin((x[j] - 3) / 16.0);
                        double uwful_d = Math.pow(x[j], (x[j] * (x[j] - Math.PI)));
                        double uwful = uwful_u / uwful_d;
                        double second_pow = Math.pow(uwful, 3);
                        double first_pow = Math.sqrt(Math.abs(x[j]));
                        double super_pow = Math.pow(first_pow, second_pow);
                        double final_pow = Math.exp(super_pow);
                        double finish = Math.asin(1 / final_pow);
                        answer[i][j] = finish;
                        break;
                    case 6:
                    case 12:
                    case 14:
                    case 16:
                    case 18:
                        double q = Math.cbrt(Math.cbrt(Math.pow((x[j] / 12), x[j])));
                        answer[i][j] = q;
                        break;
                    default:
                        double qq = Math.cbrt(-(Math.abs(x[j])));
                        double qq2 = Math.exp(qq);
                        double qq3 = Math.asin(Math.pow(qq2, 2));
                        answer[i][j] = qq3;
                        break;
                }
            }

        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.format("%-10.2f", answer[i][j]);
            }
            System.out.println(" ");
        }
    }
}

