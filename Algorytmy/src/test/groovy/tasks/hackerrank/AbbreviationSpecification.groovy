package tasks.hackerrank

import spock.lang.Ignore
import spock.lang.Specification

class AbbreviationSpecification extends Specification {
    void 'linear solution'() {
        expect:
        Abbreviation.canTransformLinear('AbcDE', 'ABDE')
        !Abbreviation.canTransformLinear('AfPZN', 'APZNC')
        Abbreviation.canTransformLinear('LIT', 'LIT')
        !Abbreviation.canTransformLinear('KXzQ', 'K')
    }

    @Ignore
    void 'linear failure'() { // proves the linear solution is bad
        expect:
        Abbreviation.canTransformLinear('bBccC', 'BBC')
    }

    void 'test case 3'() {
        expect:
        !Abbreviation.abbreviation('LLZOSYAMQRMBTZXTQMQcKGLR', 'LLZOSYAMBTZXMQKLR')
        !Abbreviation.abbreviation('MGYXKOVSMAHKOLAZZKWXKS', 'MGXKOVSAHKOLZKKDP')
        Abbreviation.abbreviation('VLKHNlpsrlrvfxftslslrrh', 'VLKHN')
        !Abbreviation.abbreviation('OQSVONTNZMDJAVRZAZCVPKh', 'OSVONTNZMDJAVRZAZCVPK')
        Abbreviation.abbreviation('AXbosoh', 'AX')
        !Abbreviation.abbreviation('EYONDOCHNZYYlBZXPGzX', 'EYONDOCHNZYYBZXPGXOG')
        Abbreviation.abbreviation('BJAFXKGENMFUvdsvcptrp', 'BJAFXKGENMFU')
        Abbreviation.abbreviation('UBUFOOSIXXsdtfmeyongkhehq', 'UBUFOOSIXX')
        Abbreviation.abbreviation('PWBIJLCOIAXGJGUXUZOutgic', 'PWBIJLCOIAXGJGUXUZO')
        Abbreviation.abbreviation('EOWZEOHWYOJTBNMcefdsp', 'EOWZEOHWYOJTBNM')
    }
}
