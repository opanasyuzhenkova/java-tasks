package ru.mail.polis.homework.simple;

import org.apache.commons.math3.analysis.solvers.LaguerreSolver;
import org.apache.commons.math3.complex.Complex;

/**
 * Возможно вам понадобится класс Math с его методами. Например, чтобы вычислить квадратный корень, достаточно написать
 * Math.sqrt(1.44)
 * Чтобы увидеть все методы класса Math, достаточно написать Math. и среда вам сама покажет возможные методы.
 * Для просмотра подробной документации по выбранному методу нажмите Ctrl + q
 */
public class DoubleAdvancedTask {

    /**
     * Вывести три корня кубического уравнения через запятую: a * x ^ 3 + b * x ^ 2 + c * x + d = 0;
     * Вывод менять не нужно, надо только посчитать x1, x2 и x3, где x1 >= x2 >= x3
     * Считаем, что все три корня вещественные.
     * <p>
     * Если используете какой-то конкретный способ, напишите какой.
     * Пример: (1, -4, -7, 10) -> "-2.0, 1.0, 5.0"
     */
    public static String equation(int a, int b, int c, int d) {
        double[] coefficients = {d, c, b, a};

        LaguerreSolver solver = new LaguerreSolver();
        Complex[] complexRoots = solver.solveAllComplex(coefficients, 0);

        double x1 = complexRoots[0].getReal();
        double x2 = complexRoots[1].getReal();
        double x3 = complexRoots[2].getReal();

        // Sort the roots in descending order
        if (x1 < x2) {
            double temp = x1;
            x1 = x2;
            x2 = temp;
        }
        if (x2 < x3) {
            double temp = x2;
            x2 = x3;
            x3 = temp;
        }
        if (x1 < x2) {
            double temp = x1;
            x1 = x2;
            x2 = temp;
        }

        return x1 + ", " + x2 + ", " + x3;
    }

    /**
     * Нужно посчитать расстояние, между двумя прямыми
     * Примеры: (1, 1, 2, -1) -> 0
     * (0, 1, 0, 5) -> 4
     * y = ax+ b => x = (y - b)/a
     */
    public static float length(double a1, double b1, double a2, double b2) {
        double result;

        if (a1 == a2) {
            result = Math.abs(b2 - b1) / Math.sqrt(a1 * a1 + 1);
            return (float) result;
        }

        if (a1 * b2 - a2 * b1 != 0) {
            return 0;
        }

//        if (a1 == a2 && a1 == 0) {
//            return (float) (b2 - b1);
//        }
        result = Math.abs(b2 - b1) / Math.sqrt(a1 * a1 + a2 * a2);
        System.out.println((float) b2 - b1);
        return (float) result;

    }

    /**
     * Даны три точки в пространстве (x1, y1, z1) , (x2, y2, z2) и (x3, y3, z3). Вам нужно найти Z координату
     * четвертой точки (x4, y4, z4), которая находится на той же плоскости что и первые три.
     * (0, 0, 1,
     * 1, 1, 1,
     * 10, 100, 1,
     * 235, -5) -> 1
     */
    public static double surfaceFunction(int x1, int y1, int z1,
                                         int x2, int y2, int z2,
                                         int x3, int y3, int z3,
                                         int x4, int y4) {
        double ABx = x2 - x1;
        double ABy = y2 - y1;
        double ABz = z2 - z1;

        double ACx = x3 - x1;
        double ACy = y3 - y1;
        double ACz = z3 - z1;

        // Находим нормаль к плоскости (N)
        double Nx = ABy * ACz - ABz * ACy;
        double Ny = ABz * ACx - ABx * ACz;
        double Nz = ABx * ACy - ABy * ACx;

        // Находим коэффициент D
        double D = -(Nx * x1 + Ny * y1 + Nz * z1);

        // Рассчитываем Z4
        return (-D - Nx * x4 - Ny * y4) / Nz;


    }

    public static void main(String[] args) {
//        System.out.println(equation(1, -4, -7, 10));
        System.out.println(length(1, 1, 2, -1));

    }
}
