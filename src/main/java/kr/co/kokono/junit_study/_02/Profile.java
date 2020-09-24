package kr.co.kokono.junit_study._02;

import java.util.HashMap;
import java.util.Map;

/**
 * Profile클래스는 어떤 사람이 회사 혹은 구직자에게 물어볼 수 있는 적절한 질문에 대한
 * 답변을 담고 있습니다.
 */
public class Profile {

    //이 해시맵은 질문내용을 key, 질문내용과 답변(예,아니오)를 포함한 Answer를 value로 갖습니다.
    private Map<String, Answer> answers = new HashMap<>();
    private int score;
    private String name;

    public Profile(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * 구직자에 대한 질문내용을 key로
     * 질문내용을 포함한 답변 객체 answer를 value로 하여
     * 추가합니다.
     */
    public void add(Answer answer) {
        answers.put(answer.getQuestionText(), answer);
    }

    /**
     * 해당 기준들(criteria)이 프로파일에 있는 답변과 맞는지 결정하는 메서드입니다.
     *
     * @param criteria 다수의 Criterion객체를 담는 컨테이너입니다.
     * @return
     */
    public boolean matches(Criteria criteria) {
        score = 0;

        boolean kill = false;
        boolean anyMatches = false;

        /**
         * criterion객체는 고용주가 구직자를 찾거나
         * 구직자가 고용주를 찾는 기준정보 객체입니다.
         *
         * Weight는 그 질문의 중요도를 캡슐화합니다.
         */
        for (Criterion criterion: criteria) {
            //구직자의 답변
            Answer answer = answers.get(
                    criterion.getAnswer().getQuestionText());

            //구직자와 회사의 답변이 일치하는가(회사가 그질문에 돈케어로 설정했거나, 답변이 일치하거나)
            boolean match =
                    criterion.getWeight() == Weight.DontCare ||
                            answer.match(criterion.getAnswer());

            //회사와 구직자가 매칭되지 않고 + 그 질문의 가중치가 머스트 매치였으면 컷해버리겠다!
            if (!match && criterion.getWeight() == Weight.MustMatch) {
                kill = true;
            }
            //일단 질문이 매칭되면 가중치에 따라 점수를 부여한다.
            if (match) {
                score += criterion.getWeight().getValue();
            }
            anyMatches |= match;
        }
        if (kill)
            return false;
        return anyMatches;
    }
    public int score() {
        return score;
    }
}
