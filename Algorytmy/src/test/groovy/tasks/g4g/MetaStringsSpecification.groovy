package tasks.g4g

import spock.lang.Specification

class MetaStringsSpecification extends Specification {
    void 'g4g'() {
        expect:
        MetaStrings.areMetaStrings('geeks', 'keegs')
        !MetaStrings.areMetaStrings('rsting', 'string')
        MetaStrings.areMetaStrings('Converse', 'Conserve')

    }
}
