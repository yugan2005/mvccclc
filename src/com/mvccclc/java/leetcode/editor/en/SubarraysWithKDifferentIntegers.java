//Given an array A of positive integers, call a (contiguous, not necessarily dis
//tinct) subarray of A good if the number of different integers in that subarray i
//s exactly K.
//
// (For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)
//
// Return the number of good subarrays of A.
//
//
//
// Example 1:
//
//
//Input: A = [1,2,1,2,3], K = 2
//Output: 7
//Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1],
// [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
//
//
// Example 2:
//
//
//Input: A = [1,2,1,3,4], K = 3
//Output: 3
//Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2
//,1,3], [1,3,4].
//
//
//
//
// Note:
//
//
// 1 <= A.length <= 20000
// 1 <= A[i] <= A.length
// 1 <= K <= A.length
// Related Topics Hash Table Two Pointers Sliding Window
// 👍 1036 👎 20

package com.mvccclc.java.leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;


public class SubarraysWithKDifferentIntegers {
  public static void main(String[] args) {
    Solution solution = new SubarraysWithKDifferentIntegers().new Solution();
//    int[] A = new int[]{1, 2, 1, 2, 3};
//    int K = 2;
    int[] A = new int[]{1, 2, 1, 3, 4};
    int K = 3;
    System.out.println(solution.subarraysWithKDistinct(A, K));
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int subarraysWithKDistinct(int[] A, int K) {
      // 1. validate
      if (A == null || A.length == 0) {
        return 0;
      }

      return subarraysWithMaxKDistinct(A, K) - subarraysWithMaxKDistinct(A, K - 1);
    }

    private int subarraysWithMaxKDistinct(int[] A, int K) {

      // 2. initialize
      Map<Integer, Integer> counter = new HashMap<>();
      int end = 0;
      int reverseResult = 0;

      // 3. slide
      for (int start = 0; start < A.length; start++) {
        while (end < A.length && valid(counter, K)) {
          // 4. update
          update(counter, A, end, true);
          end++;
        }

        // 5. result
        int count;
        if (!valid(counter, K)) {
          count = end - start - 1;
        } else {
          count = end - start;
        }
        reverseResult += count;

        // 6. cleanup
        update(counter, A, start, false);
      }
      return reverseResult;
    }

    private boolean valid(Map<Integer, Integer> counter, int K) {
      return counter.size() <= K;
    }

    private void update(Map<Integer, Integer> counter, int[] A, int idx, boolean add) {
      int num = A[idx];
      int count = counter.getOrDefault(num, 0);
      count = add ? count + 1 : count - 1;
      if (count == 0) {
        counter.remove(num);
      } else {
        counter.put(num, count);
      }
    }
  }
//leetcode submit region end(Prohibit modification and deletion)
}