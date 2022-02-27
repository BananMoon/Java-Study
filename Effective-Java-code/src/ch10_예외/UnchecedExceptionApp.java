package ch10_예외;

import java.util.List;

public class UnchecedExceptionApp {
    public static void main(String[] args) {
        arrayIndexOutOfBoundsAndNullPointerException();
        numberFormatException();
    }
    private static void arrayIndexOutOfBoundsAndNullPointerException() {
        int[] scores = {10, 20, 30};
        List<String> stringList = null;
        try {
            System.out.println(scores[3]);  // ArrayIndexOutOfBoundsException
            System.out.println(stringList);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } finally {
            try {
                stringList.add("hi");       // NPE
            } catch (NullPointerException e) {
                System.out.println("arrayIndexOutOfBoundsAndNullPointerException = " + e.getMessage());
            }
        }
    }

    private static void numberFormatException() {
        String[] str = new String[]{"1", "2", "zero"};
        try {
            for (String s : str) {
                System.out.print(Integer.parseInt(s) + ", ");
            }
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException = " + e.getMessage());
        }
    }
}
