import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.DateTimeException;
import java.time.LocalDateTime;

class DDayDialog extends JDialog {
    private TimerView timerView;
            
    public DDayDialog(TimerView timerView) {
        super();
        this.timerView = timerView;
        setTitle("Set Timer");
        setSize(300, 200);

        int x = FrameClac.getCenterX(getWidth());
        int y = FrameClac.getCenterY(getHeight());
        setLocation(x, y);

        Container container = getContentPane();
        JPanel panel = new DialogPanel();
        container.add(panel);
    }

    class DialogPanel extends JPanel implements ActionListener {

        static final String[][] calendarArray = {
            {"2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"},
            {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"},
            {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",            "31"},
            {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",            "21", "22", "23"},
            {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",            "31", "32", "33", "34", "35", "36", "37", "38", "39", "40",       "41", "42", "43", "44", "45", "46", "47", "48", "49", "50",         "51", "52", "53", "54", "55", "56", "57", "58", "59"},
        };

        static final int YEAR = 0;
        static final int MONTH = 1;
        static final int DAY = 2;
        static final int HOUR = 3;
        static final int MINUTE = 4;

        static final int YES = 1;
        static final int NO = 0;

        public DialogPanel() {
            setLayout(new GridLayout(6, 2));
            
            JLabel yearTag = new JLabel(" Year : ");
            yearTag.setHorizontalAlignment(SwingConstants.CENTER);
            add(yearTag);
            JComboBox<String> yearBox = new JComboBox<String>(calendarArray[YEAR]);
            add(yearBox);

            JLabel monthTag = new JLabel(" Month : ");
            monthTag.setHorizontalAlignment(SwingConstants.CENTER);
            add(monthTag);
            JComboBox<String> monthBox = new JComboBox<String>(calendarArray[MONTH]);
            add(monthBox);

            JLabel dayTag = new JLabel(" Day : ");
            dayTag.setHorizontalAlignment(SwingConstants.CENTER);
            add(dayTag);
            JComboBox<String> dayBox = new JComboBox<String>(calendarArray[DAY]);
            add(dayBox);

            JLabel hourTag = new JLabel(" Hour : ");
            hourTag.setHorizontalAlignment(SwingConstants.CENTER);
            add(hourTag);
            JComboBox<String> hourBox = new JComboBox<String>(calendarArray[HOUR]);
            add(hourBox);

            JLabel minuteTag = new JLabel(" Minute : ");
            minuteTag.setHorizontalAlignment(SwingConstants.CENTER);
            add(minuteTag);
            JComboBox<String> minuteBox = new JComboBox<String>(calendarArray[MINUTE]);
            add(minuteBox);

            

            JButton okButton = new JButton("OK");
            add(okButton);
            okButton.addActionListener((e) -> {
                try {    
                    int year = Integer.parseInt((String) yearBox.getSelectedItem());
                    int month = Integer.parseInt((String) monthBox.getSelectedItem());
                    int day = Integer.parseInt((String) dayBox.getSelectedItem());
                    int hour = Integer.parseInt((String) hourBox.getSelectedItem());
                    int minute = Integer.parseInt((String) minuteBox.getSelectedItem());
                    LocalDateTime dDay = LocalDateTime.of(year, month, day, hour, minute);
                    timerView.setdDay(dDay);
                    dispose();
                } catch (DateTimeException ex) {
                    JOptionPane.showMessageDialog(this, "입력된 값이 올바르지 않습니다.", "경고", JOptionPane.WARNING_MESSAGE);
                }
                
            });

            JButton cancelButton = new JButton("Cancel");
            add(cancelButton);
            cancelButton.addActionListener((e) -> {
                dispose();
            });

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
        }

    }


}