package fon.mas.novica.micronaut.io.filters;

import jakarta.inject.Singleton;

@Singleton
public class AuthorizationHeaderHolder {
    //TODO

//    private static final ThreadLocal<String> AUTHORIZATION = new ThreadLocal<>();
    private String authorization;

    public void setAuthorizationHeader(String authorizationHeader) {
//        AUTHORIZATION.set(authorizationHeader);
        authorization = authorizationHeader;
    }

    public String getAuthorizationHeader() {
//        return AUTHORIZATION.get();
        return authorization;
    }

    public void clear() {
//        AUTHORIZATION.remove();
        authorization = null;
    }
}
