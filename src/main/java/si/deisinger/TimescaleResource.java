package si.deisinger;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import si.deisinger.dto.SensorDTO;
import si.deisinger.entity.Sensor;
import si.deisinger.service.SensorDataService;
import si.deisinger.service.SensorService;

import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/")
public class TimescaleResource {

	@Inject
	SensorService sensorService;

	@Inject
	SensorDataService sensorDataService;

	@GET
	@Path("sensors")
	public List<Sensor> getAllSensors() {
		return sensorService.getAllSensorsWithSensorData();
	}

	@POST
	@Path("sensors")
	@Transactional
	public void addSensor(SensorDTO sensorDTO) {
		sensorService.addSensor(sensorDTO);
	}

	@POST
	@Path("/generateData/{count}")
	@Transactional
	public void generateData(
			@PathParam("count")
			int count
	) {
		sensorDataService.generateAndStoreData(count);
	}
}