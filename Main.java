import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String example = sc.nextLine();
        System.out.println(calc(example));

    }


    public static String calc(String input) {
        String[] massivInput = input.split(" ");
        if (massivInput.length < 2) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("throws Exception //т.к. строка не является математической операцией");
                System.exit(0);
            }
        }
        if (massivInput.length > 3) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                return "";
            }
        }
        int countNumeric = 0;                                   //находим количесво арабских цифр
        for (int i = 0; i < massivInput.length; i++) {
            if ((massivInput[i].matches("[-+]?\\d+"))) {
                countNumeric++;
            }
        }
        if (countNumeric == 1) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("throws Exception //т.к. используются одновременно разные системы счисления");
                System.exit(0);
            }
        }
        int number1 = 0;
        int number2 = 0;
        if (countNumeric == 0) {
            number1 = convertToArabic(massivInput[0]);
            number2 = convertToArabic(massivInput[2]);
        } else {
            number1 = Integer.parseInt(massivInput[0]);
            number2 = Integer.parseInt(massivInput[2]);
        }


        int result = 0;
        switch (massivInput[1]) {
            case ("+"):
                result = number1 + number2;
                break;
            case ("-"):
                result = number1 - number2;
                break;
            case ("/"):
                result = number1 / number2;
                break;
            case ("*"):
                result = number1 * number2;
                break;
            default:
                break;
        }
        //return "gg";

        if (countNumeric == 0)
            return convertToRoman(result);
        return Integer.toString(result);



    }


    static int convertToArabic(String roman) {
        int intValue = 0;
        for (int i = 0; i < roman.length(); i++) {
            switch (roman.charAt(i)) {
                case ('I'):
                    intValue += 1;
                    break;
                case ('V'):
                    intValue += 5;
                    break;
                case ('X'):
                    intValue += 10;
                    break;
                default:
                    break;
            }


        }
        if (roman.contains("IV"))
            intValue -= 2;
        if (roman.contains("IX"))
            intValue -= 2;

        return intValue;
    }

    static String convertToRoman(int num) {
        if (num < 0) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("throws Exception //т.к. в римской системе нет отрицательных чисел");
                System.exit(0);

            }
        }
        String[] keys =  new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] vals = new int[]{ 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        StringBuilder ret = new StringBuilder();
        int ind = 0;
        while (ind < keys.length){
            while (num >= vals[ind]){
                int d = num / vals[ind];
                num = num % vals[ind];
                for (int i = 0; i < d; i++) {
                    ret.append(keys[ind]);
                }
            }
            ind++;
        }

        return ret.toString();
    }
}


















