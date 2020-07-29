//Given an array A of 0s and 1s, we may change up to K values from 0 to 1.
//
// Return the length of the longest (contiguous) subarray that contains only 1s.
//
//
//
//
//
// Example 1:
//
//
//Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
//Output: 6
//Explanation:
//[1,1,1,0,0,1,1,1,1,1,1]
//Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
//
//
//
// Example 2:
//
//
//Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
//Output: 10
//Explanation:
//[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
//Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
//
//
//
//
// Note:
//
//
// 1 <= A.length <= 20000
// 0 <= K <= A.length
// A[i] is 0 or 1
//
//
// Related Topics Two Pointers Sliding Window
// 👍 1141 👎 24

package com.mvccclc.java.leetcode.editor.en;

public class MaxConsecutiveOnesIii {
  public static void main(String[] args) {
    Solution solution = new MaxConsecutiveOnesIii().new Solution();
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int longestOnes(int[] A, int K) {
      // 1. validate
      if (A == null || A.length == 0) {
        return 0;
      }

      // 2. initialize
      int numFlips = 0;
      int end = 0;
      int maxLength = 0;

      // 3. slide
      for (int start = 0; start < A.length; start++) {
        while (end < A.length && valid(A, end, numFlips, K)) {
          // 4. update
          numFlips = update(A, end, numFlips, true);
          end++;
        }

        // 5. result
        int length = end - start;
        if (maxLength < length) {
          maxLength = length;
        }

        // 6. cleanup
        numFlips = update(A, start, numFlips, false);
      }
      return maxLength;
    }

    private boolean valid(int[] A, int idx, int numFlips, int K) {
      int currFlip = A[idx] == 0 ? 1 : 0;
      return (numFlips + currFlip) <= K;
    }

    private int update(int[] A, int idx, int numFlips, boolean add) {
      int currFlip = A[idx] == 0 ? 1 : 0;
      return add ? numFlips + currFlip : numFlips - currFlip;
    }
  }
//leetcode submit region end(Prohibit modification and deletion)
}