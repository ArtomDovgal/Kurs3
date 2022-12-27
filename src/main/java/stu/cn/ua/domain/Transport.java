package stu.cn.ua.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "transport")
public class Transport {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long transportId;
  private Integer numberOfSeats;

  @ManyToOne
  @JoinColumn(name = "trasportCategoryId")
  private TransportCategory transportCategory;
  private Integer numberOfWorkers;


  public Transport() {

  }

  public long getTransportId() {
    return transportId;
  }

  public void setTransportId(long transportId) {
    this.transportId = transportId;
  }

  public Integer getNumberOfSeats() {
    return numberOfSeats;
  }

  public void setNumberOfSeats(Integer numberOfSeats) {
    this.numberOfSeats = numberOfSeats;
  }

  public TransportCategory getTransportCategory() {
    return transportCategory;
  }

  public void setTransportCategory(TransportCategory transportCategory) {
    this.transportCategory = transportCategory;
  }

  public Integer getNumberOfWorkers() {
    return numberOfWorkers;
  }

  public void setNumberOfWorkers(Integer numberOfWorkers) {
    this.numberOfWorkers = numberOfWorkers;
  }
}
