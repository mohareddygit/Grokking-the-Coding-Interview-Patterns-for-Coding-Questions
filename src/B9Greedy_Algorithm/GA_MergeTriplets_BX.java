package B9Greedy_Algorithm;

/**
 * Leetcode 1899: Merge Triplets to Form Target Triplet, using a greedy + filtering strategy.
 *
 */
public class GA_MergeTriplets_BX {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        boolean[] found = new boolean[3]; // Tracks if each target value is matched

        for (int[] triplet : triplets) {
            // Skip triplets that exceed target in any position
            if (triplet[0] > target[0] || triplet[1] > target[1] || triplet[2] > target[2]) {
                continue;
            }

            // Mark positions that match target exactly
            for (int i = 0; i < 3; i++) {
                if (triplet[i] == target[i]) {
                    found[i] = true;
                }
            }
        }

        // All target values must be matched by some triplet
        return found[0] && found[1] && found[2];
    }

    public static void main(String[] args) {
        GA_MergeTriplets_BX solver = new GA_MergeTriplets_BX();
        int[][] triplets = {{2,5,3},{1,8,4},{1,7,5}};
        int[] target = {2,7,5};
        System.out.println("Can form target? " + solver.mergeTriplets(triplets, target)); // Output: true
    }
}
