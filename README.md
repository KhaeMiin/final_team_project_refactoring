
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
### 5.1. 배포한 웹 서버에서 이미지 업로드 시 404 에러
<img src=https://user-images.githubusercontent.com/91078445/151153439-4dcc5eaf-3d00-4912-a158-69de1adf3849.png width="400"><img src=https://user-images.githubusercontent.com/91078445/151155125-b83dcdff-7522-4218-bd56-8fd14f64a9a0.JPG width="400"><br>

- 서버를 배포하기 전 로컬에서 작업을 했던 저는 프로젝트 내부에 이미지가 업로드될 폴더를 만들어서 웹에서 클라이언트가 이미지를 업로드시 내부에 저장이 되도록 설정하였습니다.
- Putty를 이용하여 EC2인스턴스에 접속하여  Git을 이용하여 서비스를 배포하였습니다.

- 하지만 서버를 작동했을때 웹에서 이미지업로드시 프로젝트 내부에 만들어놓은 폴더에 이미지가 추가되지 않았고 결론적으로 이미지를 찾을 수 없다는 404가 나왔습니다.
-  그리고 검색을 하면서 (https://kimfk567.tistory.com/85) 라는 글을 읽고 배포 후 썸네일이미지,프로필사진에 사용하는 파일 외부저장소의 위치를 AWS 서버로 위치를 바꿔주어야 한다는 것을 알게되었습니다.

-  **기존 코드**에서는 웹 서버 내에 업로드될 폴더를 만들어 업로드하기 위해 필요한 상대경로를 path로 설정하였습니다.
(※해당 코드의 문제점은 재배포할 때 마다 폴더가 초기화됨)

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

- 우선 EC2 인스턴스에 접속 후 이미지가 저장될 새로운 디렉토리를 생성해줍니다.
```
	mkdir -p ~/backup/thumbnail(upload 폴더)
```

- 톰캣 설정변경을 통해 톰캣으로 웹 프로젝트를 실행 시 웹 파일이 담겨질 위치를 정할 수 있습니다.
```
	vim <tomcat 설치 디렉토리>/conf/server.xml > server.xml파일을 열어서
	<Context docBase="/home/ec2-user/backup/thumbnail" path="/thumbnail" reloadable="true" /> //</Host>라고 되어있는 부분 윗줄에 Context부분을 추가해줍니다.

	<Context docBase="서버의 upload 폴더경로" path="URL상의 upload 폴더경로" reloadable="true" />
```

-  MvcConfiguration클래스를 만들어 **추가된클래스**와 같이 WebMvcConfigurer를 implement 합니다. 
- 그 후, addResourceHandlers 메서드를 override합니다.
- 그리고 아래 **추가된클래스** 처럼 새로 resource handler를 추가해줍니다.

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
      /* '/js/**'로 호출하는 자원은 '/static/js/' 폴더 아래에서 찾는다. */ 
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/").setCachePeriod(60 * 60 * 24 * 365); 
      /* '/css/**'로 호출하는 자원은 '/static/css/' 폴더 아래에서 찾는다. */ 
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/").setCachePeriod(60 * 60 * 24 * 365); 
      /* '/img/**'로 호출하는 자원은 '/static/img/' 폴더 아래에서 찾는다. */ 
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/").setCachePeriod(60 * 60 * 24 * 365); 
      /* '/fonts/**'로 호출하는 자원은 '/static/fonts/' 폴더 아래에서 찾는다. */ 
        registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/static/fonts/").setCachePeriod(60 * 60 * 24 * 365); 
        registry.addResourceHandler("/style/**").addResourceLocations("classpath:/static/fonts/").setCachePeriod(60 * 60 * 24 * 365); 
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
<summary>npm run dev 실행 오류</summary>
<div markdown="1">

- Webpack-dev-server 버전을 3.0.0으로 다운그레이드로 해결
- `$ npm install —save-dev webpack-dev-server@3.0.0`

</div>
</details>

<details>
<summary>vue-devtools 크롬익스텐션 인식 오류 문제</summary>
<div markdown="1">
  
  - main.js 파일에 `Vue.config.devtools = true` 추가로 해결
  - [https://github.com/vuejs/vue-devtools/issues/190](https://github.com/vuejs/vue-devtools/issues/190)
  
</div>
</details>

<details>
<summary>ElementUI input 박스에서 `v-on:keyup.enter="메소드명"`이 정상 작동 안하는 문제</summary>
<div markdown="1">
  
  - `v-on:keyup.enter.native=""` 와 같이 .native 추가로 해결
  
</div>
</details>

<details>
<summary> Post 목록 출력시에 Member 객체 출력 에러 </summary>
<div markdown="1">
  
  - 에러 메세지(500에러)
    - No serializer found for class org.hibernate.proxy.pojo.javassist.JavassistLazyInitializer and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationConfig.SerializationFeature.FAIL_ON_EMPTY_BEANS)
  - 해결
    - Post 엔티티에 @ManyToOne 연관관계 매핑을 LAZY 옵션에서 기본(EAGER)옵션으로 수정
  
</div>
</details>
    
<details>
<summary> 프로젝트를 git init으로 생성 후 발생하는 npm run dev/build 오류 문제 </summary>
<div markdown="1">
  
  ```jsx
    $ npm run dev
    npm ERR! path C:\Users\integer\IdeaProjects\pilot\package.json
    npm ERR! code ENOENT
    npm ERR! errno -4058
    npm ERR! syscall open
    npm ERR! enoent ENOENT: no such file or directory, open 'C:\Users\integer\IdeaProjects\pilot\package.json'
    npm ERR! enoent This is related to npm not being able to find a file.
    npm ERR! enoent

    npm ERR! A complete log of this run can be found in:
    npm ERR!     C:\Users\integer\AppData\Roaming\npm-cache\_logs\2019-02-25T01_23_19_131Z-debug.log
  ```
  
  - 단순히 npm run dev/build 명령을 입력한 경로가 문제였다.
   
</div>
</details>    

<details>
<summary> 태그 선택후 등록하기 누를 때 `object references an unsaved transient instance - save the transient instance before flushing` 오류</summary>
<div markdown="1">
  
  - Post 엔티티의 @ManyToMany에 영속성 전이(cascade=CascadeType.ALL) 추가
    - JPA에서 Entity를 저장할 때 연관된 모든 Entity는 영속상태여야 한다.
    - CascadeType.PERSIST 옵션으로 부모와 자식 Enitity를 한 번에 영속화할 수 있다.
    - 참고
        - [https://stackoverflow.com/questions/2302802/object-references-an-unsaved-transient-instance-save-the-transient-instance-be/10680218](https://stackoverflow.com/questions/2302802/object-references-an-unsaved-transient-instance-save-the-transient-instance-be/10680218)
   
</div>
</details>    

<details>
<summary> JSON: Infinite recursion (StackOverflowError)</summary>
<div markdown="1">
  
  - @JsonIgnoreProperties 사용으로 해결
    - 참고
        - [http://springquay.blogspot.com/2016/01/new-approach-to-solve-json-recursive.html](http://springquay.blogspot.com/2016/01/new-approach-to-solve-json-recursive.html)
        - [https://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue](https://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue)
        
</div>
</details>  
    
<details>
<summary> H2 접속문제</summary>
<div markdown="1">
  
  - H2의 JDBC URL이 jdbc:h2:~/test 으로 되어있으면 jdbc:h2:mem:testdb 으로 변경해서 접속해야 한다.
        
</div>
</details> 
    
<details>
<summary> 컨텐츠수정 모달창에서 태그 셀렉트박스 드랍다운이 뒤쪽에 보이는 문제</summary>
<div markdown="1">
  
   - ElementUI의 Global Config에 옵션 추가하면 해결
     - main.js 파일에 `Vue.us(ElementUI, { zIndex: 9999 });` 옵션 추가(9999 이하면 안됌)
   - 참고
     - [https://element.eleme.io/#/en-US/component/quickstart#global-config](https://element.eleme.io/#/en-US/component/quickstart#global-config)
        
</div>
</details> 

<details>
<summary> HTTP delete Request시 개발자도구의 XHR(XMLHttpRequest )에서 delete요청이 2번씩 찍히는 이유</summary>
<div markdown="1">
  
  - When you try to send a XMLHttpRequest to a different domain than the page is hosted, you are violating the same-origin policy. However, this situation became somewhat common, many technics are introduced. CORS is one of them.

        In short, server that you are sending the DELETE request allows cross domain requests. In the process, there should be a **preflight** call and that is the **HTTP OPTION** call.

        So, you are having two responses for the **OPTION** and **DELETE** call.

        see [MDN page for CORS](https://developer.mozilla.org/en-US/docs/Web/HTTP/Access_control_CORS).

    - 출처 : [https://stackoverflow.com/questions/35808655/why-do-i-get-back-2-responses-of-200-and-204-when-using-an-ajax-call-to-delete-o](https://stackoverflow.com/questions/35808655/why-do-i-get-back-2-responses-of-200-and-204-when-using-an-ajax-call-to-delete-o)
        
</div>
</details> 

<details>
<summary> 이미지 파싱 시 og:image 경로가 달라서 제대로 파싱이 안되는 경우</summary>
<div markdown="1">
  
  - UserAgent 설정으로 해결
        - [https://www.javacodeexamples.com/jsoup-set-user-agent-example/760](https://www.javacodeexamples.com/jsoup-set-user-agent-example/760)
        - [http://www.useragentstring.com/](http://www.useragentstring.com/)
        
</div>
</details> 
    
<details>
<summary> 구글 로그인으로 로그인한 사용자의 정보를 가져오는 방법이 스프링 2.0대 버전에서 달라진 것</summary>
<div markdown="1">
  
  - 1.5대 버전에서는 Controller의 인자로 Principal을 넘기면 principal.getName(0에서 바로 꺼내서 쓸 수 있었는데, 2.0대 버전에서는 principal.getName()의 경우 principal 객체.toString()을 반환한다.
    - 1.5대 버전에서 principal을 사용하는 경우
    - 아래와 같이 사용했다면,

    ```jsx
    @RequestMapping("/sso/user")
    @SuppressWarnings("unchecked")
    public Map<String, String> user(Principal principal) {
        if (principal != null) {
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
            Authentication authentication = oAuth2Authentication.getUserAuthentication();
            Map<String, String> details = new LinkedHashMap<>();
            details = (Map<String, String>) authentication.getDetails();
            logger.info("details = " + details);  // id, email, name, link etc.
            Map<String, String> map = new LinkedHashMap<>();
            map.put("email", details.get("email"));
            return map;
        }
        return null;
    }
    ```

    - 2.0대 버전에서는
    - 아래와 같이 principal 객체의 내용을 꺼내 쓸 수 있다.

    ```jsx
    UsernamePasswordAuthenticationToken token =
                    (UsernamePasswordAuthenticationToken) SecurityContextHolder
                            .getContext().getAuthentication();
            Map<String, Object> map = (Map<String, Object>) token.getPrincipal();

            String email = String.valueOf(map.get("email"));
            post.setMember(memberRepository.findByEmail(email));
    ```
        
</div>
</details> 
    
<details>
<summary> 랭킹 동점자 처리 문제</summary>
<div markdown="1">
  
  - PageRequest의 Sort부분에서 properties를 "rankPoint"를 주고 "likeCnt"를 줘서 댓글수보다 좋아요수가 우선순위 갖도록 설정.
  - 좋아요 수도 똑같다면..........
        
</div>
</details> 
    
</br>

## 6. 회고 / 느낀점
>프로젝트 개발 회고 글: https://zuminternet.github.io/ZUM-Pilot-integer/
