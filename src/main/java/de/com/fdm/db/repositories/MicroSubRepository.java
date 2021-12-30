package de.com.fdm.db.repositories;

import de.com.fdm.db.data.MicroSub;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MicroSubRepository extends CrudRepository<MicroSub, Long> {
    List<MicroSub> findAllByBroadcasterUserId(String broadcasterUserId);
}
