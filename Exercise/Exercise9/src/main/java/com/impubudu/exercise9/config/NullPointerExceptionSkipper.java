package com.impubudu.exercise9.config;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;

public class NullPointerExceptionSkipper implements SkipPolicy {
    @Override
    public boolean shouldSkip(Throwable exception, int skipCount) throws SkipLimitExceededException {
        if (exception instanceof NullPointerException && skipCount <= 5) {
            return true;
        } else {
            return false;
        }
    }
}
