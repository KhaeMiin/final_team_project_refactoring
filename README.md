




# :pushpin: Bunddeuk - 크리에이터를 위한 크라우드 펀딩사이트
>본인의 아이디어 상품을 소개하고 후원을 받을 수 있는 펀딩사이트<br>
>https://bit.ly/3KMdACS

</br>

## 1. 제작 기간 & 참여 인원(역할)
- 2021년 11월 10일 ~ 2021년 12월 15일
- [팀 프로젝트] : 김해민 외 4명
- 프로젝트(펀딩상품) 등록,수정 기능
- MySql RDS생성과 데이터베이스 연결하기
- AWS EC2에 웹  프로젝트 배포
- 웹 페이지 Header, Footer, Banner 디자인
</br>

## 2. 사용 기술
#### `Back-end`
  - Java 8
  - Spring Boot 2.5.6
  - Maven 3.8
  - MySQL 8.0
  - Mybatis 2.2
  - AWS EC2
  - AWS RDS
#### `Front-end`
  - JavaScript
  - JQuery 2.2.4
  - BootStrap 4.1
  - HTML5

</br>

## 3. ERD 설계
<img src=https://user-images.githubusercontent.com/91078445/150670857-0df284d4-0f9b-4586-a072-43aaddbedeca.png width="500">
<br>

## 4. 핵심 기능
이 서비스의 핵심 기능은 컨텐츠 등록 기능입니다.  
사용자는 자유롭게 컨텐츠의 카테고리와 날짜를 선택 후 에디터게시판형으로 이미지와 텍스트를 입력 후 올리기 버튼만 누르면 끝입니다.  
이 단순한 기능의 흐름을 보면, 서비스가 어떻게 동작하는지 알 수 있습니다.  

<details>
<summary><b>핵심 기능 설명 펼치기</b></summary>
<div markdown="1">

