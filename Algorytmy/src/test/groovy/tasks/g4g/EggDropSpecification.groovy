package tasks.g4g

import spock.lang.Specification

class EggDropSpecification extends Specification {
    void 'recursive'() {
        expect:
        EggDrop.minEggDrops(2,36) == 8
        EggDrop.minEggDrops(2,100) == 14
    }

    void 'iterative'() {
        expect:
        EggDrop.minEggDropIterative(2,36) == 8
        EggDrop.minEggDropIterative(2,100) == 14
    }
}
