package com.harsh.kryptic.thirpartyapi;

import com.harsh.kryptic.utils.Utils;
import org.springframework.http.codec.ClientCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.function.Consumer;

@Component
public class ApiClient {
    public static WebClient getApiClient(){
        String BASE_URL = Utils.getApplicationProperties().getProperty(Utils.THIRD_PARTY_API_URL_CG);
        // coins supported api has a large json response, hence increase in memory size
        final Consumer<ClientCodecConfigurer> consumer = configurer -> {
            final ClientCodecConfigurer.ClientDefaultCodecs codecs = configurer
                    .defaultCodecs();
            codecs.maxInMemorySize(16 * 1024 * 1024);
        };
        return WebClient.builder()
                .codecs(consumer)
                .baseUrl(BASE_URL)
                .filter(logRequest())
                .build();
    }

    private static ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            System.out.println("url :: " + clientRequest.url());
            return Mono.just(clientRequest);
        });
    }
}
