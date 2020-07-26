//Given a string, find the length of the longest substring without repeating cha
//racters.
//
//
// Example 1:
//
//
//Input: "abcabcbb"
//Output: 3
//Explanation: The answer is "abc", with the length of 3.
//
//
//
// Example 2:
//
//
//Input: "bbbbb"
//Output: 1
//Explanation: The answer is "b", with the length of 1.
//
//
//
// Example 3:
//
//
//Input: "pwwkew"
//Output: 3
//Explanation: The answer is "wke", with the length of 3.
//             Note that the answer must be a substring, "pwke" is a subsequence
// and not a substring.
//
//
//
//
// Related Topics Hash Table Two Pointers String Sliding Window
// 👍 9685 👎 584

package com.mvccclc.java.leetcode.editor.en;

import java.util.HashSet;
import java.util.Set;


public class LongestSubstringWithoutRepeatingCharacters {
  public static void main(String[] args) {
    Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
    String s = "abcabcbb";
    System.out.println(solution.lengthOfLongestSubstring(s));
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int lengthOfLongestSubstring(String s) {
      // 1. validate
      if (s == null || s.length() == 0) {
        return 0;
      }

      // 2. initialization
      Set<Character> charSet = new HashSet<>();
      int end = 0;
      int maxLength = 0;

      // 3. sliding
      for (int start = 0; start < s.length(); start++) {
        while (end < s.length() && valid(charSet, s, end)) {
          // 4. update
          update(charSet, s, end, true);
          end++;
        }

        // 5. result
        int currLength = end - start;
        if (maxLength < currLength) {
          maxLength = currLength;
        }

        // 6. cleanup
        update(charSet, s, start, false);
      }
      return maxLength;
    }

    private void update(Set<Character> charSet, String s, int idx, boolean add) {
      if (add) {
        charSet.add(s.charAt(idx));
      } else {
        charSet.remove(s.charAt(idx));
      }
    }

    private boolean valid(Set<Character> charSet, String s, int idx) {
      return !charSet.contains(s.charAt(idx));
    }
  }
//leetcode submit region end(Prohibit modification and deletion)
}