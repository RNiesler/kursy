package tasks.g4g

import spock.lang.Specification

class MinimumSquaresSpecification extends Specification {
    void 'g4g'() {
        expect:
        MinimumSquares.minimumSquares(36, 30) == 5
        MinimumSquares.minimumSquares(4,5) == 5
    }

    void 'iterative'() {
        expect:
        MinimumSquares.minimumSquaresIterative(36, 30) == 5
        MinimumSquares.minimumSquaresIterative(4,5) == 5
    }
}
