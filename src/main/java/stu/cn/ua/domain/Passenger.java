package stu.cn.ua.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "passenger")
public class Passenger {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long passengerId;

  private String privileges;
  private String firstName;
  private String lastName;
  private String phone;

  @OneToMany(mappedBy = "passenger")
  private Set<Ticket> tickets = new HashSet<>();

  public Passenger() {

  }
}
