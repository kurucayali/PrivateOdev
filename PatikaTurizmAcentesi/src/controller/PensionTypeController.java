package controller;

import service.PensionTypeService;
import model.PensionType;
import java.util.List;

public class PensionTypeController {
    private PensionTypeService pensionTypeService;

    public PensionTypeController(PensionTypeService pensionTypeService) {
        this.pensionTypeService = pensionTypeService;
    }

    public void addPensionType(PensionType pensionType) {
        pensionTypeService.addPensionType(pensionType);
    }

    public PensionType getPensionType(int id) {
        return pensionTypeService.getPensionType(id);
    }

    public List<PensionType> getAllPensionTypes() {
        return pensionTypeService.getAllPensionTypes();
    }

    public void updatePensionType(PensionType pensionType) {
        pensionTypeService.updatePensionType(pensionType);
    }

    public void deletePensionType(int id) {
        pensionTypeService.deletePensionType(id);
    }
}
