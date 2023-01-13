package stu.cn.ua.mapper;

import stu.cn.ua.domain.Transport;
import stu.cn.ua.domain.TransportCategory;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class TransportMapper {

    private Long transportId;

    private Integer numberOfSeats;

    private Long transportCategoryId;

    private Integer numberOfWorkers;

    public TransportMapper(){

    }

    public TransportMapper(Transport transport) {
        this.transportId = transport.getTransportId();
        this.numberOfSeats = transport.getNumberOfSeats();
        this.transportCategoryId = transport.getTransportCategory().getTransportCategoryId();
        this.numberOfWorkers = transport.getNumberOfWorkers();
    }

    public Long getTransportId() {
        return transportId;
    }

    public void setTransportId(Long transportId) {
        this.transportId = transportId;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Long getTransportCategoryId() {
        return transportCategoryId;
    }

    public void setTransportCategoryId(Long transportCategoryId) {
        this.transportCategoryId = transportCategoryId;
    }

    public Integer getNumberOfWorkers() {
        return numberOfWorkers;
    }

    public void setNumberOfWorkers(Integer numberOfWorkers) {
        this.numberOfWorkers = numberOfWorkers;
    }
}
