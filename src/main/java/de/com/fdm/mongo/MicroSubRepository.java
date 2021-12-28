package de.com.fdm.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MicroSubRepository extends MongoRepository<MicroSub, String> {
    List<MicroSub> findAllByBroadcasterUserId(String broadcasterUserId);
}
