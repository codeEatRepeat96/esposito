package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import model.Visit;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class VisitRepository implements PanacheRepositoryBase<Visit, Long> {

    public List<Visit> findByPatient(String patientSsn) {
        return list("patientSsn", patientSsn);
    }

}
