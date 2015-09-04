package com.github.eepiloto.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public final class UtilDao {

    private static ValidatorFactory validator=Validation.buildDefaultValidatorFactory();

    public static Validator getValidator() {
        return validator.getValidator();
    }

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("eepilotoPU");

    public static EntityManager getFactory() {
        return factory.createEntityManager();
    }

}
