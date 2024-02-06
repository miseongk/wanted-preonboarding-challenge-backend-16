package com.wanted.preonboarding.ticket.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ReserveRequest(

        String name,
        String phoneNumber,
        BigDecimal balance,
        UUID performanceId,
        int round,
        String line,
        int seat
) {

}
