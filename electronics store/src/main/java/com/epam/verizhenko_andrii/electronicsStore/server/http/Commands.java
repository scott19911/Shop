package com.epam.verizhenko_andrii.electronicsStore.server.http;

/**
 *Basic interface for all commands
 */
public interface Commands {
    /**
     * execute command and return result by string
     *
     * @return - String result
     */
    String execute();
}
