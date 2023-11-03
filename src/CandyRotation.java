import java.util.*;

public class CandyRotation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> gameSets = new ArrayList<>();
        int numberOfCan;
        int candiesInEachCan;
        int numberOfRotation;

        String[] gameSetArray = scanner.nextLine().split(" ");
        numberOfCan = Integer.valueOf(gameSetArray[0]);
        candiesInEachCan = Integer.valueOf(gameSetArray[1]);
        numberOfRotation = Integer.valueOf(gameSetArray[2]);


        //  캔 숫자 설정
        Map<Integer, List> cans = new HashMap<>();
        for (int i = 1; i < numberOfCan + 1; i++) {
            cans.put(i, new ArrayList<Integer>());
        }

        //  각 캔에 존재하는 사탕입력
        for (int i = 1; i < numberOfCan + 1; i++) {
            String[] candyArray = scanner.nextLine().split(" ");
            for (String s : candyArray) {
                cans.get(i).add(Integer.valueOf(s));
            }
        }

        //이동가능 회수 설정
        Map<Integer, Integer> ableToMoveTo = new HashMap<>();
        for (int i = 1; i < numberOfCan + 1; i++) {
            ableToMoveTo.put(i, numberOfRotation);
        }

        //필요 이동량 설정
        Map<Integer, Integer> needOfMoveTo = new HashMap<>();
        for (int i = 1; i < numberOfCan + 1; i++) {
            needOfMoveTo.put(i, 0);
        }

        for (int i = 1; i < numberOfCan + 1; i++) {
            int canNumber = i;
            List can = cans.get(i);
            for (int j = 0; j < can.size(); j++) {
                int candy = Integer.parseInt(can.get(j).toString());
                if (!(candy == canNumber)) {
                    if (candy > canNumber) {

                        if (canNumber != numberOfCan) {
                            for (int k = canNumber; (k != candy) && (k < numberOfCan); k++) {
                                int existingMove = needOfMoveTo.get(k + 1);
                                needOfMoveTo.put(k + 1, existingMove + 1);
                            }
                        } else {
                            for (int k = 0; k != candy; k++) {
                                int existingMove = needOfMoveTo.get(k + 1);
                                needOfMoveTo.put(k + 1, existingMove + 1);
                            }
                        }

                    }
                    if (candy < canNumber) {
                        int k = canNumber;

                        if (canNumber != numberOfCan) {
                            while (k < numberOfCan) {
                                int existingMove = needOfMoveTo.get(k + 1);
                                needOfMoveTo.put(k + 1, existingMove + 1);
                                k++;
                            }
                            for (int l = 0; l != candy; l++) {
                                int existingMove = needOfMoveTo.get(l + 1);
                                needOfMoveTo.put(l + 1, existingMove + 1);
                            }
                        } else {
                            for (int l = 0; l != candy; l++) {
                                int existingMove = needOfMoveTo.get(l + 1);
                                needOfMoveTo.put(l + 1, existingMove + 1);
                            }
                        }
                    }
                }
            }
        }

        //판정
        List<Integer> judge = new ArrayList<>();
        for (int i = 1; i < numberOfCan + 1; i++) {
            if (needOfMoveTo.get(i) <= ableToMoveTo.get(i)) {
                judge.add(i - 1, 1);
            } else {
                judge.add(i - 1, 0);
            }
        }

        if (judge.contains(0)) {
            System.out.println("0");
        } else {
            System.out.println("1");
        }


    }
}