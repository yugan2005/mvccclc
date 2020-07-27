//Given two strings s1 and s2, write a function to return true if s2 contains th
//e permutation of s1. In other words, one of the first string's permutations is t
//he substring of the second string.
//
//
//
// Example 1:
//
//
//Input: s1 = "ab" s2 = "eidbaooo"
//Output: True
//Explanation: s2 contains one permutation of s1 ("ba").
//
//
// Example 2:
//
//
//Input:s1= "ab" s2 = "eidboaoo"
//Output: False
//
//
//
// Constraints:
//
//
// The input strings only contain lower case letters.
// The length of both given strings is in range [1, 10,000].
//
// Related Topics Two Pointers Sliding Window
// 👍 1603 👎 60

package com.mvccclc.java.leetcode.editor.en;

public class PermutationInString {
  public static void main(String[] args) {
    Solution solution = new PermutationInString().new Solution();
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public boolean checkInclusion(String s1, String s2) {
      // 1. validate
      if (s1 == null || s2 == null || s2.length() < s1.length()) {
        return false;
      }
      int s1Length = s1.length();
      if (s1Length == 0) {
        return true;
      }

      // 2. initialization
      int[] counter = new int[26];
      for (char c : s1.toCharArray()) {
        counter[c - 'a'] -= 1;
      }
      int end = 0;

      // 3. sliding
      for (int start = 0; start < s2.length(); start++) {
        while (end < s2.length() && !valid(start, end, s1Length)) {
          // 4. update
          update(counter, s2, end, true);
          end++;
        }

        // 5. calculate
        if (valid(start, end, s1Length)) {
          boolean match = true;
          for (int count : counter) {
            if (count != 0) {
              match = false;
              break;
            }
          }
          if (match) {
            return true;
          }
        }

        // 6. cleanup
        update(counter, s2, start, false);
      }
      return false;
    }

    private boolean valid(int start, int end, int s1Length) {
      return end - start == s1Length;
    }

    private void update(int[] counter, String s, int idx, boolean add) {
      int arrayIdx = s.charAt(idx) - 'a';
      counter[arrayIdx] = add ? counter[arrayIdx] + 1 : counter[arrayIdx] - 1;
    }
  }
//leetcode submit region end(Prohibit modification and deletion)
}