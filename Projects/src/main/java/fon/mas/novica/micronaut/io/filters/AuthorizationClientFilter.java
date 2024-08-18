package fon.mas.novica.micronaut.io.filters;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.ClientFilterChain;
import io.micronaut.http.filter.HttpClientFilter;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;

@Filter("/**")
@Singleton
public class AuthorizationClientFilter implements HttpClientFilter {

    private final AuthorizationHeaderHolder authorizationHeaderHolder;

    public AuthorizationClientFilter(AuthorizationHeaderHolder authorizationHeaderHolder) {
        this.authorizationHeaderHolder = authorizationHeaderHolder;
    }

    @Override
    public Publisher<? extends HttpResponse<?>> doFilter(MutableHttpRequest<?> request, ClientFilterChain chain) {
        String authorizationHeader = authorizationHeaderHolder.getAuthorizationHeader();
        System.out.println("UZEO SAM AUTH HEADER: " + authorizationHeader);
        if (authorizationHeader != null) {
            request.header(HttpHeaders.AUTHORIZATION, authorizationHeader);
        }

        return chain.proceed(request);
    }
}

