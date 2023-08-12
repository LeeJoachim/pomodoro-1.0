import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;



class TimerView extends JPanel implements ActionListener {
    private LocalDateTime dDay = TimerCalc.getNow();
    private JLabel nowText = new JLabel();
    private JLabel untilText = new JLabel();
    private JLabel yearText = new JLabel();
    private JLabel monthText = new JLabel();
    private JLabel dayText = new JLabel();
    private JLabel hourText = new JLabel();
    private JLabel minuteText = new JLabel();
    private JLabel urlText = new JLabel();
    private boolean isSetURL = false;
    JLabel alarmTag = new JLabel(" Alarm : off ");
    private boolean isOnAlarm = false;
    private boolean isPlaying = false;




    public TimerView() {
        this.setLayout(new GridLayout(11, 2));
        
        nowText.setHorizontalAlignment(JLabel.CENTER);
        untilText.setHorizontalAlignment(JLabel.CENTER);
        yearText.setHorizontalAlignment(JLabel.CENTER);
        monthText.setHorizontalAlignment(JLabel.CENTER);
        dayText.setHorizontalAlignment(JLabel.CENTER);
        hourText.setHorizontalAlignment(JLabel.CENTER);
        minuteText.setHorizontalAlignment(JLabel.CENTER);


        JLabel nowTag = new JLabel(" Now : ");
        nowTag.setHorizontalAlignment(SwingConstants.CENTER);
        add(nowTag);
        add(nowText);

        JLabel untilTag = new JLabel(" Until : ");
        untilTag.setHorizontalAlignment(SwingConstants.CENTER);
        add(untilTag);
        add(untilText);

        JLabel yearTag = new JLabel(" Year : ");
        yearTag.setHorizontalAlignment(SwingConstants.CENTER);
        add(yearTag);
        add(yearText);

        JLabel monthTag = new JLabel(" Month : ");
        monthTag.setHorizontalAlignment(SwingConstants.CENTER);
        add(monthTag);
        add(monthText);

        JLabel dayTag = new JLabel(" Day : ");
        dayTag.setHorizontalAlignment(SwingConstants.CENTER);
        add(dayTag);
        add(dayText);

        JLabel hourTag = new JLabel(" Hour : ");
        hourTag.setHorizontalAlignment(SwingConstants.CENTER);
        add(hourTag);
        add(hourText);

        JLabel minuteTag = new JLabel(" Minute : ");
        minuteTag.setHorizontalAlignment(SwingConstants.CENTER);
        add(minuteTag);
        add(minuteText);

        JLabel urlTag = new JLabel(" URL : ");
        urlTag.setHorizontalAlignment(SwingConstants.CENTER);
        add(urlTag);

        
        urlText.setHorizontalAlignment(SwingConstants.CENTER);
        add(urlText);

        JTextField urlField = new JTextField();
        urlField.setHorizontalAlignment(SwingConstants.CENTER);
        add(urlField);

        /* JButton ok */
        JButton setURL = new JButton("Set URL");
        setURL.addActionListener((e) -> {
            urlText.setText(urlField.getText());
            urlField.setText("");
            isSetURL = true;

        });
        add(setURL);

        alarmTag.setHorizontalAlignment(SwingConstants.CENTER);
        add(alarmTag);

        JButton onAlarm = new JButton("On/Off");
        onAlarm.addActionListener((e) -> {
            if (isOnAlarm == false) {
                alarmTag.setText(" Alarm : on ");
                isOnAlarm = true;
            } else {
                alarmTag.setText(" Alarm : off ");
                isOnAlarm = false;
            }
        });
        add(onAlarm);


        JButton positive30m = new JButton("+ 30min");
        positive30m.addActionListener((e) -> {
            setdDay(dDay.plusMinutes(30));
        });
        add(positive30m);

        JButton negative30m = new JButton("- 30min");
        negative30m.addActionListener((e) -> {
            setdDay(dDay.minusMinutes(30));
        });
        add(negative30m);
        

    }
    public void setdDay(LocalDateTime dDay) {
        this.dDay = dDay;
        startTimer();
    }

    public void startTimer() {
        Timer timer = new Timer(1000, e -> {

            if (isSetURL == true && TimerCalc.gapMinutesToString(TimerCalc.getNow(), dDay).equals("0")) {
                try {
                    Desktop.getDesktop().browse(new URI(urlText.getText()));
                    isSetURL = false;
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                    isSetURL = false;
                }
            }

            if (isPlaying == false && isOnAlarm == true && TimerCalc.gapMinutesToString(TimerCalc.getNow(), dDay).equals("0")) {
                isPlaying = true;
            
                new Thread( () -> {
                    try {
                        AudioInputStream audioInputStream;
                        audioInputStream = AudioSystem.getAudioInputStream(new File("alarm.wav"));
                        Clip clip = AudioSystem.getClip();
                        clip.open(audioInputStream);
                        clip.start();
                        Thread.sleep(5000);
                        clip.close();
                        isPlaying = false;
                    } catch (IOException | LineUnavailableException | UnsupportedAudioFileException | InterruptedException e1) {
                        e1.printStackTrace();
                        alarmTag.setText(" Alarm : off ");
                        isOnAlarm = false;
                        isPlaying = false;

                    }
                }).start();
            }
            nowText.setText(TimerCalc.getNowToString("yyyy-MM-dd HH:mm:ss"));
            untilText.setText(TimerCalc.getOfToString(dDay, "yyyy-MM-dd HH:mm:ss"));
            yearText.setText(TimerCalc.gapYearsToString(TimerCalc.getNow(), dDay));
            monthText.setText(TimerCalc.gapMonthsToString(TimerCalc.getNow(), dDay));
            dayText.setText(TimerCalc.gapDaysToString(TimerCalc.getNow(), dDay));
            hourText.setText(TimerCalc.gapHoursToString(TimerCalc.getNow(), dDay));
            minuteText.setText(TimerCalc.gapMinutesToString(TimerCalc.getNow(), dDay));
        });

        timer.setInitialDelay(0);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}