package service;

import dao.SeasonDAO;
import model.Season;

import java.util.List;

public class SeasonService {
    private SeasonDAO seasonDAO;

    public SeasonService(SeasonDAO seasonDAO) {
        this.seasonDAO = seasonDAO;
    }

    public void addSeason(Season season) {
        seasonDAO.addSeason(season);
    }

    public Season getSeason(int id) {
        return seasonDAO.getSeason(id);
    }

    public List<Season> getAllSeasons() {
        return seasonDAO.getAllSeasons();
    }

    public void updateSeason(Season season) {
        seasonDAO.updateSeason(season);
    }

    public void deleteSeason(int id) {
        seasonDAO.deleteSeason(id);
    }
}
