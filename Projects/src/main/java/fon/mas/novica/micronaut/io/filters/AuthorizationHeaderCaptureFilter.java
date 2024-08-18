package fon.mas.novica.micronaut.io.filters;

import io.micronaut.core.async.publisher.Publishers;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import org.reactivestreams.Publisher;

@Filter("/**")
public class AuthorizationHeaderCaptureFilter implements HttpServerFilter {

    private final AuthorizationHeaderHolder authorizationHeaderHolder;

    public AuthorizationHeaderCaptureFilter(AuthorizationHeaderHolder authorizationHeaderHolder) {
        this.authorizationHeaderHolder = authorizationHeaderHolder;
    }

    @Override
    public Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request, ServerFilterChain chain) {
        String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null) {
            System.out.println("POSTAVIO SAM AUTH HEADER: " + authorizationHeader);
            authorizationHeaderHolder.setAuthorizationHeader(authorizationHeader);
        }

        return Publishers.map(chain.proceed(request), response -> {
            // Clear the header after the request is processed
            authorizationHeaderHolder.clear();
            return response;
        });
    }
}
