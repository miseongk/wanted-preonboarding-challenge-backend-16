package com.wanted.preonboarding.ticket.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id", nullable = false)
    private Performance performance;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private int round;

    @Column(nullable = false)
    private int gate;

    @Column(nullable = false, length = 2)
    private String line;

    @Column(nullable = false)
    private int seat;

    public Reservation(
            final Performance performance,
            final String name,
            final String phoneNumber,
            final int round,
            final int gate,
            final String line,
            final int seat
    ) {
        this.performance = performance;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.round = round;
        this.gate = gate;
        this.line = line;
        this.seat = seat;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getRound() {
        return round;
    }

    public String getLine() {
        return line;
    }

    public int getSeat() {
        return seat;
    }
}
