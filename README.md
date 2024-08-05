# 📂wanted-pre-onboarding-backend

## ✅서비스 개요
- 본 서비스는 기업의 채용을 위한 웹 서비스 입니다.
- 회사는 채용공고를 생성하고, 이에 사용자는 지원합니다.

## 📃API 명세서
|No.|Method|Function|EndPoint|
|--|-------|---|----|
|1|POST|채용공고 등록|/recruit|
|2|PATCH|채용공고 수정|/recruit/{recruitId}|
|3|DELETE|채용공고 삭제|/recruit/{recruitId}|
|4|GET|채용공고 목록 조회|/recruit|
|5|GET|채용공고 상세 조회|/recruit/{recruitId}|
|6|POST|채용공고 지원|/apply|
|7|GET|채용공고 검색|/recruit?kw={keyword}|

## ✅사용기술&툴
<img src="https://img.shields.io/badge/Spring Data JPA-6DB33F?style=flat-square&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/STS4-6DB33F?style=flat-square&logo=spring&logoColor=white">  <img src="https://img.shields.io/badge/H2-41454A?style=flat-square&logo=&logoColor=white"> <img src="https://img.shields.io/badge/Java 17-FF160B?style=flat-square&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/Gradle-02303A?style=flat-square&logo=gradle&logoColor=white">
<br>

## ✅요구사항
(회사와 사용자는 DB에 임의로 등록하여 사용합니다)
1. **채용공고를 등록합니다.**
- 회사는 아래 데이터와 같이 채용공고를 등록합니다.
```java
{
  "companyId":"1",
  "position":"백엔드 주니어 개발자",
  "bonus":1000000,
  "content":"원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
  "stack":"Java"
}

--- #회사정보가 없으면 등록 불가.
```

2. **채용공고를 수정합니다.**
- 회사는 아래 데이터와 같이 채용공고를 수정합니다. (회사 id 이외 모두 수정 가능합니다.)
```java
{
  "position":"백엔드 주니어 개발자",
  "bonus":1000000,
  "content":"원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
  "stack":"Java"
}

{
  "position":"프론트엔드 주니어 개발자", --- #변경됨
  "bonus":1500000, --- #변경됨
  "content":"원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
  "stack":"Java"
}

--- #필수값 필드 비어있으면 등록 불가
```

3.**채용공고를 삭제합니다.**
```java
DB에서 삭제됩니다
```

4. **채용공고 목록을 가져옵니다.**
- 사용자는 채용공고 목록을 아래와 같이 확인할 수 있습니다.
```java
[
    {
        "recruitId": 1,
        "companyId": 1,
        "position": "프론트엔드 주니어 개발자",
        "bonus": 1500000,
        "stack": "Java"
    },
    {
        "recruitId": 2,
        "companyId": 2,
        "position": "프론트엔드 주니어 개발자",
        "bonus": 2000000,
        "stack": "Python"
    }
]
```

5. **채용 상세 페이지를 가져옵니다.**
- “채용내용”이 추가적으로 담겨있음.
```java
{
    "recruitId": 1,
    "companyId": 1,
    "position": "프론트엔드 주니어 개발자",
    "bonus": 1500000,
    "content": "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
    "stack": "Java"
}
```

6. **사용자는 채용공고에 지원합니다(선택사항 및 가산점요소).**
- ➡️ 사용자는 채용공고에 아래와 같이 지원합니다. (가점 요소이며, 필수 구현 요소가 아님)
- 사용자는 1회만 지원 가능합니다.
```java
{
  "recruitId": "1",
  "memberId": "1"
} --- #지원 성공

--- # 중복지원시
Exception: 이미 지원한 채용공고입니다.
```

7. **채용공고 검색기능(선택사항 및 가산점요소)**
- 사용자는 stack / postion으로 채용공고 검색이 가능합니다.
```
* 영문 대소문자 구분 없이 검색 가능
@Query("SELECT r FROM Recruit r "
        + "WHERE LOWER(r.stack) LIKE LOWER(CONCAT('%', :kw, '%')) "
        + "OR LOWER(r.position) LIKE LOWER(CONCAT('%', :kw, '%'))")

1. /recruit?kw=java

[
    {
        "recruitId": 138,
        "companyId": 1,
        "position": "백엔드 주니어 개발자",
        "bonus": 1500000,
        "stack": "Java"
    }
]

2. /recruit?kw=개발자
[
    {
        "recruitId": 138,
        "companyId": 1,
        "position": "백엔드 주니어 개발자",
        "bonus": 1500000,
        "stack": "Java"
    },
    {
        "recruitId": 139,
        "companyId": 2,
        "position": "프론트엔드 주니어 개발자",
        "bonus": 2000000,
        "stack": "Python"
    }
]

```


## 🫧
✔️ DTO, 어노테이션 활용, 효율적인 단위테스트(mock 사용등) 등에 아쉬움이 남아 최적화된 코드를 위해 더 공부해볼 것
