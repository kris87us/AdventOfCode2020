package adventofcode2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReportRepair {
    private static String input = "";

    static {
        try {
            input = Files.readString(Path.of("/Users/gopaliyer/IdeaProjects/Practice/src/adventofcode2020/input/ReportRepair.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final String[] inputArr = input.split("\n");
    private static int answer = 0;

    public static void solvePart1(String[] input) {
        for (String s : input) {
            for (int j = 1; j < input.length - 1; j++) {
                if (Integer.parseInt(s) + Integer.parseInt(input[j]) == 2020) {
                    answer = Integer.parseInt(s) * Integer.parseInt(input[j]);
                    break;
                }
            }
        }
        System.out.println(answer);
    }

    public static void solvePart2(String[] input) {
        for (String s : input) {
            for (int j = 1; j < input.length - 1; j++) {
                for (int k = 2; k < input.length - 2; k++) {
                    if (Integer.parseInt(s) + Integer.parseInt(input[j]) + Integer.parseInt(input[k]) == 2020) {
                        answer = Integer.parseInt(s) * Integer.parseInt(input[j]) * Integer.parseInt(input[k]);
                        break;
                    }
                }
            }
        }
        System.out.println(answer);
    }

    public static void main(String[] args) {
        solvePart1(inputArr);
        solvePart2(inputArr);
    }
}
