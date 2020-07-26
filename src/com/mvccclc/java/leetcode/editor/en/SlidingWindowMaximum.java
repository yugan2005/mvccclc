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

import java.util.Comparator;
import java.util.TreeMap;


public class SlidingWindowMaximum{
    public static void main(String[] args) {
         Solution solution = new SlidingWindowMaximum().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 1. validate
        if (nums == null || nums.length == 0 || nums.length < k) {
            return null;
        }

        TreeMap<Integer, Integer> numCounter = new TreeMap<>(Comparator.reverseOrder());
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++) {
            update(numCounter, nums, i, true);
        }

        for (int i = k; i < nums.length; i++) {
            result[i-k] = numCounter.firstKey();
            update(numCounter, nums, i, true);
            update(numCounter, nums, i-k, false);
        }
        result[nums.length - k] = numCounter.firstKey();
        return result;
    }

    private void update( TreeMap<Integer, Integer> numCounter, int[] nums, int idx, boolean add) {
        int num = nums[idx];
        int count = numCounter.getOrDefault(num, 0);
        count = add ? count + 1 : count - 1;
        if (count > 0) {
            numCounter.put(num, count);
        } else {
            numCounter.remove(num);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}