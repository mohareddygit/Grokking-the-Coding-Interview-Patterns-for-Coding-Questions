
[139. Word Break](https://leetcode.com/problems/word-break/)

Given a string  `s`  and a dictionary of strings  `wordDict`, return  `true`  if  `s`  can be segmented into a space-separated sequence of one or more dictionary words.

**Note**  that the same word in the dictionary may be reused multiple times in the segmentation.

**Example 1:**

**Input:** s = "leetcode", wordDict = ["leet","code"]
**Output:** true
**Explanation:** Return true because "leetcode" can be segmented as "leet code".

**Example 2:**

**Input:** s = "applepenapple", wordDict = ["apple","pen"]
**Output:** true
**Explanation:** Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.

**Example 3:**

**Input:** s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
**Output:** false

**Constraints:**

-   `1 <= s.length <= 300`
-   `1 <= wordDict.length <= 1000`
-   `1 <= wordDict[i].length <= 20`
-   `s`  and  `wordDict[i]`  consist of only lowercase English letters.
-   All the strings of  `wordDict`  are  **unique**.



### **LeetCode 139: Word Break** is using **Dynamic Programming (Bottom-Up)**.

Core Idea

The problem asks if a string `s` can be fully segmented using words from a dictionary. To solve this, we break the big problem into smaller sub-problems: _If we can successfully segment the first `i` characters of the string, can we then find a word in the dictionary that completes the segment up to index `j`?_

Why DP is Required

The Word Break problem has  **optimal substructure**  and  **overlapping subproblems**. This means:

-   A string is breakable if its  **prefix**  is breakable  **AND**  the  **suffix**  is a valid word.
-   You must check all possible split points because one choice (e.g., taking "cat") might fail while another (e.g., taking "cats") might succeed.

Standard solutions use  **Dynamic Programming**  or  **BFS/DFS with Memoization**  to ensure they check every possible valid way to segment the string, rather than just deleting words they find.

1. Optimal Substructure

A problem has optimal substructure if an optimal solution to the entire problem can be constructed from optimal solutions to its subproblems.

In Word Break, the "optimal" solution is simply a boolean: _Can string 𝑆
be segmented?_

-   **The Breakdown:** To know if the string `S[0...i]` is segmentable, you only need to know two things:
    1.  Is there some smaller prefix `S[0...j]` (where 𝑗<𝑖) that is **already known** to be segmentable?
    2.  Is the remaining suffix `S[j...i]` a valid word in the dictionary?
-   **Constructing the Solution:** If the answer to both is "Yes," then the larger problem `S[0...i]` is also "Yes." You don't need to re-verify how `S[0...j]` was broken; you only care that it _could_ be.

2. Overlapping Subproblems

This occurs when the algorithm solves the **same small subproblems** multiple times. Without DP or memoization, the computation tree for Word Break explodes exponentially.

Consider `s = "aaaaaaaa"` and `wordDict = ["a", "aa", "aaa"]`.  
To solve for the whole string, a recursive approach would check:

-   Can we break `"a"` + `wordBreak("aaaaaaa")`?
-   Can we break `"aa"` + `wordBreak("aaaaaa")`?
-   Can we break `"aaa"` + `wordBreak("aaaaa")`?

Notice the overlap:

1.  To solve `wordBreak("aaaaaaa")`, the computer will eventually need to solve `wordBreak("aaaa")`.
2.  To solve `wordBreak("aaaaaa")`, the computer will **also** need to solve `wordBreak("aaaa")`.

**Without DP:** You would re-calculate whether `"aaaa"` can be segmented dozens of times.  
**With DP:** You calculate `dp[4]` (representing `"aaaa"`) once, store it, and every future request for that substring just does a constant time 𝑂(1) lookup.

##Approach: Dynamic Programming (Bottom-Up)

1.  **DP Array:** Create a boolean array `dp` of size `n + 1` (where `n` is the length of `s`).
    -   `dp[i]` will be `true` if the prefix `s[0...i-1]` can be segmented into words from the dictionary.
2.  **Base Case:** `dp[0] = true` because an empty string is considered segmentable.
3.  **Transitions:** For every index `i` from 1 to `n`, check every possible split point `j` (where `0 <= j < i`).
    -   If `dp[j]` is `true` (the first part is valid) **AND** the substring `s[j...i]` exists in the dictionary, then `dp[i]` becomes `true`.
4.  **Optimization:** Use a `HashSet` for the dictionary to ensure 𝑂(1)

    word lookups.

```java
import java.util.*;

public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // Convert list to Set for O(1) constant time lookups
        Set<String> wordSet = new HashSet<>(wordDict);
        int n = s.length();
        
        // dp[i] means s.substring(0, i) can be segmented
        boolean[] dp = new boolean[n + 1];
        
        // Base case: empty string can always be "broken"
        dp[0] = true;
        
        // Fill the dp array
        for (int i = 1; i <= n; i++) {
            // Check all possible split points before 'i'
            for (int j = 0; j < i; j++) {
                // If the prefix s[0...j] is valid AND 
                // the remaining part s[j...i] is a word in the dict
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // Found a valid segmentation for s[0...i], move to next i
                }
            }
        }
        
        // Result is whether the entire string can be segmented
        return dp[n];
    }
}

```

Complexity Analysis

-   **Time Complexity: **𝑂(𝑛2⋅𝑘)** where 𝑛mis the length of the string and 𝑘
    is the time to create a substring. In most modern Java environments, substring creation is 𝑂(𝑘), making the total roughly 𝑂(𝑛3) in the worst case.
-   **Space Complexity:  **𝑂(𝑛)**  for the `dp` array and 𝑂(𝑚⋅𝐿) for the `HashSet`, where 𝑚 is the number of words and 𝐿 is their average length.

Why this works

Imagine `s = "leetcode"` and `wordDict = ["leet", "code"]`.

-   `dp[0]` is true.
-   When `i=4`, it finds `j=0`: `dp[0]` is true and `s.substring(0,4)` ("leet") is in dict. So `dp[4] = true`.
-   When `i=8`, it finds `j=4`: `dp[4]` is true and `s.substring(4,8)` ("code") is in dict. So `dp[8] = true`.
-   The final answer `dp[8]` is `true`