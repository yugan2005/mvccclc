# mvccclc

## Sliding Windows
### template
1. validation => edge cases
2. initialization => choose data structure for memorization
3. sliding => `for` loop for `start`, `while` loop for `end`
4. update => update memory while slide `end`
5. result => update result as slide `end` complete or out-of-bounds
6. cleanup => update memory while slide start

### problems
#### 76. `MinimumWindowSubstring.java`
* 5 result stage: as `end` sliding complete, `end` idx is one over the idx that first make the substring valid: matching what `length` and `substring` method expect.

#### 3. `LongestSubstringWithoutRepeatingCharacters.java`
* 5 result stage: should not and "could not" use the `if(!valid(charSet, s, end))` check. Because if the sliding complete, no matter due to slide `end` complete or out-of-bounds, we always should get and update the result.

#### 239. `SlidingWindowMaximum.java`
* not really a sliding window problem, but data structure problem of using Heap
* UPDATED: used monotonic queue data structure. referred to this solution: [youtube](https://youtu.be/2SXqBsTR6a8)

#### 424. `LongestRepeatingCharacterReplacement.java`
* 5 result stage: need distinguish how the slide of `end` completes. If it is due to `valid`, the `end` has moved an extra one position, on the other hand, if it is due to out-of-bounds, it does not move extra one position.

#### 480. `SlidingWindowMedian`
* like 239 not really a sliding window problem, but data structure problem of using Heap.
* could not use the monotonic queue data structure anymore
* For median, one trick is that for length of `k` no matter `k` is even or odd `median = arr[k / 2] + arr[(k -1) / 2]`

#### 567. `PermutationInString`
* typical sliding window template
* 5 result stage: Although don't have to do the `valid` check, it does not hurt to follow this pattern.

#### 978. `LongestTurbulentSubarray`
* 5. result stage, could not do `!vaid()` check. - Should not (finish transverse or !valid, all need update result), and could not (will have IndexOutBound issue).
* 4. update stage, for equal case `sign == 0`, should not put into queue (otherwise, for `[9, 9]` will have issue)
* 6. cleanup, `pollFirst()` is okay. Even for empty queue, this operation has no issue. 

#### 992. `SubarraysWithKDifferentIntegers`
* Very good sliding window problem
* Need change the exact K into (at most K) - (at most K-1). Because at Most K fit sliding window paradigm.
* 5 result stage: result not only add 1, but add end - start - 1 / end - start, so that it covers all cases with right boundary at end - 1 / end;
* 5 result stage: Need handle `valid` or because of finish transverse DIFFERENTLY (as 424. `LongestRepeatingCharacterReplacement.java`)

#### 995. `MinimumNumberOfKConsecutiveBitFlips`
* Not a typical sliding window problem but have similar essential idea: Transverse array once, maintaining a data structure and update while sliding.
* 1st submission, I used a `Dequeue` and brute force flipped the bits. It is right but TLE.
* Trick is to save the cumsum of the # of the flip for every idx while sliding
* The # of the flip at position `i` is then: `accumulatedFlip` + `currFlip` - `overCountedFlip`
    * `accumulatedFlip`: equivalent as every flip will not only flip `K` bits but flip all the right side bits
    * `overCountedFlip`: minus the accumulated flips that is `K` bits ahead of the current position, because that is over counted in the above step
    * `currFlip`: whether the current bit needs be flipped or not.
* Also, need special handling when the remaining length is less than `K`, i.e. cannot flip anymore.
    




