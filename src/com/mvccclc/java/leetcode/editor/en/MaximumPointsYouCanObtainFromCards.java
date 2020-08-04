//There are several cards arranged in a row, and each card has an associated num
//ber of points The points are given in the integer array cardPoints.
//
// In one step, you can take one card from the beginning or from the end of the
//row. You have to take exactly k cards.
//
// Your score is the sum of the points of the cards you have taken.
//
// Given the integer array cardPoints and the integer k, return the maximum scor
//e you can obtain.
//
//
// Example 1:
//
//
//Input: cardPoints = [1,2,3,4,5,6,1], k = 3
//Output: 12
//Explanation: After the first step, your score will always be 1. However, choos
//ing the rightmost card first will maximize your total score. The optimal strateg
//y is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 1
//2.
//
//
// Example 2:
//
//
//Input: cardPoints = [2,2,2], k = 2
//Output: 4
//Explanation: Regardless of which two cards you take, your score will always be
// 4.
//
//
// Example 3:
//
//
//Input: cardPoints = [9,7,7,9,7,7,9], k = 7
//Output: 55
//Explanation: You have to take all the cards. Your score is the sum of points o
//f all cards.
//
//
// Example 4:
//
//
//Input: cardPoints = [1,1000,1], k = 1
//Output: 1
//Explanation: You cannot take the card in the middle. Your best score is 1.
//
//
// Example 5:
//
//
//Input: cardPoints = [1,79,80,1,1,1,200,1], k = 3
//Output: 202
//
//
//
// Constraints:
//
//
// 1 <= cardPoints.length <= 10^5
// 1 <= cardPoints[i] <= 10^4
// 1 <= k <= cardPoints.length
//
// Related Topics Array Dynamic Programming Sliding Window
// 👍 449 👎 17

package com.mvccclc.java.leetcode.editor.en;

public class MaximumPointsYouCanObtainFromCards {
  public static void main(String[] args) {
    Solution solution = new MaximumPointsYouCanObtainFromCards().new Solution();
    int[] cardPoints = new int[]{96, 90, 41, 82, 39, 74, 64, 50, 30};
    int k = 8;
    System.out.println(solution.maxScore(cardPoints, k));
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int maxScore(int[] cardPoints, int k) {
      // 1. validate
      if (cardPoints == null || cardPoints.length == 0 || k == 0 || cardPoints.length < k) {
        return 0;
      }

      // 2. initialize
      int score = 0;
      for (int i = 0; i < k; i++) {
        score += cardPoints[i];
      }
      int maxScore = score;

      for (int nTail = 1; nTail <= k; nTail++) {
        score = update(cardPoints, score, nTail, k);
        if (maxScore < score) {
          maxScore = score;
        }
      }

      return maxScore;
    }

    private int update(int[] cardPoints, int score, int nTail, int k) {
      score -= cardPoints[k - nTail];
      return score + cardPoints[cardPoints.length - nTail];
    }
  }
//leetcode submit region end(Prohibit modification and deletion)
}