//On an infinite number line, the position of the i-th stone is given by stones[
//i]. Call a stone an endpoint stone if it has the smallest or largest position.
//
// Each turn, you pick up an endpoint stone and move it to an unoccupied positio
//n so that it is no longer an endpoint stone.
//
// In particular, if the stones are at say, stones = [1,2,5], you cannot move th
//e endpoint stone at position 5, since moving it to any position (such as 0, or 3
//) will still keep that stone as an endpoint stone.
//
// The game ends when you cannot make any more moves, ie. the stones are in cons
//ecutive positions.
//
// When the game ends, what is the minimum and maximum number of moves that you
//could have made? Return the answer as an length 2 array: answer = [minimum_moves
//, maximum_moves]
//
//
//
// Example 1:
//
//
//Input: [7,4,9]
//Output: [1,2]
//Explanation:
//We can move 4 -> 8 for one move to finish the game.
//Or, we can move 9 -> 5, 4 -> 6 for two moves to finish the game.
//
//
//
// Example 2:
//
//
//Input: [6,5,4,3,10]
//Output: [2,3]
//We can move 3 -> 8 then 10 -> 7 to finish the game.
//Or, we can move 3 -> 7, 4 -> 8, 5 -> 9 to finish the game.
//Notice we cannot move 10 -> 2 to finish the game, because that would be an ill
//egal move.
//
//
//
// Example 3:
//
//
//Input: [100,101,104,102,103]
//Output: [0,0]
//
//
//
//
//
// Note:
//
//
// 3 <= stones.length <= 10^4
// 1 <= stones[i] <= 10^9
// stones[i] have distinct values.
//
//
//
//
//
//
//
// Related Topics Array Sliding Window
// 👍 173 👎 280

package com.mvccclc.java.leetcode.editor.en;

import java.util.Arrays;


public class MovingStonesUntilConsecutiveIi {
  public static void main(String[] args) {
    Solution solution = new MovingStonesUntilConsecutiveIi().new Solution();
//    int[] stones = new int[]{100, 101, 104, 102, 103};
//    int[] stones = new int[]{7, 4, 9};
//    int[] stones = new int[]{8, 7, 6, 5, 2};
//    int[] stones = new int[]{6, 5, 4, 3, 10};
    int[] stones = new int[]{8, 7, 6, 5, 10};
    System.out.println(Arrays.toString(solution.numMovesStonesII(stones)));
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int[] numMovesStonesII(int[] stones) {
      // the ending condition is always continuous

      // 1. validate
      if (stones == null || stones.length <= 2) {
        return new int[]{0, 0};
      }

      Arrays.sort(stones);

      int totalGaps = 0;
      for (int i = 0; i < stones.length - 1; i++) {
        totalGaps += stones[i + 1] - stones[i] - 1;
      }

      // this edge case need be handled. -> continuous
      if (totalGaps == 0) {
        return new int[]{0, 0};
      }

      // max move

      int headGap = stones[1] - stones[0] - 1;
      int tailGap = stones[stones.length - 1] - stones[stones.length - 2] - 1;
      int minEndGap = Math.min(headGap, tailGap);
      // if minEndGap == 0: one end is continuous. Just rotate from that end: maxMove = totalGaps
      // if minEndGap != 0:
      // 1) one move to make it continuous: 1
      // 2) removed the minEndGap, also remove 1 gap inside: -(minEndGap + 1)
      int maxMove = 1 + totalGaps - (minEndGap + 1);

      // min move -> sliding window of size as numStones, and find the window that contains the most number of stones

      // 2. initialize
      int endIdx = 0;
      int stoneCount = 0;
      int maxStoneContained = -1;
      int minMoveStartIdx = -1;
      int minMoveEndIdx = -1;
      int numStones = stones.length;

      // 3. slide
      for (int startIdx = 0; startIdx < stones.length; startIdx++) {
        while (endIdx < stones.length && valid(stones, startIdx, endIdx, numStones)) {
          // 4. update
          stoneCount = update(stoneCount, true);
          endIdx++;
        }

        // 5. result
        // out of the while loop either because invalid, endIdx need minus 1, or endIdx outOfBound, need minus 1 too

        if (maxStoneContained < stoneCount) {
          maxStoneContained = stoneCount;
          minMoveStartIdx = startIdx;
          minMoveEndIdx = endIdx - 1;
        }

        // 6. cleanup
        stoneCount = update(stoneCount, false);
      }

      // minMove should be based on minMoveStartIdx to minMoveEndIdx
      int minMove = numStones - maxStoneContained;
      // the only corner cases
      // [2, 3, 4, 7] -> can not get [2, 3, 4, 5], only be able to get [3, 4, 5, 6]
      // [2, 5, 6, 7] -> can not get [4, 5, 6, 7], only be able to get [3, 4, 5, 6]
      if (minMove == 1 && // 1. only one stone outside of the window
          // 2. inside the window it is continuous
          (stones[minMoveEndIdx] - stones[minMoveStartIdx] + 1 == maxStoneContained) &&
          // 3. the stone outside of the window is two position away
          (minMoveStartIdx == 1 ? stones[1] - stones[0] : stones[stones.length - 1] - stones[stones.length - 2]) >= 3) {
        minMove += 1;
      }

      return new int[]{minMove, maxMove};
    }

    private boolean valid(int[] stones, int start, int end, int numStones) {
      // the position of stones[start] to stones[end] still inside the window of numStones size
      return stones[end] - stones[start] < numStones;
    }

    private int update(int stoneCount, boolean add) {
      return add ? stoneCount + 1 : stoneCount - 1;
    }
  }
//leetcode submit region end(Prohibit modification and deletion)
}