//Given an array of integers nums and an integer limit, return the size of the l
//ongest non-empty subarray such that the absolute difference between any two elem
//ents of this subarray is less than or equal to limit.
//
//
// Example 1:
//
//
//Input: nums = [8,2,4,7], limit = 4
//Output: 2
//Explanation: All subarrays are:
//[8] with maximum absolute diff |8-8| = 0 <= 4.
//[8,2] with maximum absolute diff |8-2| = 6 > 4.
//[8,2,4] with maximum absolute diff |8-2| = 6 > 4.
//[8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
//[2] with maximum absolute diff |2-2| = 0 <= 4.
//[2,4] with maximum absolute diff |2-4| = 2 <= 4.
//[2,4,7] with maximum absolute diff |2-7| = 5 > 4.
//[4] with maximum absolute diff |4-4| = 0 <= 4.
//[4,7] with maximum absolute diff |4-7| = 3 <= 4.
//[7] with maximum absolute diff |7-7| = 0 <= 4.
//Therefore, the size of the longest subarray is 2.
//
//
// Example 2:
//
//
//Input: nums = [10,1,2,4,7,2], limit = 5
//Output: 4
//Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute
//diff is |2-7| = 5 <= 5.
//
//
// Example 3:
//
//
//Input: nums = [4,2,2,2,4,4,2,2], limit = 0
//Output: 3
//
//
//
// Constraints:
//
//
// 1 <= nums.length <= 10^5
// 1 <= nums[i] <= 10^9
// 0 <= limit <= 10^9
//
// Related Topics Array Sliding Window
// 👍 592 👎 18

package com.mvccclc.java.leetcode.editor.en;

import java.util.ArrayDeque;
import java.util.Deque;


public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {
  public static void main(String[] args) {
    Solution solution = new LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit().new Solution();
//    int[] nums = new int[]{10, 1, 2, 4, 7, 2};
//    int limit = 5;
    int[] nums = new int[]{8, 2, 4, 7};
    int limit = 4;
//    int[] nums = new int[]{4, 2, 2, 2, 4, 4, 2, 2};
//    int limit = 0;
//    int[] nums = new int[]{7,40,10,10,40,39,96,21,54,73,33,17,2,72,5,76,28,73,59,22,100,91,80,66,5,49,26,45,13,27,74,87,56,76,25,64,14,86,50,38,65,64,3,42,79,52,37,3,21,26,42,73,18,44,55,28,35,87};
//    int limit = 63;
    System.out.println(solution.longestSubarray(nums, limit));
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {

    /**
     * Method 1
     */
//    public int longestSubarray(int[] nums, int limit) {
//      // 1. validate
//      if (nums == null || nums.length == 0) {
//        return 0;
//      }
//
//      // 2. initialize
//      TreeSet<Element> subArray = new TreeSet<>();
//      int maxLength = 0;
//      int start = 0;
//
//      // 3. slide
//      for (int end = 0; end < nums.length; end++) {
//        // 6. cleanup
//        update(subArray, nums, end, true);
//
//        while (start < end && !valid(subArray, limit)) {
//          // 4. update
//          update(subArray, nums, start, false);
//          start++;
//        }
//
//        // 5. result
//        if (valid(subArray, limit)) {
//          maxLength = Math.max(maxLength, end - start + 1);
//        }
//      }
//
//      return maxLength;
//    }
//
//    private boolean valid(TreeSet<Element> subArray, int limit) {
//      return subArray.last().getValue() - subArray.first().getValue() <= limit;
//    }
//
//    private void update(TreeSet<Element> subArray, int[] nums, int idx, boolean add) {
//      Element element = new Element(idx, nums[idx]);
//      if (add) {
//        subArray.add(element);
//      } else {
//        subArray.remove(element);
//      }
//    }
//
//    class Element implements Comparable<Element> {
//      private final int idx;
//      private final int value;
//
//      public Element(int idx, int value) {
//        this.idx = idx;
//        this.value = value;
//      }
//
//      public int getIdx() {
//        return idx;
//      }
//
//      public int getValue() {
//        return value;
//      }
//
//      @Override
//      public int compareTo(Element other) {
//        int valueCompare = Integer.compare(this.value, other.value);
//        return valueCompare == 0 ? Integer.compare(this.idx, other.getIdx()) : valueCompare;
//      }
//    }

    /**
     * Method 2
     */
    public int longestSubarray(int[] nums, int limit) {
      // 1. validate
      if (nums == null || nums.length == 0) {
        return 0;
      }

      // 2. initialize
      Deque<Integer> ascendingIdxQueue = new ArrayDeque<>();
      Deque<Integer> descendingIdxQueue = new ArrayDeque<>();
      int maxLength = 0;
      int start = 0;

      // 3. slide
      for (int end = 0; end < nums.length; end++) {
        // 6. cleanup
        update(ascendingIdxQueue, descendingIdxQueue, nums, end, true);

        while (start < end && !valid(ascendingIdxQueue, descendingIdxQueue, nums, limit)) {
          // 4. update
          update(ascendingIdxQueue, descendingIdxQueue, nums, start, false);
          start++;
        }

        // 5. result
        if (valid(ascendingIdxQueue, descendingIdxQueue, nums, limit)) {
          maxLength = Math.max(maxLength, end - start + 1);
        }
      }

      return maxLength;
    }

    private boolean valid(Deque<Integer> ascendingIdxQueue, Deque<Integer> descendingIdxQueue, int[] nums, int limit) {
      if (ascendingIdxQueue.isEmpty() || descendingIdxQueue.isEmpty()) {
        return true;
      }
      return nums[descendingIdxQueue.peekFirst()] - nums[ascendingIdxQueue.peekFirst()] <= limit;
    }

    private void update(Deque<Integer> ascendingIdxQueue, Deque<Integer> descendingIdxQueue, int[] nums, int idx,
        boolean add) {
      int val = nums[idx];
      if (add) {
        // add
        while (!ascendingIdxQueue.isEmpty() && nums[ascendingIdxQueue.peekLast()] > val) {
          ascendingIdxQueue.pollLast();
        }
        ascendingIdxQueue.offerLast(idx);
        while (!descendingIdxQueue.isEmpty() && nums[descendingIdxQueue.peekLast()] < val) {
          descendingIdxQueue.pollLast();
        }
        descendingIdxQueue.offerLast(idx);
      } else {
        // remove
        if (!ascendingIdxQueue.isEmpty() && nums[ascendingIdxQueue.peekFirst()] == val) {
          ascendingIdxQueue.pollFirst();
        }
        if (!descendingIdxQueue.isEmpty() && nums[descendingIdxQueue.peekFirst()] == val) {
          descendingIdxQueue.pollFirst();
        }
      }
    }
  }
//leetcode submit region end(Prohibit modification and deletion)
}