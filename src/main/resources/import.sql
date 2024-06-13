-- Delete sensor table if exists
DROP TABLE IF EXISTS sensor CASCADE;

-- Delete sensor_data table if exists
DROP TABLE IF EXISTS sensor_data CASCADE;

-- Create sensor table
CREATE TABLE sensor
(
    id       SERIAL PRIMARY KEY,
    type     TEXT NOT NULL,
    location TEXT NOT NULL
);

-- Create sensor_data table
CREATE TABLE sensor_data
(
    id        SERIAL,
    time      TIMESTAMPTZ NOT NULL,
    sensor_id INTEGER REFERENCES sensor (id),
    value     DOUBLE PRECISION
);

-- Create hypertable for sensor_data on time
SELECT create_hypertable('sensor_data', 'time');

-- Insert initial data into sensor table
INSERT INTO sensor (type, location)
VALUES ('Temperature', 'Living Room'),
       ('Humidity', 'Kitchen'),
       ('Pressure', 'Bedroom');

-- Insert initial data into sensor_data table with random values
INSERT INTO sensor_data (time, sensor_id, value)
SELECT generate_series(now() - INTERVAL '24 hours', now(), INTERVAL '5 minutes'),
       floor(random() * 3 + 1)::INTEGER,
       random() * 100
FROM generate_series(1, 100);
