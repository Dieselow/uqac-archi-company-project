package ca.uqac.archicompanyproject.domain.secretary;

import ca.uqac.archicompanyproject.domain.users.User;
import javassist.NotFoundException;

public interface SecretaryService {
    Secretary saveSecretary(Secretary secretary);
    Secretary addSecretary(Secretary secretary);
    void deleteSecretary(Secretary secretary);
    Secretary findSecretaryById(Integer id) throws NotFoundException;
    Secretary findSecretaryByEmail(String email) throws NotFoundException;
    Iterable<Secretary> getSecretaries();
}
