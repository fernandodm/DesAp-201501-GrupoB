package ar.edu.unq.desapp.groupb.builders;

import ar.edu.unq.desapp.groupb.model.Symptom;

public class SymptomBuilder extends AbstractBuilder<Symptom> {

    @Override
    public Symptom anyObject() {
        return new Symptom();
    }

}