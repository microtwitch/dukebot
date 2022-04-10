package de.com.fdm.bot.commands;

import de.com.fdm.lib.Result;


public enum Identifier {
    PING {
        @Override
        public Command getCommand(Parameters params) {
            return new PingCommand(params);
        }
    },
    ECHO {
        @Override
        public Command getCommand(Parameters params) {
            return new EchoCommand(params);
        }
    },
    HTTPSTATUS {
        @Override
        public Command getCommand(Parameters params) {
            return new HttpStatusCommand(params);
        }
    },
    ID {
        @Override
        public Command getCommand(Parameters params) {
            return new UserIdCommand(params);
        }
    },
    USER {
        @Override
        public Command getCommand(Parameters params) {
            return new IdUserCommand(params);
        }
    };

    public static Result<Identifier, Exception> getIdentifier(String rawIdentifier) {
        try {
            Identifier identifier = Identifier.valueOf(rawIdentifier.toUpperCase());
            return Result.ok(identifier);
        } catch (IllegalArgumentException e) {
            return Result.error(e);
        }
    }

    public abstract Command getCommand(Parameters params);
}
