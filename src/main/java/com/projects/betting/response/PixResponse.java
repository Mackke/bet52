package com.projects.betting.response;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class PixResponse {
    private String name;
    private Map<String, String> pix;
}
