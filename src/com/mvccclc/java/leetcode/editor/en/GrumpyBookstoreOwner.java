//Today, the bookstore owner has a store open for customers.length minutes. Ever
//y minute, some number of customers (customers[i]) enter the store, and all those
// customers leave after the end of that minute.
//
// On some minutes, the bookstore owner is grumpy. If the bookstore owner is gru
//mpy on the i-th minute, grumpy[i] = 1, otherwise grumpy[i] = 0. When the booksto
//re owner is grumpy, the customers of that minute are not satisfied, otherwise th
//ey are satisfied.
//
// The bookstore owner knows a secret technique to keep themselves not grumpy fo
//r X minutes straight, but can only use it once.
//
// Return the maximum number of customers that can be satisfied throughout the d
//ay.
//
//
//
// Example 1:
//
//
//Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
//Output: 16
//Explanation: The bookstore owner keeps themselves not grumpy for the last 3 mi
//nutes.
//The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5
//= 16.
//
//
//
//
// Note:
//
//
// 1 <= X <= customers.length == grumpy.length <= 20000
// 0 <= customers[i] <= 1000
// 0 <= grumpy[i] <= 1
// Related Topics Array Sliding Window
// 👍 450 👎 44

package com.mvccclc.java.leetcode.editor.en;

public class GrumpyBookstoreOwner {
  public static void main(String[] args) {
    Solution solution = new GrumpyBookstoreOwner().new Solution();
    int[] customers = new int[]{1, 0, 1, 2, 1, 1, 7, 5};
    int[] grumpy = new int[]{0, 1, 0, 1, 0, 1, 0, 1};
    int X = 3;
    System.out.println(solution.maxSatisfied(customers, grumpy, X));
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {

      // 1. validate
      if (customers == null || customers.length == 0 || grumpy == null || grumpy.length != customers.length) {
        return 0;
      }

      // 2. initialize
      int end = 0;
      int addedCustomer = 0;
      int maxAddedCustomer = 0;

      // 3. slide
      for (int start = 0; start < customers.length; start++) {
        while (end < customers.length && valid(start, end, X)) {
          // 4. update
          addedCustomer = update(customers, grumpy, addedCustomer, end, true);
          end++;
        }

        // 5. result
        if (maxAddedCustomer < addedCustomer) {
          maxAddedCustomer = addedCustomer;
        }

        // 6. clean up
        addedCustomer = update(customers, grumpy, addedCustomer, start, false);
      }

      int originalCustomer = 0;
      for (int i = 0; i < customers.length; i++) {
        originalCustomer += customers[i] * (1 - grumpy[i]);
      }

      return originalCustomer + maxAddedCustomer;
    }

    private boolean valid(int start, int end, int X) {
      return end - start < X;
    }

    private int update(int[] customers, int[] grumpy, int addedCustomer, int idx, boolean add) {
      int changedNumCustomer = grumpy[idx] == 0 ? 0 : customers[idx];
      return add ? addedCustomer + changedNumCustomer : addedCustomer - changedNumCustomer;
    }
  }
//leetcode submit region end(Prohibit modification and deletion)
}