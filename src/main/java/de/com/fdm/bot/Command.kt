package de.com.fdm.bot;


import de.com.fdm.twitch.tmi.TmiMessage;

public interface Command {
    String execute(TmiMessage tmiMessage);
}
