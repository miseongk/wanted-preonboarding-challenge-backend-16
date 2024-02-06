package com.wanted.preonboarding.ticket.domain;

import com.wanted.preonboarding.core.exception.PreonboardingException;
import com.wanted.preonboarding.ticket.domain.vo.ReserveStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PerformanceSeatInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id", nullable = false)
    private Performance performance;

    @Column(nullable = false)
    private int round;

    @Column(nullable = false)
    private int gate;

    @Column(nullable = false, length = 2)
    private String line;

    @Column(nullable = false)
    private int seat;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ReserveStatus isReserve = ReserveStatus.ENABLE;

    public void reserve() {
        if (isReserve == ReserveStatus.DISABLE) {
            throw new PreonboardingException("이미 예약된 좌석입니다.");
        }
        this.isReserve = ReserveStatus.DISABLE;
    }

    public int getRound() {
        return round;
    }

    public int getGate() {
        return gate;
    }

    public String getLine() {
        return line;
    }

    public int getSeat() {
        return seat;
    }
}
