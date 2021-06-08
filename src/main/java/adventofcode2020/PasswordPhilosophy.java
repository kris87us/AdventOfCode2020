package adventofcode2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PasswordPhilosophy {
    private static String input = "";

    static {
        try {
            input = Files.readString(Path.of("/Users/gopaliyer/IdeaProjects/Practice/src/adventofcode2020/input/PasswordPhilosophy.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final String[] inputArr = input.split("\n");

    public static void solve(String[] inputArr) {
        int validCount1 = 0;
        int validCount2 = 0;
        for (String s : inputArr) {
            final String[] singleLine = s.split(" ");

            final String[] occurenceCount = singleLine[0].split("-");
            final String lowerOccurence = occurenceCount[0];
            final String upperOccurence = occurenceCount[1];

            final String mandatoryCharacter = singleLine[1].replace(":", "");

            final String password = singleLine[2];

            if (password.contains(mandatoryCharacter)) {
                Integer tempCount = 0;
                for (char str : password.toCharArray()) {
                    if (str == mandatoryCharacter.charAt(0)) {
                        tempCount++;
                    }
                }
                if (tempCount.compareTo(Integer.parseInt(lowerOccurence)) >= 0 && tempCount.compareTo(Integer.parseInt(upperOccurence)) <= 0) {
                    validCount1++;
                }
                boolean one = password.charAt(Integer.parseInt(lowerOccurence) - 1) == mandatoryCharacter.charAt(0);
                boolean two = password.charAt(Integer.parseInt(upperOccurence) - 1) == mandatoryCharacter.charAt(0);
                if (one != two) {
                    System.out.println("Password: " + password + " Length: " + password.length());
                    System.out.println("Mandate Char: " + mandatoryCharacter + " present at " + lowerOccurence + " and " + upperOccurence);
                    validCount2++;
                }
            }
        }
        System.out.println(validCount1);
        System.out.println(validCount2);
    }

    public static void main(String[] args) {
        solve(inputArr);
    }
}
