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

@Table(name = "passenger")
public class Passenger {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "passenger_id")
  private Long passengerId;

  private String privileges;
  @Column(name = "first_name")
  private String firstName;
  @Column(name = "last_name")
  private String lastName;
  private String phone;

  @OneToMany(mappedBy = "passenger")
  private Set<Ticket> tickets = new HashSet<>();

  public Passenger() {

  }
}
