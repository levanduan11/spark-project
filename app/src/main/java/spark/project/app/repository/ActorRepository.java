package spark.project.app.repository;

import com.spark.domainmodel.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
}