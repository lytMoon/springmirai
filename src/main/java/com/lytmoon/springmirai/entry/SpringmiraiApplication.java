package com.lytmoon.springmirai.entry;

import com.lytmoon.springmirai.network.NetManager;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.auth.BotAuthorization;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.utils.BotConfiguration;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.apache.logging.log4j.LogManager.getLogger;

@SpringBootApplication
public class SpringmiraiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringmiraiApplication.class, args);
        getLogger().info("Plugin loaded!");


//		3341495355L
        Bot bot = BotFactory.INSTANCE.newBot(3341495355L, BotAuthorization.byQRCode(), new BotConfiguration());
        bot.getConfiguration().setProtocol(BotConfiguration.MiraiProtocol.ANDROID_WATCH);
        bot.login();

        bot.getEventChannel().subscribeAlways(GroupMessageEvent.class, SpringmiraiApplication::onGroupMessage);

    }

    private static void onGroupMessage(@NotNull GroupMessageEvent event) {
        String messageContent = event.getMessage().contentToString();
        if (messageContent.contains("@卷卷") || messageContent.contains("@3341495355")) {
            System.out.println(messageContent);
            if (messageContent.contains("R1") || messageContent.contains("r1")) {
                NetManager.getInstance().getDeepSeekResponse(messageContent, 1, event);
            } else {
                NetManager.getInstance().getDeepSeekResponse(messageContent, 0, event);
            }
            event.getGroup().sendMessage(NetManager.getInstance().dsresponseList.get(0).getChoices().get(0).getMessage().getContent());
        }
    }

}
