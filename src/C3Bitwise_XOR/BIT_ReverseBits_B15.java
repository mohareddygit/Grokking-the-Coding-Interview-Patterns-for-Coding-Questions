package C3Bitwise_XOR;

/**
 * Leetcode 190: Reverse Bits, which reverses the bits of a 32-bit unsigned integer.
 */
public class BIT_ReverseBits_B15 {
    public int reverseBits(int n) {
        int result = 0;

        for (int i = 0; i < 32; i++) {
            result <<= 1;         // Shift result left to make room
            result |= (n & 1);    // Add the last bit of n
            n >>>= 1;             // Unsigned right shift n
        }

        return result;
    }
}
