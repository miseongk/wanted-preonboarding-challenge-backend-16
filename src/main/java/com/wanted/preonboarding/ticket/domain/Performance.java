package com.wanted.preonboarding.ticket.domain;

import com.wanted.preonboarding.ticket.domain.vo.PerformanceType;
import com.wanted.preonboarding.ticket.domain.vo.ReserveStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Performance {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int round;

    @Column(nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    private PerformanceType type;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ReserveStatus isReserve = ReserveStatus.DISABLE;

    public boolean canPay(final BigDecimal balance) {
        return balance.compareTo(BigDecimal.valueOf(price)) >= 1;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
