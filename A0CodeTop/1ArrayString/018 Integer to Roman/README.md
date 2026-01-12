## 12. Integer to Roman 🔢

**Difficulty**: `Medium` - **Tags**: `Math`, `Greedy`

[LeetCode Problem Link](https://leetcode.com/problems/integer-to-roman/description/)

### Description
Roman numerals are represented by seven different symbols:

| Symbol | Value |
|--------|-------|
| I      | 1     |
| V      | 5     |
| X      | 10    |
| L      | 50    |
| C      | 100   |
| D      | 500   |
| M      | 1000  |

Given an integer, convert it to a Roman numeral by breaking the integer into its decimal components and mapping them to corresponding Roman symbols.

### Examples

**Example 1:**

Input:
```java
num = 3749
```

Output:
```
"MMMDCCXLIX"
```

Explanation:
- 3000 = MMM (M + M + M)
- 700 = DCC (D + C + C)
- 40 = XL (X less than L)
- 9 = IX (I less than X)

**Example 2:**

Input:
```java
num = 58
```

Output:
```
"LVIII"
```

Explanation:
- 50 = L
- 8 = VIII

**Example 3:**

Input:
```java
num = 1994
```

Output:
```
"MCMXCIV"
```

Explanation:
- 1000 = M
- 900 = CM
- 90 = XC
- 4 = IV

### Constraints

- The input `num` is in the range [1, 3999].

## 12. Integer to Roman Conversion 🔢

**Difficulty**: `Medium` - **Tags**: `Math`, `Greedy`

[LeetCode Problem Link](https://leetcode.com/problems/integer-to-roman/description/)

### Description
Roman numerals are represented by seven different symbols:

| Symbol | Value |
|--------|-------|
| I      | 1     |
| V      | 5     |
| X      | 10    |
| L      | 50    |
| C      | 100   |
| D      | 500   |
| M      | 1000  |

Given an integer, convert it to a Roman numeral by breaking the integer into its decimal components and mapping them to corresponding Roman symbols.

### Examples

**Example 1:**

Input:
```java
num = 3749
```

Output:
```
"MMMDCCXLIX"
```

Explanation:
- 3000 = MMM (M + M + M)
- 700 = DCC (D + C + C)
- 40 = XL (X less than L)
- 9 = IX (I less than X)

**Example 2:**

Input:
```java
num = 58
```

Output:
```
"LVIII"
```

Explanation:
- 50 = L
- 8 = VIII

**Example 3:**

Input:
```java
num = 1994
```

Output:
```
"MCMXCIV"
```

Explanation:
- 1000 = M
- 900 = CM
- 90 = XC
- 4 = IV

### Constraints

- The input `num` is in the range [1, 3999].

### Solution 💡

**LeetCode 12: Integer to Roman**  in 2026, the most effective approach is a  **Greedy Algorithm**  using a predefined mapping of integer values to Roman symbols.

1. The Idea

The Roman numeral system is built on specific symbols (I, V, X, L, C, D, M) and special subtractive cases (IV, IX, XL, XC, CD, CM). To represent a number with the fewest symbols possible, you should always pick the  **largest possible value**  first and subtract it from the total until you reach zero.

2. Solution Approach

-   **Initialization:**  Create two parallel arrays (or a single object array) containing the 13 unique Roman values and their corresponding symbols in  **descending order**.
-   **Iteration:**  Loop through the values array. While the current  `num`  is greater than or equal to the current  `value`, subtract that value from  `num`  and append the corresponding symbol to a  `StringBuilder`.
-   **Completion:**  Once  `num`  reaches 0, the built string is the final Roman representation.

3. Java Implementation

Using  `StringBuilder`  is standard for 2026 performance as it avoids the overhead of creating multiple immutable string objects.



```java
class Solution {
    public String intToRoman(int num) {
        // Predefined values and symbols in descending order
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        
        StringBuilder res = new StringBuilder();
        
        // Loop through each value from largest to smallest
        for (int i = 0; i < values.length; i++) {
            // Subtract the value as many times as possible
            while (num >= values[i]) {
                num -= values[i];
                res.append(symbols[i]);
            }
        }
        
        return res.toString();
    }
}

```

4. Complexity Analysis

-   **Time Complexity:**  **O(1)**. Although it involves loops, the number of symbols is fixed (13), and the input range is limited (up to 3,999), meaning the number of operations is bounded by a constant.
-   **Space Complexity:**  **O(1)**. We use a constant amount of extra space for the mapping arrays and the result string (which has a maximum length of 15 characters for inputs up to 3,999).

5. Alternative: Hardcoded Place Values

Another fast approach involves using four arrays for each decimal place (thousands, hundreds, tens, and ones) and directly accessing them using  `num / 1000`,  `(num % 1000) / 100`, etc.. This is even faster but less flexible than the greedy version

#### Time Complexity ⏳
- **O(1)**: The time complexity is constant since the number of Roman symbols is fixed.

#### Space Complexity 💾
- **O(1)**: The space complexity is constant as we only use a few variables and a fixed-size array.

You can find the full solution [here](Solution.java).