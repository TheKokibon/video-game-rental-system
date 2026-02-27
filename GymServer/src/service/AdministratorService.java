package service;

import domain.Administrator;
import repository.AdministratorRepository;

public class AdministratorService {
    private final AdministratorRepository repo = new AdministratorRepository();

    public Administrator login(String username, String password) throws Exception {
        Administrator a = repo.login(username, password);
        if (a == null) throw new Exception("Pogrešan username ili password.");
        return a;
    }
}