package ar.edu.unq.desapp.groupb.builders;

import ar.edu.unq.desapp.groupb.model.MedicalHistory;

public class MedicalHistoryBuilder extends AbstractBuilder<MedicalHistory> {

    @Override
    public MedicalHistory anyObject() {
        return new MedicalHistory();
    }

}