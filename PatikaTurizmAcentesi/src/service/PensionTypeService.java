package service;

import dao.PensionTypeDAO;
import model.PensionType;
import java.util.List;

public class PensionTypeService {
    private PensionTypeDAO pensionTypeDAO;

    public PensionTypeService(PensionTypeDAO pensionTypeDAO) {
        this.pensionTypeDAO = pensionTypeDAO;
    }

    public void addPensionType(PensionType pensionType) {
        pensionTypeDAO.addPensionType(pensionType);
    }

    public PensionType getPensionType(int id) {
        return pensionTypeDAO.getPensionType(id);
    }

    public List<PensionType> getAllPensionTypes() {
        return pensionTypeDAO.getAllPensionTypes();
    }

    public void updatePensionType(PensionType pensionType) {
        pensionTypeDAO.updatePensionType(pensionType);
    }

    public void deletePensionType(int id) {
        pensionTypeDAO.deletePensionType(id);
    }
}
