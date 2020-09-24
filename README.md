# Junit4 학습
------------------------------------
### 2020.09.24 학습 내용
#### 1. gradle에서 junit4 설정하기

``` Java
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
