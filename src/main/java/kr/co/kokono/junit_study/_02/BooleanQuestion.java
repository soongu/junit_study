package kr.co.kokono.junit_study._02;

public class BooleanQuestion extends Question {

    public BooleanQuestion(int id, String text) {
        super(text, new String[] {"No", "Yes"}, id);
    }

    @Override
    public boolean match(int expected, int actual) {
        return expected == actual;
    }
}
