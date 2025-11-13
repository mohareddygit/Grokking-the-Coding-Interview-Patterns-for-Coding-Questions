package B8K_Way_Merge;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedArrays {

    // Helper class to store necessary information for the Min-Heap
    static class Element implements Comparable<Element> {
        int value;
        int arrayIndex; // Which array this element belongs to
        int elementIndex; // Which position within that array

        Element(int value, int arrayIndex, int elementIndex) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }

        // Define natural ordering for the min-heap (smallest value first)
        @Override
        public int compareTo(Element other) {
            return this.value - other.value;
        }
    }

    /**
     * Merges k sorted arrays into a single sorted list.
     *
     * @param arrays A list of sorted integer arrays.
     * @return A single list containing all elements in sorted order.
     */
    public static List<Integer> mergeKSortedArrays(List<int[]> arrays) {
        // 1. Initialize a Min-Heap of Elements
        PriorityQueue<Element> minHeap = new PriorityQueue<>();
        
        // Result list to store the merged elements
        List<Integer> result = new ArrayList<>();

        // 2. Add the first element of each array to the heap
        for (int i = 0; i < arrays.size(); i++) {
            if (arrays.get(i).length > 0) {
                minHeap.add(new Element(arrays.get(i)[0], i, 0));
            }
        }

        // 3. Merge and Build the new list
        while (!minHeap.isEmpty()) {
            // Extract the smallest element from the heap
            Element smallest = minHeap.poll();
            
            // Append its value to the result list
            result.add(smallest.value);
            
            int arrIndex = smallest.arrayIndex;
            int nextElementIndex = smallest.elementIndex + 1;
            
            // 4. If the source array has more elements, add the next one to the heap
            if (nextElementIndex < arrays.get(arrIndex).length) {
                minHeap.add(new Element(
                    arrays.get(arrIndex)[nextElementIndex],
                    arrIndex,
                    nextElementIndex
                ));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<int[]> arrays = new ArrayList<>();
        arrays.add(new int[]{1, 5, 9});
        arrays.add(new int[]{2, 6, 10, 15});
        arrays.add(new int[]{3, 7, 11});
        
        List<Integer> mergedList = mergeKSortedArrays(arrays);
        
        System.out.println("Merged K Sorted Arrays: " + mergedList);
        // Output: [1, 2, 3, 5, 6, 7, 9, 10, 11, 15]
    }
}
