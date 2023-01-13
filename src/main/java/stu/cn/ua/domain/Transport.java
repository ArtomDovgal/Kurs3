package stu.cn.ua.domain;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import stu.cn.ua.mapper.TransportMapper;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "transport")
public class Transport {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "transport_id")
  private Long transportId;

  @Column(name = "number_of_seats")
  private Integer numberOfSeats;

  @ManyToOne
  @JoinColumn(name = "transport_category_id")
  private TransportCategory transportCategory;

  @Column(name="number_of_workers")
  private Integer numberOfWorkers;

  public Transport() {

  }

  public Transport(TransportMapper transportMapper) {
    this.transportId = transportMapper.getTransportId();
    this.numberOfSeats = transportMapper.getNumberOfSeats();
    this.numberOfWorkers = transportMapper.getNumberOfWorkers();
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
