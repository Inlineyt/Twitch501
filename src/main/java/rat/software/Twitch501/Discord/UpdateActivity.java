package rat.software.Twitch501.Discord;

import org.javacord.api.entity.activity.ActivityType;
import rat.software.Twitch501.Main;

public class UpdateActivity {

    public void update (String State) {
        switch (State) {
            case "Offline":
                Main.getApi().updateActivity("Wartet auf Melvins Stream");
                break;
            case "Online":
                Main.getApi().updateActivity(ActivityType.STREAMING, "Streamt Aktuell auf https://twitch.tv/melvin501");

                break;
        }
    }
}
