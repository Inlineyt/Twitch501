package rat.software.Twitch501;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.philippheuer.events4j.api.domain.IDisposable;
import com.github.philippheuer.events4j.api.service.IEventHandler;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.events.ChannelGoLiveEvent;
import com.github.twitch4j.events.ChannelGoOfflineEvent;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.server.Server;
import rat.software.Twitch501.Discord.MessageDispatcher;
import rat.software.Twitch501.Discord.SetupDiscordBot;
import rat.software.Twitch501.Discord.UpdateActivity;

public class Main {

    static    TwitchClient twitchClient = TwitchClientBuilder.builder()
            .withClientId("insert Client ID here")
            .withClientSecret("Insert Client Secret here")
            .withEnableHelix(true)
            .withEnableChat(true)
            .withChatAccount(new OAuth2Credential("twitch","oauth:<insert here the accessToken>"     ))

            .build();

    static   DiscordApi api = new DiscordApiBuilder().setToken("insert Token here").login().join();



    public static void main (String[] args) {

        Server server = Main.getApi().getServerById("969302162205212832").get();
        TextChannel channel = Main.getApi().getTextChannelById("969302203825274960").get();


        IDisposable eventHandlerOnline = twitchClient.getChat().getEventManager().getEventHandler(SimpleEventHandler.class).onEvent(ChannelGoLiveEvent.class, eventLive -> {

            new MessageDispatcher().GoLiveEmbed(channel, eventLive.getChannel().getName(), eventLive.getStream().getGameName().toString(), eventLive.getStream().getThumbnailUrl(512, 288), "https://twitch.tv/" + eventLive.getChannel().getName());
            new UpdateActivity().update("Online");

        });

        IDisposable eventHandlerOffline = twitchClient.getChat().getEventManager().getEventHandler(SimpleEventHandler.class).onEvent(ChannelGoOfflineEvent.class, eventLive -> {

            new UpdateActivity().update("Offline");

        });


        new UpdateActivity().update("Offline");
        twitchClient.getChat().sendMessage("Melvin501", "Bot aktiv");
        twitchClient.getClientHelper().enableStreamEventListener("Melvin501");
        twitchClient.getEventManager().onEvent(ChannelGoLiveEvent.class, event -> {

            new UpdateActivity().update("Online");


        });


        var eventManager = Main.getTwitchClient().getEventManager();

        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("!setup Melvin501")) {
                if (event.getMessageAuthor().getId() == Long.parseLong("896075100808433674")) {


                                     Main.getApi().getYourself().updateNickname(server, "Twitch501", "Bot Initialized");


                } else if (event.getMessageAuthor().getId() == Long.parseLong("279268647661469696")) {
                    if (event.getMessageAuthor().getId() == Long.parseLong("896075100808433674")) {

                        IDisposable eventHandler = twitchClient.getChat().getEventManager().getEventHandler(SimpleEventHandler.class).onEvent(ChannelGoLiveEvent.class, eventLive -> {

                            new MessageDispatcher().GoLiveEmbed(channel, eventLive.getChannel().getName(), eventLive.getStream().getGameName().toString(), eventLive.getStream().getThumbnailUrl(512, 288), "https://twitch.tv/" + eventLive.getChannel().getName());
                            Main.getApi().updateActivity(ActivityType.STREAMING, "Streamt Aktuell auf https://twitch.tv/melvin501");
                        });



                        Main.getApi().getYourself().updateNickname(server, "Twitch501", "Bot Initialized");

                    }
                }
            }
        });


    }





    public static TwitchClient getTwitchClient() {
        return  twitchClient;
    }

    public static DiscordApi getApi() {
        return api;
    }


}

