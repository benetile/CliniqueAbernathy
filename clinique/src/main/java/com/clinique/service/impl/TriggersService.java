package com.clinique.service.impl;

public enum TriggersService {
    HEMOGLOBIN("Hémoglobine A1C","hémoglobine A1C","Hemoglobin A1C"," hemoglobin A1C "),
    MICROALBUMIN("Microalbumine", "microalbumine","Microalbumin","microalbumin"),
    WEIGHT("Poids","poids","Weight","weight"),
    SMOKER("Fumeur","fumeur","smoker","Smoker"),
    ABNORMAL("Anormal","anormal","Abnormal","abnormal"),
    CHOLESTEROL("Cholestérol","cholestérol","Cholesterol","cholesterol"),
    VERTIGO("Vertige","vertige","Vertigo","vertigo"),
    RELAPSE("Rechute","rechute","Relapse", "relapse"),
    REACTION("réaction", "Réaction","reaction", "Reaction"),
    ANTIBODIES("Anticorps","anticorps","Antibodies", "antibody"),
    SIZE("Taille","taille","Size", "size");


    TriggersService(String triggerMajOne, String triggerMinOne, String triggerMajTwo, String triggerMinTwo) {
        triggerFrMaj = triggerMajOne;
        triggerFrMin = triggerMinOne;
        triggerEnMaj = triggerMajTwo;
        triggerEnmin = triggerMinTwo;
    }

    private String triggerFrMaj;
    private String triggerFrMin;
    private String triggerEnMaj;
    private String triggerEnmin;


    @Override
    public String toString() {
        return super.toString();
    }

    public String getTriggerEnMaj() {
        return triggerEnMaj;
    }

    public String getTriggerFrMaj() {
        return triggerFrMaj;
    }

    public String getTriggerEnmin() {
        return triggerEnmin;
    }

    public String getTriggerFrMin() {
        return triggerFrMin;
    }
}
