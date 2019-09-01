import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * This is a solution for https://www.hackerrank.com/challenges/special-palindrome-again/problem
 */

class KeyWithCount {
    char key;
    long count;

    KeyWithCount(char key, long count) {
        this.key = key;
        this.count = count;
    }
}

public class SpecialStringAgain {

    static final String DEFAULT_OUTPUT_FILE = "SpecialStringAgainOutput.txt";

    static List<KeyWithCount> createKeys(String s) {
        List<KeyWithCount> chars = new ArrayList<>();
        KeyWithCount key = null;
        for (int i = 0; i < s.length(); ++i) {
            if (key == null) {
                key = new KeyWithCount(s.charAt(i), 1);
                continue;
            }

            if (key.key == s.charAt(i)) {
                key.count += 1;
                continue;
            }

            chars.add(key);
            key = new KeyWithCount(s.charAt(i), 1);
        }
        chars.add(key);
        return chars;
    }

    static long substrCount(int n, String s) {
        List<KeyWithCount> keys = createKeys(s);
        long count = 0;
        for (int i = 0; i < keys.size(); i++) {
            // number of substring in string: n * (n + 1 ) / 2
            count = count + (keys.get(i).count * (keys.get(i).count + 1) / 2);

            // all characters except the middle one are the same
            if (i < keys.size() - 2 && keys.get(i + 1).count == 1) {
                KeyWithCount kLeft = keys.get(i);
                KeyWithCount kRight = keys.get(i + 2);
                if (kLeft.key == kRight.key) {
                    count = count + Math.min(kLeft.count, kRight.count);
                }
            }
        }
        
        return count;
    }

    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(System.in);
            BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(
                    Optional.
                        ofNullable(System.getenv("OUTPUT_PATH"))
                        .orElse(DEFAULT_OUTPUT_FILE)
                    ))) {

            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            String s = scanner.nextLine();

            long result = substrCount(n, s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();

            bufferedWriter.close();

            scanner.close();
        }
    }
}
