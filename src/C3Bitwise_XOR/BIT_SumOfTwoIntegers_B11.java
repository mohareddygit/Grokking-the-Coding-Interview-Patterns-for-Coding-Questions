package C3Bitwise_XOR;

/**
 * Leetcode 371: Sum of Two Integers, which computes the sum of two integers without using + or - operators, using bitwise operations.
 */
public class BIT_SumOfTwoIntegers_B11 {
    public int getSum(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1; // Carry is AND shifted left
            a = a ^ b;                // Sum without carry is XOR
            b = carry;                // Repeat with carry
        }
        return a;
    }
}
