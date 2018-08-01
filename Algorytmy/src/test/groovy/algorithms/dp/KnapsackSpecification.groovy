package algorithms.dp

import spock.lang.Specification
import static algorithms.dp.Knapsack.Item //groovyc complains when using Knapsack.Item[]

class KnapsackSpecification extends Specification {
    void 'g4g'() {
        setup:
        final items = [
                new Item(60, 10),
                new Item(100, 20),
                new Item(120, 30)
        ] as Item[]
        final capacity = 50;

        expect:
        Knapsack.solve(capacity, items) == 220
    }
}
