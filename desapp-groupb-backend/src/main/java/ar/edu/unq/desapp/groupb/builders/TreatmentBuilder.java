package ar.edu.unq.desapp.groupb.builders;

import ar.edu.unq.desapp.groupb.model.Treatment;

public class TreatmentBuilder extends AbstractBuilder<Treatment> {

    @Override
    public Treatment anyObject() {
        return new Treatment();
    }

}