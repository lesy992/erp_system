package com.young.erp_system.common.jwt;

public interface TokenBlacklistPort {
    boolean isBlacklisted(String token);
}
