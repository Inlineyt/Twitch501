package rat.software.Twitch501.Discord;

import com.github.twitch4j.graphql.internal.FetchVideoCommentsQuery;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import rat.software.Twitch501.Main;

import java.awt.*;

public class MessageDispatcher {




    public void GoLiveEmbed(TextChannel channel, String Streamer, String Game , String Thumbnail , String StreamURL){




        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Neuer Stream von " + Streamer)
                .addField("Spiel:" , Game)
                .setImage(Thumbnail)
                .setDescription(StreamURL)
                .setColor(Color.CYAN);
        channel.sendMessage(embed );
        channel.sendMessage(Main.getApi().getRoleById("969619320839766046").get().getMentionTag());



    }

    public void SetupEmbed(TextChannel channel){




        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Setup501")
                .setDescription("Guten Tag! \n Sie haben mich hinzugefügt zu diesem Server Daher möchte ich ein Paar Dinge wissen: \n ChannelID für Den Twitch Text Kanal \n")
                .setColor(Color.CYAN);
        channel.sendMessage(embed);


    }



    public void InitializedEmbed(TextChannel channel , String TwitchChannelName){




        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Setup501")
                .setDescription("Guten Tag! \n Sie haben mich Erfolgreich Inizialisiert Kanal:" + TwitchChannelName)
                .setColor(Color.CYAN);
        channel.sendMessage(embed);


    }





}
