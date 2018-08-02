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

    void 'g4g bottom up'() {
        setup:
        final items = [
                new Item(60, 10),
                new Item(100, 20),
                new Item(120, 30)
        ] as Item[]
        final capacity = 50;

        expect:
        Knapsack.solveBottomUp(capacity, items) == 220
    }

    void 'unbounded g4g'() {
        setup:
        final items = [
                new Item(1, 1),
                new Item(30, 50)
        ] as Item[]
        final capacity = 100

        expect:
        Knapsack.unboundedKnapsack(capacity, items) == 100
    }

    void 'unbounded g4g 2'() {
        setup:
        final items = [
                new Item(10, 1),
                new Item(40, 3),
                new Item(50, 4),
                new Item(70, 5)
        ] as Item[]
        final capacity = 8
        expect:
        Knapsack.unboundedKnapsack(capacity, items) == 110
    }

    void 'unbounded g4g bottom up'() {
        setup:
        final items = [
                new Item(1, 1),
                new Item(30, 50)
        ] as Item[]
        final capacity = 100

        expect:
        Knapsack.unboundedBottomUp(capacity, items) == 100
    }

    void 'unbounded g4g bottom up 2'() {
        setup:
        final items = [
                new Item(10, 1),
                new Item(40, 3),
                new Item(50, 4),
                new Item(70, 5)
        ] as Item[]
        final capacity = 8
        expect:
        Knapsack.unboundedBottomUp(capacity, items) == 110
    }
}
