package com.projects.betting.response;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.projects.betting.dto.GameDto;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class BettingRowResponse {
    private UUID id;
    private List<GameDto> games;
    private Instant createdAt;
}
