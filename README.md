


# :pushpin: Bunddeuk - 크리에이터를 위한 크라우드 펀딩사이트
>본인의 아이디어 상품을 소개하고 후원을 받을 수 있는 펀딩사이트
>https://bit.ly/3KMdACS

</br>

## 1. 제작 기간 & 참여 인원
- 2021년 11월 10일 ~ 2021년 12월 15일
- [팀 프로젝트] : 김해민 외 4명

</br>

## 2. 사용 기술
#### `Back-end`
  - Java 8
  - Spring Boot 2.5.6
  - Maven 3.8
  - MySQL 8.0
  - Mybatis 2.2
#### `Front-end`
  - JavaScript
  - JQuery 2.2.4
  - BootStrap 4.1
  - HTML5

</br>

## 3. ERD 설계
![](https://user-images.githubusercontent.com/91078445/150670857-0df284d4-0f9b-4586-a072-43aaddbedeca.png)


## 4. 핵심 기능
이 서비스의 핵심 기능은 컨텐츠 등록 기능입니다.  
사용자는 자유롭게 컨텐츠의 카테고리와 날짜를 선택 후 에디터게시판형으로 이미지와 텍스트를 입력 후 올리기 버튼만 누르면 끝입니다.  
이 단순한 기능의 흐름을 보면, 서비스가 어떻게 동작하는지 알 수 있습니다.  

<details>
<summary><b>핵심 기능 설명 펼치기</b></summary>
<div markdown="1">

### 4.1. 전체 흐름
![](https://zuminternet.github.io/images/portal/post/2019-04-22-ZUM-Pilot-integer/flow1.png)

### 4.2. 사용자 요청
![](https://zuminternet.github.io/images/portal/post/2019-04-22-ZUM-Pilot-integer/flow_vue.png)

- **URL 정규식 체크** :pushpin: [코드 확인](https://github.com/Integerous/goQuality/blob/b587bbff4dce02e3bec4f4787151a9b6fa326319/frontend/src/components/PostInput.vue#L67)
  - Vue.js로 렌더링된 화면단에서, 사용자가 등록을 시도한 URL의 모양새를 정규식으로 확인합니다.
  - URL의 모양새가 아닌 경우, 에러 메세지를 띄웁니다.

- **Axios 비동기 요청** :pushpin: [코드 확인]()
  - URL의 모양새인 경우, 컨텐츠를 등록하는 POST 요청을 비동기로 날립니다.

### 4.3. Controller

![](https://zuminternet.github.io/images/portal/post/2019-04-22-ZUM-Pilot-integer/flow_controller.png)

- **요청 처리** :pushpin: [코드 확인](https://github.com/Integerous/goQuality/blob/b2c5e60761b6308f14eebe98ccdb1949de6c4b99/src/main/java/goQuality/integerous/controller/PostRestController.java#L55)
  - Controller에서는 요청을 화면단에서 넘어온 요청을 받고, Service 계층에 로직 처리를 위임합니다.

- **결과 응답** :pushpin: [코드 확인]()
  - Service 계층에서 넘어온 로직 처리 결과(메세지)를 화면단에 응답해줍니다.

### 4.4. Service

![](https://zuminternet.github.io/images/portal/post/2019-04-22-ZUM-Pilot-integer/flow_service1.png)

- **Http 프로토콜 추가 및 trim()** :pushpin: [코드 확인]()
  - 사용자가 URL 입력 시 Http 프로토콜을 생략하거나 공백을 넣은 경우,  
  올바른 URL이 될 수 있도록 Http 프로토콜을 추가해주고, 공백을 제거해줍니다.

- **URL 접속 확인** :pushpin: [코드 확인]()
  - 화면단에서 모양새만 확인한 URL이 실제 리소스로 연결되는지 HttpUrlConnection으로 테스트합니다.
  - 이 때, 빠른 응답을 위해 Request Method를 GET이 아닌 HEAD를 사용했습니다.
  - (HEAD 메소드는 GET 메소드의 응답 결과의 Body는 가져오지 않고, Header만 확인하기 때문에 GET 메소드에 비해 응답속도가 빠릅니다.)

  ![](https://zuminternet.github.io/images/portal/post/2019-04-22-ZUM-Pilot-integer/flow_service2.png)

- **Jsoup 이미지, 제목 파싱** :pushpin: [코드 확인]()
  - URL 접속 확인결과 유효하면 Jsoup을 사용해서 입력된 URL의 이미지와 제목을 파싱합니다.
  - 이미지는 Open Graphic Tag를 우선적으로 파싱하고, 없을 경우 첫 번째 이미지와 제목을 파싱합니다.
  - 컨텐츠에 이미지가 없을 경우, 미리 설정해둔 기본 이미지를 사용하고, 제목이 없을 경우 생략합니다.


### 4.5. Repository

![](https://zuminternet.github.io/images/portal/post/2019-04-22-ZUM-Pilot-integer/flow_repo.png)

- **컨텐츠 저장** :pushpin: [코드 확인]()
  - URL 유효성 체크와 이미지, 제목 파싱이 끝난 컨텐츠는 DB에 저장합니다.
  - 저장된 컨텐츠는 다시 Repository - Service - Controller를 거쳐 화면단에 송출됩니다.

</div>
</details>

</br>

## 5. 핵심 트러블 슈팅
### 5.1. WAR 배포한 웹 서버에서 이미지 업로드 시 404 에러
<img src=https://user-images.githubusercontent.com/91078445/151153439-4dcc5eaf-3d00-4912-a158-69de1adf3849.png width="400"><br>

- 프로젝트 올리기에서 이미지를 업로드 할 때 진행중인 프로젝트의 workspace에 파일이 업로드 되도록 하였습니다.
- 업로드 경로: d:/eclipseWorkspace/ProjectName/src/main/webapp/uploadfile
- Putty를 이용하여 EC2 인스턴스에 접속하여  Git을 이용하여 Project Clone 후 서비스를 배포하였습니다.
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
- <build>안에 <defaultGoal>install</defaultGoal>를 추가함
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
>프로젝트 개발 회고 글: https://zuminternet.github.io/ZUM-Pilot-integer/
