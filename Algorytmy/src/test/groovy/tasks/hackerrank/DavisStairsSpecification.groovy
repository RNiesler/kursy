package tasks.hackerrank;

import spock.lang.Specification;

public class DavisStairsSpecification extends Specification {
    void 'test big with modulo'() {
        expect:
        DavisStairs.stepPerms(35) == 1132436852
        DavisStairs.stepPerms(30) == 53798080
        DavisStairs.stepPerms(33) == 334745777
        DavisStairs.stepPerms(36) == 2082876103
        DavisStairs.stepPerms(20) == 121415
    }
}
