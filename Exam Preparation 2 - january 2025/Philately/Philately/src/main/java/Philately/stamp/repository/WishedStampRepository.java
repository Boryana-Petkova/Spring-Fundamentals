package Philately.stamp.repository;

import Philately.stamp.model.WishedStamp;
import Philately.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WishedStampRepository extends JpaRepository<WishedStamp, UUID> {
}
