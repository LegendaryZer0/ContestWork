package ru.company.view;

import ru.company.service.IService;


public class View implements Iview {
    private final IService service;

    public View(IService service) {
        this.service = service;
    }


    @Override
    public void show(String url) {
        service.execute(url);
        return;
    }
}
