import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exeption1 {
        Scanner sc = new Scanner(System.in);
        int c;
        int rCheck = 0;
        System.out.println("Введите выражение формата a + b римскими или арабскими числами в диапазоне от 1 до 10 включительно.\nДоступные операции + - * /");
        String str = sc.nextLine();
        sc.close();
        String[] str1 = str.trim().toUpperCase().split(" ");
        String romeEx = "IVIIIX";
        if (str1.length < 3)
            throw new Exeption1("введённое не является математической операцией, либо неверный формат записи");
        if (str1.length > 3)
            throw new Exeption1("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        for (int i = 0; i < 3; i = i + 2) {
            if (romeEx.contains(str1[i]))
                rCheck++;
        }
        if (rCheck == 1)
            throw new Exeption1("оба числа должны быть из одной системы счисления и удовлетворять диапазону (1 - 10)");
        if (rCheck == 2){
            c = calc(String.valueOf(RomeNum.valueOf(str1[0]).getArabic()), String.valueOf(RomeNum.valueOf(str1[2]).getArabic()), str1[1]);
            if (c < 1)
                throw new Exeption1("в римской системе нет отрицательных значений и 0");
            String result = "";
            if((c/10) > 0){
                for(RomeNum num: RomeNum.values()){
                    if ((c - c % 10) == num.getArabic()) {
                        result += num;
                        break;
                    }
                }
            }
            for(RomeNum num: RomeNum.values()){
                if (c % 10 == num.getArabic()) {
                    result += num;
                    break;
                }
            }
            System.out.println(result);
        }else {
            c = calc(str1[0], str1[2], str1[1]);
            System.out.println(c);
        }
    }

    static int calc(String s0, String s2, String s) throws Exeption1 {
        int a, b, c;
        try{
            a = Integer.parseInt(s0);
            b = Integer.parseInt(s2);
        }catch (NumberFormatException e){
            throw new Exeption1("введены некорректные символы, либо введенные римские числа больше X");
        }

        if ((a < 1 || a > 10) || (b < 1 || b > 10))
            throw new Exeption1("число не из нужного диапазона (1 - 10)");
        switch (s) {
            case "+": {
                c = a + b;
                break;
            }
            case "-": {
                c = a - b;
                break;
            }
            case "*": {
                c = a * b;
                break;
            }
            case "/": {
                c = a / b;
                break;
            }
            default: {
                throw new Exeption1("некоректный оператор (+, -, /, *)");
            }
        } return c;
    }
}