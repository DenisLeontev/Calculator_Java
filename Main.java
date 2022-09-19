package Kata_Calculator_Test;

import static Kata_Calculator_Test.Calculator.*;

public class Main {

    public static String calc(String input) {


        // Проверяем количество арифметических действий(АД) в выражении, дб 1
        int sumActions = 0;
        for (int y = 0; y < input.length(); y++) {
            for (String action : actions) {
                if (input.charAt(y) == action.charAt(0)) {
                    sumActions++;
                }
            }
        }
        if (sumActions > 1) {
            throw new RuntimeException("Не верный формат выражения! Введите выражение в формате 2+3 или V-I");
        }

        // Определяем какое (АД) нужно совершить
        int actionIndex = -1;
        for (int i = 0; i < actions.length; i++) {
            if (input.contains(actions[i])) {
                actionIndex = i;
                break;
            }
        }
        // Проверяем есть ли АД в массиве возможных операций вычисления
        if (actionIndex == -1) {
            System.out.println("Не допустимое арифметическое действие!");
            return null;
        }

        // Делим строку по символу разделителю, чтобы получить числа для АД


        String[] data = input.split(regexActions[actionIndex]);

        // Проверяем, что оба числа находятся в одной системе исчисления Араб или Рим
        if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
            // Объявляем переменные для проведения АД
            int a;
            int b;

            // Определяем Римские числа для конвертации в арабские и выполнения АД
            boolean isRoman = converter.isRoman(data[0]);
            if (isRoman) { // Римские конвертируем в Арабские числа формат Integer
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);
            } else { // Арабские приводим к формату Integer
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }

            // Вводим переменную для выведения результата АД
            int result;
            // Проверяем, что значения переменный не выходят за допустимых значений
            try {
                if ((a < 1 || a > 10) | (b < 1 || b > 10)) {
                    throw new RuntimeException("Значения выходят за рамки допустимого интервала от 1 до 10!");
                }
                // Выполняем с приведенными к арабским числам АД
                result = switch (actions[actionIndex]) {
                    case "+" -> a + b;
                    case "-" -> a - b;
                    case "*" -> a * b;
                    default -> a / b;
                };
                //Выводим результат на консоль
                if (isRoman) { // Если введено выражение Римскими числами
                    try {
                        if (result <= 0) {
                            throw new RuntimeException("Недопустимое значение.В Римской системе исчисление нет 0 и отрицательных значений");
                        }
                        return ("Результат вычисления: " + converter.intToRoman(result));
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    }

                } else { // Если введено выражение Арабскими числами
                    return ("Результат вычисления: " + result);
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Ошибка ввода! Числа должны быть в одном формате!");
        }
        return null;
    }
}
