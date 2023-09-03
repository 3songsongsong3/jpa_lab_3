package org.example;

import javax.persistence.*;
import java.util.Date;


// DDL 생성기능에 영향을 주는 어노테이션 다소 추가
// DDL을 자동 생성할 때만 사용되고, JPA의 실행 로직에는 영향을 주지 않는다.
// -> 그래도 개발자가 엔티티만 보고도 다양한 제약조건을 파악할 수 있는 장점이 있다.
@Entity
@SequenceGenerator(name = "TEST_SEQ_GENERATOR",
                    sequenceName = "TEST_SEQ",
                    initialValue = 1, allocationSize = 1 // allocationSize의 default는 50이다.
)
@Table(name = "MEMBER", uniqueConstraints = {@UniqueConstraint(
        name = "NAME_AGE_UNIQUE",
        columnNames = {"NAME", "AGE"} )})
public class Member {

    @Id
    // 1. Identity 전략으로 기본키 생성을 데이터베이스에 위임함
    // 데이터베이스에 엔티티를 저장해서 식별자 값을 획득, 영속성 컨텍스트에 저장
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 2. 데이터베이스 시퀀스 생성용 테이블에서 식별자 값을 획득한 후 영속성 컨텍스트에 저장
    // @GeneratedValue(strategy = GenerationType.TABLE, generator = "TEST_SEQ_GENERATOR")
    // 3. 데이터베이스 방언에 따라 IDENTITY, SEQUENCE, TABLE 전략 중 하나를 자동으로 선택
    // @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "TEST_SEQ_GENERATOR") // 시퀀스 전략
    @Column(name = "ID")
    private String id;

    // 컬럼 제약조건 추가
    @Column(name = "NAME", nullable = false, length = 10)
    private String username;

    private Integer age;

    // 추가
    // enum 타입을 사용하기 위해 매핑
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lasModifiedDate;

    // CLOB ,BLOB 같이 필드 길이 제한이 없는 타입 매핑
    @Lob
    private String description;


}
