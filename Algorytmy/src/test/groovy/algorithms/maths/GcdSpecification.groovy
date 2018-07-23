package algorithms.maths;

import spock.lang.Specification;

class GcdSpecification extends Specification {
    void 'recursive test'() {
        expect:
        Gcd.euclideanRecursive(105, 252) == 21
        Gcd.euclideanRecursive(49, 28) == 7
        Gcd.euclideanRecursive(3213, 1386) == 63
        Gcd.euclideanRecursive(1071, 462) == 21
    }


    void 'iterative test'() {
        expect:
        Gcd.euclideanRecursive(105, 252) == 21
        Gcd.euclideanRecursive(49, 28) == 7
        Gcd.euclideanRecursive(3213, 1386) == 63
        Gcd.euclideanRecursive(1071, 462) == 21
    }


    void 'extended test'() {
        when:
        def result1 = Gcd.extendedEuclidean(105, 252)
        then:
        result1.gcd == 21
        result1.gcd == result1.eA * 105 + result1.eB * 252

        when:
        def result2 = Gcd.extendedEuclidean(49, 28)
        then:
        result2.gcd == 7
        result2.gcd == result2.eA * 49 + result2.eB * 28

        when:
        def result3 = Gcd.extendedEuclidean(3213, 1386)
        then:
        result3.gcd == 63
        result3.gcd == result3.eA * 3213 + result3.eB * 1386

        when:
        def result4 = Gcd.extendedEuclidean(1071, 462)
        then:
        result4.gcd == 21
        result4.gcd == result4.eA * 1071 + result4.eB * 462
    }
}
