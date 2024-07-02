package controller;

import model.Season;
import service.SeasonService;

import java.util.List;

public class SeasonController {
    private SeasonService seasonService;

    public SeasonController(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    public void addSeason(Season season) {
        seasonService.addSeason(season);
    }

    public Season getSeason(int id) {
        return seasonService.getSeason(id);
    }

    public List<Season> getAllSeasons() {
        return seasonService.getAllSeasons();
    }

    public void updateSeason(Season season) {
        seasonService.updateSeason(season);
    }

    public void deleteSeason(int id) {
        seasonService.deleteSeason(id);
    }
}
