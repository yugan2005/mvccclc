//Median is the middle value in an ordered integer list. If the size of the list
// is even, there is no middle value. So the median is the mean of the two middle
//value.
//Examples:
//
// [2,3,4] , the median is 3
//
// [2,3], the median is (2 + 3) / 2 = 2.5
//
// Given an array nums, there is a sliding window of size k which is moving from
// the very left of the array to the very right. You can only see the k numbers in
// the window. Each time the sliding window moves right by one position. Your job
//is to output the median array for each window in the original array.
//
// For example,
//Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
//
//
//Window position                Median
//---------------               -----
//[1  3  -1] -3  5  3  6  7       1
// 1 [3  -1  -3] 5  3  6  7       -1
// 1  3 [-1  -3  5] 3  6  7       -1
// 1  3  -1 [-3  5  3] 6  7       3
// 1  3  -1  -3 [5  3  6] 7       5
// 1  3  -1  -3  5 [3  6  7]      6
//
//
// Therefore, return the median sliding window as [1,-1,-1,3,5,6].
//
// Note:
//You may assume k is always valid, ie: k is always smaller than input array's s
//ize for non-empty array.
//Answers within 10^-5 of the actual value will be accepted as correct.
// Related Topics Sliding Window
// 👍 918 👎 84

package com.mvccclc.java.leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class SlidingWindowMedian {
  public static void main(String[] args) {
    Solution solution = new SlidingWindowMedian().new Solution();
//    int[] nums = new int[] {1,3,-1,-3,5,3,6,7};
//    int k = 3;

//    int[] nums = new int[] {1,2};
//    int k = 1;

    int[] nums = new int[]{2147483647, 2147483647};
    int k = 2;
    System.out.println(Arrays.toString(solution.medianSlidingWindow(nums, k)));
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
      if (nums == null || nums.length == 0) {
        return null;
      }
      SortedCounterMap upper = new SortedCounterMap(null);
      SortedCounterMap lower = new SortedCounterMap(Comparator.reverseOrder());

      List<Integer> firstK = new ArrayList<>();
      for (int i = 0; i < k; i++) {
        firstK.add(nums[i]);
      }
      Collections.sort(firstK);
      for (int i = 0; i < k; i++) {
        if (i < k / 2) {
          lower.offer(firstK.get(i));
        } else {
          upper.offer(firstK.get(i));
        }
      }
      double[] medians = new double[nums.length - k + 1];
      medians[0] = (k % 2 == 0) ? (upper.peek() / 2.0 + lower.peek() / 2.0) : (upper.peek());

      for (int i = k; i < nums.length; i++) {
        // add first
        if (nums[i] < upper.peek()) {
          lower.offer(nums[i]);
        } else {
          upper.offer(nums[i]);
        }
        // remove later --> won't have empty map NoSuchElementException
        if (nums[i - k] < upper.peek()) {
          lower.remove(nums[i - k]);
        } else {
          upper.remove(nums[i - k]);
        }
        while (lower.size < k / 2) {
          lower.offer(upper.poll());
        }
        while (lower.size > k / 2) {
          upper.offer(lower.poll());
        }
        // avoid overflow
        medians[i - k + 1] = (k % 2 == 0) ? (upper.peek() / 2.0 + lower.peek() / 2.0) : (upper.peek());
      }
      return medians;
    }

    class SortedCounterMap {
      private TreeMap<Integer, Integer> counterMap;
      private int size;

      SortedCounterMap(Comparator<Integer> comparator) {
        if (comparator != null) {
          counterMap = new TreeMap<>(comparator);
        } else {
          counterMap = new TreeMap<>();
        }
        size = 0;
      }

      void offer(int i) {
        int count = counterMap.getOrDefault(i, 0);
        count += 1;
        counterMap.put(i, count);
        size += 1;
      }

      void remove(int i) {
        int count = counterMap.get(i);
        count -= 1;
        if (count == 0) {
          counterMap.remove(i);
        } else {
          counterMap.put(i, count);
        }
        size -= 1;
      }

      int peek() {
        return counterMap.firstKey();
      }

      int poll() {
        Map.Entry<Integer, Integer> firstEntry = counterMap.firstEntry();

        int count = firstEntry.getValue();
        int num = firstEntry.getKey();
        count -= 1;
        if (count == 0) {
          counterMap.remove(num);
        } else {
          counterMap.put(num, count);
        }
        size -= 1;
        return num;
      }

      int size() {
        return size;
      }
    }
  }
//leetcode submit region end(Prohibit modification and deletion)
}