package si.deisinger.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import si.deisinger.entity.Sensor;
import si.deisinger.entity.SensorData;
import si.deisinger.repository.SensorDataRepository;
import si.deisinger.repository.SensorRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@ApplicationScoped
public class SensorDataService {

	@Inject
	SensorRepository sensorRepository;

	@Inject
	SensorDataRepository sensorDataRepository;

	private final Random random = new Random();

	@Transactional
	public void generateAndStoreData(int count) {
		List<Sensor> sensorEntities = sensorRepository.listAll();
		LocalDateTime now = LocalDateTime.now();

		for (int i = 0; i < count; i++) {
			Sensor sensor = sensorEntities.get(random.nextInt(sensorEntities.size()));

			SensorData data = new SensorData();
			data.setTime(now.minusHours(24).plusMinutes(i * 5));
			data.setSensor(sensor);
			data.setValue(random.nextDouble() * 100);

			sensorDataRepository.persist(data);
		}
	}
}