package tasks.hackerrank


import spock.lang.Specification

class PrimalitySpecification extends Specification {
    void '1 not prime'() {
        expect:
        Primality.primality(1) == "Not prime"
    }

    void 'big'() {
        expect:
        Primality.primality(1000000007) == "Prime"
        Primality.primality(100000003) == "Not prime"
        Primality.primality(1000003) == "Prime"
    }

    void 'test case 0'() {
        expect:
        Primality.primality(12) == "Not prime"
        Primality.primality(5) == "Prime"
        Primality.primality(7) == "Prime"
    }

    void 'test case 1'() {
        expect:
        Primality.primality(31) == "Prime"
        Primality.primality(33) == "Not prime"
    }

    void 'test sqrt'() {
        expect:
        Primality.primality(9) == "Not prime"
    }

    void big2() {
        expect:
        Primality.primality(1000000000) == "Not prime"
        Primality.primality(1000000001) == "Not prime"
        Primality.primality(1000000002) == "Not prime"
        Primality.primality(1000000003) == "Not prime"
        Primality.primality(1000000004) == "Not prime"
        Primality.primality(1000000005) == "Not prime"
        Primality.primality(1000000006) == "Not prime"
        Primality.primality(1000000007) == "Prime"
        Primality.primality(1000000008) == "Not prime"
        Primality.primality(1000000009) == "Prime"
    }
}
