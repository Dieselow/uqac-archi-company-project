package ca.uqac.archicompanyproject.domain.secretary;

import javassist.NotFoundException;

public interface SecretaryService {
    Secretary saveSecretary(Secretary secretary);
    void deleteSecretary(Secretary secretary);
    Secretary findSecretaryById(Integer id) throws NotFoundException;
    Secretary findSecretaryByEmail(String email) throws NotFoundException;
    Iterable<Secretary> getSecretaries();
}
