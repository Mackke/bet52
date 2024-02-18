package com.projects.betting.request;

import java.util.Map;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class BettingRowRequest {
    private String name;
    private Map<String, String> row;
}
