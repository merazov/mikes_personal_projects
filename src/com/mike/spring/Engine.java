package com.mike.spring;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Engine {
    private final String name;
    private final int version;
}
