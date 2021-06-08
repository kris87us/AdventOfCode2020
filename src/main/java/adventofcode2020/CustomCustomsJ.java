package adventofcode2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CustomCustomsJ {
    public static void main(String[] args) throws IOException {
        var input = Arrays.stream(Files.readString(Path.of("/Users/gopaliyer/IdeaProjects/Practice/src/adventofcode2020/06.txt"))
                .split("\n\n"))
                .map(line -> line.replaceAll("\n", " ").trim())
                .collect(Collectors.toList());
        System.out.println(problem1(input));
        System.out.println(problem2(input));
    }

    static int problem1(List<String> input) {
        int count = 0;
        for (var line : input) {
            count += line.chars().filter(c -> c != ' ').distinct().count();
        }
        return count;
    }

    static int problem2(List<String> input) {
        int count = 0;
        for (var line : input) {
            var answers = line.split(" ");
            count += answers[0].chars()
                    .filter(ch -> IntStream.range(1, answers.length).allMatch(i -> answers[i].indexOf(ch) >= 0))
                    .count();
        }
        return count;
    }
}
