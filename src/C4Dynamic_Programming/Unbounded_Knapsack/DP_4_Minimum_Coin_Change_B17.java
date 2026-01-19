package C4Dynamic_Programming.Unbounded_Knapsack;

// Problem Statement: Minimum Coin Change
// LeetCode Question: 322.Coin Change
// this is a variation of 0/1 knapsack problem without any bounds

import java.util.Arrays;

public class DP_4_Minimum_Coin_Change_B17 {

    /**
     * Calculates the fewest number of coins needed to make up the target amount.
     * This dynamic programming solution has
     * time complexity of O(amount * number of coins)
     * space complexity of O(amount)
     * @param coins An array of coin denominations.
     * @param amount The target total amount of money.
     * @return The minimum number of coins, or -1 if the amount cannot be made.
     */
    public int coinChangeBottomUp(int[] coins, int amount) {
        // dp[i] will store the minimum number of coins needed to make up amount 'i'.
        // We need an array of size amount + 1.
        int[] dp = new int[amount + 1];

        // Initialize the array with a value greater than the possible maximum number of coins (amount + 1),
        // effectively representing "infinity" or "impossible to make" at first.
        // This is a common technique to easily find the minimum later.
        Arrays.fill(dp, amount + 1);

        // Base case: 0 coins are needed to make an amount of 0.
        dp[0] = 0;

        // Iterate through all possible amounts from 1 up to the target amount.
        for (int i = 1; i <= amount; i++) {
            // For each amount 'i', iterate through all available coin denominations.
            for (int coin : coins) {
                // If the current coin denomination is less than or equal to the current amount 'i',
                // we can consider using this coin.
                if (i >= coin) {
                    // Update dp[i] to the minimum of its current value and
                    // the number of coins needed for the remaining amount (i - coin) plus 1 (for the current coin).
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        // After filling the entire DP table, dp[amount] holds the minimum coins needed.
        // If dp[amount] is still the initial "infinity" value (amount + 1), it means
        // the amount cannot be made up using the given coins, so return -1.
        return dp[amount] > amount ? -1 : dp[amount];
    }







    //  Brute-Force solution
    class Solution_1 {

        public int countChange(int[] denominations, int total) {
            int result = this.countChangeRecursive(denominations, total, 0);
            return (result == Integer.MAX_VALUE ? -1 : result);
        }

        private int countChangeRecursive(int[] denominations, int total, int currentIndex) {
            // base check
            if (total == 0)
                return 0;

            if(denominations.length == 0 || currentIndex >= denominations.length)
                return Integer.MAX_VALUE;

            // recursive call after selecting the coin at the currentIndex
            // if the coin at currentIndex exceeds the total, we shouldn't process this
            int count1 = Integer.MAX_VALUE;
            if( denominations[currentIndex] <= total ) {
                int res = countChangeRecursive(denominations, total - denominations[currentIndex], currentIndex);
                if(res != Integer.MAX_VALUE){
                    count1 = res + 1;
                }
            }

            // recursive call after excluding the coin at the currentIndex
            int count2 = countChangeRecursive(denominations, total, currentIndex + 1);

            return Math.min(count1, count2);
        }

    }

    // Top-down Dynamic Programming with Memoization
    class Solution_2 {

        public int countChange(int[] denominations, int total) {
            Integer[][] dp = new Integer[denominations.length][total + 1];
            int result = this.countChangeRecursive(dp, denominations, total, 0);
            return (result == Integer.MAX_VALUE ? -1 : result);
        }

        private int countChangeRecursive(Integer[][] dp, int[] denominations, int total, int currentIndex) {
            // base check
            if (total == 0)
                return 0;

            if(denominations.length == 0 || currentIndex >= denominations.length)
                return Integer.MAX_VALUE;

            // check if we have not already processed a similar sub-problem
            if(dp[currentIndex][total] == null) {
                // recursive call after selecting the coin at the currentIndex
                // if the coin at currentIndex exceeds the total, we shouldn't process this
                int count1 = Integer.MAX_VALUE;
                if( denominations[currentIndex] <= total ) {
                    int res = countChangeRecursive(dp, denominations, total - denominations[currentIndex], currentIndex);
                    if(res != Integer.MAX_VALUE){
                        count1 = res + 1;
                    }
                }

                // recursive call after excluding the coin at the currentIndex
                int count2 = countChangeRecursive(dp, denominations, total, currentIndex + 1);
                dp[currentIndex][total] = Math.min(count1, count2);
            }
            return dp[currentIndex][total];
        }

    }

    // Bottom-up Dynamic Programming

    class Solution_3 {

        public int countChange(int[] denominations, int total)
        {
            int n = denominations.length;
            int[][] dp = new int[n][total + 1];

            for(int i=0; i < n; i++)
                for(int j=0; j <= total; j++)
                    dp[i][j] = Integer.MAX_VALUE;

            // populate the total=0 columns, as we don't need any coin to make zero total
            for(int i=0; i < n; i++)
                dp[i][0] = 0;

            for(int i=0; i < n; i++) {
                for(int t=1; t <= total; t++) {
                    if(i > 0)
                        dp[i][t] = dp[i-1][t]; //exclude the coin
                    if(t >= denominations[i]) {
                        if(dp[i][t-denominations[i]] != Integer.MAX_VALUE)
                            dp[i][t] = Math.min(dp[i][t], dp[i][t-denominations[i]]+1); // include the coin
                    }
                }
            }

            // total combinations will be at the bottom-right corner.
            return (dp[n-1][total] == Integer.MAX_VALUE ? -1 : dp[n-1][total]);
        }

    }


}
