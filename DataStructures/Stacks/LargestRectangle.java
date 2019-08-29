import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;
import java.util.Scanner;

/**
 * This is a solution for https://www.hackerrank.com/challenges/largest-rectangle/problem
 */

public class LargestRectangle {

    static final String DEFAULT_OUTPUT_FILE = "LargestRectangleOutput.txt";

    static long largestRectangle(int[] heights) {
        int[] h = new int[heights.length + 1];  // copy of array with sentinel 0
        System.arraycopy(heights, 0, h, 0, heights.length);
        Deque<int[]> houses = new ArrayDeque<>();   // [0] - is position, [1] - is height
        long maxSquare = 0;

        for (int i = 0; i < h.length; i++) {
            int insertPosition = i;

            while (!houses.isEmpty() && houses.peek()[1] > h[i]) {
                int last[] = houses.pop();
                long sq = (i - last[0]) * last[1];
                maxSquare = Math.max(maxSquare, sq);
                insertPosition = last[0];
            }

            houses.push(new int[]{insertPosition, h[i]});
        }
        return maxSquare;
    }

    public static void main(String[] args) throws IOException {

        try (Scanner scanner = new Scanner(System.in);
            BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(
                    Optional.
                        ofNullable(System.getenv("OUTPUT_PATH"))
                        .orElse(DEFAULT_OUTPUT_FILE)
                    ))) {
            final int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
    
            int[] h = new int[n];
    
            String[] hItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
    
            for (int i = 0; i < n; i++) {
                int hItem = Integer.parseInt(hItems[i]);
                h[i] = hItem;
            }
    
            final long result = largestRectangle(h);
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }
}
