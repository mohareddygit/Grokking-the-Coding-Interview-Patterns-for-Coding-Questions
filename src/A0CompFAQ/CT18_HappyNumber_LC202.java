package A0CompFAQ;
import java.util.HashSet;
import java.util.Set;

public class CT18_HappyNumber_LC202 {
    


    public boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNext(n);
        }
        return n == 1;
    }

    /**
     * The most efficient way to extract digits
     * for squaring is using arithmetic operators (% and /) rather than string conversion
     * @param n
     * @return
     */
    private int getNext(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }

    /**
     * Time: (O(log n)) — The number of digits in (n) is (log n).
     * Space: (O(log n)) with a HashSet or (O(1)) with Floyd's algorithm.
     * @param n
     * @return
     */
    /**
     * Approach 2: Floyd’s Cycle-Finding Algorithm (Space Optimized)
     * To solve this in O(1) auxiliary space, you can use the
     * "Tortoise and the Hare" method.
     * You treat the sequence of sums as a linked list where two pointers move at different speeds.
     * If they meet at any number other than 1, a cycle exists
     * @param n
     * @return
     */
    public boolean isHappyFloydCycle(int n) {
        int slow = n;
        int fast = getNext(n);
        while (fast != 1 && slow != fast) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }
        return fast == 1;
    }


}
