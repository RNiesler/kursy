package tasks.hackerrank

import spock.lang.Specification

class CommonChildSpecification extends Specification {
    void 'test case 14 iterative'() {
        expect:
        CommonChild.commonChildIterative('SHINCHAN','NOHARAAA') == 3
    }
}
