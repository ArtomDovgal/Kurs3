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
  private long numberOfSeats;

  @ManyToOne
  @JoinColumn(name = "trasportCateforyId")
  private TransportCategory transportCategory;
  private long numberOfWorkers;


  public Transport() {

  }
}
