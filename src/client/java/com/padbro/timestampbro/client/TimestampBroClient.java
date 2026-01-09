package com.padbro.timestampbro.client;

import com.padbro.timestampbro.client.config.TimestampBroConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ClientModInitializer;

public class TimestampBroClient implements ClientModInitializer {
    private static ConfigHolder<TimestampBroConfig> config;

    @Override
    public void onInitializeClient() {
        config = AutoConfig.register(TimestampBroConfig.class, Toml4jConfigSerializer::new);
    }

    public static TimestampBroConfig getConfig() {
        return config.get();
    }
}
