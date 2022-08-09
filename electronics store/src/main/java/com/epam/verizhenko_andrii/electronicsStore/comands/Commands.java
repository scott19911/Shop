package com.epam.verizhenko_andrii.electronicsStore.comands;

import com.epam.verizhenko_andrii.electronicsStore.service.Events;

public interface Commands {
     Events execute(Events event);
}
