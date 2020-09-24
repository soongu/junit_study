# Junit4 학습
------------------------------------
### 2020.09.24 학습 내용
#### 1. gradle에서 junit4 설정하기

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

#### 2. Junit 기초 학습
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