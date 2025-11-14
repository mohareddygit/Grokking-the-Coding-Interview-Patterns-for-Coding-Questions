package C3Bitwise_XOR;

/**
 * Leetcode 191: Number of 1 Bits, using bit manipulation to count the number of 1s in the binary representation of an unsigned integer.
 */
public class BIT_NumberOf1Bits_B12 {
    public int hammingWeight(int n) {
        int count = 0;

        while (n != 0) {
            count += n & 1; // Add 1 if last bit is set
            n >>>= 1;       // Unsigned right shift
        }

        return count;
    }
}
