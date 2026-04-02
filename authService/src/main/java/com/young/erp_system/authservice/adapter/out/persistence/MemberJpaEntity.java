package com.young.erp_system.authservice.adapter.out.persistence;

import com.young.erp_system.authservice.domain.DelYn;
import com.young.erp_system.authservice.domain.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String memberName;

    private String memberAddress;

    private String memberEmail;

    private String memberPassword;

    @Enumerated(EnumType.STRING)
    private DelYn delYn;

    @Enumerated(EnumType.STRING)
    private Role memberRole;

    public MemberJpaEntity(String memberName, String memberAddress, String memberEmail, String memberPassword, DelYn memberDelYn, Role memberRole) {
        this.memberName = memberName;
        this.memberAddress = memberAddress;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.delYn = memberDelYn;
        this.memberRole = memberRole;
    }
}
