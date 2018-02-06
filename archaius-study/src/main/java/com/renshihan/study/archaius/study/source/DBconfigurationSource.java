package com.renshihan.study.archaius.study.source;

import com.netflix.config.PollResult;
import com.netflix.config.PolledConfigurationSource;

public class DBconfigurationSource implements PolledConfigurationSource{
    @Override
    public PollResult poll(boolean b, Object o) throws Exception {
        return null;
    }
}
