//Given an array points containing the coordinates of points on a 2D plane, sort
//ed by the x-values, where points[i] = [xi, yi] such that xi < xj for all 1 <= i
//< j <= points.length. You are also given an integer k.
//
// Find the maximum value of the equation yi + yj + |xi - xj| where |xi - xj| <=
// k and 1 <= i < j <= points.length. It is guaranteed that there exists at least
//one pair of points that satisfy the constraint |xi - xj| <= k.
//
//
// Example 1:
//
//
//Input: points = [[1,3],[2,0],[5,10],[6,-10]], k = 1
//Output: 4
//Explanation: The first two points satisfy the condition |xi - xj| <= 1 and if
//we calculate the equation we get 3 + 0 + |1 - 2| = 4. Third and fourth points al
//so satisfy the condition and give a value of 10 + -10 + |5 - 6| = 1.
//No other pairs satisfy the condition, so we return the max of 4 and 1.
//
// Example 2:
//
//
//Input: points = [[0,0],[3,0],[9,2]], k = 3
//Output: 3
//Explanation: Only the first two points have an absolute difference of 3 or les
//s in the x-values, and give the value of 0 + 0 + |0 - 3| = 3.
//
//
//
// Constraints:
//
//
// 2 <= points.length <= 10^5
// points[i].length == 2
// -10^8 <= points[i][0], points[i][1] <= 10^8
// 0 <= k <= 2 * 10^8
// points[i][0] < points[j][0] for all 1 <= i < j <= points.length
// xi form a strictly increasing sequence.
//
// Related Topics Array Sliding Window
// 👍 147 👎 3

package com.mvccclc.java.leetcode.editor.en;

import java.util.ArrayDeque;
import java.util.Deque;


public class MaxValueOfEquation {
  public static void main(String[] args) {
    Solution solution = new MaxValueOfEquation().new Solution();
    int[][] points = new int[4][2];
    points[0] = new int[]{1, 3};
    points[1] = new int[]{2, 0};
    points[2] = new int[]{5, 10};
    points[3] = new int[]{6, -10};
    int k = 1;
    System.out.println(solution.findMaxValueOfEquation(points, k));
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {

//    /**
//     * Method 1: Use priority queue
//     */
//    public int findMaxValueOfEquation(int[][] points, int k) {
//      // 1. validate - no need
//
//      // 2. initialize
//      int maxValue = Integer.MIN_VALUE;
//      int start = 0;
//      PriorityQueue<Point> pointQueue = new PriorityQueue<>(Comparator.reverseOrder());
//
//      // 3. slide
//      for (int end = 0; end < points.length; end++) {
//
//        while (start < end && !valid(points, start, end, k)) {
//          // 4. update
//          update(pointQueue, points, start, false);
//          start++;
//        }
//
//        // 5. result
//        if (!pointQueue.isEmpty()) {
//          maxValue = Math.max(maxValue, points[end][0] + points[end][1] + pointQueue.peek().getValue());
//        }
//
//        // 6. clean up
//        update(pointQueue, points, end, true);
//      }
//
//      return maxValue;
//    }
//
//    private boolean valid(int[][] points, int start, int end, int k) {
//      return points[end][0] - points[start][0] <= k;
//    }
//
//    private void update(PriorityQueue<Point> pointQueue, int[][] points, int idx, boolean add) {
//      if (add) {
//        pointQueue.offer(new Point(points[idx][0], points[idx][1]));
//        return;
//      }
//      int x = points[idx][0];
//      while (!pointQueue.isEmpty() && pointQueue.peek().getX() <= x) {
//        pointQueue.poll();
//      }
//    }
//
//    private class Point implements Comparable<Point> {
//      private final int x;
//      private final int value;
//
//      Point(int x, int y) {
//        this.x = x;
//        value = y - x;
//      }
//
//      public int getX() {
//        return x;
//      }
//
//      public int getValue() {
//        return value;
//      }
//
//      @Override
//      public int compareTo(Point that) {
//        return this.value - that.value;
//      }
//    }

    /**
     * Method 2 . Monotonic queue
     */
    public int findMaxValueOfEquation(int[][] points, int k) {
      // 1. validate - no need

      // 2. initialize
      int maxValue = Integer.MIN_VALUE;
      int start = 0;
      Deque<Integer> descendingQueue = new ArrayDeque<>();

      // 3. slide
      for (int end = 0; end < points.length; end++) {
        while (start < end && !valid(points, start, end, k)) {
          // 4. update
          update(descendingQueue, points, start, false);
          start++;
        }

        // 5. result
        if (!descendingQueue.isEmpty()) {
          maxValue =
              Math.max(maxValue, points[end][0] + points[end][1] + getValue(points, descendingQueue.peekFirst()));
        }

        // 6. clean up
        update(descendingQueue, points, end, true);
      }

      return maxValue;
    }

    private boolean valid(int[][] points, int start, int end, int k) {
      return points[end][0] - points[start][0] <= k;
    }

    private void update(Deque<Integer> descendingQueue, int[][] points, int idx, boolean add) {
      if (add) {
        while (!descendingQueue.isEmpty() && getValue(points, descendingQueue.peekLast()) < getValue(points, idx)) {
          descendingQueue.pollLast();
        }
        descendingQueue.offerLast(idx);
        return;
      }
      if (!descendingQueue.isEmpty() && descendingQueue.peekFirst() == idx) {
        descendingQueue.pollFirst();
      }
    }

    private int getValue(int[][] points, int idx) {
      return points[idx][1] - points[idx][0];
    }
  }
//leetcode submit region end(Prohibit modification and deletion)
}