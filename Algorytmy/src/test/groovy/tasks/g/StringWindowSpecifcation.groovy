package tasks.g

import spock.lang.Specification
import tasks.g.StringWindow.Window

class StringWindowSpecifcation extends Specification {
    String getWindowString(String str, Window window) {
        return str.substring(window.start, window.end)
    }

    void 'leet'() {
        setup:
        final s = 'ADOBECODEBANC'
        final t = 'ABC'
        when:
        final windowOpt = StringWindow.findMinimum(t, s)
        then:
        windowOpt.isPresent()
        getWindowString(s, windowOpt.get()) == 'BANC'
    }

    void 'g4g'() {
        setup:
        final s = 'this is a test string'
        final t = 'tist'
        when:
        final windowOpt = StringWindow.findMinimum(t, s)
        then:
        windowOpt.isPresent()
        getWindowString(s, windowOpt.get()) == 't stri'
    }

    void 'g4g 2'() {
        setup:
        final s = 'geeksforgeeks'
        final t = 'ork'
        when:
        final windowOpt = StringWindow.findMinimum(t, s)
        then:
        windowOpt.isPresent()
        getWindowString(s, windowOpt.get()) == 'ksfor'
    }

    void 'inorder'() {
        setup:
        final s = 'afgbeacbcfdbfsc'
        final t = 'abc'
        expect:
        StringWindow.findMinLengthInOrder(t, s) == 4
    }

    void 'inorder linear'() {
        setup:
        final s = 'afgbeacbcfdbfsc'
        final t = 'abc'
        when:
        final windowOpt = StringWindow.findMinimumInOrder(t, s)
        then:
        windowOpt.isPresent()
        getWindowString(s, windowOpt.get()) == 'acbc'
    }
}
