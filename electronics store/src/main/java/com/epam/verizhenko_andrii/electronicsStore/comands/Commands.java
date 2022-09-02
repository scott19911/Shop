package com.epam.verizhenko_andrii.electronicsStore.comands;

import com.epam.verizhenko_andrii.electronicsStore.service.Events;
/**
 *Basic interface for all commands
 */
public interface Commands {
    /**
     * command execution method
     * @param event - the object on which the command will be executed
     * @return - object after command processing
     */
    Events execute(Events event);
}
