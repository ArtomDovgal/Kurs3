package stu.cn.ua.service;

import org.springframework.stereotype.Service;
import stu.cn.ua.domain.FlightLog;
import stu.cn.ua.persistence.FlightLogRepository;

import java.util.Set;

@Service
public class FlightLogService {

    private final FlightLogRepository flightLogRepository;

    public FlightLogService(FlightLogRepository flightLogRepository) {
        this.flightLogRepository = flightLogRepository;
    }


    public Set<FlightLog> getAll() {
        return flightLogRepository.findAll();
    }

}
