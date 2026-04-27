package com.young.erp_system.authorizationservice.application.port.out;

import com.young.erp_system.authorizationservice.domain.Menu;

import java.util.List;
import java.util.Optional;

public interface LoadMenuPort {

    List<Menu> findAll();

    Optional<Menu> findById(Long menuId);
}
