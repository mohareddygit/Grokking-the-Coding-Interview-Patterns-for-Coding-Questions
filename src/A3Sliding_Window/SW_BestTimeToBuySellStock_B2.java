package A3Sliding_Window;

public class SW_BestTimeToBuySellStock_B2 {
    /**
     * One-Pass Greedy Approach
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE; // Track the lowest price seen so far
        int maxProfit = 0;                // Track the maximum profit

        for (int price : prices) {
            // Update minPrice if current price is lower
            if (price < minPrice) {
                minPrice = price;
            }
            // Calculate profit if sold today and update maxProfit
            else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }

        return maxProfit;
    }

    /**
     * Sliding window approach
     * @param prices
     * @return
     */
    public int maxProfitSlidingWindow(int[] prices) {
        int left = 0; // Buy day
        int right = 1; // Sell day
        int maxProfit = 0;

        while (right < prices.length) {
            // If profitable, calculate and update maxProfit
            if (prices[right] > prices[left]) {
                int profit = prices[right] - prices[left];
                maxProfit = Math.max(maxProfit, profit);
            } else {
                // If not profitable, move buy day forward
                left = right;
            }
            right++; // Always move sell day forward
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        SW_BestTimeToBuySellStock_B2 solver = new SW_BestTimeToBuySellStock_B2();
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println("Max Profit: " + solver.maxProfit(prices)); // Output: 5
    }
}
