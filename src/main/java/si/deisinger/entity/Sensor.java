package si.deisinger.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Sensor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String type;
	private String location;

	@OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference // Prevents recursion in JSON serialization
	private List<SensorData> sensorDataList;

	// Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<SensorData> getSensorDataList() {
		return sensorDataList;
	}

	public void setSensorDataList(List<SensorData> sensorDataList) {
		this.sensorDataList = sensorDataList;
	}

}