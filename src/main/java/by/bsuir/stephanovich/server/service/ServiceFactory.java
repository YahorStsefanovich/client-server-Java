package by.bsuir.stephanovich.server.service;

import by.bsuir.stephanovich.server.dao.DaoFactory;

public class ServiceFactory {
    private DaoFactory daoFactory;
    private static ServiceFactory serviceFactory;

    private ServiceFactory(){

    }

    public static ServiceFactory getServiceFactory(){
        if (serviceFactory == null)
            serviceFactory = new ServiceFactory();
        return serviceFactory;
    }
}
