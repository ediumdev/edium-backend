package com.edium.service.core.security;

import com.edium.library.spring.PermissionType;
import org.springframework.security.core.Authentication;

public interface Permission {
    boolean isAllowed(Authentication authentication, Object targetDomainObject, PermissionType permissionType);
}
