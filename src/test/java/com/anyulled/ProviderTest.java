package com.anyulled;

import dev.openfeature.sdk.EvaluationContext;
import dev.openfeature.sdk.MutableContext;
import dev.openfeature.sdk.OpenFeatureAPI;
import io.getunleash.util.UnleashConfig;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProviderTest {

    UnleashConfig config = UnleashConfig.builder()
            .appName("test")
            .unleashAPI("http://unleash.org")
            .build();
    Provider provider = new Provider(config);

    @Test
    void getBooleanEvaluation() {
        OpenFeatureAPI.getInstance().setProvider(provider);
        EvaluationContext context = new MutableContext();

        assertThat(OpenFeatureAPI.getInstance().getClient().getBooleanValue("key", false, context))
                .isFalse();
    }
}