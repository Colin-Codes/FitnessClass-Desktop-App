import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.border.Border;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.*;
import java.util.ArrayList;


public class GUIWeekendFitnessClass extends JFrame {

    private Club clbWeekendFitnessClub;
    
    private JMenuBar jmbMenuBar;
    
    /**
     * Preferences
     */
    private JPanel pnlPreferences;
    private JLabel lblPreferencesTitle;
    private JTextField txtTitle;
    private JButton btnSaveTitle;
    
    /**
     * Bookings - Find
     */
    private JPanel pnlBookingsFind;
    private JLabel lblBookingsFindTitle;
    private ArrayList<Integer> arlBookingsFindMapping;
    private DefaultListModel lstBookingsModel;
    private JList lstBookingsFind;
    private JScrollPane pnlBookingsFindScroll;
    private JButton btnBookingsFindEdit;
    private JButton btnBookingsFindDelete;
    
    /**
     * Bookings - Edit
     */
    private Integer intBookingsEditBookingID;
    private JPanel pnlBookingsEdit;
    private JLabel lblBookingsEditTitle;
    private JLabel lblBookingsEditName;
    private JComboBox cmbBookingsEditPayment;
    private JComboBox cmbBookingsEditStatus;
    private ArrayList<Integer> arlBookingsEditClassMapping;
    private DefaultListModel lstBookingsEditClassModel;
    private JList lstBookingsEditClass;
    private JScrollPane pnlBookingsClassScroll;
    private JButton btnBookingsEditSave;
    private JButton btnBookingsEditCancel;
    
    /**
     * About
     */
    private JPanel pnlAbout;
    private JLabel lblAboutTitle;
    private JLabel lblAbout;
    
