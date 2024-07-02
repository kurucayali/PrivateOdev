package view;

import controller.SeasonController;
import controller.HotelController;
import model.Season;
import model.Hotel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;

public class SeasonManagementView extends JFrame {
    private JPanel panelMain;
    private JTable tableSeasons;
    private JComboBox<String> comboBoxHotelName;
    private JComboBox<Integer> comboBoxStartYear;
    private JComboBox<Integer> comboBoxStartMonth;
    private JComboBox<Integer> comboBoxStartDay;
    private JComboBox<Integer> comboBoxEndYear;
    private JComboBox<Integer> comboBoxEndMonth;
    private JComboBox<Integer> comboBoxEndDay;
    private JTextField textFieldName;
    private JComboBox<Double> comboBoxMultiplier;
    private JButton buttonAddSeason;
    private JButton buttonUpdateSeason;
    private JButton buttonDeleteSeason;
    private JButton buttonSaveSeason;
    private JButton buttonExit;
    private JScrollPane scrollPaneSeasons;

    private SeasonController seasonController;
    private HotelController hotelController;
    private DefaultTableModel tableModel;
    private int selectedSeasonId = -1;  // Seçilen dönemin ID'sini tutacak

    public SeasonManagementView(SeasonController seasonController, HotelController hotelController) {
        this.seasonController = seasonController;
        this.hotelController = hotelController;

        setTitle("Dönem Yönetimi Paneli");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panelMain);

        // Otel isimlerini combobox'a ekle
        List<Hotel> hotels = hotelController.getAllHotels();
        for (Hotel hotel : hotels) {
            comboBoxHotelName.addItem(hotel.getName());
        }

