Beyond the two-pointer (in-place) approach, here are several other common methods in Java to remove duplicates from an array, with varying trade-offs in terms of time complexity, space complexity, and whether the original order is preserved:

1. Using a Set (e.g., LinkedHashSet)
   The Set interface in Java automatically prevents duplicate elements. This is often the simplest and most readable approach for general use cases. LinkedHashSet specifically maintains the original order of insertion.
   Approach: Convert the array to a List, add it to a LinkedHashSet, and then convert it back to an array.
   Time Complexity: O(n), as adding elements to a HashSet is an average O(1) operation.
   Space Complexity: O(n), for the additional Set and potentially List data structures.
   java
   import java.util.*;

public class DuplicatesWithSet {
public static Integer[] removeDuplicates(Integer[] nums) {
Set<Integer> uniqueElements = new LinkedHashSet<>(Arrays.asList(nums));
return uniqueElements.toArray(new Integer[0]);
}
}

Use code with caution.
2. Using Java 8 Streams distinct()
   For a concise and modern solution, Java 8 introduced the Stream API's distinct() method, which uses object equality (the equals() method) to filter out duplicates.
   Approach: Convert the array to a stream, call distinct(), and collect the result back into an array.
   Time Complexity: O(n), as distinct() internally uses a Set.
   Space Complexity: O(n), for the internal data structures used by the stream operations.
   java
   import java.util.Arrays;

public class DuplicatesWithStreams {
public static int[] removeDuplicates(int[] nums) {
return Arrays.stream(nums).distinct().toArray();
}
}

Use code with caution.
3. Using a Temporary Array
   This method is similar to the two-pointer approach but explicitly uses a second array to store unique elements. It requires sorting the original array first if it's unsorted.
   Approach: Sort the input array, then iterate through it, copying unique elements to a new, temporary array.
   Time Complexity: O(n log n) due to the initial sort, plus O(n) for copying.
   Space Complexity: O(n) for the temporary array.
   java
   import java.util.Arrays;

public class DuplicatesWithTempArray {
public static int[] removeDuplicates(int[] nums) {
if (nums.length <= 1) return nums;

        // Sort first if the input is not guaranteed to be sorted
        Arrays.sort(nums); 

        int[] temp = new int[nums.length];
        int j = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != nums[i + 1]) {
                temp[j++] = nums[i];
            }
        }
        temp[j++] = nums[nums.length - 1]; // Add the last element

        // Copy unique elements back to a new array of correct size
        return Arrays.copyOf(temp, j);
    }
}



