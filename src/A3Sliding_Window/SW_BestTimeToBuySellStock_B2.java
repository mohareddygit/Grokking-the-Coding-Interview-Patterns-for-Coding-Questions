package A3Sliding_Window;

public class SW_BestTimeToBuySellStock_B2 {
    /**
     * One-Pass Greedy Approach
     * @param prices
     * @return
     */
    public int maxProfitCool(int[] prices) {
        // Safety check: If there are fewer than 2 days, no transaction can occur
        if (prices == null || prices.length < 2) {
            return 0;
        }

        // Initialize 'buy' as the first day's price (our first "valley")
        int buy = prices[0];
        // Initialize maxProfit at 0 (if prices only go down, profit stays 0)
        int maxProfit = 0;

        // Start iterating from the second day (index 1)
        for (int i = 1; i < prices.length; i++) {

            // SCENARIO 1: Current price is higher than our 'buy' price
            // This is a potential selling opportunity.
            if (prices[i] > buy) {
                // Calculate profit for today and keep the highest one seen so far
                maxProfit = Math.max(prices[i] - buy, maxProfit);
            }

            // SCENARIO 2: Current price is lower than or equal to our 'buy' price
            // We found a new "valley." We should "buy" here instead to
            // maximize potential profit in future days.
            else {
                buy = prices[i];
            }
        }

        return maxProfit;
    }





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
