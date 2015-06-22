package ar.edu.unq.desapp.groupb.builders;

import ar.edu.unq.desapp.groupb.model.Medicine;

public class MedicineBuilder extends AbstractBuilder<Medicine> {

    @Override
    public Medicine anyObject() {
        return new Medicine();
    }

}