    public GUIWeekendFitnessClass()
    {
        
        /**
         * CLUB Initialisation
         */
        int dialogResult = JOptionPane.showConfirmDialog (null, "Would you like to open the application with test data?");
        if(dialogResult == JOptionPane.YES_OPTION)
        {
            clbWeekendFitnessClub = new Club(true);
        }
        else
        {
            clbWeekendFitnessClub = new Club(false);
        }
        
        /**
         * SETTINGS
         */
        JPanel pnlParent = new JPanel(null);
        pnlParent.setPreferredSize(new Dimension(500,400));
        pnlParent.setBackground(new Color(192,192,192));
        this.setTitle("Weekend Fitness Club");
        this.setSize(500,400);
        
        /**
        * MENU options
        */
       
        jmbMenuBar = new JMenuBar();

        JMenu jmnFile = new JMenu("File");
        JMenu jmnBookings = new JMenu("Bookings");
        JMenu jmnMembers = new JMenu("Members");
        JMenu jmnClasses = new JMenu("Classes");
        JMenu jmnReports = new JMenu("Reports");
        JMenu jmnHelp = new JMenu("Help");
        
        JMenuItem jmiPreferences = new JMenuItem(new preferenceHandler());
        JMenuItem jmiExit = new JMenuItem(new exitHandler());
        JMenuItem jmiBookingsFind = new JMenuItem(new bookingsFindHandler());
        JMenuItem jmiBookingsAdd = new JMenuItem(new bookingsAddHandler());
        JMenuItem jmiAbout = new JMenuItem(new aboutHandler());

        jmnFile.add(jmiPreferences);
        jmnFile.addSeparator();
        jmnFile.add(jmiExit);
        jmnBookings.add(jmiBookingsFind);
        jmnBookings.add(jmiBookingsAdd);
        jmnHelp.add(jmiAbout);

        jmbMenuBar.add(jmnFile);
        jmbMenuBar.add(jmnBookings);
        jmbMenuBar.add(jmnMembers);
        jmbMenuBar.add(jmnClasses);
        jmbMenuBar.add(jmnReports);
        jmbMenuBar.add(jmnHelp);
        this.setJMenuBar(jmbMenuBar);
        
        /**
         * Preferences panel
         */

        pnlPreferences = new JPanel(null);
        pnlPreferences.setBorder(BorderFactory.createEtchedBorder(1));
        pnlPreferences.setBounds(0,0,500,400);
        pnlPreferences.setBackground(new Color(214,217,223));
        pnlPreferences.setForeground(new Color(0,0,0));
        pnlPreferences.setEnabled(true);
        pnlPreferences.setFont(new Font("sansserif",0,12));
        pnlPreferences.setVisible(false);
            
        lblPreferencesTitle = new JLabel();
        lblPreferencesTitle.setBounds(10,5,90,35);
        lblPreferencesTitle.setBackground(new Color(214,217,223));
        lblPreferencesTitle.setForeground(new Color(0,0,0));
        lblPreferencesTitle.setEnabled(true);
        lblPreferencesTitle.setFont(new Font("sansserif",0,12));
        lblPreferencesTitle.setText("Preferences");
        lblPreferencesTitle.setVisible(true);
            
        txtTitle = new JTextField();
        txtTitle.setBounds(63,44,90,35);
        txtTitle.setBackground(new Color(255,255,255));
        txtTitle.setForeground(new Color(0,0,0));
        txtTitle.setEnabled(true);
        txtTitle.setFont(new Font("sansserif",0,12));
        txtTitle.setText(this.getTitle());
        txtTitle.setVisible(true);
        
        pnlPreferences.add(lblPreferencesTitle);
        pnlPreferences.add(txtTitle);
        
        /**
         * Bookings - Find panel
         */
        arlBookingsFindMapping = new ArrayList<Integer>();
        
        pnlBookingsFind = new JPanel(null);
        pnlBookingsFind.setBorder(BorderFactory.createEtchedBorder(1));
        pnlBookingsFind.setBounds(0,0,500,400);
        pnlBookingsFind.setBackground(new Color(214,217,223));
        pnlBookingsFind.setForeground(new Color(0,0,0));
        pnlBookingsFind.setEnabled(true);
        pnlBookingsFind.setFont(new Font("sansserif",0,12));
        pnlBookingsFind.setVisible(false);
            
        lblBookingsFindTitle = new JLabel();
        lblBookingsFindTitle.setBounds(10,5,90,20);
        lblBookingsFindTitle.setBackground(new Color(214,217,223));
        lblBookingsFindTitle.setForeground(new Color(0,0,0));
        lblBookingsFindTitle.setEnabled(true);
        lblBookingsFindTitle.setFont(new Font("sansserif",0,12));
        lblBookingsFindTitle.setText("Find Booking");
        lblBookingsFindTitle.setVisible(true);
        
        lstBookingsModel = new DefaultListModel();
        lstBookingsFind = new JList(lstBookingsModel);
        lstBookingsFind.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
        pnlBookingsFindScroll = new JScrollPane();
        pnlBookingsFindScroll.setViewportView(lstBookingsFind);
        pnlBookingsFindScroll.setBounds(10,35,480,300);
        pnlBookingsFindScroll.setBackground(new Color(255,255,255));
        pnlBookingsFindScroll.setForeground(new Color(0,0,0));
        pnlBookingsFindScroll.setEnabled(true);
        pnlBookingsFindScroll.setFont(new Font("sansserif",0,12));
        pnlBookingsFindScroll.setVisible(true);
        
        btnBookingsFindEdit = new JButton(new bookingsFindEditHandler());
	btnBookingsFindEdit.setBounds(10,345,90,35);
	btnBookingsFindEdit.setBackground(new Color(214,217,223));
	btnBookingsFindEdit.setForeground(new Color(0,0,0));
	btnBookingsFindEdit.setEnabled(true);
	btnBookingsFindEdit.setFont(new Font("sansserif",0,12));
	btnBookingsFindEdit.setText("Edit");
	btnBookingsFindEdit.setVisible(true);
        
        btnBookingsFindDelete = new JButton(new bookingsFindDeleteHandler());
	btnBookingsFindDelete.setBounds(100,345,90,35);
	btnBookingsFindDelete.setBackground(new Color(214,217,223));
	btnBookingsFindDelete.setForeground(new Color(0,0,0));
	btnBookingsFindDelete.setEnabled(true);
	btnBookingsFindDelete.setFont(new Font("sansserif",0,12));
	btnBookingsFindDelete.setText("Delete");
	btnBookingsFindDelete.setVisible(true);
        
        pnlBookingsFind.add(btnBookingsFindEdit);
        pnlBookingsFind.add(btnBookingsFindDelete);
        pnlBookingsFind.add(lblBookingsFindTitle);
        pnlBookingsFind.add(pnlBookingsFindScroll);
        
        /**
         * Bookings - Edit panel
         */
        intBookingsEditBookingID = -1;
        arlBookingsEditClassMapping = new ArrayList<Integer>();
        
        pnlBookingsEdit = new JPanel(null);
        pnlBookingsEdit.setBorder(BorderFactory.createEtchedBorder(1));
        pnlBookingsEdit.setBounds(0,0,500,400);
        pnlBookingsEdit.setBackground(new Color(214,217,223));
        pnlBookingsEdit.setForeground(new Color(0,0,0));
        pnlBookingsEdit.setEnabled(true);
        pnlBookingsEdit.setFont(new Font("sansserif",0,12));
        pnlBookingsEdit.setVisible(false);
            
        lblBookingsEditTitle = new JLabel();
        lblBookingsEditTitle.setBounds(10,5,90,20);
        lblBookingsEditTitle.setBackground(new Color(214,217,223));
        lblBookingsEditTitle.setForeground(new Color(0,0,0));
        lblBookingsEditTitle.setEnabled(true);
        lblBookingsEditTitle.setFont(new Font("sansserif",0,12));
        lblBookingsEditTitle.setText("Edit Booking");
        lblBookingsEditTitle.setVisible(true);
        
        lblBookingsEditName = new JLabel();
        lblBookingsEditName.setBounds(100,5,300,20);
        lblBookingsEditName.setBackground(new Color(214,217,223));
        lblBookingsEditName.setForeground(new Color(0,0,0));
        lblBookingsEditName.setEnabled(true);
        lblBookingsEditName.setFont(new Font("sansserif",0,12));
        lblBookingsEditName.setText("");
        lblBookingsEditName.setVisible(true);

	cmbBookingsEditPayment = new JComboBox(PaymentMethods.values());
	cmbBookingsEditPayment.setBounds(10,30,90,20);
	cmbBookingsEditPayment.setBackground(new Color(214,217,223));
	cmbBookingsEditPayment.setForeground(new Color(0,0,0));
	cmbBookingsEditPayment.setEnabled(true);
	cmbBookingsEditPayment.setFont(new Font("sansserif",0,12));
	cmbBookingsEditPayment.setVisible(true);

	cmbBookingsEditStatus = new JComboBox(BookingStatus.values());
	cmbBookingsEditStatus.setBounds(100,30,90,20);
	cmbBookingsEditStatus.setBackground(new Color(214,217,223));
	cmbBookingsEditStatus.setForeground(new Color(0,0,0));
	cmbBookingsEditStatus.setEnabled(true);
	cmbBookingsEditStatus.setFont(new Font("sansserif",0,12));
	cmbBookingsEditStatus.setVisible(true);

        lstBookingsEditClassModel = new DefaultListModel();
        lstBookingsEditClass = new JList(lstBookingsEditClassModel);
        lstBookingsEditClass.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
        pnlBookingsClassScroll = new JScrollPane();
        pnlBookingsClassScroll.setViewportView(lstBookingsEditClass);
        pnlBookingsClassScroll.setBounds(10,65,480,270);
        pnlBookingsClassScroll.setBackground(new Color(255,255,255));
        pnlBookingsClassScroll.setForeground(new Color(0,0,0));
        pnlBookingsClassScroll.setEnabled(true);
        pnlBookingsClassScroll.setFont(new Font("sansserif",0,12));
        pnlBookingsClassScroll.setVisible(true);
        
        btnBookingsEditSave = new JButton(new bookingsFindEditHandler());
	btnBookingsEditSave.setBounds(10,345,90,35);
	btnBookingsEditSave.setBackground(new Color(214,217,223));
	btnBookingsEditSave.setForeground(new Color(0,0,0));
	btnBookingsEditSave.setEnabled(true);
	btnBookingsEditSave.setFont(new Font("sansserif",0,12));
	btnBookingsEditSave.setText("Save");
	btnBookingsEditSave.setVisible(true);
        
        btnBookingsEditCancel = new JButton(new bookingsFindEditHandler());
	btnBookingsEditCancel.setBounds(100,345,90,35);
	btnBookingsEditCancel.setBackground(new Color(214,217,223));
	btnBookingsEditCancel.setForeground(new Color(0,0,0));
	btnBookingsEditCancel.setEnabled(true);
	btnBookingsEditCancel.setFont(new Font("sansserif",0,12));
	btnBookingsEditCancel.setText("Cancel");
	btnBookingsEditCancel.setVisible(true);
            
        pnlBookingsEdit.add(lblBookingsEditTitle);
        pnlBookingsEdit.add(lblBookingsEditName);
        pnlBookingsEdit.add(cmbBookingsEditPayment);
        pnlBookingsEdit.add(cmbBookingsEditStatus);
        pnlBookingsEdit.add(pnlBookingsClassScroll);
        pnlBookingsEdit.add(btnBookingsEditSave);
        pnlBookingsEdit.add(btnBookingsEditCancel);
        
        /**
         * About panel
         */

        pnlAbout = new JPanel(null);
        pnlAbout.setBorder(BorderFactory.createEtchedBorder(1));
        pnlAbout.setBounds(0,0,500,400);
        pnlAbout.setBackground(new Color(214,217,223));
        pnlAbout.setForeground(new Color(0,0,0));
        pnlAbout.setEnabled(true);
        pnlAbout.setFont(new Font("sansserif",0,12));
        pnlAbout.setVisible(false);
            
        lblAboutTitle = new JLabel();
        lblAboutTitle.setBounds(10,5,90,35);
        lblAboutTitle.setBackground(new Color(214,217,223));
        lblAboutTitle.setForeground(new Color(0,0,0));
        lblAboutTitle.setEnabled(true);
        lblAboutTitle.setFont(new Font("sansserif",0,12));
        lblAboutTitle.setText("About");
        lblAboutTitle.setVisible(true);
            
        lblAbout = new JLabel();
        lblAbout.setBounds(23,119,90,35);
        lblAbout.setBackground(new Color(214,217,223));
        lblAbout.setForeground(new Color(0,0,0));
        lblAbout.setEnabled(true);
        lblAbout.setFont(new Font("sansserif",0,12));
        lblAbout.setText(this.getTitle());
        lblAbout.setVisible(true);
        
        pnlAbout.add(lblAboutTitle);
        pnlAbout.add(lblAbout);
        
        /**
         * PANEL Construction
         */
        pnlParent.add(pnlAbout);
        pnlParent.add(pnlBookingsEdit);
        pnlParent.add(pnlBookingsFind);
        pnlParent.add(pnlPreferences);
        this.add(pnlParent);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }
    