### 4.1. 전체 흐름
![](https://user-images.githubusercontent.com/91078445/151754311-c69dc779-f875-4d32-8aeb-db5814c9325e.JPG)
Web Browser가 데이터를 요청을 하면 DB에서 데이터를 return 하여 Web Browser가 데이터를 받을 수 있게 되어있습니다.
### 4.2. Controller
![Controller](https://user-images.githubusercontent.com/91078445/151757782-56bacb66-89db-4b17-b927-1b8b0d294985.JPG)

- **요청 처리** :pushpin: [코드 확인](https://github.com/KhaeMiin/Final_Team_Project/blob/master/src/main/java/data/project/ProjectController.java#L26)
  - Controller는 웹 브라우저의 요청을 전담하여 처리합니다.
  - Controller가 Service를 호출 합니다.

- **결과 응답** :pushpin: [코드 확인](https://github.com/KhaeMiin/Final_Team_Project/blob/master/src/main/webapp/WEB-INF/project_create/projectStart.jsp#L49)
  - Service 계층에서 넘어온 로직 처리 결과를 화면단에 응답해줍니다.
  - 사용자는 프로젝트를 작성하는 페이지로 이동하여 프로젝트를 올릴 수 있습니다.

### 4.3. Service

![](https://user-images.githubusercontent.com/91078445/151934797-aef9c9bf-d87e-4033-8e9e-db234f2a5a0d.JPG)

- **카테고리별 작성한 컨텐츠 저장 또는 수정시 출력 Mapper 호출** :pushpin: [코드 확인](https://github.com/KhaeMiin/Final_Team_Project/blob/master/src/main/java/data/project/ProjectService.java#L9)
  - Service는 두가지를 구현해야합니다.
	  1. service interface [코드확인](https://github.com/KhaeMiin/Final_Team_Project/blob/master/src/main/java/data/project/ProjectMapper.java#L8)
	  2. service interface를 구현한 class 만들기 (service interface 구현체) [코드확인](https://github.com/KhaeMiin/Final_Team_Project/blob/master/src/main/java/data/project/ProjectService.java#L9)
  - Service가 DAO를 호출합니다. (DAO는 데이터베이스에 접속하여 비즈니스 로직 실행에 필요한 쿼리를 호출합니다)
  저희는 DAO를 사용하지않고 **Mapper**를 사용하였습니다.

### 4.4. Mapper


![mapper](https://user-images.githubusercontent.com/91078445/151973323-f154eec5-4c3e-4437-86c4-0894df4705da.JPG)
- **작성한 컨텐츠 저장** :pushpin: [코드 확인](https://github.com/KhaeMiin/Final_Team_Project/blob/master/src/main/resources/mappers/projectCreateSQL.xml#L3)
  - 양식에 맞게 작성 후 등록하기 버튼을 누리게 되면 해당 Mapper interface의 Method를 호출하게 됩니다.
  - 호출된 Method명과 동일한 XML파일의 id를 맵핑하여 SQL문을 호출합니다.
  - 카테고리별 양식에 맞게 작성한 컨텐츠 정보는 DB에 저장됩니다.
  - 출력시 SQL문을 호출하여 받은 결과값은 다시 Mapper > Service > Controller > Web Browser 순서로 반환되어 출력됩니다.
</div>
</details>

</br>

## 5. 핵심 트러블 슈팅
### 5.1. WAR 배포한 웹 서버에서 이미지 업로드 시 404 에러
<img src=https://user-images.githubusercontent.com/91078445/151153439-4dcc5eaf-3d00-4912-a158-69de1adf3849.png width="400"><br>

- 프로젝트 작성시 또는 프로필 수정시 이미지를 업로드 할 때 진행중인 프로젝트의 workspace에 파일이 업로드 되도록 하였습니다.
- 업로드 경로: https://github.com/KhaeMiin/Final_Team_Project/tree/master/src/main/webapp/profile_image
- Putty를 이용하여 EC2 인스턴스에 접속한 후  Git을 이용하여 Project Clone 후 서비스를 배포하였습니다.<br>
참고 블로그: https://loosie.tistory.com/408

- 하지만 서버를 작동했을 때 실행되는 경로(배포 경로)와 업로드 경로(workspace)가 다르기 때문에  
이미지를 찾을 수 없다는 404가 나왔습니다.
-  그리고 검색을 하면서 (https://kimfk567.tistory.com/85) 라는 글을 읽고 배포 후 섬네일 이미지,프로필사진에 사용하는 파일 저장소의 위치를 AWS 서버로 위치를 바꿔주어야 한다는 것을 알게 되었습니다.

-  **기존 코드**에서는 웹 서버 내에 업로드될 폴더를 만들어 업로드하기 위해 필요한 상대 경로를 path로 설정하였습니다.

<details>
<summary><b>기존 코드</b></summary>
<div markdown="1">

```java
/**
 * ProjectController.java
 */
	@PostMapping("/project/defaultUpdate")
	public String defaultUpdate(@ModelAttribute ProjectDTO dto,HttpServletRequest request) {

		//썸네일 이미지 등록 시
		
		//웹 서버 내에 업로드될 폴더를 만들어 업로드하기 위해 필요한 상대경로
		String path = request.getSession().getServletContext().getRealPath("/thumbnail_image");
		//getServletContext() : 웹 어플리케이션이 설치되어 있는 경로를 리턴해줌
		//getRealPath() : ServletContext의 getRealPath는 웹어플리케이션이 실행된 곳. 즉 설치된 곳의 경로를 찾음
		
		//저장시 저장된 날짜와 시간,분,초를 파일명뒤에 확인용으로 넣기 위해
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		
		if (dto.getUpload().getOriginalFilename().equals("")) {//만약 이미지를 아무것도 업로드 하지 않은 상태면
			dto.setThumbnail(null);
		} else {//이미지가 업로드 된 상태라면
			String uploadfile = service.getData(Integer.toString(dto.getIdx())).getThumbnail();
			//file객체 생성
			File file1 = new File(path + "/" + uploadfile);
			file1.delete();//기존 이미지는 삭제하기
			
			//저장될 이미지명
			String thumbnail = sdf.format(new Date()) + "_" + dto.getUpload().getOriginalFilename();
			dto.setThumbnail(thumbnail);

			//업로드
			try {
				dto.getUpload().transferTo(new File(path + "/" + thumbnail));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		service.defaultUpdate(dto);
		return "redirect:editor?idx=" + dto.getIdx();
	}
```

</div>
</details>

- 우선 EC2 인스턴스에 접속 후 이미지가 저장될 새로운 디렉터리를 생성해줍니다.
`mkdir -p ~/backup/thumbnail(upload 폴더)`

- 톰캣 설정변경을 통해 톰캣으로 웹 프로젝트를 실행 시 웹 파일이 담길 위치를 정할 수 있습니다.
```
	vim <tomcat 설치 디렉토리>/conf/server.xml > //server.xml파일을 열어서
	//</Host>라고 되어있는 부분 윗줄에 Context부분을 추가해줍니다.
	<Context docBase="서버의 upload 폴더경로" path="URL상의 upload 폴더경로" reloadable="true" />
```

-  MvcConfiguration클래스를 만들어 **추가된클래스**와 같이 WebMvcConfigurer를 implement 합니다. 
- 그 후, addResourceHandlers 메서드를 override합니다.
- 그리고 아래 **추가된 클래스** 처럼 새로 resource handler를 추가하였습니다.
- 참고 블로그: https://dundung.tistory.com/241

<details>
<summary><b>추가된 클래스</b></summary>
<div markdown="1">

~~~java
/**
 * WebMvcConfigurer.java
 */
public class MvcConfiguration implements WebMvcConfigurer{
//	@Value("${file.upload.image}")
//	String path;

   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
        .addResourceHandler("/thumbnail_image/**")//'/thumbnail_image/**'로 호출하는 자원은 '*/
        .addResourceLocations("file:/home/ec2-user/backup/thumbnail_image");/*'file:/home/ec2-user/backup/thumbnail_image' 폴더 아래에서 찾는다.*/
        registry
        .addResourceHandler("/profile_image/**")/*'/profile_image/**'로 호출하는 자원은 '*/
        .addResourceLocations("file:/home/ec2-user/backup/profile_image");/*'file:/home/ec2-user/backup/profile_image' 폴더 아래에서 찾는다.*/
   }
}
~~~

</div>
</details>

</br>

## 6. 그 외 트러블 슈팅
<details>
<summary>maven을 이용하여 서버 배포중 build시 발생한 에러</summary>
<div markdown="1">

- 에러메세지
	- No goals have been specified for this build. You must specify a valid lifecycle phase or a goal in the format <plugin-prefix>:<goal> or <plugin-group-id>:<plugin-artifact-id>[:<plugin-version>]:<goal>. Available lifecycle phases are: validate, initialize, generate-sources, process-sources, generate-resources, process-resources, compile, process-classes, generate-test-sources, process-test-sources, generate-test-resources, process-test-resources, test-compile, process-test-classes, test, prepare-package, package, pre-integration-test, integration-test, post-integration-test, verify, install, deploy, pre-clean, clean, post-clean, pre-site, site, post-site, site-deploy. -> [Help 1]
- 번역기를 돌려보니 빌드에는 목표가 지정되어 있지 않아서 에러가 생기는 걸 알 수 있음.
- pom.xml에  <defaultGoal>install</defaultGoal>를 <build> 태그 안에 추가함 <br>
`$<defaultGoal>: 아무것도 지정하지 않은 goal의 실행시 실행되는 목표(install, clean, package, build등)`

</div>
</details>

<details>
<summary>한글 데이터를 insert시 에러가남</summary>
<div markdown="1">
  
  - 테이블 생성 시 DEFAULT CHARSET=utf8;를 추가하여 해결
  
</div>
</details>
<details>
<summary> 허술한 DB 테이블 설계 </summary>
<div markdown="1">
  <img src=https://user-images.githubusercontent.com/91078445/151304333-75092ddf-0db2-4db9-b793-2d042f366727.JPG width="500">  <img src=https://user-images.githubusercontent.com/91078445/151304337-6f405cc4-2891-4e7a-a909-bda911c85f42.JPG width="500"><br>

- 팀원 모두가 서로 연관성이 있음에도 불구하고 팀원들과의 상의 없이 각자 자기의 테이블 생성
- 결국 제약조건이 걸려있지 않아 연관된 데이터가 삭제되지 않거나, 기본키와 외래키의 컬럼명을 서로 맞춰보지 못해 기본키를 찾지 못하는 등 불상사가 생기기도 하였음.
- 무한 수정 후 결국 모든 DB 테이블을 삭제 후 재설계
- 프로젝트 시작 전 꼼꼼한 DB 설계의 필요성을 크게 느낌
  
</div>
</details>
    
<details>
<summary> git push -u origin dev 실행 시 SSL 인증서 오류 발생</summary>
<div markdown="1">

  - git push -u origin dev 실행 시
 SSL certificate problem: self signed certificate in certificate chain 발생
 - git config --global http.sslVerify false(SSL 인증서가 필요없기에 git 설정에서 SSL 인증서를 필요로 하는 옵션을 종료시킨다.)
로 해결

※여기서 궁금증! SSL이란 무엇일까? 
- SSL(Secure Socket Layer)은 웹서버(서버)와 브라우저(클라이언트) 사이의 보안을 위해 만들었다.(보안인증서라고 하자)
- 정리:간단하게 말하면 들어오고 나가는 데이터들을 암호화하는 보안 기능을 갖고 있는 ‘보안 인증서’
</div>
</details>    

<details>
<summary> 텀블벅 프로젝트 작성에 쓰인 콘텐츠 저작권문제</summary>
<div markdown="1">
  <img src=https://user-images.githubusercontent.com/91078445/151306995-38951f88-84ca-4c3f-827b-0b07c5f5226b.JPG width="300">
    <img src=https://user-images.githubusercontent.com/91078445/151306997-60082523-27fa-4696-a593-813aaf93cbe3.JPG width="300"><br>

  - 텀블벅 사이트에서 창작자님들에게 허락을 구함 
</div>
</details>  
</br>

## 6. 회고 / 느낀점
>프로젝트 개발 회고 글: https://intelliy-min.tistory.com/8
	
*블로그 회고글 중	
### 간단한 KPT
#### Keep
-   회의,약속시간 칼같이 지킴(너무 기본적인 거라 적을까 말까 고민했지만 기본적인 것 마저 안 지키는 사람들이 있었기에)
-   코딩 규칙 세우기(네이밍규치, 기타규칙) (조장님 아이디어)
-   노션을 활용하여 팀 규칙과 일정관리, 진행상황과 회의 내용 등 기록
-   먹고 자고 싸고 코딩하고 무한 반복
-   포기하지 않고 폭풍 구글링을 하여 결국 서버 배포함 (3일을 밤새 붙잡고 했었다..)
-   메인 페이지를 맡은 팀원의 일탈에도 멘탈을 부여잡고 밤을 지새워 완성함(나는 프런트 바보다..그럼에도 포기하지 않고 2~3일 밤을 포기하고 완성하였고 모두에게 박수를 받았다....)
-   철저한 일정관리 덕분에 조금은 일찍 완성하여 여러 번의 테스트를 통해 계속해서 에러를 잡고 수정함.
-   DB에 넣을 콘텐츠의 저작권 동의받기(조장님이 직접 한 분 한 분 메시지를 보내서 허락을 구했다고 한다.) **\*사용을 허락해주신 창작자님들 감사합니다!**
-   내 구글링 실력이 많이 늘었다!!! 에러가 나면 일단 구글 검색과 파파고 번역기부터 켜는 것이 이제는 숨 쉬듯 되어버렸다.(어느정도 눈에 익숙해진 에러 메세지들이 늘어났다.)
-   최종 완성일자보다 열흘정도 앞당겨 각자 기능구현 완성을 한 후 테스트를 여러번 진행함(각자가 했을 땐 몰랐던 문제점들이 테스트기간을 통해서 많이 나왔다.) - 그 덕에 최종 발표 시 시연 도중 문제점이 많이 발생한 다른 팀들과 달리 문제없이 깔끔하게 기능을 시연함
​
#### Problem
-   @Service에서 핵심 비즈니스 로직을 담지 못하고 모든 정보 가공처리를 Controller에서 수행하였다.(스프링에 대한 이해도가 부족하기 때문이다.)
-   허술한 DB 테이블 설계: 모두가 연관성이 있음에도 모두 각자가 아무런 상의 없이 테이블을 만들었다...제약조건도 생각하지 못했고 컬럼명도 공유가 제대로 안 돼서 결국 모든 테이블을 삭제하고 다시 생성하였다.
-   무료 CSS 탬플릿을 가져와서 적용하였지만 결국 사용하지 않았고, 쓸모없는 CSS 파일들만 주야장천 남음(scss??알지도 못하는 파일들이 남았다. 처음에는 무서워서 삭제를 못 했지만 프로젝트가 완성되고 과감하게 쓸모없는 파일들은 다 삭제해버렸다. 그러면서 모든 css가 뒤틀어졌었고 하나하나 복구하는 데에 시간이 꽤 걸렸다.)
-   로그인 시 카카오로 그인 API를 구현하지 못함.
-   밤에는 조금 일찍 자고 아침에 일찍 일어나는 부지런한 패턴을 가졌더라면...
-   서버를 재배포할 때마다 image 폴더들이 초기화됨.(결국,리눅스 환경에 이미지를 올릴 폴더가 필요) → 지식이 너무 없다 보니 좀 더 공부해야겠다.
-   git을 제대로 활용하지 못함.
-   이클립스에서 Lombok이 적용이 안 되는 팀원 2명이 있었다. 결국, 방법을 찾지 못함.
​
#### Try
-   소켓 공부해서 채팅기능 꼭 만들 수 있도록 하자.
-   **역할분담 명확하게 하기**
-   git bash 명령어 공부하기
-   AWS S3를 좀 더 공부해서 S3 Bucket 생성 후 이미지 또는 파일을 업로드 하기
-   DB테이블 설계 시 컬럼명과 구조는 팀원 모두가 함께 철저하게 세우기(개인 프로젝트를 하더라도 마찬가지)
​
