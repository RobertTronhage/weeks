package se.tronhage;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;
import javax.swing.*;

public class WeekIconApp {

    public static void main(String[] args) {
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }

        try {

            LocalDate currentDate = LocalDate.now();
            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            int weekNumber = currentDate.get(weekFields.weekOfYear());


            String imagePath = "/images/week" + weekNumber + ".png"; // Place images in a resource folder
            ImageIcon icon = new ImageIcon(WeekIconApp.class.getResource(imagePath));
            Image trayImage = icon.getImage();

            PopupMenu popup = new PopupMenu();

            MenuItem exitItem = new MenuItem("Exit");
            exitItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            popup.add(exitItem);

            TrayIcon trayIcon = new TrayIcon(trayImage, "Current Week: " + weekNumber, popup);
            trayIcon.setImageAutoSize(true);

            SystemTray tray = SystemTray.getSystemTray();
            tray.add(trayIcon);

            trayIcon.displayMessage("Roberto!", "ThiZ iZ w33k " + weekNumber, TrayIcon.MessageType.INFO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}