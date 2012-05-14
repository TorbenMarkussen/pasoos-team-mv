package paystation.server.view;

import paystation.server.IllegalCoinException;
import paystation.server.PayStation;
import paystation.server.PayStationImpl;
import paystation.server.Receipt;
import softcollection.lcd.LCDDigitDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

/**
 * A graphical user interface used as alternative to a real hardware to operate the PayStation server code.
 */
public class PayStationGUI extends JFrame {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // ignore silently, as we then just do not get the required
            // look and feel for all windows
        }
        new PayStationGUI(args[0]);
    }

    /**
     * The "digital display" where readings are shown
     */
    LCDDigitDisplay display;
    /**
     * The server pay station that the gui interacts with
     */
    PayStation payStation;

    /**
     * Create the GUI
     */
    public PayStationGUI(String psName) {
        this(100, 20, psName);
    }

    /**
     * Create the GUI at (x,y) position
     */
    public PayStationGUI(int x, int y, String psName) {
        super("PayStation GUI");

        try {

            payStation = new PayStationImpl(psName);

            // all the gui setup
            JFrame.setDefaultLookAndFeelDecorated(true);
            setLocation(x, y);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Container cpane = getContentPane();

            cpane.setLayout(new BorderLayout());

            cpane.add(createCoinInputPanel(), BorderLayout.EAST);
            cpane.add(createButtonPanel(), BorderLayout.SOUTH);

            cpane.add(createDisplayPanel(), BorderLayout.CENTER);

            display.set("   0");

            pack();
            setVisible(true);

        } catch (RemoteException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (MalformedURLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    /**
     * Update the digital display with whatever the pay station server shows
     */
    private void updateDisplay() {
        String prefixedZeros = String.format("%4d", payStation.readDisplay());
        display.set(prefixedZeros);
    }

    /**
     * Create the coin input panel
     */
    private JComponent createCoinInputPanel() {
        Box p = new Box(BoxLayout.Y_AXIS);
        p.add(defineButton(" 5 c", "5"));
        p.add(defineButton("10 c", "10"));
        p.add(defineButton("25 c", "25"));
        return p;
    }

    /**
     * The button action listener that reacts on clicking the coin buttons
     */
    private ActionListener buttonActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            int coin = Integer.parseInt(s);
            try {
                payStation.addPayment(coin);
            } catch (IllegalCoinException exc) {
                // illegal coins just do nothing.
            }
            updateDisplay();
        }
    };

    /**
     * Define a button's text and action command
     */
    private JButton defineButton(String text, String actioncommand) {
        JButton b;
        b = new JButton(text);
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setActionCommand(actioncommand);
        b.addActionListener(buttonActionListener);
        return b;
    }

    /**
     * Create the panel of buttons
     */
    private JComponent createButtonPanel() {
        Box p = new Box(BoxLayout.X_AXIS);
        JButton b;
        b = new JButton("Cancel");
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(Box.createHorizontalGlue());
        p.add(b);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                payStation.cancel();
                updateDisplay();
            }
        });

        b = new JButton("Buy");
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(Box.createHorizontalGlue());
        p.add(b);
        p.add(Box.createHorizontalGlue());
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Receipt r = payStation.buy();
                updateDisplay();
                // print the receipt
                showReceiptInWindow(r);
            }
        });

        return p;
    }

    /**
     * Create the panel for the display
     */
    private JComponent createDisplayPanel() {
        display = new LCDDigitDisplay();
        return display;
    }

    private void showReceiptInWindow(Receipt receipt) {
        // make the window in which to display receipt
        JFrame frame = new JFrame("Receipt");
        // print the receipt's contents in a string array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        receipt.print(ps);
        String[] lines = baos.toString().split("\n");
        // copy the contents into a text area
        JTextArea text = new JTextArea(lines.length, 20);
        text.setEditable(false);
        text.setFont(new Font("DialogInput", Font.PLAIN, 14));

        for (int i = 0; i < lines.length; i++) {
            text.append(lines[i] + "\n");
        }
        frame.getContentPane().add(text);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * return the paystation server object
     */
    public PayStation getPayStation() {
        return payStation;
    }
}
