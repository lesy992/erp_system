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
    private Long MemberId;

    private String MemberName;

    private String MemberAddress;

    private String MemberEmail;

    private String MemberPassword;

    private DelYn DelYn;

    private Role Role;

    public MemberJpaEntity(String memberName, String memberAddress, String memberEmail, String memberPassword, DelYn memberDelYn, Role memberRole) {
        this.MemberName = memberName;
        this.MemberAddress = memberAddress;
        this.MemberEmail = memberEmail;
        this.MemberPassword = memberPassword;
        this.DelYn = memberDelYn;
        this.Role = memberRole;
    }
}
