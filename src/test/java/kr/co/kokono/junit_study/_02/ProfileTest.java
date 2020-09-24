package kr.co.kokono.junit_study._02;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProfileTest {

    /**
     * 필수질문(가중치 : MustMatch)에 대해서 잘못 대답했을 때 매칭결과가 false가
     * 나오는지에 대한 테스트
     */
    @Test
    public void matchAnswersFalseWhenMustMatchCriteriaNotMet() throws Exception {
        //given
        Profile profile = new Profile("뽀로로");
        //첫번째 질문 생성: 상여를 받았나요?
        Question question = new BooleanQuestion(1, "Got bonuses?");

        //뽀로로의 답변을 등록 - 아니오
        Answer profileAnswer = new Answer(question, Bool.FALSE);
        profile.add(profileAnswer);

        Criteria criteria = new Criteria();
        //질문 내용과 기대하는 정답을 저장. - 예
        Answer criteriaAnswer = new Answer(question, Bool.TRUE);
        //기준질문과 가중치를 기준클래스에 저장
        Criterion criterion = new Criterion(criteriaAnswer, Weight.MustMatch);

        //기준모음집에 추가
        criteria.add(criterion);

        //when
        boolean matches = profile.matches(criteria);

        //then
        assertFalse(matches);
    }

    /**
     * 가중치가 낮은(DontCare) 질문에 대해 잘못 대답했을 경우 매칭결과가 true가
     * 나오는지에 대한 테스트
     */
    @Test
    public void matchAnswersTrueForAnyDontCareCriteria() throws Exception {
        //given
        Profile profile = new Profile("뽀로로");
        //질문 생성: 점심 먹었나요?
        Question question = new BooleanQuestion(1, "Have Lunch?");

        //뽀로로의 답변을 등록 - 아니오
        Answer profileAnswer = new Answer(question, Bool.FALSE);
        profile.add(profileAnswer);

        Criteria criteria = new Criteria();
        //질문 내용과 기대하는 정답을 저장. - 예
        Answer criteriaAnswer = new Answer(question, Bool.TRUE);
        //기준질문과 가중치를 기준클래스에 저장
        Criterion criterion = new Criterion(criteriaAnswer, Weight.DontCare);

        //기준모음집에 추가
        criteria.add(criterion);

        //when
        boolean matches = profile.matches(criteria);

        //then
        assertTrue(matches);
    }

}