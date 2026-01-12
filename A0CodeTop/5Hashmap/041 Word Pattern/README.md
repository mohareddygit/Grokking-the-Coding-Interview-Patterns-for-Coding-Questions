## 290. Word Pattern 🧩

**Difficulty**: `Easy` - **Tags**: `Hash Table`, `String`

[LeetCode Problem Link](https://leetcode.com/problems/word-pattern/)

---

### Problem Statement 📜

Given a `pattern` and a string `s`, find if `s` follows the same `pattern`.

Here "follow" means a full match, such that there is a bijection between a letter in `pattern` and a non-empty word in `s`.

---

### Examples 🌟

🔹 **Example 1:**

**Input:**
```plaintext
pattern = "abba", s = "dog cat cat dog"
```

**Output:**
```plaintext
true
```

🔹 **Example 2:**

**Input:**
```plaintext
pattern = "abba", s = "dog cat cat fish"
```

**Output:**
```plaintext
false
```

🔹 **Example 3:**

**Input:**
```plaintext
pattern = "aaaa", s = "dog cat cat dog"
```

**Output:**
```plaintext
false
```

---

### Constraints ⚙️

- `1 <= pattern.length <= 300`
- `pattern` contains only lower-case English letters.
- `1 <= s.length <= 3000`
- `s` contains only lowercase English letters and spaces `' '`.
- `s` does not contain any leading or trailing spaces.
- All the words in `s` are separated by a single space.

---

### Solution 💡

To determine if `s` follows the same pattern, we need to ensure that there is a one-to-one correspondence between the characters of `pattern` and the words in `s`. We can use two hash maps for this purpose:

1. One map to store the pattern's character to word mapping.
2. Another map to store the word to pattern's character mapping.

---

#### Java Solution

```java
import java.util.HashMap;

class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");

        if (pattern.length() != words.length) return false;

        Map<Character, String> patternToWord = new HashMap<>();
        Map<String, Character> wordToPattern = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];

            if (patternToWord.containsKey(c)) {
                if (!patternToWord.get(c).equals(word)) {
                    return false;
                }
            } else {
                patternToWord.put(c, word);
            }

            if (wordToPattern.containsKey(word)) {
                if (wordToPattern.get(word) != c) {
                    return false;
                }
            } else {
                wordToPattern.put(word, c);
            }
        }

        return true;
    }
}
```

---

### Explanation of the Solution

1. **Splitting the Input String**:
   - We split `s` by spaces to get an array of words.

2. **Checking Lengths**:
   - If the length of `pattern` doesn't match the number of words in `s`, return `false`.

3. **Mapping Characters to Words**:
   - We use two hash maps:
     - `patternToWord` to map characters in `pattern` to words in `s`.
     - `wordToPattern` to map words in `s` to characters in `pattern`.

4. **Validation**:
   - For each character and corresponding word, we check if the current mapping exists.
   - If it exists and doesn't match the expected value, return `false`.
   - If a valid mapping exists, continue checking until all characters and words are validated.

5. **Return True**:
   - If all the mappings are consistent, return `true`.

---

### Time Complexity ⏳

- **O(n)**:
  - `n` is the length of the pattern (or the number of words in `s`).
  - Each character and word is processed once.

### Space Complexity 💾

- **O(n)**:
  - Space is used for two hash maps, each storing up to `n` entries.


You can find the full solution [here](Solution.java).


##LeetCode 290: Word Pattern

you must establish a **bijection** (a two-way mapping) between each character in the pattern and each word in the string `s`.

1. The Idea: Two-Way Mapping

A pattern match is only valid if:

-   Every character in the pattern maps to exactly one unique word in `s`.
-   Every unique word in `s` maps back to exactly one character in the pattern.
-   The number of characters in the pattern must exactly match the number of words in `s`.

2. Solution Approach

The most robust method uses **two HashMaps** to track these dual relationships simultaneously.

1.  **Split the String:** Use `.split(" ")` to convert the input string `s` into an array of words.
2.  **Base Case:** If the length of the pattern and the word array differ, return `false` immediately.
3.  **Synchronized Traversal:** Iterate through the pattern characters and words at the same time:
    -   If the character is already in `charToWord` map, verify it matches the current word.
    -   If the word is already in `wordToChar` map, verify it matches the current character.
    -   If neither exist, add both new mappings to their respective maps.

```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (words.length != pattern.length()) return false;

        Map<Character, String> charToWord = new HashMap<>();
        Map<String, Character> wordToChar = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];

            // Check if character mapping is consistent
            if (charToWord.containsKey(c)) {
                if (!charToWord.get(c).equals(word)) return false;
            } else {
                charToWord.put(c, word);
            }

            // Check if word mapping is consistent
            if (wordToChar.containsKey(word)) {
                if (wordToChar.get(word) != c) return false;
            } else {
                wordToChar.put(word, c);
            }
        }
        return true;
    }
}

```

4. Complexity Analysis

-   **Time Complexity:**𝑂(𝑁+𝑀)

    , where 𝑁

    is the length of the pattern and 𝑀

    is the number of characters in string `s` (due to splitting and traversal).
-   **Space Complexity:**𝑂(𝑀), as the HashMaps store the unique words from string `s`.

5. Alternative: Single Map Trick

You can also use a single `HashMap<Object, Integer>` and store the **index of the last occurrence** for both characters and words. If the indices ever differ during traversal, the pattern is invalid. This works because `HashMap.put()` returns the _previous_ value associated with the key.


Why One Map is Not Enough

If you only use one map (`charToWord`), you only check if a character consistently maps to the same word. You fail to check if  **different characters map to the same word**.

**Failure Example without  `wordToChar`:**

-   **Input:**  `pattern = "abba"`,  `s = "dog dog dog dog"`
-   **With only  `charToWord`:**
    -   `'a'`  maps to  `"dog"`.
    -   `'b'`  maps to  `"dog"`.
    -   The loop continues because  `'b'`  is a new key.
    -   **Result:**  `true`  (Incorrect! Multiple letters cannot share the same word).

The Two Rules of Bijection

The two mapping checks enforce these specific rules:

1.  **`charToWord`  Check:**  Each letter in the pattern must map to exactly one unique word.
2.  **`wordToChar`  Check:**  Each unique word in the string must map back to exactly one letter in the pattern.

By checking both, you ensure that if  `'a'`  translates to  `"dog"`, then  `"dog"`  must  **always and only**  translate back to  `'a'`

**Alternative**: **Single Map + Set**

If you want to avoid a second map, you can use a single  `HashMap<Character, String>`  to store the forward mapping and a  `HashSet<String>`  to track all words that have already been "claimed" by a character. If you encounter a new character but its corresponding word is already in the  `Set`, the pattern is invalid.