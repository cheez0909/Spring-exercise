### 1. 프로젝트 환경설정
___

```
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	testImplementation 'org.projectlombok:lombok:1.18.28'
    testImplementation 'junit:junit:4.13.1'
    compileOnly 'org.projectlombok:lombok'
	developmentOnly 'org.springframework.boot:spring-boot-devtools'
	runtimeOnly 'com.h2database:h2'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	implementation 'com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.9.0'
}
```

✅ `lombok` 라이브러리는 별도로 라이브러리 설치가 필요하다!(Setting)
✅ `p6spy-spring-boot-starter` 라이브러리는 동적 쿼리문을 자세히 볼 수 있게 해주는 라이브러리

<br>

#### 💾 application.yml 파일

```
spring:
  datasource:
    url: jdbc:h2:tcp://localhost/~/first
    username: sa
    password:
    driver-class-name: org.h2.Driver

  jpa:
    hibernate:
      ddl-auto: create
    properties:
      hibernate:
#        show_sql: true
        format_sql: true

logging.level:
  org.hibernate.orm.jdbc.bind: trace
 #   org.org.hibernate.sql: debug
```


<br>
<br>
<br>

### 2. 롬복 라이브러리 확인
___
✅ Data 클래스와 Apllication을 통해 롬복 라이브러리가 정상적으로 설치됐음을 확인

<br>
<br>
<br>

### 3. DB 연결 확인
___

✅ Member 엔티티 생성<br>
✅ MemberRepository 생성
  - `EntityManager` 와 `@PersistenceContext`를 통해 엔티티를 영구히 저장하는 환경을 만들어준다
 - `EntityManager`의 `persist` 메소드를 사용하여 엔티티를 `영속 상태`로 변경
 - 영속상태가 되면 영속성 컨텍스트 안에서 데이터를 CRUD함

<br>
<br>
<br>

### 4. `p6spy` 라이브러리 확인 및 `@Transactional`
___
✅ `p6spy` 라이브러리를 통해 쿼리문을 자세히 확인할 수 있다.<br>