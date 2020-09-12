package br.com.phc.citiesapi.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public final class RequestUtils {

	private RequestUtils() {

	}

	private static HttpServletRequest getCurrentRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	}

	public static String getHeader(final String headerName) {
		return getCurrentRequest().getHeader(headerName);
	}

}
