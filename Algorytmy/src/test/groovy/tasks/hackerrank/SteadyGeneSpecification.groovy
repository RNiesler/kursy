package tasks.hackerrank

import spock.lang.Specification

class SteadyGeneSpecification extends Specification {
    void 'with steady gene'() {
        expect:
        SteadyGene.steadyGene('ACTG') == 0
    }
}
