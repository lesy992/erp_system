package com.young.erp_system.authservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access =  AccessLevel.PRIVATE)
public class Member {

    @Getter
    private Long id;

    @Getter
    private String name;

    @Getter
    private String address;

    @Getter
    private String email;

    @Getter
    private String password;

    @Getter
    private DelYn delYn;

    @Getter
    private Role role;

    @Value
    public static class MemberId{
        public MemberId(Long value){this.memberId = value; }
        Long memberId;
    }

    @Value
    public static class MemberName{
        public MemberName(String value){this.memberName = value;}
        String memberName;
    }

    @Value
    public static class MemberAddress{
        public MemberAddress(String value) {this.memberAddress = value;}
        String memberAddress;
    }

    @Value
    public static class MemberEmail{
        public MemberEmail(String value) {this.memberEmail = value; }
        String memberEmail;
    }

    @Value
    public static class MemberPassword{
        public MemberPassword(String value){this.memberPassword = value; }
        String memberPassword;
    }

    @Value
    public static class MemberDelYn{
        public MemberDelYn(DelYn value){this.memberDelYn = value;}
        DelYn memberDelYn;
    }

    @Value
    public static class MemberRole{
        public MemberRole(Role value){this.memberRole = value;}
        Role memberRole;
    }

    // 오염이 되면 안되는 클래스, 고객 정보 , 핵심 도메인
    public static Member generatMember(
            MemberId memberId
            , MemberName memberName
            , MemberAddress memberAddress
            , MemberEmail memberEmail
            , MemberPassword memberPassword
            , MemberDelYn memberDelYn
            , MemberRole memberRole
    ){
        return new Member(
                memberId.memberId
                , memberName.memberName
                , memberAddress.memberAddress
                , memberEmail.memberEmail
                , memberPassword.memberPassword
                , memberDelYn.memberDelYn
                , memberRole.memberRole
        );
    }
}

