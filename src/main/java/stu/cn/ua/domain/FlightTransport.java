package stu.cn.ua.domain;

import javax.persistence.*;

@Entity
public class FlightTransport {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long flightTransportId;


  @Column(name = "flight_id")
  private Long flight;


  @Column(name = "transport_id")
  private Long transport;


  public long getFlightTransportId() {
    return flightTransportId;
  }

  public void setFlightTransportId(long flightTransportId) {
    this.flightTransportId = flightTransportId;
  }

  public Long getFlight() {
    return flight;
  }

  public void setFlight(Long flight) {
    this.flight = flight;
  }

  public Long getTransport() {
    return transport;
  }

  public void setTransport(Long transport) {
    this.transport = transport;
  }
}
