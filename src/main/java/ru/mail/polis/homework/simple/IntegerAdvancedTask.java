package ru.mail.polis.homework.simple;


import java.util.Arrays;

/**
 * Возможно вам понадобится класс Math с его методами. Например, чтобы вычислить квадратный корень, достаточно написать
 * Math.sqrt(1.44)
 * Чтобы увидеть все методы класса Math, достаточно написать Math. и среда вам сама покажет возможные методы.
 * Для просмотра подробной документации по выбранному методу нажмите Ctrl + q
 */
public class IntegerAdvancedTask {

    private static final double EPS = 1e-10;

    /**
     * Сумма первых n-членов геометрической прогрессии с первым элементом a и множителем r
     * a + aq + aq^2 + ... + aq^(n-1)
     * <p>
     * Пример: (1, 2, 3) -> 7
     */
    public static long progression(int a, double q, int n) {
        long result;
        if (q != 1) {
            result = (long) ((a * (Math.pow(q, n) - 1)) / (q - 1));
        } else {
            result = a * n;
        }
        return result;
    }

    /**
     * Гусеница ползает по столу квадратами по часовой стрелке. За день она двигается следующим образом:
     * сначала наверх на up, потом направо на right. Ночью она двигается вниз на down и налево на left.
     * Сколько суток понадобится гусенице, чтобы доползти до поля с травой?
     * Считаем, что на каждой клетке с координатами >= grassX или >= grassY находится трава
     * Если она этого никогда не сможет сделать, Верните число Integer.MAX_VALUE;
     * Пример: (10, 3, 5, 5, 20, 11) -> 2
     */
    public static int snake(int up, int right, int down, int left, int grassX, int grassY) {
        int day = 0; // x = 0 y = 0
        int snaceX = 0, snaceY = 0;

        if (grassX <= right || grassY <= up) {
            return 1;
        }

        if ((grassX < left && grassY < down) || (up == down)) {
            return Integer.MAX_VALUE;
        }


        while (snaceX < grassX && snaceY < grassY) {
            snaceY = snaceY + up;
            snaceX = snaceX + right;

            if (snaceX >= grassX || snaceY >= grassY)
                break;

            snaceY = snaceY - down;
            snaceX = snaceX - left;
            if (snaceX >= grassX || snaceY >= grassY)
                break;

            day++;
        }

        return day + 1;
    }

    /**
     * Дано число n в 10-ном формате и номер разряда order.
     * Выведите цифру стоящую на нужном разряде для числа n в 16-ом формате
     * Нельзя пользоваться String-ами
     * Пример: (454355, 2) -> D
     */
    public static char kDecimal(int n, int order) {
        char[] hexDigits = "0123456789ABCDEF".toCharArray();
        int length = 0;
        int temp = n;

        while (temp > 0) { // сколько символов потребуется для представления числа n в 16 системе
            temp = temp / 16;
            length++;
        }

        char[] hexDecimal = new char[length];
        int ind = length - 1; // обратный порядок

        while (n > 0) {
            int remainder = n % 16;
            hexDecimal[ind] = hexDigits[remainder];
            n = n / 16;
            ind--;
        }
        System.out.println(hexDecimal);
        return hexDecimal[length - order];
    }

    /**
     * Дано число в 10-ном формате.
     * Нужно вывести номер минимальной цифры для числа в 16-ном формате. Счет начинается справа налево,
     * выводим номер первой минимальной цифры (если их несколько)
     * Нельзя пользоваться String-ами
     * (6726455) -> 2
     */
    public static byte minNumber(long a) {
        char[] hexDigits = "0123456789ABCDEF".toCharArray();
        int minDig = Integer.MAX_VALUE;
        int position = 0;
        int index = 0;

        while (a > 0) {
            long remainder = a % 16;
            if (remainder < minDig) {
                minDig = (int) remainder;
                position = index;
            }
            a = a / 16;
            index++;

        }
        return (byte) (position + 1);
    }

    public static void main(String[] args) {
        System.out.println(kDecimal(454355, 2));
    }
}
