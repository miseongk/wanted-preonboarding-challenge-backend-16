package com.wanted.preonboarding.ticket.application.dto;

import java.util.UUID;

public record ReserveResponse(

        UUID performanceId,
        String performanceName,
        int round,
        String line,
        int seat,
        String name,
        String phoneNumber
) {

}