    private void hidePanels()
    {
        pnlPreferences.setVisible(false);
        pnlBookingsEdit.setVisible(false);
        pnlBookingsFind.setVisible(false);
        pnlAbout.setVisible(false);
    }
    
    private void showPanel(String strPanelName)
    {
        //First simply hide all the panels, then show the requested panel.
        hidePanels();
        switch (strPanelName) 
        {
            case "Preferences": pnlPreferences.setVisible(true);
                break;
            case "Bookings - Edit": pnlBookingsEdit.setVisible(true);
                    Integer intRegisterIndex = arlBookingsFindMapping.get(intBookingsFindIndex);
                    Booking newBooking = clbWeekendFitnessClub.clubBookingRegister.get(intRegisterIndex);
                    lblBookingsEditName.setText(newBooking.getDetails());
                break;
            case "Bookings - Find": pnlBookingsFind.setVisible(true);
                break;
            case "About": pnlAbout.setVisible(true);
                break;
        }
    }
    
    public class preferenceHandler extends AbstractAction 
    {
        public preferenceHandler() 
        {
            super("Preferences");
        }
        
        public void actionPerformed(ActionEvent e)
        {
            showPanel("Preferences");
        }
    }
    
    public class exitHandler extends AbstractAction 
    {
        public exitHandler() 
        {
            super("Exit");
        }
        
