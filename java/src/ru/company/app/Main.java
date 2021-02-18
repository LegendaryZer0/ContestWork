package ru.company.app;

import ru.company.service.Service;
import ru.company.util.Extractor;
import ru.company.view.Iview;
import ru.company.view.View;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Iview iview = new View(new Service());
        iview.show("https://www.simbirsoft.com/");


    }
}
