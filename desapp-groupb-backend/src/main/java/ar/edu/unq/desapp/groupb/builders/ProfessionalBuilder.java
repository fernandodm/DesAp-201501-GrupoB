package ar.edu.unq.desapp.groupb.builders;

import ar.edu.unq.desapp.groupb.model.Professional;

public class ProfessionalBuilder extends AbstractBuilder<Professional> {

    @Override
    public Professional anyObject() {
        return new Professional();
    }

}