# Matrix Algorithms in LeetCode 🧩

## Introduction 📝

In algorithms, **Matrix** refers to a two-dimensional array, often used to solve problems related to grids, image manipulation, or other grid-based data structures. Matrix problems on LeetCode vary widely, from basic operations like rotating matrices to more complex challenges like searching, optimization, or traversing cells in the matrix.

# Matrix Algorithms in LeetCode 🧩

## 1. Rotate Matrix

- **Description**: Rotate a square `n x n` matrix by 90 degrees clockwise.
- **Example**:
  - **Input**:
    ```
    [
      [1, 2, 3],
      [4, 5, 6],
      [7, 8, 9]
    ]
    ```
  - **Output**:
    ```
    [
      [7, 4, 1],
      [8, 5, 2],
      [9, 6, 3]
    ]
    ```

## 2. Set Matrix Zeroes

- **Description**: If an element in the matrix is 0, set its entire row and column to 0.
- **Example**:
  - **Input**:
    ```
    [
      [1, 1, 1],
      [1, 0, 1],
      [1, 1, 1]
    ]
    ```
  - **Output**:
    ```
    [
      [1, 0, 1],
      [0, 0, 0],
      [1, 0, 1]
    ]
    ```

## 3. Game of Life

- **Description**: Apply the Game of Life rules to update the state of the cells in the matrix.
- **Example**:
  - **Input**:
    ```
    [
      [0, 1, 0],
      [0, 0, 1],
      [1, 1, 1],
      [0, 0, 0]
    ]
    ```
  - **Output**:
    ```
    [
      [0, 0, 0],
      [1, 0, 1],
      [0, 1, 1],
      [0, 1, 0]
    ]
    ```

## 4. Spiral Matrix

- **Description**: Return the elements of a matrix in a spiral order.
- **Example**:
  - **Input**:
    ```
    [
      [1, 2, 3],
      [4, 5, 6],
      [7, 8, 9]
    ]
    ```
  - **Output**:
    ```
    [1, 2, 3, 6, 9, 8, 7, 4, 5]
    ```

## 5. Diagonal Traverse

- **Description**: Traverse the matrix diagonally.
- **Example**:
  - **Input**:
    ```
    [
      [1, 2, 3],
      [4, 5, 6],
      [7, 8, 9]
    ]
    ```
  - **Output**:
    ```
    [1, 2, 4, 7, 5, 3, 6, 8, 9]
    ```

## Common Approaches to Solve Matrix Problems 💡

### 1. **In-place Operations**

- **Explanation**: This approach modifies the matrix directly without using extra space. It’s ideal for problems like rotating a matrix or setting entire rows and columns to zero.
- **Example**: Rotating a matrix or setting rows and columns to zero.

### 2. **Traversal**

- **Explanation**: Matrix problems often require traversing through each element in the matrix, either by row, column, or even diagonally.
- **Example**: Traversing the matrix in spiral order or diagonally.

### 3. **Search Algorithms**

- **Explanation**: For problems involving searching for an element or path within a matrix, search algorithms like BFS (Breadth-First Search) or DFS (Depth-First Search) are useful.
- **Example**: Searching for the shortest path in a matrix.

### 4. **Dynamic Programming**

- **Explanation**: Dynamic programming is useful for optimization problems in matrices, such as finding the longest sequence or the most optimal path.
- **Example**: Finding the longest increasing path in a matrix.

### 5. **Space Optimization**

- **Explanation**: Some problems require memory optimization, especially when dealing with large matrices. You can sometimes modify the matrix directly to save space rather than creating new matrices.
- **Example**: Using a matrix for dynamic programming solutions without additional space.

---

## Conclusion 🎉

Matrix problems are an essential part of algorithm learning, and solving them requires using a combination of different methods and strategies, such as in-place operations, traversal, search algorithms, dynamic programming, and space optimization.

1. **Matrix Rotations** (LeetCode 48: Rotate Image)

To rotate a matrix 90 degrees clockwise **in-place**, you use a two-step geometric transformation.

-   **The Formula:** `Transpose` + `Reflect (Reverse Rows)`
  1.  **Transpose:** Swap element at `[i][j]` with `[j][i]`. This turns rows into columns.
  2.  **Reverse Rows:** For each row, reverse the elements (swap `[i][j]` with `[i][n-1-j]`).
-   **Alternative (Counter-Clockwise):** Transpose, then **Reverse Columns**.

2. Spiral Traversal (LeetCode 54: Spiral Matrix)

Spiral problems are about **Boundary Contraction**. Instead of complex math, you maintain four boundary variables that "shrink" the search space.

-   **The Variables:** `top`, `bottom`, `left`, `right`.
-   **The Logic:**
  1.  Move **Right**: From `left` to `right`, then increment `top`.
  2.  Move **Down**: From `top` to `bottom`, then decrement `right`.
  3.  Move **Left**: From `right` to `left`, then decrement `bottom`.
  4.  Move **Up**: From `bottom` to `top`, then increment `left`.
-   **Crucial Tip:** Always check `if (top <= bottom && left <= right)` after each boundary update to prevent duplicate processing.

3. Boundary/In-Place Mapping (LeetCode 73: Set Matrix Zeroes)

To mark an entire row/column as zero in 𝑂(1) space, use the **First Row and First Column** as your "storage indicators."

-   **The Strategy:**
  1.  Use `matrix[i][0]` and `matrix[0][j]` to record if row `i` or column `j` should be zeroed.
  2.  Use two boolean variables to track if the _original_ first row/column themselves need to be zeroed.
  3.  Iterate through the rest of the matrix, then use the first row/column "flags" to fill zeros.

4. Zero Shifting (LeetCode 283: Move Zeroes)

This is technically a 1D array problem, but it applies to 2D matrix flattening.

-   **The Formula:** `Two-Pointer Swap`
  1.  `lastNonZeroFoundAt = 0`
  2.  Iterate with `j`: if `nums[j] != 0`, swap `nums[j]` with `nums[lastNonZeroFoundAt]` and increment the pointer.
-   **Result:** This maintains the **relative order** of non-zero elements while pushing all zeros to the end.

5. Matrix Diagonal Formulas

Diagonals are frequently tested in "Diagonal Traverse" or "Toeplitz Matrix" problems.

-   **Main Diagonal (Top-left to Bottom-right):** Every element has the property: 𝑖−𝑗=constant
-   **Anti-Diagonal (Top-right to Bottom-left):** Every element has the property: 𝑖+𝑗=constant
-   **Usage:** You can use a `HashMap<Integer, List<Integer>>` where the key is 𝑖+𝑗 or 𝑖−𝑗 to group diagonal elements together.

6. **Sudoku Box Index:** (𝑖/3)×3+(𝑗/3)
