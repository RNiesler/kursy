import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.StreamSupport;

public class MinCutTest {

    @ParameterizedTest
    @CsvSource({"teams4.txt, Philadelphia", "teams4a.txt, Ghaddafi", "teams5.txt, Detroit", "teams7.txt, Ireland",
            "teams24.txt, Team13", "teams32.txt, Team25", "teams32.txt, Team29", "teams36.txt, Team21", "teams42.txt, Team6",
            "teams42.txt, Team15", "teams42.txt, Team25", "teams48.txt, Team6", "teams48.txt, Team23", "teams48.txt, Team47",
            "teams54.txt, Team3", "teams54.txt, Team29", "teams54.txt, Team37", "teams54.txt, Team50"})
    void testTeams4(String inputFileName, String eliminatedTeam) {
        BaseballElimination baseballElimination = new BaseballElimination(ClassLoader.getSystemResource(inputFileName).getFile());

        Assertions.assertTrue(baseballElimination.isEliminated(eliminatedTeam));
        double ar = StreamSupport.stream(baseballElimination.certificateOfElimination(eliminatedTeam).spliterator(), false)
                .mapToInt(t -> baseballElimination.wins(t) + baseballElimination.remaining(t))
                .average().getAsDouble();
        Assertions.assertTrue(ar > baseballElimination.wins(eliminatedTeam) + baseballElimination.remaining(eliminatedTeam));
    }
}
