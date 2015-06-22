package ar.edu.unq.desapp.groupb.builders;

import ar.edu.unq.desapp.groupb.model.Patient;


public class PatientBuilder extends AbstractBuilder<Patient> {

    @Override
    public Patient anyObject() {
        return new Patient();
    }

}