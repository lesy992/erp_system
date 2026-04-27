package com.young.erp_system.authorizationservice.application.port.out;

import com.young.erp_system.authorizationservice.domain.Menu;

public interface SaveMenuPort {

    Menu save(Menu menu);

    void deleteById(Long menuId);
}
