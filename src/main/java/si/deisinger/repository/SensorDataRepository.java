package si.deisinger.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import si.deisinger.entity.SensorData;

@ApplicationScoped
public class SensorDataRepository implements PanacheRepository<SensorData> {
}