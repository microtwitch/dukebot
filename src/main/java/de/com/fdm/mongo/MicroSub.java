package de.com.fdm.mongo;

import org.bson.types.ObjectId;

public class MicroSub {
    private ObjectId _id;
    private String channel;
    private String broadcasterUserId;

    public MicroSub(String channel, String broadcasterUserId) {
        this.channel = channel;
        this.broadcasterUserId = broadcasterUserId;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
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
