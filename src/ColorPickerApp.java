import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorPickerApp extends JFrame {
    private JSlider rSlider, gSlider, bSlider;
    private JTextField rText, gText, bText;
    private JSlider cSlider, mSlider, ySlider, kSlider;
    private JTextField cText, mText, yText, kText;
    private JSlider hSlider, lSlider, sSlider;
    private JTextField hText, lText, sText;
    private JPanel colorDisplay;
    private ButtonGroup colorFormatGroup;
    private JRadioButton rgbButton, hlsButton, cmykButton;

    public ColorPickerApp() {
        setTitle("Color Picker");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 1));

        // Color display panel
        colorDisplay = new JPanel();
        colorDisplay.setBackground(Color.BLACK);
        add(colorDisplay);

        // Radio buttons for color format selection
        colorFormatGroup = new ButtonGroup();
        rgbButton = new JRadioButton("RGB", true);
        hlsButton = new JRadioButton("HLS");
        cmykButton = new JRadioButton("CMYK");
        JPanel radioPanel = new JPanel();
        radioPanel.add(rgbButton);
        radioPanel.add(hlsButton);
        radioPanel.add(cmykButton);
        add(radioPanel);

        // RGB sliders and text fields
        JPanel rgbPanel = new JPanel();
        rSlider = createSlider(0, 255, 0);
        gSlider = createSlider(0, 255, 0);
        bSlider = createSlider(0, 255, 0);
        rText = createTextField();
        gText = createTextField();
        bText = createTextField();

        rgbPanel.add(new JLabel("R:"));
        rgbPanel.add(rSlider);
        rgbPanel.add(rText);
        rgbPanel.add(new JLabel("G:"));
        rgbPanel.add(gSlider);
        rgbPanel.add(gText);
        rgbPanel.add(new JLabel("B:"));
        rgbPanel.add(bSlider);
        rgbPanel.add(bText);
        add(rgbPanel);

        // CMYK sliders and text fields
        JPanel cmykPanel = new JPanel();
        cSlider = createSlider(0, 100, 0);
        mSlider = createSlider(0, 100, 0);
        ySlider = createSlider(0, 100, 0);
        kSlider = createSlider(0, 100, 100);
        cText = createTextField();
        mText = createTextField();
        yText = createTextField();
        kText = createTextField();

        cmykPanel.add(new JLabel("C:"));
        cmykPanel.add(cSlider);
        cmykPanel.add(cText);
        cmykPanel.add(new JLabel("M:"));
        cmykPanel.add(mSlider);
        cmykPanel.add(mText);
        cmykPanel.add(new JLabel("Y:"));
        cmykPanel.add(ySlider);
        cmykPanel.add(yText);
        cmykPanel.add(new JLabel("K:"));
        cmykPanel.add(kSlider);
        cmykPanel.add(kText);
        add(cmykPanel);

        // HLS sliders and text fields
        JPanel hlsPanel = new JPanel();
        hSlider = createSlider(0, 360, 0);
        lSlider = createSlider(0, 100, 0);
        sSlider = createSlider(0, 100, 0);
        hText = createTextField();
        lText = createTextField();
        sText = createTextField();

        hlsPanel.add(new JLabel("H:"));
        hlsPanel.add(hSlider);
        hlsPanel.add(hText);
        hlsPanel.add(new JLabel("L:"));
        hlsPanel.add(lSlider);
        hlsPanel.add(lText);
        hlsPanel.add(new JLabel("S:"));
        hlsPanel.add(sSlider);
        hlsPanel.add(sText);
        add(hlsPanel);


        Button btn = new Button("Choose color");
        add(btn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color selectedColor = JColorChooser.showDialog(ColorPickerApp.this, "Select a Color", colorDisplay.getBackground());
                if (selectedColor != null) {
                    colorDisplay.setBackground(selectedColor);
                }
                int r = selectedColor.getRed();
                int g = selectedColor.getGreen();
                int b = selectedColor.getBlue();
                rText.setText(Integer.toString(r));
                gText.setText(Integer.toString(g));
                bText.setText(Integer.toString(b));
                updateRGBFromTextFields();
            }
        });


        // Grouping radio buttons
        colorFormatGroup.add(rgbButton);
        colorFormatGroup.add(hlsButton);
        colorFormatGroup.add(cmykButton);

        // Add listeners for radio buttons
        rgbButton.addActionListener(e -> enableRGBControls());
        hlsButton.addActionListener(e -> enableHLSControls());
        cmykButton.addActionListener(e -> enableCMYKControls());

        // Update color on slider change
        ChangeListener changeListener = e -> updateColor();

        rSlider.addChangeListener(changeListener);
        gSlider.addChangeListener(changeListener);
        bSlider.addChangeListener(changeListener);
        cSlider.addChangeListener(changeListener);
        mSlider.addChangeListener(changeListener);
        ySlider.addChangeListener(changeListener);
        kSlider.addChangeListener(changeListener);
        hSlider.addChangeListener(changeListener);
        lSlider.addChangeListener(changeListener);
        sSlider.addChangeListener(changeListener);

        // Update RGB from text fields
        rText.addActionListener(e -> updateRGBFromTextFields());
        gText.addActionListener(e -> updateRGBFromTextFields());
        bText.addActionListener(e -> updateRGBFromTextFields());

        // Update CMYK from text fields
        cText.addActionListener(e -> updateCMYKFromTextFields());
        mText.addActionListener(e -> updateCMYKFromTextFields());
        yText.addActionListener(e -> updateCMYKFromTextFields());
        kText.addActionListener(e -> updateCMYKFromTextFields());

        // Update HLS from text fields
        hText.addActionListener(e -> updateHLSFromTextFields());
        lText.addActionListener(e -> updateHLSFromTextFields());
        sText.addActionListener(e -> updateHLSFromTextFields());

        // Initialize with RGB controls enabled
        enableRGBControls();
    }

    private JSlider createSlider(int min, int max, int initial) {
        JSlider slider = new JSlider(min, max, initial);
        slider.setMajorTickSpacing((max - min) / 5);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        return slider;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField(5);
        textField.setText("0");
        return textField;
    }

    private void enableRGBControls() {
        rSlider.setEnabled(true);
        gSlider.setEnabled(true);
        bSlider.setEnabled(true);
        rText.setEnabled(true);
        gText.setEnabled(true);
        bText.setEnabled(true);

        // Disable HLS and CMYK controls
        hSlider.setEnabled(false);
        lSlider.setEnabled(false);
        sSlider.setEnabled(false);
        hText.setEnabled(false);
        lText.setEnabled(false);
        sText.setEnabled(false);

        cSlider.setEnabled(false);
        mSlider.setEnabled(false);
        ySlider.setEnabled(false);
        kSlider.setEnabled(false);
        cText.setEnabled(false);
        mText.setEnabled(false);
        yText.setEnabled(false);
        kText.setEnabled(false);
    }

    private void enableHLSControls() {
        hSlider.setEnabled(true);
        lSlider.setEnabled(true);
        sSlider.setEnabled(true);
        hText.setEnabled(true);
        lText.setEnabled(true);
        sText.setEnabled(true);

        // Disable RGB and CMYK controls
        rSlider.setEnabled(false);
        gSlider.setEnabled(false);
        bSlider.setEnabled(false);
        rText.setEnabled(false);
        gText.setEnabled(false);
        bText.setEnabled(false);

        cSlider.setEnabled(false);
        mSlider.setEnabled(false);
        ySlider.setEnabled(false);
        kSlider.setEnabled(false);
        cText.setEnabled(false);
        mText.setEnabled(false);
        yText.setEnabled(false);
        kText.setEnabled(false);
    }

    private void enableCMYKControls() {
        cSlider.setEnabled(true);
        mSlider.setEnabled(true);
        ySlider.setEnabled(true);
        kSlider.setEnabled(true);
        cText.setEnabled(true);
        mText.setEnabled(true);
        yText.setEnabled(true);
        kText.setEnabled(true);

        // Disable RGB and HLS controls
        rSlider.setEnabled(false);
        gSlider.setEnabled(false);
        bSlider.setEnabled(false);
        rText.setEnabled(false);
        gText.setEnabled(false);
        bText.setEnabled(false);

        hSlider.setEnabled(false);
        lSlider.setEnabled(false);
        sSlider.setEnabled(false);
        hText.setEnabled(false);
        lText.setEnabled(false);
        sText.setEnabled(false);
    }

    private void updateColor() {
        if (rgbButton.isSelected()) {
            updateRGB();
            updateCMYKFromRGB();
            updateHLSFromRGB();
        } else if (hlsButton.isSelected()) {
            updateHLS();
            updateRGBFromHLS();
            updateCMYKFromRGB(); // Update CMYK based on RGB
        } else if (cmykButton.isSelected()) {
            updateCMYK();
            updateRGBFromCMYK();
            updateHLSFromRGB(); // Update HLS based on RGB
        }
    }

    private void updateRGB() {
        int r = rSlider.getValue();
        int g = gSlider.getValue();
        int b = bSlider.getValue();
        colorDisplay.setBackground(new Color(r, g, b));

        rText.setText(String.valueOf(r));
        gText.setText(String.valueOf(g));
        bText.setText(String.valueOf(b));
    }

    private void updateHLS() {
        int h = hSlider.getValue();
        int l = lSlider.getValue();
        int s = sSlider.getValue();

        hText.setText(String.valueOf(h));
        lText.setText(String.valueOf(l));
        sText.setText(String.valueOf(s));
    }

    private void updateCMYK() {
        int c = cSlider.getValue();
        int m = mSlider.getValue();
        int y = ySlider.getValue();
        int k = kSlider.getValue();

        cText.setText(String.valueOf(c));
        mText.setText(String.valueOf(m));
        yText.setText(String.valueOf(y));
        kText.setText(String.valueOf(k));
    }

    private void updateRGBFromTextFields() {
        try {
            int r = Integer.parseInt(rText.getText());
            int g = Integer.parseInt(gText.getText());
            int b = Integer.parseInt(bText.getText());

            r = Math.max(0, Math.min(255, r));
            g = Math.max(0, Math.min(255, g));
            b = Math.max(0, Math.min(255, b));

            rSlider.setValue(r);
            gSlider.setValue(g);
            bSlider.setValue(b);
            updateColor();
        } catch (NumberFormatException e) {
            // Ignore invalid input
        }
    }

    private void updateCMYKFromTextFields() {
        try {
            int c = Integer.parseInt(cText.getText());
            int m = Integer.parseInt(mText.getText());
            int y = Integer.parseInt(yText.getText());
            int k = Integer.parseInt(kText.getText());

            c = Math.max(0, Math.min(255, c));
            m = Math.max(0, Math.min(255, m));
            y = Math.max(0, Math.min(255, y));
            k = Math.max(0, Math.min(255, k));

            cSlider.setValue(c);
            mSlider.setValue(m);
            ySlider.setValue(y);
            kSlider.setValue(k);
            updateRGBFromCMYK();
            updateHLSFromRGB();
        } catch (NumberFormatException e) {
            // Ignore invalid input
        }
    }

    private void updateHLSFromTextFields() {
        try {
            int h = Integer.parseInt(hText.getText());
            int l = Integer.parseInt(lText.getText());
            int s = Integer.parseInt(sText.getText());

            h = Math.max(0, Math.min(255, h));
            l = Math.max(0, Math.min(255, l));
            s = Math.max(0, Math.min(255, s));

            hSlider.setValue(h);
            lSlider.setValue(l);
            sSlider.setValue(s);
            updateRGBFromHLS();
            updateCMYKFromRGB();
        } catch (NumberFormatException e) {
            // Ignore invalid input
        }
    }


    private void updateCMYKFromRGB() {
        double r = rSlider.getValue() / 255.0;
        double g = gSlider.getValue() / 255.0;
        double b = bSlider.getValue() / 255.0;

        double k = 1 - Math.max(r, Math.max(g, b));
        
        double c = (1 - r - k) / (1 - k);
        double m = (1 - g - k) / (1 - k);
        double y = (1 - b - k) / (1 - k);


        cText.setText(String.format("%.0f", c * 100));
        mText.setText(String.format("%.0f", m * 100));
        yText.setText(String.format("%.0f", y * 100));
        kText.setText(String.format("%.0f", k * 100));

        cSlider.setValue((int) (c * 100));
        mSlider.setValue((int) (m * 100));
        ySlider.setValue((int) (y * 100));
        kSlider.setValue((int) (k * 100));
    }

    private void updateHLSFromRGB() {
        double r = rSlider.getValue() / 255.0;
        double g = gSlider.getValue() / 255.0;
        double b = bSlider.getValue() / 255.0;

        double max = Math.max(r, Math.max(g, b));
        double min = Math.min(r, Math.min(g, b));
        double h = 0, l = (max + min) / 2, s;

        if (max == 0) {
            s = 0;
        } else {
            s = (max - min) / (1 - Math.abs(2 * l - 1));
        }

        if (max == min) {
            h = 0;

        } else {

            double tmp = 60 * (g - b) / (max - min);
            if (r == max && g >= b) {
                h = tmp;
            } else if (r == max && g < b) {
                h = tmp + 360;
            } else if (max == g) {
                h = 60 * (b - r) / (max - min) + 120;
            } else if (max == b) {
                h = 60 * (r - g) / (max - min) + 240;
            }
        }

        hText.setText(String.format("%.0f", h));
        lText.setText(String.format("%.0f", l * 100));
        sText.setText(String.format("%.0f", s * 100));

        hSlider.setValue((int) (h));
        lSlider.setValue((int) (l * 100));
        sSlider.setValue((int) (s * 100));
    }

    private void updateRGBFromHLS() {
        double h = Double.parseDouble(hText.getText());
        double l = Double.parseDouble(lText.getText()) / 100.0;
        double s = Double.parseDouble(sText.getText()) / 100.0;

        double r = 0, g = 0, b = 0;

        double c = (1 - Math.abs(2 * l - 1)) * s;
        double x = c * (1 - Math.abs((h / 60) % 2 - 1));
        double m = l - c / 2;
        if (0 <= h && h < 60) {
            r = c;
            g = x;
            b = 0;
        } else if (60 <= h && h < 120) {
            r = x;
            g = c;
            b = 0;
        } else if (120 <= h && h < 180) {
            r = 0;
            g = c;
            b = x;
        } else if (180 <= h && h < 240) {
            r = 0;
            g = x;
            b = c;
        } else if (240 <= h && h < 300) {
            r = x;
            g = 0;
            b = c;
        } else if (300 <= h && h < 360) {
            r = c;
            g = 0;
            b = x;
        }

        rSlider.setValue((int) ((r + m) * 255));
        gSlider.setValue((int) ((g + m) * 255));
        bSlider.setValue((int) ((b + m) * 255));
        updateRGB();
    }

    private void updateRGBFromCMYK() {
        float c = Float.parseFloat(cText.getText()) / 100;
        float m = Float.parseFloat(mText.getText()) / 100;
        float y = Float.parseFloat(yText.getText()) / 100;
        float k = Float.parseFloat(kText.getText()) / 100;

        int r = (int) (255 * (1 - c) * (1 - k));
        int g = (int) (255 * (1 - m) * (1 - k));
        int b = (int) (255 * (1 - y) * (1 - k));

        rSlider.setValue(r);
        gSlider.setValue(g);
        bSlider.setValue(b);
        updateRGB();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ColorPickerApp app = new ColorPickerApp();
            app.setVisible(true);
            app.setExtendedState(MAXIMIZED_BOTH);
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        });
    }
}