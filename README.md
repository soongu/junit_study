# Junit4 학습
------------------------------------

#### gradle에서 junit4 설정하기

``` groovy
plugins {
    id 'java'
}

group 'kr.co.kokono'
version '1.0-SNAPSHOT'

repositories {
    mavenCentral()
}

dependencies {
    testCompile group: 'junit', name: 'junit', version: '4.12'
}
```

#### Junit 기초 학습
- AAA기법: 준비-실행-단언(Arrange-Act-Assert)
- 어떤 테스트를 수행할지 결정해야 한다.
- 테스트 클래스에서 단위테스트 메서드별
 사용할 인스턴스 초기화를 하는 방법
  @Before 아노테이션 사용
- example
``` java
@Before
public void create() {
    profile = new Profile("뽀로로");
    question = new BooleanQuestion(1, "Got Bonuses?");
    criteria = new Criteria();
}
```
--------------------------------------------------------

#### JUnit 단언
- assertTrue: 검증하는 기대값이 참일 것이라고 단언.
- assertThat: 기대값과 실제값을 비교하여 일치할 것이라고 단언.
  - 첫번째 인자: 검증하고자 하는 값 | 두번째 인자: 매처(matcher)
  - 왼쪽에서 오른쪽으로 읽으면 됨.
  - example)
   ```assertThat(account.getBalance(), equalTo(100));```  
   == 계좌잔고가 100과 같아야한다.

