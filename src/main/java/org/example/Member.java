package org.example;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MEMBER")
public class Member {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
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
