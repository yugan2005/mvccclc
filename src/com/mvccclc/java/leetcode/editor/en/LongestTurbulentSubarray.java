//A subarray A[i], A[i+1], ..., A[j] of A is said to be turbulent if and only if
//:
//
//
// For i <= k < j, A[k] > A[k+1] when k is odd, and A[k] < A[k+1] when k is even
//;
// OR, for i <= k < j, A[k] > A[k+1] when k is even, and A[k] < A[k+1] when k is
// odd.
//
//
// That is, the subarray is turbulent if the comparison sign flips between each
//adjacent pair of elements in the subarray.
//
// Return the length of a maximum size turbulent subarray of A.
//
//
//
//
// Example 1:
//
//
//Input: [9,4,2,10,7,8,8,1,9]
//Output: 5
//Explanation: (A[1] > A[2] < A[3] > A[4] < A[5])
//
//
//
// Example 2:
//
//
//Input: [4,8,12,16]
//Output: 2
//
//
//
// Example 3:
//
//
//Input: [100]
//Output: 1
//
//
//
//
//
//
//
// Note:
//
//
// 1 <= A.length <= 40000
// 0 <= A[i] <= 10^9
// Related Topics Array Dynamic Programming Sliding Window
// 👍 357 👎 86

package com.mvccclc.java.leetcode.editor.en;

import java.util.ArrayDeque;
import java.util.Deque;


public class LongestTurbulentSubarray {
  public static void main(String[] args) {
    Solution solution = new LongestTurbulentSubarray().new Solution();
    int[] A = new int[]{9, 4, 2, 10, 7, 8, 8, 1, 9};
    System.out.println(solution.maxTurbulenceSize(A));
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int maxTurbulenceSize(int[] A) {

      // 1. validate
      if (A == null) {
        return 0;
      }
      if (A.length <= 1) {
        return A.length;
      }

      // 2. initialize
      Deque<Integer> signs = new ArrayDeque<>();
      int end = 1;
      int maxLength = signs.size();

      // 3. sliding
      for (int start = 0; start < A.length; start++) {
        while (end < A.length && valid(signs, A, end)) {
          // 4. update
          int sign = Integer.compare(A[end], A[end - 1]);
          if (sign != 0) {
            signs.offerLast(Integer.compare(A[end], A[end - 1]));
          }
          end++;
        }

        // 5. result
        if (maxLength < signs.size() + 1) {
          maxLength = signs.size() + 1;
        }

        // 6. cleanup
        signs.pollFirst();
      }

      return maxLength;
    }

    private boolean valid(Deque<Integer> signs, int[] A, int idx) {
      return signs.isEmpty() || signs.peekLast() * Integer.compare(A[idx], A[idx - 1]) < 0;
    }
  }
//leetcode submit region end(Prohibit modification and deletion)
}