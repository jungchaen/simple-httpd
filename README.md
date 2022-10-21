# simple-httpd
HTTP Sever 만들기

## Usage
1. 포트번호를 인자로 받습니다.
2. 웹 브라우저에서 URL로 simple-httpd 서버에 요청합니다.
2. 서버는 HTTP 응답 메시지를 Socket을 통해 클라이언트(웹 브라우저)에게 보냅니다.

## Exmpale
프로그램 실행
> 포트번호를 입력하세요 : 3000

클라이언트(웹 브라우저)에 URL 입력
> localhost:3000/src/main/resources/document-root

클라이언트(웹 브라우저)에 URL 입력
> localhost:3000/src/main/resources/document-root/form-get1.html

### 참고사항
Python에 SimpleHttpServer 모듈과 유사한 프로그램을 작성하였습니다.
- 사용자의 관점에서 모듈화된 프로그램을 제공하도록 작성하였습니다.
