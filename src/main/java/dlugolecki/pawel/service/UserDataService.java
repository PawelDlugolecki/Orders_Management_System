package dlugolecki.pawel.service;

import dlugolecki.pawel.exception.MyException;

import java.math.BigDecimal;
import java.util.Scanner;

public class UserDataService {

    private Scanner sc = new Scanner(System.in);

    public String getString(String message) {
        System.out.println(message);
        String text = sc.nextLine();

        if (!text.matches("[a-zA-Z_ ]*")) {
            throw new MyException("USER_DATA_SERVICE", "String value is incorrect: " + text);
        }
        return text;
    }

    public int getInt(String message) {
        System.out.println(message);
        String text = sc.nextLine();

        if (!text.matches("\\d+")) {
            throw new MyException("USER_DATA_SERVICE", "Int value is incorrect: " + text);
        }
        return Integer.parseInt(text);
    }

    public Long getLong(String message) {
        System.out.println(message);
        String text = sc.nextLine();

        if (!text.matches("\\d+")) {
            throw new MyException("USER_DATA_SERVICE", "Long value is incorrect: " + text);
        }
        return Long.parseLong(text);
    }

    public BigDecimal getBigDecimal(String message) {
        System.out.println(message);
        String text = sc.nextLine();

        if (!text.matches("(\\d+\\.)*\\d+")) {
            throw new MyException("USER_DATA_SERVICE", "BigDecimal value is not a decimal: " + text);
        }
        return new BigDecimal(text);
    }
}
