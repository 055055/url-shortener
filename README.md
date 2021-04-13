# URL_Shortening Project

### 프로젝트 설명
URL을 입력받아 짧게 줄여주고, Shortening된 URL을 입력하면 원래 URL로 리다이렉트하는 URL Shortening Service
예) https://en.wikipedia.org/wiki/URL_shortening => http://localhost/JZfOQNro


### 프로젝트 요건

+ URL 입명력폼 제공 및 결과 출력
+ URL Shortening Key는 8 Character 이내로 생성되어야 합니다.
+ 동일한 URL에 대한 요청은 동일한 Shortening Key로 응답해야 합니다.
+ 동일한 URL에 대한 요청 수 정보를 가져야 한다(화면 제공 필수 아님)
+ Shortening된 URL을 요청받으면 원래 URL로 리다이렉트 합니다.
+ Database 사용은 필수 아님


### 가산점

+ Unit test 및 Integration test 작성.

### 기술 
+ java 11
+ thymeleaf
+ spring-boot
+ spring-boot-starter-data-jpa
+ spring-boot-starter-validation
+ com.h2database
+ sweetalert
+ jquery
+ bootstrap  
+ datatables
+ multiple gradle
+ lombok
+ spring-boot-starter-test'


### 프로젝트 제출물

+ 소스코드가 담긴 github.
+ github의 readme에는 해당 웹서버를 리눅스 기준으로 실행하기 위해 필요한 설치/빌드 방법이 작성되어 있어야 합니다.
    + sudo yum install java-11-openjdk-devel  (자바 11 설치)
    + sudo /usr/sbin/alternatives --config java (자바 11 선택)
    + sudo yum install git (깃 설치)
    + git clone https://github.com/055055/url-shortener.git (프로젝트 클론)
    + cd url-shortener/
    + ./gradlew build (프로젝트 빌드)
    +  nohup java -jar ./url-shortener/build/libs/url-shortener-0.0.1-SNAPSHOT.jar 2>&1 &    (프로젝트 실행)
    + http://localhost:8081/h2-console/  내장 h2 database


+ 단축 URL 생성
  <img width="1432" alt="make-short-url" src="https://user-images.githubusercontent.com/42599161/114569612-1aa30300-9cb0-11eb-82e2-0953ab7f5356.png">


+ 단축 URL 생성 확인
  <img width="721" alt="short-url-data-table" src="https://user-images.githubusercontent.com/42599161/114569751-33abb400-9cb0-11eb-9aeb-e5f16b7bd972.png">








