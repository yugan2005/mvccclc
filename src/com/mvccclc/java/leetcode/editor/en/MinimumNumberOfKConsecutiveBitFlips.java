//In an array A containing only 0s and 1s, a K-bit flip consists of choosing a (
//contiguous) subarray of length K and simultaneously changing every 0 in the suba
//rray to 1, and every 1 in the subarray to 0.
//
// Return the minimum number of K-bit flips required so that there is no 0 in th
//e array. If it is not possible, return -1.
//
//
//
// Example 1:
//
//
//Input: A = [0,1,0], K = 1
//Output: 2
//Explanation: Flip A[0], then flip A[2].
//
//
//
// Example 2:
//
//
//Input: A = [1,1,0], K = 2
//Output: -1
//Explanation: No matter how we flip subarrays of size 2, we can't make the arra
//y become [1,1,1].
//
//
//
// Example 3:
//
//
//Input: A = [0,0,0,1,0,1,1,0], K = 3
//Output: 3
//Explanation:
//Flip A[0],A[1],A[2]: A becomes [1,1,1,1,0,1,1,0]
//Flip A[4],A[5],A[6]: A becomes [1,1,1,1,1,0,0,0]
//Flip A[5],A[6],A[7]: A becomes [1,1,1,1,1,1,1,1]
//
//
//
//
//
//
// Note:
//
//
// 1 <= A.length <= 30000
// 1 <= K <= A.length
// Related Topics Greedy Sliding Window
// 👍 317 👎 30


package com.mvccclc.java.leetcode.editor.en;

public class MinimumNumberOfKConsecutiveBitFlips {
    public static void main(String[] args) {
        Solution solution = new MinimumNumberOfKConsecutiveBitFlips().new Solution();
        int[] A = new int[]{0, 0, 0, 1, 0, 1, 1, 0};
        int K = 3;
//        int[] A = new int[]{0, 1, 0};
//        int K = 1;
//        int[] A = new int[]{1, 1, 0};
//        int K = 2;
        System.out.println(solution.minKBitFlips(A, K));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minKBitFlips(int[] A, int K) {

            // 1. validate
            if (A == null || A.length == 0) {
                return 0;
            }

            if (A.length < K) {
                for (int value : A) {
                    if (value == 0) {
                        return -1;
                    }
                }
                return 0;
            }

            int[] flipCount = new int[A.length];
            int accumulatedFlip = 0;

            for (int i = 0; i <= A.length - K; i++) {
                int overCountedFlip = i >= K ? flipCount[i - K] : 0;
                int currFlip = A[i] == 0 ? 1 : 0;
                currFlip = (accumulatedFlip + currFlip - overCountedFlip) % 2;
                accumulatedFlip += currFlip;
                flipCount[i] = accumulatedFlip;
            }

            for (int i = A.length - K + 1; i < A.length; i++) {
                int overCountedFlip = i >= K ? flipCount[i - K] : 0;
                int currFlip = A[i] == 0 ? 1 : 0;
                if ((accumulatedFlip + currFlip - overCountedFlip) % 2 != 0) {
                    return -1;
                }
            }
            return accumulatedFlip;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}