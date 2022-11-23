package ru.practucum.explore.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practucum.explore.events.dto.Static;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
public class Client extends BaseClient {
    private static final String API_PREFIX = "/hit";

    @Autowired
    public Client(@Value("${stats.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }



    public void create(HttpServletRequest httpRequest) {
        Static staticDto = new Static(
                "enw",
                httpRequest.getRequestURI(),
                httpRequest.getRemoteAddr(),
                LocalDateTime.now()
        );

        post("hit", staticDto);
    }

}
