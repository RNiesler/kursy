package tasks.g4g

import spock.lang.Specification

class MaxAsSpecification extends Specification {
    void 'g4g'() {
        expect:
        MaxAs.maxAs(1) == 1
        MaxAs.maxAs(2) == 2
        MaxAs.maxAs(3) == 3
        MaxAs.maxAs(4) == 4
        MaxAs.maxAs(5) == 5
        MaxAs.maxAs(6) == 6
        MaxAs.maxAs(7) == 9
        MaxAs.maxAs(8) == 12
        MaxAs.maxAs(9) == 16
        MaxAs.maxAs(10) == 20
        MaxAs.maxAs(11) == 27
        MaxAs.maxAs(12) == 36
        MaxAs.maxAs(13) == 48
        MaxAs.maxAs(14) == 64
        MaxAs.maxAs(15) == 81
        MaxAs.maxAs(16) == 108
        MaxAs.maxAs(17) == 144
        MaxAs.maxAs(18) == 192
        MaxAs.maxAs(19) == 256
        MaxAs.maxAs(20) == 324
    }


    void 'g4g optimal'() {
        expect:
        MaxAs.maxAsOptimal(1) == 1
        MaxAs.maxAsOptimal(2) == 2
        MaxAs.maxAsOptimal(3) == 3
        MaxAs.maxAsOptimal(4) == 4
        MaxAs.maxAsOptimal(5) == 5
        MaxAs.maxAsOptimal(6) == 6
        MaxAs.maxAsOptimal(7) == 9
        MaxAs.maxAsOptimal(8) == 12
        MaxAs.maxAsOptimal(9) == 16
        MaxAs.maxAsOptimal(10) == 20
        MaxAs.maxAsOptimal(11) == 27
        MaxAs.maxAsOptimal(12) == 36
        MaxAs.maxAsOptimal(13) == 48
        MaxAs.maxAsOptimal(14) == 64
        MaxAs.maxAsOptimal(15) == 81
        MaxAs.maxAsOptimal(16) == 108
        MaxAs.maxAsOptimal(17) == 144
        MaxAs.maxAsOptimal(18) == 192
        MaxAs.maxAsOptimal(19) == 256
        MaxAs.maxAsOptimal(20) == 324
    }
}
