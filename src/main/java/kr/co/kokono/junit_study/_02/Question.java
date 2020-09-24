package kr.co.kokono.junit_study._02;

public abstract class Question {

    private String text;
    private String[] answerChoices;
    private int id;

    public Question(String text, String[] answerChoices, int id) {
        this.text = text;
        this.answerChoices = answerChoices;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public String getAnswerChoices(int i) {
        return answerChoices[i];
    }

    public boolean match(Answer answer) {
        return false;
    }

    public abstract boolean match(int expected, int actual);

    public int indexOf(String matchingAnswerChoice) {
        for (int i = 0; i < answerChoices.length; i++) {
            if (answerChoices[i].equals(matchingAnswerChoice))
                return i;
        }
        return -1;
    }
}
