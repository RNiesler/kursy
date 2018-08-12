package tasks.g4g

import spock.lang.Specification

class IntegerStreamMedianSpecification extends Specification{
    void 'g4g'() {
        setup:
        final streamMedian = new IntegerStreamMedian();

        when:
        streamMedian.add(5)
        then:
        streamMedian.median == 5

        when:
        streamMedian.add(15)
        then:
        streamMedian.median == 10
    }
}
