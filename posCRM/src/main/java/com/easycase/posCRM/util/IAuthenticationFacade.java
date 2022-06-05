package com.easycase.posCRM.util;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
	 Authentication getAuthentication();
}
