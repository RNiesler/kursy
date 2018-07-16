package algorithms.divideandconquer

import spock.lang.Specification

class SelectionRankSpecification extends Specification {
    void 'test when first'() {
        def testList = [1, 2, 3, 4, 5, 6, 7, 8]
        Collections.shuffle(testList)
        expect:
        1 == SelectionRank.selectKth(testList as Integer[], 0).get()
    }

    void 'test when last'() {
        def testList = [1, 2, 3, 4, 5, 6, 7, 8]
        Collections.shuffle(testList)
        expect:
        8 == SelectionRank.selectKth(testList as Integer[], 7).get()
    }

    void 'test when middle'() {
        def testList = [1, 2, 3, 4, 5, 6, 7, 8]
        Collections.shuffle(testList)
        expect:
        4 == SelectionRank.selectKth(testList as Integer[], 3).get()
    }

    void 'test when outside'() {
        def testList = [1, 2, 3, 4, 5, 6, 7, 8]
        Collections.shuffle(testList)
        expect:
        !SelectionRank.selectKth(testList as Integer[], 8).present
    }
}
