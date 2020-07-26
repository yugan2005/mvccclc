//Given a string s that consists of only uppercase English letters, you can perf
//orm at most k operations on that string.
//
// In one operation, you can choose any character of the string and change it to
// any other uppercase English character.
//
// Find the length of the longest sub-string containing all repeating letters yo
//u can get after performing the above operations.
//
// Note:
//Both the string's length and k will not exceed 104.
//
// Example 1:
//
//
//Input:
//s = "ABAB", k = 2
//
//Output:
//4
//
//Explanation:
//Replace the two 'A's with two 'B's or vice versa.
//
//
//
//
// Example 2:
//
//
//Input:
//s = "AABABBA", k = 1
//
//Output:
//4
//
//Explanation:
//Replace the one 'A' in the middle with 'B' and form "AABBBBA".
//The substring "BBBB" has the longest repeating letters, which is 4.
//
//
//
// Related Topics Two Pointers Sliding Window
// 👍 1431 👎 84

package com.mvccclc.java.leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;


public class LongestRepeatingCharacterReplacement {
  public static void main(String[] args) {
    Solution solution = new LongestRepeatingCharacterReplacement().new Solution();
    String s = "AABABBA";
    int k = 1;
    System.out.println(solution.characterReplacement(s, k));
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int characterReplacement(String s, int k) {

      // 1. validate
      if (s == null || s.length() == 0) {
        return 0;
      }

      // 2. initialization
      int end = 0;
      Map<Character, Integer> charCounter = new HashMap<>();
      int maxLength = 0;

      // 3. sliding
      for (int start = 0; start < s.length(); start++) {
        while (end < s.length() && valid(charCounter, k)) {
          // 4. update
          update(charCounter, s, end, true);
          end++;
        }

        // 5. result
        int currLength;
        if (!valid(charCounter, k)) {
          currLength = end - start - 1;
        } else {
          currLength = end - start;
        }
        if (maxLength < currLength) {
          maxLength = currLength;
        }

        // 6. cleanup
        update(charCounter, s, start, false);
      }

      return maxLength;
    }

    private boolean valid(Map<Character, Integer> charCounter, int k) {
      int mostFreqCount = 0;
      int totalCnt = 0;
      for (int count : charCounter.values()) {
        if (mostFreqCount < count) {
          mostFreqCount = count;
        }
        totalCnt += count;
      }
      return k >= totalCnt - mostFreqCount;
    }

    private void update(Map<Character, Integer> charCounter, String s, int idx, boolean add) {
      char c = s.charAt(idx);
      int count = charCounter.getOrDefault(c, 0);
      count = add ? count + 1 : count - 1;
      charCounter.put(c, count);
    }
  }
//leetcode submit region end(Prohibit modification and deletion)
}