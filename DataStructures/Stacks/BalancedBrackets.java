import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

/**
 * This is a solution for https://www.hackerrank.com/challenges/balanced-brackets/problem
 */

public class BalancedBrackets {

    static final String DEFAULT_OUTPUT_FILE = "BalancedBracketsOutput.txt";
    static final Map<Character, Character> openBrackets = new HashMap<>();

    static {
        openBrackets.put('}', '{');
        openBrackets.put(')', '(');
        openBrackets.put(']', '[');
    }

    static String isBalanced(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c: s.toCharArray()) {
            if (openBrackets.containsValue(c)) {
                stack.push(c);
            } else if (openBrackets.containsKey(c)) {
                if (stack.isEmpty() || openBrackets.get(c) != stack.pop()) {
                    return "NO";
                }
            }
        }

        return stack.isEmpty() ? "YES" : "NO";
    }

    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(System.in);
            BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(
                    Optional.
                        ofNullable(System.getenv("OUTPUT_PATH"))
                        .orElse(DEFAULT_OUTPUT_FILE)
                    ))) {
            final int t = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int tItr = 0; tItr < t; tItr++) {
                final String s = scanner.nextLine();
                final String result = isBalanced(s);
                bufferedWriter.write(result);
                bufferedWriter.newLine();
            }
        }
    }
}
