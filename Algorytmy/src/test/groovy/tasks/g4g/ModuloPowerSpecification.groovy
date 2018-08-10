package tasks.g4g

import spock.lang.Specification

class ModuloPowerSpecification extends Specification {
    void 'g4g'() {
        expect:
        ModuloPower.moduloPower(2,3,5) == 3
        ModuloPower.moduloPower(2,5,13) == 6
    }
}
