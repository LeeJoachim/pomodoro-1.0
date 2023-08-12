import javax.swing.*;
import java.awt.*;

class MainFrame extends JFrame {

    private TimerView timerView;

    public MainFrame() {
        basicSettings();

        // View
        Container container = getContentPane();
        timerView = new TimerView();
        container.add(timerView);

        // Menu Settings
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu scheduleMenu = new JMenu("Schedule");
        menuBar.add(scheduleMenu);
                JMenuItem setItem = new JMenuItem("Set");
                scheduleMenu.add(setItem);
                setItem.addActionListener((e) -> {
                    DDayDialog dDayDialog = new DDayDialog(timerView);
                    dDayDialog.setModal(true);
                    dDayDialog.setVisible(true);
                });
                JMenuItem exitItem = new JMenuItem("Exit");
                scheduleMenu.add(exitItem);
                        exitItem.addActionListener((e) -> { System.exit(0); }); 


        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        JMenuItem timerInfo = new JMenuItem("Pomodoro's Info");
        helpMenu.add(timerInfo);
        timerInfo.addActionListener((e) -> {
            JOptionPane.showMessageDialog(null, 
                    "Pomodoro 1.0 for Macintosh\n\n" + 
                    "Author: Lee Joachim\n" +
                    "Date: Sunday, August 13, 2023\n" +
                    "Version: 1.0\n", 
                    "pomodoro's Info", 
                            JOptionPane.INFORMATION_MESSAGE);
        });                


        setVisible(true);
        
    }

    private void basicSettings() {
        setTitle("POMODORO");
        setSize(500, 600);
        int x = FrameClac.getCenterX(getWidth());
        int y = FrameClac.getCenterY(getHeight());
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class FrameClac {
    public static int getCenterX(int width) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        return (dim.width - width) / 2;
    }
    public static int getCenterY(int height) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        return (dim.height - height) / 2;
    }
}