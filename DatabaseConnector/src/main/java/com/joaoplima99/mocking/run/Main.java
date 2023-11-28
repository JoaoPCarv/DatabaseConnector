package com.joaoplima99.mocking.run;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {

        BufferedReader bf = new BufferedReader(new FileReader(new File(Main.class.getResource("/testing-patterns/asterisk-chains.pat").toURI())));
        bf.lines().forEach(System.out::println);
    }
}