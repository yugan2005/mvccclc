//Given a string s and an integer k.
//
// Return the maximum number of vowel letters in any substring of s with length
//k.
//
// Vowel letters in English are (a, e, i, o, u).
//
//
// Example 1:
//
//
//Input: s = "abciiidef", k = 3
//Output: 3
//Explanation: The substring "iii" contains 3 vowel letters.
//
//
// Example 2:
//
//
//Input: s = "aeiou", k = 2
//Output: 2
//Explanation: Any substring of length 2 contains 2 vowels.
//
//
// Example 3:
//
//
//Input: s = "leetcode", k = 3
//Output: 2
//Explanation: "lee", "eet" and "ode" contain 2 vowels.
//
//
// Example 4:
//
//
//Input: s = "rhythms", k = 4
//Output: 0
//Explanation: We can see that s doesn't have any vowel letters.
//
//
// Example 5:
//
//
//Input: s = "tryhard", k = 4
//Output: 1
//
//
//
// Constraints:
//
//
// 1 <= s.length <= 10^5
// s consists of lowercase English letters.
// 1 <= k <= s.length
// Related Topics String Sliding Window
// 👍 181 👎 10

package com.mvccclc.java.leetcode.editor.en;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class MaximumNumberOfVowelsInASubstringOfGivenLength {
  public static void main(String[] args) {
    Solution solution = new MaximumNumberOfVowelsInASubstringOfGivenLength().new Solution();
    String s = "abciiidef";
    int k = 3;
    System.out.println(solution.maxVowels(s, k));
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int maxVowels(String s, int k) {

      // 1. validate
      if (s == null || s.length() == 0 || s.length() < k) {
        return 0;
      }

      // 2. initialize
      Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
      int count = 0;

      s = s.toLowerCase();

      int idx = 0;
      while (idx < k) {
        if (vowels.contains(s.charAt(idx))) {
          count += 1;
        }
        idx++;
      }
      int maxCount = count;

      while (idx < s.length()) {
        if (vowels.contains(s.charAt(idx))) {
          count += 1;
        }
        if (vowels.contains(s.charAt(idx - k))) {
          count -= 1;
        }
        maxCount = Math.max(maxCount, count);
        idx++;
      }
      return maxCount;
    }
  }
//leetcode submit region end(Prohibit modification and deletion)
}