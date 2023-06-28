package com.anyulled;

import dev.openfeature.sdk.EvaluationContext;
import dev.openfeature.sdk.FeatureProvider;
import dev.openfeature.sdk.Metadata;
import dev.openfeature.sdk.ProviderEvaluation;
import dev.openfeature.sdk.Value;
import io.getunleash.DefaultUnleash;
import io.getunleash.Unleash;
import io.getunleash.UnleashContext;
import io.getunleash.util.UnleashConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Provider implements FeatureProvider {
    private static final Logger logger = LoggerFactory.getLogger(Provider.class);
    private final Unleash unleash;
    private final Metadata metaData = new ProviderMetaData();

    public Provider(UnleashConfig config) {
        unleash = new DefaultUnleash(config);
    }

    public static void main(String[] args) {
        logger.info("hello world");
    }

    @Override
    public Metadata getMetadata() {
        return metaData;
    }

    @Override
    public ProviderEvaluation<Boolean> getBooleanEvaluation(String key, Boolean defaultValue, EvaluationContext ctx) {
        UnleashContext unleashContext = UnleashContext.builder()
                .build();
        return ProviderEvaluation.<Boolean>builder()
                .value(unleash.isEnabled(key, unleashContext, defaultValue))
                .build();
    }

    @Override
    public ProviderEvaluation<String> getStringEvaluation(String key, String defaultValue, EvaluationContext ctx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProviderEvaluation<Integer> getIntegerEvaluation(String key, Integer defaultValue, EvaluationContext ctx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProviderEvaluation<Double> getDoubleEvaluation(String key, Double defaultValue, EvaluationContext ctx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProviderEvaluation<Value> getObjectEvaluation(String key, Value defaultValue, EvaluationContext ctx) {
        throw new UnsupportedOperationException();
    }

    private static final class ProviderMetaData implements Metadata {
        @Override
        public String getName() {
            return "Unleash.OpenFeature.ServerProvider";
        }
    }
}
