package de.com.fdm.db.data;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "microsub")
public class MicroSub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "microsub_id")
    private Long id;

    private String channel;

    @Column(name = "broadcaster_user_id")
    private String broadcasterUserId;

    public MicroSub() {}

    public MicroSub(String channel, String broadcasterUserId) {
        this.channel = channel;
        this.broadcasterUserId = broadcasterUserId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getBroadcasterUserId() {
        return broadcasterUserId;
    }

    public void setBroadcasterUserId(String broadcasterUserId) {
        this.broadcasterUserId = broadcasterUserId;
    }
}
