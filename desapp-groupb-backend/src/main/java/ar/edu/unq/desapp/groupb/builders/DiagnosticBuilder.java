package ar.edu.unq.desapp.groupb.builders;

import ar.edu.unq.desapp.groupb.model.Diagnostic;

public class DiagnosticBuilder extends AbstractBuilder<Diagnostic> {

    @Override
    public Diagnostic anyObject() {
        return new Diagnostic();
    }

}
