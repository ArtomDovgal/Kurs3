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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "transport_id")
  private long transportId;

  @Column(name = "number_of_seats")
  private Integer numberOfSeats;

  @ManyToOne
  @JoinColumn(name = "transport_category_id")
  private TransportCategory transportCategory;

  @Column(name="number_of_workers")
  private Integer numberOfWorkers;

  public Transport() {

  }

}
