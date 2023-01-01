package stu.cn.ua.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "transport")
public class TransportCategory {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long transportCategoryId;

  @Column(name = "vechicleType")
  private String vehicleType;
  private long theNumberOfSeatsInThisCategory;
  private String model;

  @OneToMany(mappedBy = "transportCategory")
  private Set<Transport> transports = new HashSet<>();

  public TransportCategory() {

  }

  public long getTransportCategoryId() {
    return transportCategoryId;
  }

  public void setTransportCategoryId(long transportCategoryId) {
    this.transportCategoryId = transportCategoryId;
  }

  public String getVehicleType() {
    return vehicleType;
  }

  public void setVehicleType(String vehicleType) {
    this.vehicleType = vehicleType;
  }

  public long getTheNumberOfSeatsInThisCategory() {
    return theNumberOfSeatsInThisCategory;
  }

  public void setTheNumberOfSeatsInThisCategory(long theNumberOfSeatsInThisCategory) {
    this.theNumberOfSeatsInThisCategory = theNumberOfSeatsInThisCategory;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public Set<Transport> getTransports() {
    return transports;
  }

  public void setTransports(Set<Transport> transports) {
    this.transports = transports;
  }
}
