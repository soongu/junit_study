package kr.co.kokono.junit_study._01;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public class ScoreCollection {
    private List<Scoreable> scores = new ArrayList<>();

    public void add(Scoreable scoreable) {
        scores.add(scoreable);
    }

    public OptionalDouble arithmeticMean() {
        return scores.stream()
                .mapToInt(Scoreable::getScore)
                .average();
    }
}
