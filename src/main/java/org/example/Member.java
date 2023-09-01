package org.example;

import javax.persistence.*;
import java.util.Date;


// DDL 생성기능에 영향을 주는 어노테이션 다소 추가
// DDL을 자동 생성할 때만 사용되고, JPA의 실행 로직에는 영향을 주지 않는다.
// -> 그래도 개발자가 엔티티만 보고도 다양한 제약조건을 파악할 수 있는 장점이 있다.
@Entity
@Table(name = "MEMBER", uniqueConstraints = {@UniqueConstraint(
        name = "NAME_AGE_UNIQUE",
        columnNames = {"NAME", "AGE"} )})
public class Member {

    @Id
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
