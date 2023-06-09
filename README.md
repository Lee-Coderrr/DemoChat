# SimpleChat 애플리케이션

이 프로젝트는 Spring Boot, Thymeleaf, WebSocket을 사용하여 구축된 간단한 채팅 애플리케이션입니다. 사용자들은 실시간으로 메시지를 주고받을 수 있습니다.

## 화면
- main 화면
![main](images/main.png)


- chat 화면
![main](images/chat_1.png)
![main](images/chat_2.png)


## 기능

1. 실시간 메시지 전송
2. 사용자별 메시지 구분
3. 메시지 전송 시간 표시
4. 사용자 이름 및 메시지 내용 관련 예외 처리

## 사용한 기술

- Spring Boot
- Spring Data JPA
- WebSocket
- Thymeleaf
- JavaScript
- HTML/CSS

## 설치 및 실행 방법

### 필요한 도구

- JDK 11
- Gradle
- MySQL

### 실행 방법

1. GitHub에서 프로젝트를 복제합니다.

   ```bash
   git clone https://github.com/yourusername/simplechat.git
   ```

2. MySQL 데이터베이스를 생성합니다.

3. 해당 스프링부트 프로젝트를 실행합니다. 

4. application.properties에 사용할 데이터베이스 주소 및 이름, 패스워드를 입력하세요.

5. 웹 브라우저에서 `http://localhost:8080`로 이동하여 애플리케이션에 접속합니다.
