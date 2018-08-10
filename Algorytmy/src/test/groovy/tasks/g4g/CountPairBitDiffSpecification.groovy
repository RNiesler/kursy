package tasks.g4g

import spock.lang.Specification

//Given an integer array of n integers, find sum of bit differences in all pairs that can be formed from array elements.
// Bit difference of a pair (x, y) is count of different bits at same positions in binary representations of x and y.
//For example, bit difference for 2 and 7 is 2. Binary representation of 2 is 010 and 7 is 111 ( first and last bits differ in two numbers).
class CountPairBitDiffSpecification extends Specification {
    void 'g4g'() {
        expect:
        CountPairBitDiff.countBitDiff([1, 2] as int[]) == 2
        CountPairBitDiff.countBitDiff([1, 3, 5] as int[]) == 4
    }

}
