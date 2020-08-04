//You are given two strings s and t of the same length. You want to change s to
//t. Changing the i-th character of s to i-th character of t costs |s[i] - t[i]| t
//hat is, the absolute difference between the ASCII values of the characters.
//
// You are also given an integer maxCost.
//
// Return the maximum length of a substring of s that can be changed to be the s
//ame as the corresponding substring of twith a cost less than or equal to maxCost
//.
//
// If there is no substring from s that can be changed to its corresponding subs
//tring from t, return 0.
//
//
// Example 1:
//
//
//Input: s = "abcd", t = "bcdf", maxCost = 3
//Output: 3
//Explanation: "abc" of s can change to "bcd". That costs 3, so the maximum leng
//th is 3.
//
// Example 2:
//
//
//Input: s = "abcd", t = "cdef", maxCost = 3
//Output: 1
//Explanation: Each character in s costs 2 to change to charactor in t, so the m
//aximum length is 1.
//
//
// Example 3:
//
//
//Input: s = "abcd", t = "acde", maxCost = 0
//Output: 1
//Explanation: You can't make any change, so the maximum length is 1.
//
//
//
// Constraints:
//
//
// 1 <= s.length, t.length <= 10^5
// 0 <= maxCost <= 10^6
// s and t only contain lower case English letters.
//
// Related Topics Array Sliding Window
// 👍 268 👎 19

package com.mvccclc.java.leetcode.editor.en;

public class GetEqualSubstringsWithinBudget {
  public static void main(String[] args) {
    Solution solution = new GetEqualSubstringsWithinBudget().new Solution();
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int equalSubstring(String s, String t, int maxCost) {

      // 1. validate
      if (s == null || t == null || s.length() != t.length() || s.length() == 0) {
        return 0;
      }

      // 2. initialize
      int cost = 0;
      int maxEqualLength = 0;
      int end = 0;

      // 3. slide
      for (int start = 0; start < s.length(); start++) {
        while (end < s.length() && valid(s, t, cost, maxCost, end)) {
          // 4. update
          cost = update(s, t, cost, end, true);
          end++;
        }

        // 5. result
        if (maxEqualLength < end - start) {
          maxEqualLength = end - start;
        }

        // 6. clean up
        cost = update(s, t, cost, start, false);
      }

      return maxEqualLength;
    }

    private boolean valid(String s, String t, int cost, int maxCost, int idx) {
      int newCost = Math.abs(s.charAt(idx) - t.charAt(idx));
      return cost + newCost <= maxCost;
    }

    private int update(String s, String t, int cost, int idx, boolean add) {
      int newCost = Math.abs(s.charAt(idx) - t.charAt(idx));
      newCost = add ? newCost : -newCost;
      return cost + newCost;
    }
  }
//leetcode submit region end(Prohibit modification and deletion)
}