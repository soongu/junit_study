package kr.co.kokono.junit_study._02;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 중복 코드 리팩토링하기
 * # @Before 메서드로 테스트 초기화
 */
public class ProfileTest2 {

    private Profile profile;
    private BooleanQuestion question;
    private Criteria criteria;

    /**
     * 공통 코드 제거를 위한 리팩토링
     */
    @Before
    public void create() {
        profile = new Profile("뽀로로");
        question = new BooleanQuestion(1, "Got Bonuses?");
        criteria = new Criteria();
    }

    /**
     * 필수질문(가중치 : MustMatch)에 대해서 잘못 대답했을 때 매칭결과가 false가
     * 나오는지에 대한 테스트
     */
    @Test
    public void matchAnswersFalseWhenMustMatchCriteriaNotMet() throws Exception {
        //given
        //뽀로로의 답변을 등록 - 아니오
        profile.add(new Answer(question, Bool.FALSE));

        //질문 내용과 기대하는 정답을 저장. - 예
        //기준질문과 가중치를 기준클래스에 저장
        //기준모음집에 추가
        criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.MustMatch));

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
        //뽀로로의 답변을 등록 - 아니오
        profile.add(new Answer(question, Bool.FALSE));

        //질문 내용과 기대하는 정답을 저장. - 예
        //기준질문과 가중치를 기준클래스에 저장
        //기준모음집에 추가
        criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.DontCare));

        //when
        boolean matches = profile.matches(criteria);

        //then
        assertTrue(matches);
    }

}