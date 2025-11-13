## 🔍 Key Differences Between Leetcode 1288 and 435

| Feature                      | Leetcode 1288: Remove Covered Intervals | Leetcode 435: Non-overlapping Intervals |
|-----------------------------|------------------------------------------|------------------------------------------|
| **Goal**                    | Count intervals *not covered* by others | Remove minimum intervals to eliminate *overlaps* |
| **Definition of conflict**  | One interval is *fully contained* in another | Two intervals *share time* (overlap) |
| **Strategy**                | Sort by start ↑, end ↓; skip covered     | Sort by end ↑; greedily keep non-overlapping |
| **Return value**            | Number of remaining intervals            | Number of intervals to remove            |
| **Greedy logic**            | Skip intervals where `prevStart ≤ currStart && prevEnd ≥ currEnd` | Keep intervals where `currStart ≥ prevEnd` |
| **Example**                 | `[[1,4],[3,6],[2,8]]` → Output: `2`      | `[[1,2],[2,3],[3,4],[1,3]]` → Output: `1` |
