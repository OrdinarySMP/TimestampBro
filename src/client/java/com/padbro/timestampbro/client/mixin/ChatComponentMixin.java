package com.padbro.timestampbro.client.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.padbro.timestampbro.client.TimestampBroClient;
import net.minecraft.client.GuiMessageTag;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MessageSignature;
import net.minecraft.network.chat.MutableComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Mixin(value = ChatComponent.class)
public class ChatComponentMixin {
    @WrapMethod(method = "addMessage(Lnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/MessageSignature;Lnet/minecraft/client/GuiMessageTag;)V")
    private void wrapAddMessage(
            Component component,
            MessageSignature messageSignature,
            GuiMessageTag guiMessageTag,
            Operation<Void> original
    ) {
        if (!TimestampBroClient.getConfig().generalConfig.enable) {
            original.call(component, messageSignature, guiMessageTag);
            return;
        }
        original.call(addTimestamp(component), messageSignature, guiMessageTag);
    }

    @Unique
    private Component addTimestamp(Component component) {
        String format = TimestampBroClient.getConfig().generalConfig.format;
        String hoverText = TimestampBroClient.getConfig().generalConfig.hoverText;
        int textColor = TimestampBroClient.getConfig().generalConfig.textColor;
        int hoverColor = TimestampBroClient.getConfig().generalConfig.hoverColor;

        SimpleDateFormat timeFormat = new SimpleDateFormat(format);
        String formattedTime = timeFormat.format(new Date());
        MutableComponent timestamp;
        if (Objects.equals(hoverText, "")) {
            timestamp = Component.literal(formattedTime + " ").withColor(textColor);
        } else {
            timestamp = Component.literal(hoverText + " ")
                    .withColor(hoverColor)
                    .withStyle(style ->
                            style
                                    .withHoverEvent(new HoverEvent.ShowText(Component.literal(formattedTime).withColor(textColor)))
                    );
        }

        return Component.empty()
                .append(timestamp)
                .append(component);
    }
}
