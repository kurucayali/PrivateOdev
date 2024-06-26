package manager;

import dao.SeasonDao;
import model.Season;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

// SeasonManager sınıfı, iş mantığını içeren servis katmanıdır
public class SeasonManager {
    private SeasonDao seasonDao;

    // SeasonManager constructor
    public SeasonManager(Connection connection) {
        this.seasonDao = new SeasonDao(connection);
    }

    // Dönem ekleme metodu
    public void addSeason(Season season) throws SQLException {
        seasonDao.addSeason(season);
    }

    // Dönem id'sine göre dönemi getirme metodu
    public Season getSeasonById(int seasonId) throws SQLException {
        return seasonDao.getSeasonById(seasonId);
    }

    // Dönem güncelleme metodu
    public void updateSeason(Season season) throws SQLException {
        seasonDao.updateSeason(season);
    }

    // Dönem silme metodu
    public void deleteSeason(int seasonId) throws SQLException {
        seasonDao.deleteSeason(seasonId);
    }

    // Belirli bir otelin tüm dönemlerini listeleme metodu
    public List<Season> getSeasonsByHotelId(int hotelId) throws SQLException {
        return seasonDao.getSeasonsByHotelId(hotelId);
    }
}
