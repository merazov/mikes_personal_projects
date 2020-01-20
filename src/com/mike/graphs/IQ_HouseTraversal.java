package com.mike.graphs;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

public class IQ_HouseTraversal {

    /**
     * Match: a course where k packages exactly are picked up
     * 
     * @param vertex The current vertex
     * @param remaining Number of packages left to be picked
     * @param soFar The path followed so far
     * @param matches Matches collector
     */
    public void dfs(final Vertex vertex, int remaining,
                    final List<Vertex> soFar, List<List<Vertex>> matches) {
        if (vertex.getValue() > remaining) {
            return;
        } else if (vertex.getValue() == remaining) {
            List<Vertex> path = new ArrayList<>(soFar);
            path.add(vertex);
            matches.add(path);
            return;
        }

        //visiting
        remaining = remaining - vertex.getValue();
        List<Vertex> soFarExtended = new ArrayList<>(soFar);
        soFarExtended.add(vertex);
        vertex.setVisited(true);

        for (Vertex adjacent : vertex.getAdjacent()) {
            if (!adjacent.isVisited()) {
                dfs(adjacent, remaining, soFarExtended, matches);
            }
        }
    }

    public static void main(String[] args) {
        // 1
        // 2 3
        // 4
        Vertex vertex1 = new Vertex();
        Vertex vertex2 = new Vertex();
        Vertex vertex3 = new Vertex();
        Vertex vertex4 = new Vertex();

        vertex1.setValue(1);
        vertex1.setAdjacent(ImmutableList.of(vertex2, vertex3));

        vertex2.setValue(2);
        vertex2.setAdjacent(ImmutableList.of(vertex4));

        vertex3.setValue(3);
        vertex3.setAdjacent(new ArrayList<>());

        vertex4.setValue(4);
        vertex4.setAdjacent(new ArrayList<>());

        IQ_HouseTraversal sut = new IQ_HouseTraversal();
        List<List<Vertex>> matches = new ArrayList<>();

        sut.dfs(vertex1, 4, new ArrayList<>(), matches);
        System.out.println("match:" + matches);
        assertThat(matches.size(), is(1));
        assertThat(matches.get(0), hasItem(vertex1));
        assertThat(matches.get(0), hasItem(vertex3));

        vertex1.setVisited(false);
        vertex2.setVisited(false);
        vertex3.setVisited(false);
        vertex4.setVisited(false);
        matches = new ArrayList<>();
        sut.dfs(vertex1, 7, new ArrayList<>(), matches);
        System.out.println("match:" + matches);
        assertThat(matches.size(), is(1));
        assertThat(matches.get(0).size(), is(3));
        assertThat(matches.get(0), hasItem(vertex2));
        assertThat(matches.get(0), hasItem(vertex4));

        //Two matches
        // 1
        // 2 3
        // 4
        vertex1 = new Vertex();
        vertex2 = new Vertex();
        vertex3 = new Vertex();
        vertex4 = new Vertex();

        vertex1.setValue(1);
        vertex1.setAdjacent(ImmutableList.of(vertex2, vertex3));

        vertex2.setValue(2);
        vertex2.setAdjacent(ImmutableList.of(vertex4));

        vertex3.setValue(2);
        vertex3.setAdjacent(new ArrayList<>());

        vertex4.setValue(4);
        vertex4.setAdjacent(new ArrayList<>());

        matches = new ArrayList<>();
        sut.dfs(vertex1, 3, new ArrayList<>(), matches);
        System.out.println("match:" + matches);
        assertThat(matches.size(), is(2));
        assertThat(matches.get(0), hasItem(vertex1));
        assertThat(matches.get(0), hasItem(vertex2));
        assertThat(matches.get(0).size(), is(2));
        assertThat(matches.get(1), hasItem(vertex1));
        assertThat(matches.get(1), hasItem(vertex3));
        assertThat(matches.get(1).size(), is(2));

        vertex1.setVisited(false);
        vertex2.setVisited(false);
        vertex3.setVisited(false);
        vertex4.setVisited(false);
        matches = new ArrayList<>();
        sut.dfs(vertex1, 7, new ArrayList<>(), matches);
        System.out.println("match:" + matches);
        assertThat(matches.size(), is(1));
        assertThat(matches.get(0).size(), is(3));
        assertThat(matches.get(0), hasItem(vertex2));
        assertThat(matches.get(0), hasItem(vertex4));
    }
}
