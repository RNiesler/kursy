package tasks.g4g

import spock.lang.Specification

class CoinGameStrategySpecification extends Specification {
    void 'g4g'() {
        expect:
        CoinGameStrategy.maxWin([5, 3, 7, 10] as int[]) == 15
        CoinGameStrategy.maxWin([8, 15, 3, 7] as int[]) == 22
        CoinGameStrategy.maxWin([20, 30, 2, 2, 2, 10] as int[]) == 42
    }
}