        public void actionPerformed(ActionEvent e)
        {
            
            System.exit(0);
        }
    }
    
    public class bookingsFindHandler extends AbstractAction 
    {
        public bookingsFindHandler() 
        {
            super("Find");
        }
        
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                showPanel("Bookings - Find");
                lstBookingsModel.clear();
                arlBookingsFindMapping.clear();
                for (Booking currentBooking : clbWeekendFitnessClub.clubBookingRegister.values())
                {
                    lstBookingsModel.addElement(currentBooking.getDetails());
                    arlBookingsFindMapping.add(currentBooking.getID());
                }
                lstBookingsFind.setCellRenderer(new DefaultListCellRenderer());
                lstBookingsFind.setVisible(true);
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
    
    public class bookingsFindEditHandler extends AbstractAction 
    {        
        public void actionPerformed(ActionEvent e)
        {
            int intBookingsFindIndex = lstBookingsFind.getSelectedIndex();
            try
            {
                if (intBookingsFindIndex >= 0)
                {
                    showPanel("Bookings - Edit");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please select a Booking first");
                }
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
    
    public class bookingsFindDeleteHandler extends AbstractAction 
    {        
        public void actionPerformed(ActionEvent e)
        {
            int intBookingsFindIndex = lstBookingsFind.getSelectedIndex();
            try
            {
                if (intBookingsFindIndex >= 0)
                {
                    Integer intRegisterIndex = arlBookingsFindMapping.get(intBookingsFindIndex);
                    clbWeekendFitnessClub.clubBookingRegister.cancel(intRegisterIndex);
                    showPanel("Bookings - Edit");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please select a Booking first");
                }
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
    
    public class bookingsEditSaveHandler extends AbstractAction 
    {        
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                showPanel("Bookings - Find");
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
    
    public class bookingsEditCancelHandler extends AbstractAction 
    {        
        public void actionPerformed(ActionEvent e)
        {
            hidePanels();
        }
    }
    
    public class bookingsAddHandler extends AbstractAction 
    {
        public bookingsAddHandler() 
        {
            super("Add");
        }
        
        public void actionPerformed(ActionEvent e)
        {
            showPanel("Bookings - Edit");
            
        }
    }
    
    public class aboutHandler extends AbstractAction 
    {
        public aboutHandler() 
        {
            super("About");
        }
        
        public void actionPerformed(ActionEvent e)
        {
            showPanel("About");
        }
    }
    
    public static void main(String[] args)
    {
        System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        javax.swing.SwingUtilities.invokeLater(new Runnable() 
            {
                public void run() 
                {
                    new GUIWeekendFitnessClass();
                }
            }
        );
    }
}