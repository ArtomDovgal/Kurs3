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
}
