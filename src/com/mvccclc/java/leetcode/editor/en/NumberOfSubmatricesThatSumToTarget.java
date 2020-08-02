//Given a matrix, and a target, return the number of non-empty submatrices that
//sum to target.
//
// A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x
//<= x2 and y1 <= y <= y2.
//
// Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if th
//ey have some coordinate that is different: for example, if x1 != x1'.
//
//
//
// Example 1:
//
//
//Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
//Output: 4
//Explanation: The four 1x1 submatrices that only contain 0.
//
//
//
// Example 2:
//
//
//Input: matrix = [[1,-1],[-1,1]], target = 0
//Output: 5
//Explanation: The two 1x2 submatrices, plus the two 2x1 submatrices, plus the 2
//x2 submatrix.
//
//
//
//
//
// Note:
//
//
// 1 <= matrix.length <= 300
// 1 <= matrix[0].length <= 300
// -1000 <= matrix[i] <= 1000
// -10^8 <= target <= 10^8
//
// Related Topics Array Dynamic Programming Sliding Window
// 👍 519 👎 27

package com.mvccclc.java.leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;


public class NumberOfSubmatricesThatSumToTarget {
  public static void main(String[] args) {
    Solution solution = new NumberOfSubmatricesThatSumToTarget().new Solution();
//    int[][] matrix = new int[][]{{1, -1}, {-1, 1}};
//    int target = 0;
    int[][] matrix = new int[][]{{0, 1, 0}, {1, 1, 1}, {0, 1, 0}};
    int target = 0;
    System.out.println(solution.numSubmatrixSumTarget(matrix, target));
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
      // 1. validate
      if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
        return 0;
      }

      // pre-calculate colCumSum[i][j]: row#0~i (inclusive), col#j O(n^2)
      int[][] colCumSum = new int[matrix.length][matrix[0].length];
      for (int j = 0; j < matrix[0].length; j++) {
        int cumSum = 0;
        for (int i = 0; i < matrix.length; i++) {
          cumSum += matrix[i][j];
          colCumSum[i][j] = cumSum;
        }
      }

      int result = 0;

      for (int topRow = 0; topRow < matrix.length; topRow++) { // O(n^3)
        for (int bottomRow = topRow; bottomRow < matrix.length; bottomRow++) {
          int[] squashedCol = new int[matrix[0].length];
          for (int col = 0; col < squashedCol.length; col++) {
            squashedCol[col] = colCumSum[bottomRow][col] - (topRow == 0 ? 0 : colCumSum[topRow - 1][col]);
          }
          // now it is 1-D array problem
          Map<Integer, Integer> cumSumCounterMap = new HashMap<>();
          cumSumCounterMap.put(0, 1);
          int cumSum = 0;
          for (int i : squashedCol) {
            cumSum += i;
            int matchingTarget = cumSum - target;
            result += cumSumCounterMap.getOrDefault(matchingTarget, 0);
            cumSumCounterMap.put(cumSum, cumSumCounterMap.getOrDefault(cumSum, 0) + 1);
          }
        }
      }
      return result;
    }
  }
//leetcode submit region end(Prohibit modification and deletion)
}