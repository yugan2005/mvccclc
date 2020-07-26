//Given a string S and a string T, find the minimum window in S which will conta
//in all the characters in T in complexity O(n).
//
// Example:
//
//
//Input: S = "ADOBECODEBANC", T = "ABC"
//Output: "BANC"
//
//
// Note:
//
//
// If there is no such window in S that covers all characters in T, return the e
//mpty string "".
// If there is such window, you are guaranteed that there will always be only on
//e unique minimum window in S.
//
// Related Topics Hash Table Two Pointers String Sliding Window
// 👍 4590 👎 317

package com.mvccclc.java.leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;


public class MinimumWindowSubstring {
  public static void main(String[] args) {
    Solution solution = new MinimumWindowSubstring().new Solution();
    String s = "ADOBECODEBANC";
    String t = "ABC";
    System.out.println(solution.minWindow(s, t));
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public String minWindow(String s, String t) {

      String result = "";

      // 1. validation
      if (s == null || s.length() == 0 || t == null || t.length() == 0) {
        return result;
      }

      // 2. initialization
      Map<Character, Integer> charCounterTarget = new HashMap<>();
      Map<Character, Integer> charCounterCurrent = new HashMap<>();
      int minLength = Integer.MAX_VALUE;
      int missedChars = 0;
      for (char c : t.toCharArray()) {
        charCounterTarget.put(c, charCounterTarget.getOrDefault(c, 0) + 1);
        missedChars++;
      }
      int end = 0;

      // 3. Sliding
      for (int start = 0; start < s.length(); start++) {
        while (end < s.length() && !valid(missedChars)) {
          // 4. update
          missedChars = update(s, end, charCounterTarget, charCounterCurrent, missedChars, true);
          end++;
        }

        if (valid(missedChars)) {
          // 5. result
          int currMinLength = end - start;
          if (minLength > currMinLength) {
            minLength = currMinLength;
            result = s.substring(start, end);
          }
        }

        // 6. cleanup
        missedChars = update(s, start, charCounterTarget, charCounterCurrent, missedChars, false);
      }

      return result;
    }

    private int update(String s, int idx, Map<Character, Integer> charCounterTarget,
        Map<Character, Integer> charCounterCurrent, int missedChars, boolean add) {
      char c = s.charAt(idx);
      int prevCount = charCounterCurrent.getOrDefault(c, 0);
      if (prevCount < charCounterTarget.getOrDefault(c, 0) && add) {
        missedChars--;
      } else if (prevCount <= charCounterTarget.getOrDefault(c, 0) && !add) {
        missedChars++;
      }
      int currCount = add ? prevCount + 1 : prevCount - 1;
      charCounterCurrent.put(c, currCount);
      return missedChars;
    }

    private boolean valid(int missedChars) {
      return missedChars == 0;
    }
  }
//leetcode submit region end(Prohibit modification and deletion)
}