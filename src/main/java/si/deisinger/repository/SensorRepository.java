package si.deisinger.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import si.deisinger.entity.Sensor;

@ApplicationScoped
public class SensorRepository implements PanacheRepository<Sensor> {
}
