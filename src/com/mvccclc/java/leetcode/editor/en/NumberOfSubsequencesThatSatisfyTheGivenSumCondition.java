//Given an array of integers nums and an integer target.
//
// Return the number of non-empty subsequences of nums such that the sum of the
//minimum and maximum element on it is less or equal than target.
//
// Since the answer may be too large, return it modulo 10^9 + 7.
//
//
// Example 1:
//
//
//Input: nums = [3,5,6,7], target = 9
//Output: 4
//Explanation: There are 4 subsequences that satisfy the condition.
//[3] -> Min value + max value <= target (3 + 3 <= 9)
//[3,5] -> (3 + 5 <= 9)
//[3,5,6] -> (3 + 6 <= 9)
//[3,6] -> (3 + 6 <= 9)
//
//
// Example 2:
//
//
//Input: nums = [3,3,6,8], target = 10
//Output: 6
//Explanation: There are 6 subsequences that satisfy the condition. (nums can ha
//ve repeated numbers).
//[3] , [3] , [3,3], [3,6] , [3,6] , [3,3,6]
//
// Example 3:
//
//
//Input: nums = [2,3,3,4,6,7], target = 12
//Output: 61
//Explanation: There are 63 non-empty subsequences, two of them don't satisfy th
//e condition ([6,7], [7]).
//Number of valid subsequences (63 - 2 = 61).
//
//
// Example 4:
//
//
//Input: nums = [5,2,4,1,7,6,8], target = 16
//Output: 127
//Explanation: All non-empty subset satisfy the condition (2^7 - 1) = 127
//
//
// Constraints:
//
//
// 1 <= nums.length <= 10^5
// 1 <= nums[i] <= 10^6
// 1 <= target <= 10^6
//
// Related Topics Sort Sliding Window
// 👍 254 👎 16

package com.mvccclc.java.leetcode.editor.en;

import java.util.Arrays;


public class NumberOfSubsequencesThatSatisfyTheGivenSumCondition {
  public static void main(String[] args) {
    Solution solution = new NumberOfSubsequencesThatSatisfyTheGivenSumCondition().new Solution();
//    int[] nums = new int[]{3, 5, 6, 7};
//    int target = 9;
//    int[] nums = new int[]{3, 3, 6, 8};
//    int target = 10;
//    int[] nums = new int[]{2, 3, 3, 4, 6, 7};
//    int target = 12;
//    int[] nums = new int[]{5,2,4,1,7,6,8};
//    int target = 16;
//    int[] nums = new int[]{1};
//    int target = 1;
    int[] nums =
        new int[]{27, 21, 14, 2, 15, 1, 19, 8, 12, 24, 21, 8, 12, 10, 11, 30, 15, 18, 28, 14, 26, 9, 2, 24, 23, 11, 7,
            12, 9, 17, 30, 9, 28, 2, 14, 22, 19, 19, 27, 6, 15, 12, 29, 2, 30, 11, 20, 30, 21, 20, 2, 22, 6, 14, 13, 19,
            21, 10, 18, 30, 2, 20, 28, 22};
    int target = 31;
    System.out.println(solution.numSubseq(nums, target));
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {

    public int numSubseq(int[] nums, int target) {

      // 1. validate
      if (nums == null || nums.length == 0) {
        return 0;
      }

      // 2. initialize

      int max = 1000000007;
      int[] twoPowersMod = new int[nums.length];
      twoPowersMod[0] = 1;
      for (int i = 1; i < twoPowersMod.length; i++) {
        twoPowersMod[i] = (twoPowersMod[i - 1] << 1) % max;
      }

      Arrays.sort(nums);
      int count = 0;
      int end = nums.length - 1;

      // 3. slide
      for (int start = 0; start < nums.length; start++) {
        while (end > start && !valid(nums, start, end, target)) {
          end--;
        }

        // 5. result
        if (valid(nums, start, end, target)) {
          count += twoPowersMod[end - start];
          count = count % max;
        }
      }
      return count;
    }

    private boolean valid(int[] nums, int start, int end, int target) {
      return nums[start] + nums[end] <= target;
    }
  }
//leetcode submit region end(Prohibit modification and deletion)
}