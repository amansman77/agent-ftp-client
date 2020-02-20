# FTP Client agent

## 목표

FTP서버에 접속하여 파일을 주고받는 동작을 구현한다.

## 개발 프레임워크
- IDE : 4.5.1.RELEASE
- Java : SE-1.8
- FTP Client : [Apache Commons Net](https://commons.apache.org/proper/commons-net/)

## Main class

`com.ho.agent.ftp.App`

## Troubleshooting

### 한글깨짐 문제

#### FTP 서버에 파일 목록을 요청한 경우 한글명으로된 파일은 나타나지 않는 현상 발생

`FTPClient`클래스는 `FTP`클래스를 상속받아서 FTP에 통신하도록 해주는데, `FTP`클래스는 `SocketClient`를 상속받아서 사용한다

`FTPClient`클래스는 파일 리스트를 조회할 때 `FTPListParseEngine`을 활용하는데  `java.net.Socket`클래스의 `java.io.InputStream`과 `_controlEncoding`정보를 바탕으로 소켓의 데이터를 읽는다.

여기서 FTP서버의 인코딩이 UTF-8이기 때문에 `ftpClient.setControlEncoding("UTF-8");`으로 인코딩을 UTF-8로 설정해준다.

#### FTP 서버에 한글 path를 요청했을때 파일목록을 불러오지 않는 현상 발생

소켓을 생성하기 전에 인코딩을 UTF-8로 설정하도록 수정
