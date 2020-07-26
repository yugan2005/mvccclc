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


