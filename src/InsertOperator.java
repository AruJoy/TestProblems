import java.util.*;
import java.util.stream.Collectors;

public class InsertOperator {
    /**
     * https://www.acmicpc.net/problem/14888
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String NumberSize = scanner.nextLine();

        List<String> stringNumberArray = List.of(scanner.nextLine().split(" "));
        List<Integer> numbers = stringNumberArray.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        List<String> stringOperators = List.of(scanner.nextLine().split(" "));
        List<Integer> operators = stringOperators.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        ResultSet resultSet = firstOperate(numbers, operators);
        numbers.remove(0);
        numbers.remove(0);

        while (numbers.size() > 0) {
            resultSet = operate(resultSet, numbers);
            numbers.remove(0);
        }

        List<Integer> values = resultSet.getValue();
        System.out.println(Collections.max(values) + "\n" + Collections.min(values));


    }

    private static ResultSet firstOperate(List<Integer> numbers, List<Integer> operators) {
        Map<Integer, String> operatorMap = operatorMap();
        int value = 0;
        List<List<Integer>> opratorList = new ArrayList<>();
        List<Integer> valueList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            if (operators.get(i) != 0) {
                value = arithmetic(numbers.get(0), numbers.get(1), operatorMap.get(i));
                int newOperatorNumber = operators.get(i);
                List<Integer> newOperators = new ArrayList<>(operators);
                newOperators.set(i, newOperatorNumber - 1);
                opratorList.add(newOperators);
                valueList.add(value);
            }
        }
        return new ResultSet(opratorList, valueList);
    }

    private static ResultSet operate(
            ResultSet resultSet, List<Integer> numbers) {
        Map<Integer, String> operatorMap = operatorMap();
        int value;
        List<List<Integer>> opratorList = new ArrayList<>();
        List<Integer> valueList = new ArrayList<>();

        List<List<Integer>> beforeOperatorList = resultSet.getOperatorList();
        List<Integer> beforeResultList = resultSet.getValue();

        for (int i = 0; i < beforeResultList.size(); i++) {
            List<Integer> operators = beforeOperatorList.get(i);
            int beforeValue = beforeResultList.get(i);
            for (int j = 0; j < 4; j++) {
                if (operators.get(j) != 0) {
                    value = arithmetic(beforeValue, numbers.get(0), operatorMap.get(j));
                    List<Integer> newOperators = new ArrayList<>(operators);
                    int newOperatorNumber = operators.get(j);
                    newOperators.set(j, newOperatorNumber - 1);
                    opratorList.add(newOperators);
                    valueList.add(value);
                }
            }
        }
        return new ResultSet(opratorList, valueList);
    }

    private static Map<Integer, String> operatorMap() {
        Map<Integer, String> operatorMap = new HashMap<>();
        operatorMap.put(0, "+");
        operatorMap.put(1, "-");
        operatorMap.put(2, "*");
        operatorMap.put(3, "/");
        return operatorMap;
    }

    private static int arithmetic(int a, int b, String operator) {
        int value = 0;
        if (operator == "+") {
            value = a + b;
        }
        if (operator == "-") {
            value = a - b;
        }
        if (operator == "*") {
            value = a * b;
        }
        if (operator == "/") {
            value = a / b;
        }
        return value;
    }

}

class ResultSet {
    private List<List<Integer>> operatorList;
    private List<Integer> value;

    public ResultSet(List<List<Integer>> operatorList, List<Integer> value) {
        this.operatorList = operatorList;
        this.value = value;
    }

    public List<List<Integer>> getOperatorList() {
        return operatorList;
    }

    public List<Integer> getValue() {
        return value;
    }
}