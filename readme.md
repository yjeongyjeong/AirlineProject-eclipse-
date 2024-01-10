<Resource auth="Container" driverClassName="oracle.jdbc.OracleDriver" maxIdle="10" maxTotal="20" maxWaitMillis="-1" name="jdbc/myoracle" password="tiger" type="javax.sql.DataSource" url="jdbc:oracle:thin:@192.168.0.32:1521:xe" username="scott"/>

## ✈️ 항공사 웹사이트 구현(Eclipse) ✈️

### 목차
* [개요](#-개요)
* [개발 환경](#-개발-환경)
* [조원](#-조원)
* [PPT](#-ppt)
* [기능 명세서](#-기능-명세서)
* [Diagram](#-diagram)
* [기능 설명](#-기능-설명)
* [기능 시연](#-기능-시연)

### ✔ 개요
```
개발 기간 : 2023.10.25 - 2023.11.03
카카오와 아시아나 항공을 참고하여 항공사 웹사이트를 구현하였습니다.
```
***

### ⚙ 개발 환경
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E)
![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)
<br>
![Apache Tomcat](https://img.shields.io/badge/apache%20tomcat-%23F8DC75.svg?style=for-the-badge&logo=apache-tomcat&logoColor=black)
![Oracle](https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white)
<br>
![Bootstrap](https://img.shields.io/badge/bootstrap-%238511FA.svg?style=for-the-badge&logo=bootstrap&logoColor=white)
![jQuery](https://img.shields.io/badge/jquery-%230769AD.svg?style=for-the-badge&logo=jquery&logoColor=white)
![CSS3](https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white)
<br>
![Eclipse](https://img.shields.io/badge/Eclipse-FE7A16.svg?style=for-the-badge&logo=Eclipse&logoColor=white)
<img src="https://img.shields.io/badge/JSP-E34F26?style=flat-square&logo=JSP&logoColor=white">
<br>
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)
<br>
![Gmail](https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white)

<details>
	<summary>
		API 상세
	</summary>
  
* 메일 전송 : activation.jar, imap.jar, javax.mail-api-1.4.7, mailapi, smtp.jar​
* 기타 : Selenium(크롤링), Summernote(게시판 에디터), DateRangePicker(날짜 선택)

</details>

***

### ✨ 조원
|이름|역할|
|----|------|
|[김민철](https://github.com/alscjf6702)|공지사항 게시판, Q&A 게시판, CSS​|
|[김보람](https://github.com/kbr7105)|헤더, 마이페이지(관리자/일반회원), 항공일정 조회, CSS​|
|[이동우](https://github.com/dwdwdw12)|항공 데이터 크롤링, 여행일기 게시판, 이벤트 게시판, CSS|
|[정윤정](https://github.com/yjeongyjeong)|회원가입, 아이디 찾기, 패스워드 찾기, CSS​|
***

### 📂 PPT
<details>
  <summary>
   개발환경/개발일정/역할분담
  </summary>

![Slide1](https://github.com/yjeongyjeong/project01/assets/147116001/ced6e558-0b49-4542-9367-9c24320a9f04)
![Slide2](https://github.com/yjeongyjeong/project01/assets/147116001/c510938e-3acc-45ce-b4e4-56c3822dbbd7)
![Slide3](https://github.com/yjeongyjeong/project01/assets/147116001/e5fc902c-97d5-4948-a780-991040fb8188)
![Slide4](https://github.com/yjeongyjeong/project01/assets/147116001/1ffddd91-b083-4c83-bf9c-02b7b23c2486)
![Slide5](https://github.com/yjeongyjeong/project01/assets/147116001/5f380692-3b2c-470f-a898-68e339a2d819)

</details>

<details>
  <summary>
   주요기능
  </summary>
  
![Slide10](https://github.com/yjeongyjeong/project01/assets/147116001/3048518c-b2a1-4c8c-b53b-54f3a1d10a39)
![Slide11](https://github.com/yjeongyjeong/project01/assets/147116001/862802aa-34f0-4988-a33d-8a9302627e22)
![Slide12](https://github.com/yjeongyjeong/project01/assets/147116001/b7a60d39-cc9e-4f34-aeb8-d972a1af5b4c)
![Slide13](https://github.com/yjeongyjeong/project01/assets/147116001/b8ae91f8-b0df-49da-83b8-197f06421b2f)
![Slide14](https://github.com/yjeongyjeong/project01/assets/147116001/1f5a449c-58b0-4673-bb32-53f29c19bc9c)
![Slide15](https://github.com/yjeongyjeong/project01/assets/147116001/2a8ac03c-e0f3-49b3-99e5-9a22ad4dd0d6)
  
</details>

<details>
  <summary>
   주요기능
  </summary>  
  
![Slide17](https://github.com/yjeongyjeong/project01/assets/147116001/633fe9e6-827f-431a-918b-74d179e173a6)
![Slide18](https://github.com/yjeongyjeong/project01/assets/147116001/64e35c79-0d7c-4297-85a5-dca62ed1762b)
![Slide19](https://github.com/yjeongyjeong/project01/assets/147116001/2bd03d7f-172c-4bc5-aa0d-9996765b3dbd)
![Slide20](https://github.com/yjeongyjeong/project01/assets/147116001/e25ef96a-4644-4921-8e92-326e84365837)
![Slide21](https://github.com/yjeongyjeong/project01/assets/147116001/2bc0def0-b74e-4c2d-b931-362c207822be)
![Slide22](https://github.com/yjeongyjeong/project01/assets/147116001/0bbab065-9e84-4f68-8c6f-b70555ae268f)
![Slide23](https://github.com/yjeongyjeong/project01/assets/147116001/d5556188-5e7f-4ed6-a7bb-19770815dbd1)
![Slide24](https://github.com/yjeongyjeong/project01/assets/147116001/5bb4c85e-a323-45b2-9640-103590a1ef04)
![Slide25](https://github.com/yjeongyjeong/project01/assets/147116001/35984401-eccd-4726-9ae6-62f5a32a2e25)
![Slide26](https://github.com/yjeongyjeong/project01/assets/147116001/fbee83b4-b41f-4e9b-a7bd-4723eadc9a08)  
  
  </details>
  
<details>
  <summary>
   Diagram
  </summary>  

![Slide6](https://github.com/yjeongyjeong/project01/assets/147116001/ae50c435-fd8a-467e-a7a2-e144efa89f9c)
![Slide7](https://github.com/yjeongyjeong/project01/assets/147116001/54913729-ae84-4ebb-b0c0-a144434d8880)
![Slide8](https://github.com/yjeongyjeong/project01/assets/147116001/623a0ef4-af36-45a7-b325-3aa25b086136)
![Slide9](https://github.com/yjeongyjeong/project01/assets/147116001/652efea7-c088-4760-8419-68d873f50576)

</details>

### 📑 기능 명세서
<details>
  <summary>
    기능 명세서
  </summary>
  
![기능명세서](https://github.com/yjeongyjeong/AirlineProject-eclipse-/assets/147116001/9f6880b0-b96f-491d-8074-30cdd2ed88ac)

</details>

### 📊 Diagram
<details>
  <summary>
    Usecase
  </summary>

![UsecaseDiagram](https://github.com/yjeongyjeong/springProject/assets/147116001/9bf8257a-47ec-45c4-8615-8b5a4fb0b453)
 </details>
 
<details>
  <summary>
    ER
  </summary>

![ERDiagram](https://github.com/yjeongyjeong/AirlineProject-eclipse-/assets/147116001/89831676-8955-4c50-8813-772cbbbeb027)

</details>

<details>
  <summary>
    Class
  </summary>

![ClassDiagram](https://github.com/yjeongyjeong/AirlineProject-eclipse-/assets/147116001/6039b560-2e4d-4fb9-93df-13b09211e434)

</details>


   