        // Yıl, ay ve gün combobox'larını doldur
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = currentYear; i <= currentYear + 10; i++) {
            comboBoxStartYear.addItem(i);
            comboBoxEndYear.addItem(i);
        }
        for (int i = 1; i <= 12; i++) {
            comboBoxStartMonth.addItem(i);
            comboBoxEndMonth.addItem(i);
        }
        for (int i = 1; i <= 31; i++) {
            comboBoxStartDay.addItem(i);
            comboBoxEndDay.addItem(i);
        }

        // Kat sayı seçeneklerini combobox'a ekle
        comboBoxMultiplier.addItem(1.0);
        comboBoxMultiplier.addItem(1.5);
        comboBoxMultiplier.addItem(2.0);

        // Tablo modeli
        tableModel = new DefaultTableModel(new Object[]{"ID", "Otel Adı", "Başlangıç Tarihi", "Bitiş Tarihi", "Dönem Adı", "Kat Sayı"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableSeasons.setModel(tableModel);

        // Dönemleri listele
        refreshSeasonTable();

        // Dönem Ekle Butonu Olayı
        buttonAddSeason.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOrUpdateSeason(false);
            }
        });

        // Dönem Güncelle Butonu Olayı
        buttonUpdateSeason.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableSeasons.getSelectedRow();
                if (selectedRow != -1) {
                    selectedSeasonId = (int) tableSeasons.getValueAt(selectedRow, 0);
                    loadSeasonToForm(selectedSeasonId);
                    buttonSaveSeason.setEnabled(true);
                    buttonAddSeason.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(panelMain, "Lütfen güncellemek istediğiniz dönemi seçin.");
                }
            }
        });

        // Dönem Sil Butonu Olayı
        buttonDeleteSeason.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableSeasons.getSelectedRow();
                if (selectedRow != -1) {
                    int seasonId = (int) tableSeasons.getValueAt(selectedRow, 0);
                    seasonController.deleteSeason(seasonId);
                    refreshSeasonTable();
                } else {
                    JOptionPane.showMessageDialog(panelMain, "Lütfen silmek istediğiniz dönemi seçin.");
                }
            }
        });

        // Dönem Kaydet Butonu Olayı (Yeni buton)
        buttonSaveSeason.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOrUpdateSeason(true);
                buttonSaveSeason.setEnabled(false);
                buttonAddSeason.setEnabled(true);
            }
        });

        // Çıkış Yap Butonu Olayı
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Pencereyi kapat
            }
        });
    }

    private void refreshSeasonTable() {
        List<Season> seasons = seasonController.getAllSeasons();
        tableModel.setRowCount(0); // Mevcut tüm satırları temizle
        for (Season season : seasons) {
            Hotel hotel = hotelController.getHotel(season.getHotelId());
            tableModel.addRow(new Object[]{
                    season.getId(),
                    hotel.getName(),  // Otel adı göster
                    season.getStartDate(),
                    season.getEndDate(),
                    season.getName(),  // Yeni alan
                    season.getMultiplier()  // Yeni alan
            });
        }
    }

    private void loadSeasonToForm(int seasonId) {
        Season season = seasonController.getSeason(seasonId);
        if (season != null) {
            comboBoxHotelName.setSelectedItem(hotelController.getHotel(season.getHotelId()).getName());
            String[] startDateParts = season.getStartDate().split("-");
            comboBoxStartYear.setSelectedItem(Integer.parseInt(startDateParts[0]));
            comboBoxStartMonth.setSelectedItem(Integer.parseInt(startDateParts[1]));
            comboBoxStartDay.setSelectedItem(Integer.parseInt(startDateParts[2]));
            String[] endDateParts = season.getEndDate().split("-");
            comboBoxEndYear.setSelectedItem(Integer.parseInt(endDateParts[0]));
            comboBoxEndMonth.setSelectedItem(Integer.parseInt(endDateParts[1]));
            comboBoxEndDay.setSelectedItem(Integer.parseInt(endDateParts[2]));
            textFieldName.setText(season.getName());
            comboBoxMultiplier.setSelectedItem(season.getMultiplier());
        }
    }

    private void addOrUpdateSeason(boolean isUpdate) {
        String hotelName = (String) comboBoxHotelName.getSelectedItem();
        int startYear = (int) comboBoxStartYear.getSelectedItem();
        int startMonth = (int) comboBoxStartMonth.getSelectedItem();
        int startDay = (int) comboBoxStartDay.getSelectedItem();
        int endYear = (int) comboBoxEndYear.getSelectedItem();
        int endMonth = (int) comboBoxEndMonth.getSelectedItem();
        int endDay = (int) comboBoxEndDay.getSelectedItem();
        String startDate = startYear + "-" + startMonth + "-" + startDay;
        String endDate = endYear + "-" + endMonth + "-" + endDay;
        String name = textFieldName.getText();
        double multiplier = (double) comboBoxMultiplier.getSelectedItem();

        if (!hotelName.isEmpty() && !name.isEmpty()) {
            int hotelId = hotelController.getHotelByName(hotelName).getId();
            Season season = new Season();
            season.setHotelId(hotelId);
            season.setStartDate(startDate);
            season.setEndDate(endDate);
            season.setName(name);
            season.setMultiplier(multiplier);

            if (isUpdate) {
                season.setId(selectedSeasonId);
                seasonController.updateSeason(season);
            } else {
                seasonController.addSeason(season);
            }
            refreshSeasonTable();
            clearForm();
        } else {
            JOptionPane.showMessageDialog(panelMain, "Lütfen tüm alanları doldurun.");
        }
    }

    private void clearForm() {
        comboBoxHotelName.setSelectedIndex(0);
        comboBoxStartYear.setSelectedIndex(0);
        comboBoxStartMonth.setSelectedIndex(0);
        comboBoxStartDay.setSelectedIndex(0);
        comboBoxEndYear.setSelectedIndex(0);
        comboBoxEndMonth.setSelectedIndex(0);
        comboBoxEndDay.setSelectedIndex(0);
        textFieldName.setText("");
        comboBoxMultiplier.setSelectedIndex(0);
    }
}
