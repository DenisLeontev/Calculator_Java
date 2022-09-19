package Kata_Calculator_Test;

import java.util.Scanner;

import static Kata_Calculator_Test.Main.calc;


public class Calculator {
    static Converter converter = new Converter();
    static String[] actions = {"+", "-", "/", "*"}; // возможные операции вычисления
    static String[] regexActions = {"\\+", "-", "/", "\\*"}; // возможные делители для преобразования введенного текста в аргументы выражения


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // создаем сканер для ввода выражения для вычисления
        System.out.println("Калькулятор");
        System.out.println("Введите выражение в формате 2+3 или V-I!");
        System.out.println("Используйте числа только от 1 до 10: ");
        String input = scanner.nextLine(); // строка для ввода выражения для вычисления
        String result = calc(input);
        System.out.println(result);
    }
}
