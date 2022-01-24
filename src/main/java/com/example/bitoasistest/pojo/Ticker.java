package com.example.bitoasistest.pojo;
import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter
@Entity
@Table(name = "ticker")
public class Ticker {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "bid")
    private BigDecimal bid;
    @Column(name = "bid_size")
    private BigDecimal bidSize;
    @Column(name = "ask")
    private BigDecimal ask;
    @Column(name = "ask_size")
    private BigDecimal askSize;
    @Column(name = "daily_change")
    private BigDecimal dailyChange;
    @Column(name = "daily_change_realtime")
    private BigDecimal dailyChangeRealTime;
    @Column(name = "last_prize")
    private BigDecimal lastPrice;
    @Column(name = "volume")
    private BigDecimal volume;
    @Column(name = "high")
    private BigDecimal high;
    @Column(name = "low")
    private BigDecimal low;

}
