package de.com.fdm.db.data;

import de.com.fdm.bot.access.UserLevel;
import de.com.fdm.bot.commands.Command;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "twitch_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "twitch_user_id")
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_level")
    private UserLevel level;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserLevel getLevel() {
        return level;
    }

    public void setLevel(UserLevel level) {
        this.level = level;
    }

    public boolean canExecute(Command cmd) {
        if (getLevel() == UserLevel.OWNER) {
            return true;
        }

        if (getLevel() == UserLevel.UNKNOWN) {
            return false;
        }

        return getLevel() == UserLevel.PLEB && cmd.getLevel() == UserLevel.PLEB;
    }
}
