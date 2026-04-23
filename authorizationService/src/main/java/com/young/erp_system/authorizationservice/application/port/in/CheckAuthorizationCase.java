package com.young.erp_system.authorizationservice.application.port.in;

public interface CheckAuthorizationCase {

    boolean checkAuthorization(CheckAuthorizationCommand command);
}
