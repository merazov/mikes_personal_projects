package com.mike.testing.junit;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mock;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Miguel Erazo
 *         Interesting sources:
 *         https://carlosbecker.com/posts/junit-rules/
 */
public class PlayingWithJunitRules {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Rule
    public MockRule mock = new MockRule(this);
    
    @Data
    @RequiredArgsConstructor
    private static class MyClass{
        String mike;
    }

    @Mock
    private MyClass service;
    
    @Test
    public void testA() throws IOException {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Dude, this is invalid!");
        
        File icon = tempFolder.newFile("icon.png");
        System.out.println("[A] path=" + icon.getAbsolutePath());
        throw new IllegalArgumentException("Dude, this is invalid!");
    }

    @Test
    public void testB() throws IOException {
        File icon = tempFolder.newFile("icon.png");
        System.out.println("[A] path=" + icon.getAbsolutePath());
    }
    
    public static void main(String[] args) {
        
    }
}
