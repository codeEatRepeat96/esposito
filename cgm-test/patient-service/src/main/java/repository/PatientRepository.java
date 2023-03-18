package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import model.Patient;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PatientRepository implements PanacheRepositoryBase<Patient, String> {

}
