package kr.co.kokono.junit_study._01;

import org.junit.Test;

import java.util.OptionalDouble;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class ScoreCollectionTest {

    @Test
    public void answerMeanOfTwoNumber() throws Exception {
        //given
        ScoreCollection collection = new ScoreCollection();
        collection.add(() -> 5);
        collection.add(() -> 7);

        //when
        double actualMean = collection.arithmeticMean().getAsDouble();

        //then
        assertThat(actualMean, equalTo(6.0));
    }
}