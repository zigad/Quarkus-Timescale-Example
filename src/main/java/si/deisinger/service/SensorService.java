package si.deisinger.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import si.deisinger.dto.SensorDTO;
import si.deisinger.entity.Sensor;
import si.deisinger.repository.SensorRepository;

import java.util.List;

@ApplicationScoped
public class SensorService {

	@Inject
	SensorRepository sensorRepository;

	@Transactional
	public void addSensor(SensorDTO sensorDTO) {
		Sensor sensor = new Sensor();
		sensor.setType(sensorDTO.getType());
		sensor.setLocation(sensorDTO.getLocation());
		sensorRepository.persist(sensor);
	}

	@Transactional
	public List<Sensor> getAllSensorsWithSensorData() {
		List<Sensor> sensorEntities = sensorRepository.listAll();

		for (Sensor sensor : sensorEntities) {
			// Load sensorDataList eagerly
			sensor.getSensorDataList().size(); // Force loading of sensorDataList
		}

		return sensorEntities;
	}
}
