package Kata_Calculator_Test;

import java.util.Scanner;


public class Calculator {
    public static void main(String[] args) {

        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"}; // возможные операции вычесления
        String[] regexActions = {"\\+", "-", "/", "\\*"}; // возможные делители для преобразования введеного текста в аргументы выражения

        Scanner scanner = new Scanner(System.in); // создаем сканер для ввода выражения для вычисления
        System.out.println("Калькулятор");
        System.out.println("Введите выражение в формате 2+3 или V-I");
        System.out.println("Используйте числа только от 1 до 10: ");
        String expression = scanner.nextLine(); // строка для ввода выражения для вычисления

        // Определяем арифмитическое действие (АД)
        int actionIndex = -1;
        for (int i = 0; i < actions.length; i++) {
            if (expression.contains(actions[i])) {
                actionIndex = i;
                break;
            }
        }
        // Проверяем есть АД в массиве возможных операций вычисления
        if (actionIndex == -1) {
            System.out.println("Не допустимое арифметичекое действие!");
            return;
        }

        // Делим строку по символу разделителю, чтобы получить числа для АД
        String[] data = expression.split(regexActions[actionIndex]);

        // Проверяем, что оба числа находятся в одной системе исчесления Араб или Рим
        if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
            // Объявляем переменные для проведения АД
            int a;
            int b;

            // Определяем Римские числа для конвертации в арабскии и выполнения АД
            boolean isRoman = converter.isRoman(data[0]);
            if (isRoman) { // Римские конветрируем в Арабские числа формат Integer
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);
            } else { // Арабские приводим к формату Integer
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }

            // Вводим переменную для выведения результата АД
            int result;
            // Проверяем, что значения переменный не выходят за допустисых значений
            try {
                if ((a < 1 || a > 10) | (b < 1 || b > 10)) {
                    throw new RuntimeException("Значения выходят за рамки допустимого интервала от 1 до 10!");
                }
                // Выполняем с приведенными к арабским числам АД
                switch (actions[actionIndex]) {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = a - b;
                        break;
                    case "*":
                        result = a * b;
                        break;
                    default:
                        result = a / b;
                        break;
                }
               //Выводим результат на консоль
               if(isRoman) { // Если введено выражение Римскими числами
                   try {
                       if (result<=0){
                           throw new RuntimeException("Недопустимое значение.В Римской системе исчисление нет 0 и отрицательных значений");
                       }
                       System.out.println("Результат вычисления: " + converter.intToRoman(result));
                   }catch (RuntimeException e) {
                       e.printStackTrace();
                   }

               }else { // Если введено выражение Арабскими числами
                   System.out.println(result);
               }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Ошибка ввода! Числа должны быть в одном формате!");
        }
    }
}