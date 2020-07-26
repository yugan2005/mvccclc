//Given an array nums, there is a sliding window of size k which is moving from
//the very left of the array to the very right. You can only see the k numbers in
//the window. Each time the sliding window moves right by one position. Return the
// max sliding window.
//
// Follow up:
//Could you solve it in linear time?
//
// Example:
//
//
//Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
//Output: [3,3,5,5,6,7]
//Explanation:
//
//Window position                Max
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
//
//
//
// Constraints:
//
//
// 1 <= nums.length <= 10^5
// -10^4 <= nums[i] <= 10^4
// 1 <= k <= nums.length
//
// Related Topics Heap Sliding Window
// 👍 3524 👎 170

package com.mvccclc.java.leetcode.editor.en;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;


public class SlidingWindowMaximum {
  public static void main(String[] args) {
    Solution solution = new SlidingWindowMaximum().new Solution();
    int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
    int k = 3;
    System.out.println(Arrays.toString(solution.maxSlidingWindow(nums, k)));
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
      // 1. validate
      if (nums == null || nums.length == 0 || nums.length < k) {
        return null;
      }

      // Monotonic Queue
      int[] result = new int[nums.length - k + 1];
      Deque<Integer> decreasingQueue = new ArrayDeque<>();
      for (int i = 0; i < k; i++) {
        while (!decreasingQueue.isEmpty() && nums[i] > decreasingQueue.peekLast()) {
          decreasingQueue.pollLast();
        }
        decreasingQueue.offerLast(nums[i]);
      }
      for (int i = 0; i < result.length - 1; i++) {
        result[i] = decreasingQueue.peekFirst();
        if (nums[i] == decreasingQueue.peekFirst()) {
          decreasingQueue.pollFirst();
        }
        while (!decreasingQueue.isEmpty() && nums[i + k] > decreasingQueue.peekLast()) {
          decreasingQueue.pollLast();
        }
        decreasingQueue.offerLast(nums[i + k]);
      }
      result[result.length - 1] = decreasingQueue.peekFirst();
      return result;
    }
  }
//leetcode submit region end(Prohibit modification and deletion)
}