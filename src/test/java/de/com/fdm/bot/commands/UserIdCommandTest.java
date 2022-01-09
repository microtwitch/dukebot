package de.com.fdm.bot.commands;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserIdCommandTest {

    @Test
    void noIdProvided() {
        UserIdCommand cmd = new UserIdCommand("test", new ArrayList<>(), new MockTwitchApiProvider());
        String result = cmd.execute();
        assertEquals("No user id provided", result);
    }

    @Test
    void execute() {
        ArrayList<String> args = new ArrayList<>();
        args.add("12345");
        UserIdCommand cmd = new UserIdCommand("test", args, new MockTwitchApiProvider());
        String result = cmd.execute();
        assertEquals("12345 -> justinfan", result);
    }
}
