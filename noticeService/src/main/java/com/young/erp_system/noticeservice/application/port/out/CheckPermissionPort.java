package com.young.erp_system.noticeservice.application.port.out;

public interface CheckPermissionPort {

    void checkPermission(String role, String resource, String action);
}
