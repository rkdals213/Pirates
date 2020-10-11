# Pirates
1. 어플리케이션 환경설정 및 설치가이드
    - 사용 FrameWork : Spring boot
    - Java Version : 8
    - JDK Version : zulu-8
    - Project SDK : 1.8 java version "1.8.0_192"
    - 빌드환경 : Maven
    - 사용 DB : H2
    - DB 연결 URL : jdbc:h2:~/pirate;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=true;
    - DB Username : sa
    - DB Password :    
    
2. 테이블 생성 SQL : 프로젝트 실행시 자동 생성 (jpa)

![DB_Diagram](https://user-images.githubusercontent.com/56009774/95680623-f3427f00-0c15-11eb-8264-b9ae22c00f94.PNG)


   
    create table STORE
    (
        ID          BIGINT auto_increment
            primary key,
        ADDRESS     VARCHAR(255),
        DESCRIPTION VARCHAR(255),
        LEVEL       INTEGER not null,
        NAME        VARCHAR(255),
        OWNER       VARCHAR(255),
        PHONE       VARCHAR(255)
    );
    ```
    create table BUSINESS_TIMES
    (
        ID       BIGINT auto_increment
            primary key,
        CLOSE    VARCHAR(255),
        DAY      VARCHAR(255),
        OPEN     VARCHAR(255),
        STORE_ID BIGINT,
        constraint FKNVMYAR2EJURV0SP4K2IPCD0RA
            foreign key (STORE_ID) references STORE (ID)
    );
    ```
    ```
    create table STORE_HOLIDAYS
    (
        STORE_ID BIGINT not null,
        HOLIDAYS VARCHAR(255),
        constraint FKAF1G6QHYX1KKAITJXW6GE5CSA
            foreign key (STORE_ID) references STORE (ID)
    );
    ```

3. API 사용가이드
    - 테스트 환경 : Swagger2
    - Test URL : http://localhost:8080/swagger-ui.html
    - A. 점포 추가 API : Post /registStore
    - B. 점포 휴무일 등록 API : Post /registHoliday
    - C. 점포 목록 조회 API : Get /getStores
    - D. 점포 상세 정보 조회 API : Get /getStoreInfo
    - E. 점포 삭제 API : Delete /deleteStore
    
