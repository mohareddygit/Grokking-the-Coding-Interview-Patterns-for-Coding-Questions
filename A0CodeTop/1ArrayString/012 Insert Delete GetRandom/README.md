## 380. Insert Delete GetRandom O(1) 🎲

**Difficulty**: `Medium` - **Tags**: `Design`, `Hash Table`, `Randomized`

### Description
Implement the `RandomizedSet` class:

- `RandomizedSet()` Initializes the `RandomizedSet` object.
- `bool insert(int val)` Inserts an item `val` into the set if not present. Returns `true` if the item was not present, `false` otherwise.
- `bool remove(int val)` Removes an item `val` from the set if present. Returns `true` if the item was present, `false` otherwise.
- `int getRandom()` Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.

You must implement the functions of the class such that each function works in average O(1) time complexity.

### Examples

**Example 1:**

Input:
```python
["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
[[], [1], [2], [2], [], [1], [2], []]
```

Output:
```
[null, true, false, true, 2, true, false, 2]
```

Explanation:
```python
RandomizedSet randomizedSet = new RandomizedSet();
randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
randomizedSet.insert(2); // 2 was already in the set, so return false.
randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.
```

---

### Solution 💡

**LeetCode 380: Insert Delete GetRandom O(1)**, you must design a data structure that supports adding, removing, and retrieving a random element, all in **average O(1)** time.

1. The Core Idea: Hybrid Data Structure

No single standard data structure can perform all three operations in 𝑂(1)

time. Instead, you must combine two:

-   **Dynamic Array (`ArrayList`):** Provides 𝑂(1)

    access by index, which is essential for the `getRandom` operation.
-   **Hash Table (`HashMap`):** Provides 𝑂(1)

    average time to check if an element exists and to find its index within the array.

2. Solution Approach

The main challenge is deleting an element in 𝑂(1)

from an array, as removing from the middle normally requires 𝑂(𝑛)

shifting.

-   **Insert:** Append the new element to the end of the `ArrayList` and store its value and index in the `HashMap`.
-   **GetRandom:** Generate a random index between 0 and size−1

    and return the element at that index in the `ArrayList`.
-   **Remove (The "Swap and Pop" Trick):**
    1.  Look up the index of the element to be removed in the `HashMap`.
    2.  Swap that element with the **last element** in the `ArrayList`.
    3.  Update the `HashMap` with the new index of the swapped element.
    4.  Remove the last element from the `ArrayList` (this is an 𝑂(1)  operation).
    5.  Remove the target element from the `HashMap`

#### Java

```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

class RandomizedSet {
    private ArrayList<Integer> nums;
    private HashMap<Integer, Integer> map;
    private Random rand;

    public RandomizedSet() {
        nums = new ArrayList<>();
        map = new HashMap<>();
        rand = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        map.put(val, nums.size());
        nums.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;

        int indexToRemove = map.get(val);
        int lastElement = nums.get(nums.size() - 1);

        // Swap last element with the one to be removed
        nums.set(indexToRemove, lastElement);
        map.put(lastElement, indexToRemove);

        // Remove the last element
        nums.remove(nums.size() - 1);
        map.remove(val);
        return true;
    }

    public int getRandom() {
        return nums.get(rand.nextInt(nums.size()));
    }
}

```

#### Time Complexity ⏳
- `insert` operation: **O(1)**
- `remove` operation: **O(1)**
- `getRandom` operation: **O(1)**

#### Space Complexity 💾
- The space complexity is **O(n)**, where `n` is the number of elements in the set, due to the storage in the hash map and the list.
- as you store each unique element twice (once in the list and once in the map)


You can find the full `Solution.java` file [here](Solution.java).

<br>
<br>

#### why dont java have such a class implementation if it can solve operations in O(1)

While the

**RandomizedSet** (Hybrid HashMap/ArrayList) approach effectively solves the specific challenge of 𝑂(1)

random access alongside insertion and deletion, Java does not include this in its standard `java.util` library for several design and architectural reasons:

1. Conceptual "Weight" and API Purity

The Java Collections Framework (JCF) is designed to be "reasonably small in conceptual weight".

-   **Purpose-Driven Interfaces:** Java separates collections by their primary mathematical purpose: `Set` (uniqueness), `List` (order/index), and `Map` (association).
-   **Ambiguity:** A "RandomizedSet" is a hybrid that doesn't fit cleanly into these categories. For instance, a `Set` interface does not naturally support index-based random access because its elements are considered unordered.

2. High Memory Overhead

To achieve 𝑂(1)

for all three operations, you must store every element twice:

-   Once in an `ArrayList` (for indexed access).
-   Once in a `HashMap` (for 𝑂(1)

    value-to-index lookup).  
    Standard Java collections prioritize a balance between performance and memory efficiency. Doubling the memory footprint for a specialized `getRandom()` use case is often considered too "expensive" for a general-purpose library.

3. Niche Use Case

Most real-world applications do not require constant-time random selection from a set that is also being frequently modified.

-   If you need a random element from a static set, calling `toArray()` or creating an `ArrayList` once is sufficient.
-   If you need to randomize an entire collection, `Collections.shuffle(List)` is the standard, albeit  𝑂(𝑛)

    , solution.

4. Implementation Fragility

The "swap-and-pop" trick used to make deletion𝑂(1)

in this structure works by **changing the order of elements** in the underlying array whenever a removal occurs.

-   In a standard library, users often expect certain properties (like insertion order or sorted order) to be preserved.
-   A collection that reorders its internal structure silently every time `remove()` is called could lead to unpredictable bugs in user code that relies on stability