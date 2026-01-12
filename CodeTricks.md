In Java, the technique

`s.charAt(i) - 'A'` (or `-'a'`) is a way to map characters to **zero-based integer indices**. This is a standard optimization in 2026 used to replace expensive HashMaps with simple, high-performance arrays.

Here is how the technique works under the hood:

1. Characters are actually Numbers

In Java, the `char` type is a 16-bit Unicode integer. When you perform a mathematical operation (like subtraction) on a `char`, Java uses its underlying ASCII/Unicode value.

-   The ASCII value of `'A'` is **65**.
-   The ASCII value of `'B'` is **66**.
-   The ASCII value of `'C'` is **67**.

2. The "Offset" Math

By subtracting the starting character of a range (the "offset"), you transform the character into its position in the alphabet:

Character / ASCII Math / Result (Index)

`'A'` / `65 - 65` / **0**

`'B'` / `66 - 65` / **1**

`'C'`/ `67 - 65` / **2**

`'Z'` / `90 - 65` / **25**

3. Why we use it (The Benefits)

-   **Memory Efficiency:** An `int[26]` array is significantly smaller than a `HashMap<Character, Integer>`. A HashMap stores objects (Character objects, Integer objects, Map Entry objects), while an array stores raw primitives.
-   **Speed:** Accessing an array index (`array[index]`) is an **𝑂(1)** operation that happens directly in hardware. A HashMap requires calculating a hash code, handling potential collisions, and traversing internal buckets.
-   **Fixed Range:** If the problem specifies the input is only "uppercase English letters," you know exactly how large your array needs to be (26).

4. Implementation Example

Instead of this:



```java
Map<Character, Integer> map = new HashMap<>();
map.put('C', map.getOrDefault('C', 0) + 1);

```

Use code with caution.

You do this:



```java
int[] counts = new int[26];
counts[s.charAt(i) - 'A']++; // Increments the value at index 2

```

Use code with caution.

Common Variations

-   **Lowercase only:** Use `s.charAt(i) - 'a'`.
-   **Uppercase and Lowercase:** Use an `int[52]` and logic to handle the gap between ASCII 'Z' (90) and 'a' (97), or more simply, just use an `int[128]` to cover all standard ASCII characters.
-   **Digits:** Use `s.charAt(i) - '0'` to convert the character `'5'` into the actual integer `5`.

**Note for 2026:** While this is highly efficient, it only works for **fixed character sets**. If your input can contain any Unicode character (emojis, Chinese characters, etc.), you must use a `HashMap`.
