import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Pragma {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int myInt1 = scanner.nextInt();
        int myInt2 = scanner.nextInt();
        int myInt3  = scanner.nextInt();

        scanner.close();

        System.out.println(myInt1);
        System.out.println(myInt2);
        System.out.println(myInt3);


    }

    public static int findLongestSubsequence(List<Integer> arr) {

        // Write your code here
        List<Integer> newLists = arr.subList(1, arr.size() - 1);

        Collections.sort(newLists);

        List<Integer> newList = new ArrayList<>();

        for (int i = 0; i < newLists.size() - 1; i++) {
            int result = newLists.get(i) - newLists.get(i + 1);
            newList.add(Math.abs(result));
        }

        return newList.stream().reduce(0, Integer::sum);
    }
}
