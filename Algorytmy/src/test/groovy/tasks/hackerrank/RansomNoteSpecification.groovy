package tasks.hackerrank

import spock.lang.Specification

class RansomNoteSpecification extends Specification {
    void 'sample 0'() {
        setup:
        final magazine = ['give', 'me', 'one', 'grand', 'today', 'night'] as String[]
        final note = ['give', 'one', 'grand', 'today'] as String[]
        expect:
        RansomNote.checkMagazine(magazine, note)
    }

    void 'sample 1'() {
        setup:
        final magazine = ['two', 'times', 'three', 'is', 'not', 'four'] as String[]
        final note = ['two', 'times', 'two', 'is', 'four'] as String[]
        expect:
        !RansomNote.checkMagazine(magazine, note)
    }

    void 'sample 2'() {
        setup:
        final magazine = ['ive', 'got', 'a', 'lovely', 'bunch', 'of', 'coconuts'] as String[]
        final note = ['ive', 'got', 'some', 'coconuts'] as String[]
        expect:
        !RansomNote.checkMagazine(magazine, note)
    }
}
