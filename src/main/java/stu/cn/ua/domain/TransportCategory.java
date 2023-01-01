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
@Table(name = "transport_category")
public class TransportCategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="transport_category_id")
  private long transportCategoryId;

  @Column(name = "vechicle_type")
  private String vehicleType;

  @Column(name = "the_number_of_seats_in_this_category")
  private long theNumberOfSeatsInThisCategory;

  @Column(name = "model")
  private String model;

  @OneToMany(mappedBy = "transportCategory")
  private Set<Transport> transports = new HashSet<>();

  public TransportCategory() {
  }
}
