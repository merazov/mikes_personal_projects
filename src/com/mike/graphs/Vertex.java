package com.mike.graphs;

import java.util.List;

import lombok.Data;

@Data
public class Vertex {
    private int value;
    private boolean visited = false;
    private List<Vertex> adjacent;
}
