import java.util.*;

/**
 * https://www.acmicpc.net/problem/2504
 */
public class ValueParentheses {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<String> parenthesesInput = new ArrayList<>(List.of(scanner.nextLine().split("")));
        Collections.reverse(parenthesesInput);
        try {
            List<String> parentheses = new ArrayList<>(parenthesesInput);
            int condition = 1;
            Stack parenthesesClose = new Stack<>();
            while (parentheses.size() != 0) {
                if (condition == 1) {
                    if (parentheses.get(0).equals("]") || parentheses.get(0).equals(")")) {
                        parenthesesClose.push(parentheses.remove(0));
                    } else {
                        condition = 0;
                    }
                }
                if (condition == 0) {
                    if (parentheses.get(0).equals("(") && parenthesesClose.peek().equals(")")) {
                        parentheses.remove(0);
                        parenthesesClose.pop();
                    } else if (parentheses.get(0).equals("[") && parenthesesClose.peek().equals("]")) {
                        parentheses.remove(0);
                        parenthesesClose.pop();
                    } else if (parentheses.get(0).equals("]") || parentheses.get(0).equals(")")) {
                        parenthesesClose.push(parentheses.remove(0));
                        condition = 1;
                    } else {
                        System.out.println(0);
                        return;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(0);
            return;
        }
        int result = 0;
        int middleValue = 0;
        int condition = 1;
        boolean first = true;
        boolean init = true;
        Stack parenthesesClose = new Stack<>();
        List<String> parentheses = new ArrayList<>(parenthesesInput);
        while (parentheses.size() != 0) {
            if (condition == 1) {
                if (parentheses.get(0).equals("]") || parentheses.get(0).equals(")")) {
                    parenthesesClose.push(parentheses.remove(0));
                } else {
                    condition = 0;
                }
            }
            if (condition == 0) {
                if (parentheses.get(0).equals("(") && parenthesesClose.peek().equals(")")) {
                    parentheses.remove(0);
                    parenthesesClose.pop();
                    if ((!first) && init) {
                        middleValue = middleValue + 2;
                    }
                    if ((!first) && (!init)) {
                        middleValue = middleValue * 2;
                    }
                    if (first) {
                        middleValue = 2;
                        first = false;
                    }
                    init = false;

                    if (parenthesesClose.isEmpty()) {
                        result += middleValue;
                        middleValue = 0;
                        first = true;
                    }
                } else if (parentheses.get(0).equals("[") && parenthesesClose.peek().equals("]")) {
                    parentheses.remove(0);
                    parenthesesClose.pop();
                    if ((!first) && init) {
                        middleValue = middleValue + 3;
                    }
                    if ((!first) && (!init)) {
                        middleValue = middleValue * 3;
                    }
                    if (first) {
                        middleValue = 3;
                        first = false;
                    }
                    init = false;

                    if (parenthesesClose.isEmpty()) {
                        result += middleValue;
                        middleValue = 0;
                        first = true;
                    }
                }
                try {
                    if (parentheses.get(0).equals("]") || parentheses.get(0).equals(")")) {
                        condition = 1;
                        init = true;
                    }
                } catch (Exception e) {
                }
            }
        }
        System.out.println(result);
    }
}