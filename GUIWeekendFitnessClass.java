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
import java.util.Calendar; 
import java.math.BigDecimal;


public class GUIWeekendFitnessClass extends JFrame 
{

    public static void main(String[] args)
    {
        GUIWeekendFitnessClass WeekendFitnessClass = new GUIWeekendFitnessClass();
    }
    
    private boolean testMode = true; //Set to false to load without test data
    
    private Club clbWeekendFitnessClub;
    
    private JMenuBar jmbMenuBar;
    
    /**
     * About
     */
    private JPanel pnlAbout;
    private JLabel lblAboutTitle;
    private JLabel lblAbout;
    private JLabel lblAboutDeadline;
    private JLabel lblAboutStudentID;
    private JLabel lblAboutCourseCode;
    
    /**
     * Bookings - Find
     */
    private Integer intBookingsFindIndex;
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
    private Integer intBookingsEditClassIndex;
    private Integer intBookingsEditMemberIndex;
    private JPanel pnlBookingsEdit;
    private JLabel lblBookingsEditTitle;
    private JLabel lblBookingsEditName;
    private JComboBox cmbBookingsEditPayment;
    private JComboBox cmbBookingsEditStatus;
    private JComboBox cmbBookingsEditMember;
    private ArrayList<Integer> arlBookingsEditClassMapping;
    private ArrayList<Integer> arlBookingsEditMemberMapping;
    private DefaultListModel lstBookingsEditClassModel;
    private JList lstBookingsEditClass;
    private JScrollPane pnlBookingsClassScroll;
    private JComboBox cmbBookingsEditRating;
    private JTextField txtBookingsEditReview;
    private JButton btnBookingsEditSave;
    private JButton btnBookingsEditCancel;
    
    /**
     * Members - Find
     */
    private Integer intMembersFindIndex;
    private JPanel pnlMembersFind;
    private JLabel lblMembersFindTitle;
    private ArrayList<Integer> arlMembersFindMapping;
    private DefaultListModel lstMembersModel;
    private JList lstMembersFind;
    private JScrollPane pnlMembersFindScroll;
    private JButton btnMembersFindEdit;
    private JButton btnMembersFindDelete;
    
    /**
     * Members - Edit
     */
    private Integer intMembersEditMemberID;
    private JPanel pnlMembersEdit;
    private JLabel lblMembersEditTitle;
    private JLabel lblMembersEditName;
    private JLabel lblMembersEditFirstName;
    private JLabel lblMembersEditLastName;
    private JTextField txtMembersEditFirstName;
    private JTextField txtMembersEditLastName;
    private JButton btnMembersEditSave;
    private JButton btnMembersEditCancel;
    
    /**
     * Classes - Find
     */
    private Integer intClassesFindIndex;
    private JPanel pnlClassesFind;
    private JLabel lblClassesFindTitle;
    private ArrayList<Integer> arlClassesFindMapping;
    private DefaultListModel lstClassesModel;
    private JList lstClassesFind;
    private JScrollPane pnlClassesFindScroll;
    private JButton btnClassesFindEdit;
    private JButton btnClassesFindDelete;
    
    /**
     * Classes - Edit
     */
    private Integer intClassesEditClassID;
    private Integer intClassesEditExerciseIndex;
    private JPanel pnlClassesEdit;
    private JLabel lblClassesEditTitle;
    private JLabel lblClassesEditName;
    private JLabel lblClassesEditDate;
    private JTextField txtClassesEditDate;
    private JComboBox cmbClassesEditSlot;
    private JComboBox cmbClassesEditExercise;
    private ArrayList<Integer> arlClassesEditExerciseMapping;
    private JButton btnClassesEditSave;
    private JButton btnClassesEditCancel;
    
    /**
     * Exercises - Find
     */
    private Integer intExercisesFindIndex;
    private JPanel pnlExercisesFind;
    private JLabel lblExercisesFindTitle;
    private ArrayList<Integer> arlExercisesFindMapping;
    private DefaultListModel lstExercisesModel;
    private JList lstExercisesFind;
    private JScrollPane pnlExercisesFindScroll;
    private JButton btnExercisesFindEdit;
    private JButton btnExercisesFindDelete;
    
    /**
     * Exercises - Edit
     */
    private Integer intExercisesEditExerciseID;
    private JPanel pnlExercisesEdit;
    private JLabel lblExercisesEditTitle;
    private JLabel lblExercisesEditName;
    private JLabel lblExercisesEditExerciseName;
    private JLabel lblExercisesEditExercisePrice;
    private JTextField txtExercisesEditName;
    private JTextField txtExercisesEditPrice;
    private JButton btnExercisesEditSave;
    private JButton btnExercisesEditCancel;
    
    /**
     * Reports
     */
    private JPanel pnlReports;
    private JLabel lblReportsTitle;
    private JLabel lblReportsYear;
    private JLabel lblReportsMonth;
    private JTextField txtReportsYear;
    private JTextField txtReportsMonth;
    private JButton btnReportsSearch;
    private JLabel lblReportsChampion;
    private JTextField txtReportsChampion;
    private JLabel lblReportsResults;
    private DefaultListModel lstReportsResultsModel;
    private JList lstReportsResults;
    private JScrollPane pnlReportsResultsScroll;
        
    public GUIWeekendFitnessClass()
    {
        
        /**
         * CLUB Initialisation
         */
        try
        {
            clbWeekendFitnessClub = new Club(testMode);
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
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
        JMenu jmnExercises = new JMenu("Exercises");
        
        JMenuItem jmiReports = new JMenuItem(new reportsHandler());
        JMenuItem jmiExit = new JMenuItem(new exitHandler());
        JMenuItem jmiBookingsFind = new JMenuItem(new bookingsFindHandler());
        JMenuItem jmiBookingsAdd = new JMenuItem(new bookingsAddHandler());
        JMenuItem jmiMembersFind = new JMenuItem(new membersFindHandler());
        JMenuItem jmiMembersAdd = new JMenuItem(new membersAddHandler());
        JMenuItem jmiClassesFind = new JMenuItem(new classesFindHandler());
        JMenuItem jmiClassesAdd = new JMenuItem(new classesAddHandler());
        JMenuItem jmiExercisesFind = new JMenuItem(new exercisesFindHandler());
        JMenuItem jmiExercisesAdd = new JMenuItem(new exercisesAddHandler());
        JMenuItem jmiAbout = new JMenuItem(new aboutHandler());
    
        jmnFile.add(jmiReports);
        jmnFile.add(jmiAbout);
        jmnFile.addSeparator();
        jmnFile.add(jmiExit);
        jmnBookings.add(jmiBookingsFind);
        jmnBookings.add(jmiBookingsAdd);
        jmnMembers.add(jmiMembersFind);
        jmnMembers.add(jmiMembersAdd);
        jmnClasses.add(jmiClassesFind);
        jmnClasses.add(jmiClassesAdd);
        jmnExercises.add(jmiExercisesFind);
        jmnExercises.add(jmiExercisesAdd);
    
        jmbMenuBar.add(jmnFile);
        jmbMenuBar.add(jmnBookings);
        jmbMenuBar.add(jmnMembers);
        jmbMenuBar.add(jmnClasses);
        jmbMenuBar.add(jmnExercises);
        this.setJMenuBar(jmbMenuBar);
        
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
            
        lblAbout = new JLabel();
        lblAbout.setBounds(10,5,480,20);
        lblAbout.setBackground(new Color(214,217,223));
        lblAbout.setForeground(new Color(0,0,0));
        lblAbout.setEnabled(true);
        lblAbout.setFont(new Font("sansserif",0,12));
        lblAbout.setText("About");
        lblAbout.setVisible(true);
            
        lblAboutTitle = new JLabel();
        lblAboutTitle.setBounds(10,30,480,20);
        lblAboutTitle.setBackground(new Color(214,217,223));
        lblAboutTitle.setForeground(new Color(0,0,0));
        lblAboutTitle.setEnabled(true);
        lblAboutTitle.setFont(new Font("sansserif",0,12));
        lblAboutTitle.setText("Assignment 2: Weekend Fitness Club");
        lblAboutTitle.setVisible(true);
            
        lblAboutCourseCode = new JLabel();
        lblAboutCourseCode.setBounds(10,55,480,20);
        lblAboutCourseCode.setBackground(new Color(214,217,223));
        lblAboutCourseCode.setForeground(new Color(0,0,0));
        lblAboutCourseCode.setEnabled(true);
        lblAboutCourseCode.setFont(new Font("sansserif",0,12));
        lblAboutCourseCode.setText("Course code: 7WCM0041-0901-2018");
        lblAboutCourseCode.setVisible(true);
            
        lblAboutStudentID = new JLabel();
        lblAboutStudentID.setBounds(10,80,480,20);
        lblAboutStudentID.setBackground(new Color(214,217,223));
        lblAboutStudentID.setForeground(new Color(0,0,0));
        lblAboutStudentID.setEnabled(true);
        lblAboutStudentID.setFont(new Font("sansserif",0,12));
        lblAboutStudentID.setText("Student ID: 16018361");
        lblAboutStudentID.setVisible(true);
            
        lblAboutDeadline = new JLabel();
        lblAboutDeadline.setBounds(10,105,480,20);
        lblAboutDeadline.setBackground(new Color(214,217,223));
        lblAboutDeadline.setForeground(new Color(0,0,0));
        lblAboutDeadline.setEnabled(true);
        lblAboutDeadline.setFont(new Font("sansserif",0,12));
        lblAboutDeadline.setText("Submission deadline: 09/01/2019");
        lblAboutDeadline.setVisible(true);
        
        pnlAbout.add(lblAbout);
        pnlAbout.add(lblAboutTitle);
        pnlAbout.add(lblAboutCourseCode);
        pnlAbout.add(lblAboutStudentID);
        pnlAbout.add(lblAboutDeadline);
        
        /**
         * Reports
         */
        
        pnlReports = new JPanel(null);
        pnlReports.setBorder(BorderFactory.createEtchedBorder(1));
        pnlReports.setBounds(0,0,500,400);
        pnlReports.setBackground(new Color(214,217,223));
        pnlReports.setForeground(new Color(0,0,0));
        pnlReports.setEnabled(true);
        pnlReports.setFont(new Font("sansserif",0,12));
        pnlReports.setVisible(false);
        
        lblReportsTitle = new JLabel();
        lblReportsTitle.setBounds(10,5,90,20);
        lblReportsTitle.setBackground(new Color(214,217,223));
        lblReportsTitle.setForeground(new Color(0,0,0));
        lblReportsTitle.setEnabled(true);
        lblReportsTitle.setFont(new Font("sansserif",0,12));
        lblReportsTitle.setText("Reports");
        lblReportsTitle.setVisible(true);
                
        lblReportsYear = new JLabel();
        lblReportsYear.setBounds(10,25,90,20);
        lblReportsYear.setBackground(new Color(214,217,223));
        lblReportsYear.setForeground(new Color(0,0,0));
        lblReportsYear.setEnabled(true);
        lblReportsYear.setFont(new Font("sansserif",0,12));
        lblReportsYear.setText("Year");
        lblReportsYear.setVisible(true);
                
        txtReportsYear = new JTextField();
        txtReportsYear.setBounds(100,25,90,20);
        txtReportsYear.setBackground(new Color(255,255,255));
        txtReportsYear.setForeground(new Color(0,0,0));
        txtReportsYear.setEnabled(true);
        txtReportsYear.setFont(new Font("sansserif",0,12));
        txtReportsYear.setText("");
        txtReportsYear.setVisible(true);
        
        lblReportsMonth = new JLabel();
        lblReportsMonth.setBounds(10,50,90,20);
        lblReportsMonth.setBackground(new Color(214,217,223));
        lblReportsMonth.setForeground(new Color(0,0,0));
        lblReportsMonth.setEnabled(true);
        lblReportsMonth.setFont(new Font("sansserif",0,12));
        lblReportsMonth.setText("Month");
        lblReportsMonth.setVisible(true);
                
        txtReportsMonth = new JTextField();
        txtReportsMonth.setBounds(100,50,90,20);
        txtReportsMonth.setBackground(new Color(255,255,255));
        txtReportsMonth.setForeground(new Color(0,0,0));
        txtReportsMonth.setEnabled(true);
        txtReportsMonth.setFont(new Font("sansserif",0,12));
        txtReportsMonth.setText("");
        txtReportsMonth.setVisible(true);
                
        btnReportsSearch = new JButton(new reportsResultsHandler());
        btnReportsSearch.setBounds(200,25,90,35);
        btnReportsSearch.setBackground(new Color(214,217,223));
        btnReportsSearch.setForeground(new Color(0,0,0));
        btnReportsSearch.setEnabled(true);
        btnReportsSearch.setFont(new Font("sansserif",0,12));
        btnReportsSearch.setText("Search");
        btnReportsSearch.setVisible(true);
                
        lblReportsChampion = new JLabel();
        lblReportsChampion.setBounds(10,75,480,20);
        lblReportsChampion.setBackground(new Color(214,217,223));
        lblReportsChampion.setForeground(new Color(0,0,0));
        lblReportsChampion.setEnabled(true);
        lblReportsChampion.setFont(new Font("sansserif",0,12));
        lblReportsChampion.setText("Monthly Champion Class:");
        lblReportsChampion.setVisible(true);
                
        txtReportsChampion = new JTextField();
        txtReportsChampion.setBounds(10,100,480,20);
        txtReportsChampion.setBackground(new Color(255,255,255));
        txtReportsChampion.setForeground(new Color(0,0,0));
        txtReportsChampion.setEnabled(true);
        txtReportsChampion.setFont(new Font("sansserif",0,12));
        txtReportsChampion.setText("");
        txtReportsChampion.setVisible(true);
                
        lblReportsResults = new JLabel();
        lblReportsResults.setBounds(10,125,480,20);
        lblReportsResults.setBackground(new Color(214,217,223));
        lblReportsResults.setForeground(new Color(0,0,0));
        lblReportsResults.setEnabled(true);
        lblReportsResults.setFont(new Font("sansserif",0,12));
        lblReportsResults.setText("Monthly Class Attendance and Ratings:");
        lblReportsResults.setVisible(true);
        
        lstReportsResultsModel = new DefaultListModel();
        lstReportsResults = new JList(lstReportsResultsModel);
        lstReportsResults.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
        pnlReportsResultsScroll = new JScrollPane();
        pnlReportsResultsScroll.setViewportView(lstReportsResults);
        pnlReportsResultsScroll.setBounds(10,150,480,240);
        pnlReportsResultsScroll.setBackground(new Color(255,255,255));
        pnlReportsResultsScroll.setForeground(new Color(0,0,0));
        pnlReportsResultsScroll.setEnabled(true);
        pnlReportsResultsScroll.setFont(new Font("sansserif",0,12));
        pnlReportsResultsScroll.setVisible(true);
        
        pnlReports.add(lblReportsTitle);
        pnlReports.add(lblReportsYear);
        pnlReports.add(txtReportsYear);
        pnlReports.add(lblReportsMonth);
        pnlReports.add(txtReportsMonth);
        pnlReports.add(btnReportsSearch);
        pnlReports.add(lblReportsChampion);
        pnlReports.add(txtReportsChampion);
        pnlReports.add(lblReportsResults);
        pnlReports.add(pnlReportsResultsScroll);
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
        intBookingsEditClassIndex = -1;
        arlBookingsEditClassMapping = new ArrayList<Integer>();
        intBookingsEditMemberIndex = -1;
        arlBookingsEditMemberMapping = new ArrayList<Integer>();
        
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
    
        cmbBookingsEditMember = new JComboBox();
        cmbBookingsEditMember.setBounds(190,30,90,20);
        cmbBookingsEditMember.setBackground(new Color(214,217,223));
        cmbBookingsEditMember.setForeground(new Color(0,0,0));
        cmbBookingsEditMember.setEnabled(true);
        cmbBookingsEditMember.setFont(new Font("sansserif",0,12));
        cmbBookingsEditMember.setVisible(false);
    
        lstBookingsEditClassModel = new DefaultListModel();
        lstBookingsEditClass = new JList(lstBookingsEditClassModel);
        lstBookingsEditClass.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
        pnlBookingsClassScroll = new JScrollPane();
        pnlBookingsClassScroll.setViewportView(lstBookingsEditClass);
        pnlBookingsClassScroll.setBounds(10,65,480,230);
        pnlBookingsClassScroll.setBackground(new Color(255,255,255));
        pnlBookingsClassScroll.setForeground(new Color(0,0,0));
        pnlBookingsClassScroll.setEnabled(true);
        pnlBookingsClassScroll.setFont(new Font("sansserif",0,12));
        pnlBookingsClassScroll.setVisible(true);
        
        cmbBookingsEditRating = new JComboBox(Ratings.values());
        cmbBookingsEditRating.setBounds(10,300,90,20);
        cmbBookingsEditRating.setBackground(new Color(214,217,223));
        cmbBookingsEditRating.setForeground(new Color(0,0,0));
        cmbBookingsEditRating.setEnabled(true);
        cmbBookingsEditRating.setFont(new Font("sansserif",0,12));
        cmbBookingsEditRating.setVisible(true);
        
        txtBookingsEditReview = new JTextField();
        txtBookingsEditReview.setBounds(100,300,380,20);
        txtBookingsEditReview.setBackground(new Color(255,255,255));
        txtBookingsEditReview.setForeground(new Color(0,0,0));
        txtBookingsEditReview.setEnabled(true);
        txtBookingsEditReview.setFont(new Font("sansserif",0,12));
        txtBookingsEditReview.setText("");
        txtBookingsEditReview.setVisible(true);
        
        btnBookingsEditSave = new JButton(new bookingsEditSaveHandler());
        btnBookingsEditSave.setBounds(10,345,90,35);
        btnBookingsEditSave.setBackground(new Color(214,217,223));
        btnBookingsEditSave.setForeground(new Color(0,0,0));
        btnBookingsEditSave.setEnabled(true);
        btnBookingsEditSave.setFont(new Font("sansserif",0,12));
        btnBookingsEditSave.setText("Save");
        btnBookingsEditSave.setVisible(true);
        
        btnBookingsEditCancel = new JButton(new bookingsEditCancelHandler());
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
        pnlBookingsEdit.add(cmbBookingsEditMember);
        pnlBookingsEdit.add(pnlBookingsClassScroll);
        pnlBookingsEdit.add(cmbBookingsEditRating);
        pnlBookingsEdit.add(txtBookingsEditReview);
        pnlBookingsEdit.add(btnBookingsEditSave);
        pnlBookingsEdit.add(btnBookingsEditCancel);
    
        /**
         * Members - Find
         */
        arlMembersFindMapping = new ArrayList<Integer>();
        
        pnlMembersFind = new JPanel(null);
        pnlMembersFind.setBorder(BorderFactory.createEtchedBorder(1));
        pnlMembersFind.setBounds(0,0,500,400);
        pnlMembersFind.setBackground(new Color(214,217,223));
        pnlMembersFind.setForeground(new Color(0,0,0));
        pnlMembersFind.setEnabled(true);
        pnlMembersFind.setFont(new Font("sansserif",0,12));
        pnlMembersFind.setVisible(false);
            
        lblMembersFindTitle = new JLabel();
        lblMembersFindTitle.setBounds(10,5,90,20);
        lblMembersFindTitle.setBackground(new Color(214,217,223));
        lblMembersFindTitle.setForeground(new Color(0,0,0));
        lblMembersFindTitle.setEnabled(true);
        lblMembersFindTitle.setFont(new Font("sansserif",0,12));
        lblMembersFindTitle.setText("Find Member");
        lblMembersFindTitle.setVisible(true);
        
        lstMembersModel = new DefaultListModel();
        lstMembersFind = new JList(lstMembersModel);
        lstMembersFind.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
        pnlMembersFindScroll = new JScrollPane();
        pnlMembersFindScroll.setViewportView(lstMembersFind);
        pnlMembersFindScroll.setBounds(10,35,480,300);
        pnlMembersFindScroll.setBackground(new Color(255,255,255));
        pnlMembersFindScroll.setForeground(new Color(0,0,0));
        pnlMembersFindScroll.setEnabled(true);
        pnlMembersFindScroll.setFont(new Font("sansserif",0,12));
        pnlMembersFindScroll.setVisible(true);
        
        btnMembersFindEdit = new JButton(new membersFindEditHandler());
        btnMembersFindEdit.setBounds(10,345,90,35);
        btnMembersFindEdit.setBackground(new Color(214,217,223));
        btnMembersFindEdit.setForeground(new Color(0,0,0));
        btnMembersFindEdit.setEnabled(true);
        btnMembersFindEdit.setFont(new Font("sansserif",0,12));
        btnMembersFindEdit.setText("Edit");
        btnMembersFindEdit.setVisible(true);
        
        btnMembersFindDelete = new JButton(new membersFindDeleteHandler());
        btnMembersFindDelete.setBounds(100,345,90,35);
        btnMembersFindDelete.setBackground(new Color(214,217,223));
        btnMembersFindDelete.setForeground(new Color(0,0,0));
        btnMembersFindDelete.setEnabled(true);
        btnMembersFindDelete.setFont(new Font("sansserif",0,12));
        btnMembersFindDelete.setText("Delete");
        btnMembersFindDelete.setVisible(true);
        
        pnlMembersFind.add(btnMembersFindEdit);
        pnlMembersFind.add(btnMembersFindDelete);
        pnlMembersFind.add(lblMembersFindTitle);
        pnlMembersFind.add(pnlMembersFindScroll);
    
        /**
         * Members - Edit
         */
        intMembersEditMemberID = -1;
        
        pnlMembersEdit = new JPanel(null);
        pnlMembersEdit.setBorder(BorderFactory.createEtchedBorder(1));
        pnlMembersEdit.setBounds(0,0,500,400);
        pnlMembersEdit.setBackground(new Color(214,217,223));
        pnlMembersEdit.setForeground(new Color(0,0,0));
        pnlMembersEdit.setEnabled(true);
        pnlMembersEdit.setFont(new Font("sansserif",0,12));
        pnlMembersEdit.setVisible(false);
            
        lblMembersEditTitle = new JLabel();
        lblMembersEditTitle.setBounds(10,5,90,20);
        lblMembersEditTitle.setBackground(new Color(214,217,223));
        lblMembersEditTitle.setForeground(new Color(0,0,0));
        lblMembersEditTitle.setEnabled(true);
        lblMembersEditTitle.setFont(new Font("sansserif",0,12));
        lblMembersEditTitle.setText("Edit Member");
        lblMembersEditTitle.setVisible(true);
        
        lblMembersEditName = new JLabel();
        lblMembersEditName.setBounds(100,5,300,20);
        lblMembersEditName.setBackground(new Color(214,217,223));
        lblMembersEditName.setForeground(new Color(0,0,0));
        lblMembersEditName.setEnabled(true);
        lblMembersEditName.setFont(new Font("sansserif",0,12));
        lblMembersEditName.setText("");
        lblMembersEditName.setVisible(true);
        
        lblMembersEditFirstName = new JLabel();
        lblMembersEditFirstName.setBounds(10,30,90,20);
        lblMembersEditFirstName.setBackground(new Color(214,217,223));
        lblMembersEditFirstName.setForeground(new Color(0,0,0));
        lblMembersEditFirstName.setEnabled(true);
        lblMembersEditFirstName.setFont(new Font("sansserif",0,12));
        lblMembersEditFirstName.setText("First name");
        lblMembersEditFirstName.setVisible(true);
           
        txtMembersEditFirstName = new JTextField();
        txtMembersEditFirstName.setBounds(100,30,390,20);
        txtMembersEditFirstName.setBackground(new Color(255,255,255));
        txtMembersEditFirstName.setForeground(new Color(0,0,0));
        txtMembersEditFirstName.setEnabled(true);
        txtMembersEditFirstName.setFont(new Font("sansserif",0,12));
        txtMembersEditFirstName.setText("");
        txtMembersEditFirstName.setVisible(true);
        
        lblMembersEditLastName = new JLabel();
        lblMembersEditLastName.setBounds(10,55,90,20);
        lblMembersEditLastName.setBackground(new Color(214,217,223));
        lblMembersEditLastName.setForeground(new Color(0,0,0));
        lblMembersEditLastName.setEnabled(true);
        lblMembersEditLastName.setFont(new Font("sansserif",0,12));
        lblMembersEditLastName.setText("Last name");
        lblMembersEditLastName.setVisible(true);
           
        txtMembersEditLastName = new JTextField();
        txtMembersEditLastName.setBounds(100,55,390,20);
        txtMembersEditLastName.setBackground(new Color(255,255,255));
        txtMembersEditLastName.setForeground(new Color(0,0,0));
        txtMembersEditLastName.setEnabled(true);
        txtMembersEditLastName.setFont(new Font("sansserif",0,12));
        txtMembersEditLastName.setText("");
        txtMembersEditLastName.setVisible(true);
        
        btnMembersEditSave = new JButton(new membersEditSaveHandler());
        btnMembersEditSave.setBounds(10,345,90,35);
        btnMembersEditSave.setBackground(new Color(214,217,223));
        btnMembersEditSave.setForeground(new Color(0,0,0));
        btnMembersEditSave.setEnabled(true);
        btnMembersEditSave.setFont(new Font("sansserif",0,12));
        btnMembersEditSave.setText("Save");
        btnMembersEditSave.setVisible(true);
        
        btnMembersEditCancel = new JButton(new membersEditCancelHandler());
        btnMembersEditCancel.setBounds(100,345,90,35);
        btnMembersEditCancel.setBackground(new Color(214,217,223));
        btnMembersEditCancel.setForeground(new Color(0,0,0));
        btnMembersEditCancel.setEnabled(true);
        btnMembersEditCancel.setFont(new Font("sansserif",0,12));
        btnMembersEditCancel.setText("Cancel");
        btnMembersEditCancel.setVisible(true);
            
        pnlMembersEdit.add(lblMembersEditTitle);
        pnlMembersEdit.add(lblMembersEditName);
        pnlMembersEdit.add(lblMembersEditFirstName);
        pnlMembersEdit.add(lblMembersEditLastName);
        pnlMembersEdit.add(txtMembersEditFirstName);
        pnlMembersEdit.add(txtMembersEditLastName);        
        pnlMembersEdit.add(btnMembersEditSave);
        pnlMembersEdit.add(btnMembersEditCancel);
    
        /**
         * Classes - Find
         */
        arlClassesFindMapping = new ArrayList<Integer>();
        
        pnlClassesFind = new JPanel(null);
        pnlClassesFind.setBorder(BorderFactory.createEtchedBorder(1));
        pnlClassesFind.setBounds(0,0,500,400);
        pnlClassesFind.setBackground(new Color(214,217,223));
        pnlClassesFind.setForeground(new Color(0,0,0));
        pnlClassesFind.setEnabled(true);
        pnlClassesFind.setFont(new Font("sansserif",0,12));
        pnlClassesFind.setVisible(false);
            
        lblClassesFindTitle = new JLabel();
        lblClassesFindTitle.setBounds(10,5,90,20);
        lblClassesFindTitle.setBackground(new Color(214,217,223));
        lblClassesFindTitle.setForeground(new Color(0,0,0));
        lblClassesFindTitle.setEnabled(true);
        lblClassesFindTitle.setFont(new Font("sansserif",0,12));
        lblClassesFindTitle.setText("Find Class");
        lblClassesFindTitle.setVisible(true);
        
        lstClassesModel = new DefaultListModel();
        lstClassesFind = new JList(lstClassesModel);
        lstClassesFind.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
        pnlClassesFindScroll = new JScrollPane();
        pnlClassesFindScroll.setViewportView(lstClassesFind);
        pnlClassesFindScroll.setBounds(10,35,480,300);
        pnlClassesFindScroll.setBackground(new Color(255,255,255));
        pnlClassesFindScroll.setForeground(new Color(0,0,0));
        pnlClassesFindScroll.setEnabled(true);
        pnlClassesFindScroll.setFont(new Font("sansserif",0,12));
        pnlClassesFindScroll.setVisible(true);
        
        btnClassesFindEdit = new JButton(new classesFindEditHandler());
        btnClassesFindEdit.setBounds(10,345,90,35);
        btnClassesFindEdit.setBackground(new Color(214,217,223));
        btnClassesFindEdit.setForeground(new Color(0,0,0));
        btnClassesFindEdit.setEnabled(true);
        btnClassesFindEdit.setFont(new Font("sansserif",0,12));
        btnClassesFindEdit.setText("Edit");
        btnClassesFindEdit.setVisible(true);
        
        btnClassesFindDelete = new JButton(new classesFindDeleteHandler());
        btnClassesFindDelete.setBounds(100,345,90,35);
        btnClassesFindDelete.setBackground(new Color(214,217,223));
        btnClassesFindDelete.setForeground(new Color(0,0,0));
        btnClassesFindDelete.setEnabled(true);
        btnClassesFindDelete.setFont(new Font("sansserif",0,12));
        btnClassesFindDelete.setText("Delete");
        btnClassesFindDelete.setVisible(true);
        
        pnlClassesFind.add(btnClassesFindEdit);
        pnlClassesFind.add(btnClassesFindDelete);
        pnlClassesFind.add(lblClassesFindTitle);
        pnlClassesFind.add(pnlClassesFindScroll);
    
        /**
         * Classes - Edit
         */
        intClassesEditClassID = -1;
        intClassesEditExerciseIndex = -1;
        arlClassesEditExerciseMapping = new ArrayList<Integer>();
        
        pnlClassesEdit = new JPanel(null);
        pnlClassesEdit.setBorder(BorderFactory.createEtchedBorder(1));
        pnlClassesEdit.setBounds(0,0,500,400);
        pnlClassesEdit.setBackground(new Color(214,217,223));
        pnlClassesEdit.setForeground(new Color(0,0,0));
        pnlClassesEdit.setEnabled(true);
        pnlClassesEdit.setFont(new Font("sansserif",0,12));
        pnlClassesEdit.setVisible(false);
            
        lblClassesEditTitle = new JLabel();
        lblClassesEditTitle.setBounds(10,5,90,20);
        lblClassesEditTitle.setBackground(new Color(214,217,223));
        lblClassesEditTitle.setForeground(new Color(0,0,0));
        lblClassesEditTitle.setEnabled(true);
        lblClassesEditTitle.setFont(new Font("sansserif",0,12));
        lblClassesEditTitle.setText("Edit Class");
        lblClassesEditTitle.setVisible(true);
        
        lblClassesEditName = new JLabel();
        lblClassesEditName.setBounds(100,5,300,20);
        lblClassesEditName.setBackground(new Color(214,217,223));
        lblClassesEditName.setForeground(new Color(0,0,0));
        lblClassesEditName.setEnabled(true);
        lblClassesEditName.setFont(new Font("sansserif",0,12));
        lblClassesEditName.setText("");
        lblClassesEditName.setVisible(true);
        
        lblClassesEditDate = new JLabel();
        lblClassesEditDate.setBounds(10,30,300,20);
        lblClassesEditDate.setBackground(new Color(214,217,223));
        lblClassesEditDate.setForeground(new Color(0,0,0));
        lblClassesEditDate.setEnabled(true);
        lblClassesEditDate.setFont(new Font("sansserif",0,12));
        lblClassesEditDate.setText("Date");
        lblClassesEditDate.setVisible(true);
           
        txtClassesEditDate = new JTextField();
        txtClassesEditDate.setBounds(100,30,90,20);
        txtClassesEditDate.setBackground(new Color(255,255,255));
        txtClassesEditDate.setForeground(new Color(0,0,0));
        txtClassesEditDate.setEnabled(true);
        txtClassesEditDate.setFont(new Font("sansserif",0,12));
        txtClassesEditDate.setText("");
        txtClassesEditDate.setVisible(true);
    
        cmbClassesEditSlot = new JComboBox(Slot.values());
        cmbClassesEditSlot.setBounds(10,55,200,20);
        cmbClassesEditSlot.setBackground(new Color(214,217,223));
        cmbClassesEditSlot.setForeground(new Color(0,0,0));
        cmbClassesEditSlot.setEnabled(true);
        cmbClassesEditSlot.setFont(new Font("sansserif",0,12));
        cmbClassesEditSlot.setVisible(true);
    
        cmbClassesEditExercise = new JComboBox();
        cmbClassesEditExercise.setBounds(10,80,200,20);
        cmbClassesEditExercise.setBackground(new Color(214,217,223));
        cmbClassesEditExercise.setForeground(new Color(0,0,0));
        cmbClassesEditExercise.setEnabled(true);
        cmbClassesEditExercise.setFont(new Font("sansserif",0,12));
        cmbClassesEditExercise.setVisible(true);
        
        btnClassesEditSave = new JButton(new classesEditSaveHandler());
        btnClassesEditSave.setBounds(10,345,90,35);
        btnClassesEditSave.setBackground(new Color(214,217,223));
        btnClassesEditSave.setForeground(new Color(0,0,0));
        btnClassesEditSave.setEnabled(true);
        btnClassesEditSave.setFont(new Font("sansserif",0,12));
        btnClassesEditSave.setText("Save");
        btnClassesEditSave.setVisible(true);
        
        btnClassesEditCancel = new JButton(new classesEditCancelHandler());
        btnClassesEditCancel.setBounds(100,345,90,35);
        btnClassesEditCancel.setBackground(new Color(214,217,223));
        btnClassesEditCancel.setForeground(new Color(0,0,0));
        btnClassesEditCancel.setEnabled(true);
        btnClassesEditCancel.setFont(new Font("sansserif",0,12));
        btnClassesEditCancel.setText("Cancel");
        btnClassesEditCancel.setVisible(true);
            
        pnlClassesEdit.add(lblClassesEditTitle);
        pnlClassesEdit.add(lblClassesEditName);
        pnlClassesEdit.add(lblClassesEditDate);
        pnlClassesEdit.add(txtClassesEditDate);
        pnlClassesEdit.add(cmbClassesEditSlot);
        pnlClassesEdit.add(cmbClassesEditExercise);        
        pnlClassesEdit.add(btnClassesEditSave);
        pnlClassesEdit.add(btnClassesEditCancel);
    
        /**
         * Exercises - Find
         */
        arlExercisesFindMapping = new ArrayList<Integer>();
        
        pnlExercisesFind = new JPanel(null);
        pnlExercisesFind.setBorder(BorderFactory.createEtchedBorder(1));
        pnlExercisesFind.setBounds(0,0,500,400);
        pnlExercisesFind.setBackground(new Color(214,217,223));
        pnlExercisesFind.setForeground(new Color(0,0,0));
        pnlExercisesFind.setEnabled(true);
        pnlExercisesFind.setFont(new Font("sansserif",0,12));
        pnlExercisesFind.setVisible(false);
            
        lblExercisesFindTitle = new JLabel();
        lblExercisesFindTitle.setBounds(10,5,90,20);
        lblExercisesFindTitle.setBackground(new Color(214,217,223));
        lblExercisesFindTitle.setForeground(new Color(0,0,0));
        lblExercisesFindTitle.setEnabled(true);
        lblExercisesFindTitle.setFont(new Font("sansserif",0,12));
        lblExercisesFindTitle.setText("Find Exercise");
        lblExercisesFindTitle.setVisible(true);
        
        lstExercisesModel = new DefaultListModel();
        lstExercisesFind = new JList(lstExercisesModel);
        lstExercisesFind.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
        pnlExercisesFindScroll = new JScrollPane();
        pnlExercisesFindScroll.setViewportView(lstExercisesFind);
        pnlExercisesFindScroll.setBounds(10,35,480,300);
        pnlExercisesFindScroll.setBackground(new Color(255,255,255));
        pnlExercisesFindScroll.setForeground(new Color(0,0,0));
        pnlExercisesFindScroll.setEnabled(true);
        pnlExercisesFindScroll.setFont(new Font("sansserif",0,12));
        pnlExercisesFindScroll.setVisible(true);
        
        btnExercisesFindEdit = new JButton(new exercisesFindEditHandler());
        btnExercisesFindEdit.setBounds(10,345,90,35);
        btnExercisesFindEdit.setBackground(new Color(214,217,223));
        btnExercisesFindEdit.setForeground(new Color(0,0,0));
        btnExercisesFindEdit.setEnabled(true);
        btnExercisesFindEdit.setFont(new Font("sansserif",0,12));
        btnExercisesFindEdit.setText("Edit");
        btnExercisesFindEdit.setVisible(true);
        
        btnExercisesFindDelete = new JButton(new exercisesFindDeleteHandler());
        btnExercisesFindDelete.setBounds(100,345,90,35);
        btnExercisesFindDelete.setBackground(new Color(214,217,223));
        btnExercisesFindDelete.setForeground(new Color(0,0,0));
        btnExercisesFindDelete.setEnabled(true);
        btnExercisesFindDelete.setFont(new Font("sansserif",0,12));
        btnExercisesFindDelete.setText("Delete");
        btnExercisesFindDelete.setVisible(true);
        
        pnlExercisesFind.add(btnExercisesFindEdit);
        pnlExercisesFind.add(btnExercisesFindDelete);
        pnlExercisesFind.add(lblExercisesFindTitle);
        pnlExercisesFind.add(pnlExercisesFindScroll);
    
        /**
         * Exercises - Edit
         */
        intExercisesEditExerciseID = -1;
        
        pnlExercisesEdit = new JPanel(null);
        pnlExercisesEdit.setBorder(BorderFactory.createEtchedBorder(1));
        pnlExercisesEdit.setBounds(0,0,500,400);
        pnlExercisesEdit.setBackground(new Color(214,217,223));
        pnlExercisesEdit.setForeground(new Color(0,0,0));
        pnlExercisesEdit.setEnabled(true);
        pnlExercisesEdit.setFont(new Font("sansserif",0,12));
        pnlExercisesEdit.setVisible(false);
            
        lblExercisesEditTitle = new JLabel();
        lblExercisesEditTitle.setBounds(10,5,90,20);
        lblExercisesEditTitle.setBackground(new Color(214,217,223));
        lblExercisesEditTitle.setForeground(new Color(0,0,0));
        lblExercisesEditTitle.setEnabled(true);
        lblExercisesEditTitle.setFont(new Font("sansserif",0,12));
        lblExercisesEditTitle.setText("Edit Exercise");
        lblExercisesEditTitle.setVisible(true);
        
        lblExercisesEditName = new JLabel();
        lblExercisesEditName.setBounds(100,5,300,20);
        lblExercisesEditName.setBackground(new Color(214,217,223));
        lblExercisesEditName.setForeground(new Color(0,0,0));
        lblExercisesEditName.setEnabled(true);
        lblExercisesEditName.setFont(new Font("sansserif",0,12));
        lblExercisesEditName.setText("");
        lblExercisesEditName.setVisible(true);
        
        lblExercisesEditExerciseName = new JLabel();
        lblExercisesEditExerciseName.setBounds(10,30,300,20);
        lblExercisesEditExerciseName.setBackground(new Color(214,217,223));
        lblExercisesEditExerciseName.setForeground(new Color(0,0,0));
        lblExercisesEditExerciseName.setEnabled(true);
        lblExercisesEditExerciseName.setFont(new Font("sansserif",0,12));
        lblExercisesEditExerciseName.setText("Name");
        lblExercisesEditExerciseName.setVisible(true);
        
        lblExercisesEditExercisePrice = new JLabel();
        lblExercisesEditExercisePrice.setBounds(100,30,300,20);
        lblExercisesEditExercisePrice.setBackground(new Color(214,217,223));
        lblExercisesEditExercisePrice.setForeground(new Color(0,0,0));
        lblExercisesEditExercisePrice.setEnabled(true);
        lblExercisesEditExercisePrice.setFont(new Font("sansserif",0,12));
        lblExercisesEditExercisePrice.setText("Price");
        lblExercisesEditExercisePrice.setVisible(true);
           
        txtExercisesEditName = new JTextField();
        txtExercisesEditName.setBounds(10,55,90,20);
        txtExercisesEditName.setBackground(new Color(255,255,255));
        txtExercisesEditName.setForeground(new Color(0,0,0));
        txtExercisesEditName.setEnabled(true);
        txtExercisesEditName.setFont(new Font("sansserif",0,12));
        txtExercisesEditName.setText("");
        txtExercisesEditName.setVisible(true);
           
        txtExercisesEditPrice = new JTextField();
        txtExercisesEditPrice.setBounds(100,55,90,20);
        txtExercisesEditPrice.setBackground(new Color(255,255,255));
        txtExercisesEditPrice.setForeground(new Color(0,0,0));
        txtExercisesEditPrice.setEnabled(true);
        txtExercisesEditPrice.setFont(new Font("sansserif",0,12));
        txtExercisesEditPrice.setText("");
        txtExercisesEditPrice.setVisible(true);
        
        btnExercisesEditSave = new JButton(new exercisesEditSaveHandler());
        btnExercisesEditSave.setBounds(10,345,90,35);
        btnExercisesEditSave.setBackground(new Color(214,217,223));
        btnExercisesEditSave.setForeground(new Color(0,0,0));
        btnExercisesEditSave.setEnabled(true);
        btnExercisesEditSave.setFont(new Font("sansserif",0,12));
        btnExercisesEditSave.setText("Save");
        btnExercisesEditSave.setVisible(true);
        
        btnExercisesEditCancel = new JButton(new exercisesEditCancelHandler());
        btnExercisesEditCancel.setBounds(100,345,90,35);
        btnExercisesEditCancel.setBackground(new Color(214,217,223));
        btnExercisesEditCancel.setForeground(new Color(0,0,0));
        btnExercisesEditCancel.setEnabled(true);
        btnExercisesEditCancel.setFont(new Font("sansserif",0,12));
        btnExercisesEditCancel.setText("Cancel");
        btnExercisesEditCancel.setVisible(true);
            
        pnlExercisesEdit.add(lblExercisesEditTitle);
        pnlExercisesEdit.add(lblExercisesEditName);
        pnlExercisesEdit.add(lblExercisesEditExerciseName);
        pnlExercisesEdit.add(lblExercisesEditExercisePrice);
        pnlExercisesEdit.add(txtExercisesEditName);
        pnlExercisesEdit.add(txtExercisesEditPrice);        
        pnlExercisesEdit.add(btnExercisesEditSave);
        pnlExercisesEdit.add(btnExercisesEditCancel);
        
        /**
         * PANEL Construction
         */
        pnlParent.add(pnlReports);
        pnlParent.add(pnlBookingsEdit);
        pnlParent.add(pnlBookingsFind);
        pnlParent.add(pnlMembersEdit);
        pnlParent.add(pnlMembersFind);
        pnlParent.add(pnlClassesEdit);
        pnlParent.add(pnlClassesFind);
        pnlParent.add(pnlExercisesEdit);
        pnlParent.add(pnlExercisesFind);
        pnlParent.add(pnlAbout);
        this.add(pnlParent);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }
    
    private void hidePanels()
    {
        pnlReports.setVisible(false);
        pnlBookingsEdit.setVisible(false);
        pnlBookingsFind.setVisible(false);
        pnlMembersEdit.setVisible(false);
        pnlMembersFind.setVisible(false);
        pnlClassesEdit.setVisible(false);
        pnlClassesFind.setVisible(false);
        pnlExercisesEdit.setVisible(false);
        pnlExercisesFind.setVisible(false);
        pnlAbout.setVisible(false);
    }
    
    private void showAbout()
    {
        hidePanels();
        pnlAbout.setVisible(true);
    }
    
    private void showReports_Search()
    {
        hidePanels();
        pnlReports.setVisible(true);
        
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(Club.getSystemDate());
        Integer currentYear = new Integer(currentDate.get(Calendar.YEAR));
        Integer currentMonth = new Integer(currentDate.get(Calendar.MONTH) + 1);
                
        txtReportsYear.setText(currentYear.toString());
        txtReportsMonth.setText(currentMonth.toString());
             
        txtReportsChampion.setText("");
            
        lstReportsResultsModel.clear();        
    }
    
    private void showReports_Results()
    {
        hidePanels();
        pnlReports.setVisible(true);
           
        int reportYear;
        int reportMonth;
        
        try
        {
            reportYear = Integer.parseInt(txtReportsYear.getText());
            reportMonth = Integer.parseInt(txtReportsMonth.getText());
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Please check the month and year values: " + ex.getMessage());
            return;
        }
            
            ;  
        try
        {
            txtReportsChampion.setText(clbWeekendFitnessClub.reportMonthlyClassChampion(reportMonth, reportYear));
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "The monthly champion report could not be displayed: " + ex.getMessage());
            return;
        }
            
            
        try
        {
            lstReportsResultsModel.clear();
            for (String reportLine : clbWeekendFitnessClub.reportMonthlyClassResults(reportMonth, reportYear))
            {
                lstReportsResultsModel.addElement(reportLine);
            }
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "The monthly class report could not be displayed: " + ex.getMessage());
            return;
        }
        
    }
    
    private void showBookings_Add()
    {
        hidePanels();
        pnlBookingsEdit.setVisible(true);
        intBookingsEditBookingID = -1;
        lblBookingsEditName.setVisible(false);
        cmbBookingsEditMember.setVisible(true);
        cmbBookingsEditRating.setVisible(false);
        txtBookingsEditReview.setVisible(false);
        intBookingsEditMemberIndex = -1;
        
        cmbBookingsEditPayment.setSelectedIndex(0);
        cmbBookingsEditStatus.setSelectedIndex(0);
        pnlBookingsClassScroll.setBounds(10,65,480,270);
        
        cmbBookingsEditMember.removeAllItems();
        arlBookingsEditMemberMapping.clear();
        for (Member currentMember : clbWeekendFitnessClub.clubMemberRegister.values())
        {
            arlBookingsEditMemberMapping.add(currentMember.getID());
            cmbBookingsEditMember.addItem(currentMember.getFirstName() + " " + currentMember.getLastName());
        }
        
        lstBookingsEditClassModel.clear();
        arlBookingsEditClassMapping.clear();
        for (Class currentClass : clbWeekendFitnessClub.clubClassRegister.values())
        {
            lstBookingsEditClassModel.addElement(currentClass.getLabel());
            arlBookingsEditClassMapping.add(currentClass.getID());
            if (lstBookingsEditClass.getSelectedIndex() < 0 && currentClass.getDate().compareTo(Club.getSystemDate()) > 0)
            {
                intBookingsEditClassIndex = arlBookingsEditClassMapping.size() - 1;
                lstBookingsEditClass.setSelectedIndex(intBookingsEditClassIndex);
            }
        }
        lstBookingsEditClass.ensureIndexIsVisible(lstBookingsEditClass.getSelectedIndex());
    }
    
    private void showBookings_Edit() 
    {
        hidePanels();
        pnlBookingsEdit.setVisible(true);
        lblBookingsEditName.setVisible(true);
        cmbBookingsEditMember.setVisible(false);
        cmbBookingsEditRating.setVisible(true);
        txtBookingsEditReview.setVisible(true);
        pnlBookingsClassScroll.setBounds(10,65,480,230);
        intBookingsEditBookingID = arlBookingsFindMapping.get(intBookingsFindIndex);
        Booking editBooking = clbWeekendFitnessClub.clubBookingRegister.get(intBookingsEditBookingID);
        Class editBookingClass = editBooking.getBookingClass();
        Member editBookingMember = editBooking.getBookingMember();
        lblBookingsEditName.setText(editBookingMember.getFirstName() + " " + editBookingMember.getLastName());
        cmbBookingsEditPayment.setSelectedItem(editBooking.getPayment());
        cmbBookingsEditStatus.setSelectedItem(editBooking.getStatus());
        lstBookingsEditClassModel.clear();
        arlBookingsEditClassMapping.clear();
        for (Class currentClass : clbWeekendFitnessClub.clubClassRegister.values())
        {
            lstBookingsEditClassModel.addElement(currentClass.getLabel());
            arlBookingsEditClassMapping.add(currentClass.getID());
            if (currentClass.equals(editBookingClass))
            {
                intBookingsEditClassIndex = arlBookingsEditClassMapping.size() - 1;
            }
        }
        lstBookingsEditClass.setSelectedIndex(intBookingsEditClassIndex);
        lstBookingsEditClass.ensureIndexIsVisible(lstBookingsEditClass.getSelectedIndex());
        
        cmbBookingsEditRating.setSelectedItem(editBooking.getRating());
        txtBookingsEditReview.setText(editBooking.getReview());        
    }
    
    private void showBookings_Find() 
    {
        hidePanels();
        pnlBookingsFind.setVisible(true);
        lstBookingsModel.clear();
        arlBookingsFindMapping.clear();
        for (Booking currentBooking : clbWeekendFitnessClub.clubBookingRegister.values())
        {
            lstBookingsModel.addElement(currentBooking.getLabel());
            arlBookingsFindMapping.add(currentBooking.getID());
        }
        lstBookingsFind.setVisible(true);
        
    }
    
    private void showBookings_Save()
    {
        hidePanels();
        if (intBookingsEditBookingID > 0)
        {
            try
            {
                Booking saveBooking = clbWeekendFitnessClub.clubBookingRegister.get(intBookingsEditBookingID);
                if (cmbBookingsEditPayment.getSelectedItem() != saveBooking.getPayment())
                {
                    saveBooking.setPayment((PaymentMethods) cmbBookingsEditPayment.getSelectedItem());
                }
                if (cmbBookingsEditStatus.getSelectedItem() != saveBooking.getStatus())
                {
                    saveBooking.setStatus((BookingStatus) cmbBookingsEditStatus.getSelectedItem());
                }
                if (cmbBookingsEditRating.getSelectedItem() != saveBooking.getRating())
                {
                    saveBooking.setRating((Ratings) cmbBookingsEditRating.getSelectedItem());
                }
                if (txtBookingsEditReview.getText().equals(saveBooking.getReview()) == false)
                {
                    saveBooking.setReview(txtBookingsEditReview.getText());
                }
                if (intBookingsEditClassIndex != lstBookingsEditClass.getSelectedIndex())
                {
                   Class saveBookingClass = clbWeekendFitnessClub.clubClassRegister.get(arlBookingsEditClassMapping.get(lstBookingsEditClass.getSelectedIndex()));
                   Member saveBookingMember = saveBooking.getBookingMember();
                   clbWeekendFitnessClub.clubBookingRegister.add(saveBookingMember, saveBookingClass, saveBooking.getPayment(), saveBooking.getStatus());
                   clbWeekendFitnessClub.clubBookingRegister.cancel(intBookingsEditBookingID);
                } 
                showBookings_Find();
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "The booking could not be saved: " + ex.getMessage());
                showBookings_Edit();
            }
        }
        else
        {
            try 
            {
                Class saveBookingClass = clbWeekendFitnessClub.clubClassRegister.get(arlBookingsEditClassMapping.get(lstBookingsEditClass.getSelectedIndex()));
                Member saveBookingMember = clbWeekendFitnessClub.clubMemberRegister.get(arlBookingsEditMemberMapping.get(cmbBookingsEditMember.getSelectedIndex()));
                clbWeekendFitnessClub.clubBookingRegister.add(saveBookingMember, saveBookingClass, (PaymentMethods) cmbBookingsEditPayment.getSelectedItem(), (BookingStatus) cmbBookingsEditStatus.getSelectedItem()); 
                showBookings_Find();
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "The booking could not be saved: " + ex.getMessage());
                showBookings_Add();
            }
        }
    }
    
    private void showBookings_Delete()
    {
        hidePanels();
        int intDeleteBookingReply = JOptionPane.showConfirmDialog(null, "Are you sure you would like to cancel this booking?", "Warning!", JOptionPane.YES_NO_OPTION);
        if (intDeleteBookingReply == JOptionPane.YES_OPTION) 
        {
            Integer intCancelIndex = arlBookingsFindMapping.get(intBookingsFindIndex);
            clbWeekendFitnessClub.clubBookingRegister.cancel(intCancelIndex);
        }
        showBookings_Find();
    }
    
    private void showMembers_Add()
    {
        hidePanels();
        pnlMembersEdit.setVisible(true);
        intMembersEditMemberID = -1;
        lblMembersEditName.setVisible(false);
        txtMembersEditFirstName.setText("");
        txtMembersEditLastName.setText("");
    }
    
    private void showMembers_Edit()
    {
        hidePanels();
        pnlMembersEdit.setVisible(true);
        lblMembersEditName.setVisible(true);
        
        intMembersEditMemberID = arlMembersFindMapping.get(intMembersFindIndex);
        Member editMember = clbWeekendFitnessClub.clubMemberRegister.get(intMembersEditMemberID);
        lblMembersEditName.setText(editMember.getLabel());
        txtMembersEditFirstName.setText(editMember.getFirstName());
        txtMembersEditLastName.setText(editMember.getLastName());
    }
    
    private void showMembers_Find()
    {
        hidePanels();
        pnlMembersFind.setVisible(true);
        lstMembersModel.clear();
        arlMembersFindMapping.clear();
        for (Member currentMember : clbWeekendFitnessClub.clubMemberRegister.values())
        {
            lstMembersModel.addElement(currentMember.getLabel());
            arlMembersFindMapping.add(currentMember.getID());
        }
        lstMembersFind.setVisible(true);
    }
    
    private void showMembers_Save()
    {
        hidePanels();
        if (intMembersEditMemberID > 0)
        {
            try
            {
                Member saveMember = clbWeekendFitnessClub.clubMemberRegister.get(intMembersEditMemberID);
                if (txtMembersEditFirstName.getText().equals(saveMember.getFirstName()) == false)
                {
                    saveMember.setFirstName(txtMembersEditFirstName.getText());
                }
                if (txtMembersEditLastName.getText().equals(saveMember.getLastName()) == false)
                {
                    saveMember.setLastName(txtMembersEditLastName.getText());
                }
                showMembers_Find();  
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "The member could not be saved: " + ex.getMessage());
                showMembers_Edit();
            }
        }
        else
        {
            try 
            {
                clbWeekendFitnessClub.clubMemberRegister.add(txtMembersEditFirstName.getText(), txtMembersEditLastName.getText()); 
                showMembers_Find();  
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "The member could not be saved: " + ex.getMessage());
                showMembers_Add();
            }
        }
    }
    
    private void showMembers_Delete()
    {
        hidePanels();
        int intDeleteMemberReply = JOptionPane.showConfirmDialog(null, "Are you sure you would like to cancel this membership? This will also cancel any bookings associated with this member.", "Warning!", JOptionPane.YES_NO_OPTION);
        if (intDeleteMemberReply == JOptionPane.YES_OPTION) 
        {
            // Cancellation of associated bookings is handled on the backend.
            Integer intCancelIndex = arlMembersFindMapping.get(intMembersFindIndex);
            clbWeekendFitnessClub.clubMemberRegister.cancel(intCancelIndex);
        }
        showMembers_Find();
    }
    
    private void showClasses_Add()
    {
        hidePanels();
        pnlClassesEdit.setVisible(true);
        intClassesEditClassID = -1;
        lblClassesEditName.setVisible(false);
        intClassesEditExerciseIndex = -1;
        cmbClassesEditSlot.setSelectedIndex(0);
        txtClassesEditDate.setText(Club.getConvertedDate(Club.getSystemDate()));                
        
        cmbClassesEditExercise.removeAllItems();
        arlClassesEditExerciseMapping.clear();
        for (Exercise currentExercise : clbWeekendFitnessClub.clubExerciseRegister.values())
        {
            arlClassesEditExerciseMapping.add(currentExercise.getID());
            cmbClassesEditExercise.addItem(currentExercise.getLabel());
        }
        cmbClassesEditExercise.setSelectedItem(0);
    }
    
    private void showClasses_Edit()
    {
        hidePanels();
        pnlClassesEdit.setVisible(true);
        lblClassesEditName.setVisible(false);
        intClassesEditClassID = arlClassesFindMapping.get(intClassesFindIndex);
        
        Class editClass = clbWeekendFitnessClub.clubClassRegister.get(intClassesEditClassID);
        Exercise editClassExercise = editClass.getClassExercise();
        
        cmbClassesEditSlot.setSelectedItem(editClass.getSlot());
        txtClassesEditDate.setText(Club.getConvertedDate(editClass.getDate()));                
        
        cmbClassesEditExercise.removeAllItems();
        arlClassesEditExerciseMapping.clear();
        for (Exercise currentExercise : clbWeekendFitnessClub.clubExerciseRegister.values())
        {
            arlClassesEditExerciseMapping.add(currentExercise.getID());
            cmbClassesEditExercise.addItem(currentExercise.getLabel());
            if (currentExercise.equals(editClassExercise))
            {
                intClassesEditExerciseIndex = arlClassesEditExerciseMapping.size() - 1;
            }
        }
        cmbClassesEditExercise.setSelectedIndex(intClassesEditExerciseIndex);
    }
    
    private void showClasses_Find()
    {
        hidePanels();
        pnlClassesFind.setVisible(true);
        lstClassesModel.clear();
        arlClassesFindMapping.clear();
        for (Class currentClass : clbWeekendFitnessClub.clubClassRegister.values())
        {
            lstClassesModel.addElement(currentClass.getLabel());
            arlClassesFindMapping.add(currentClass.getID());
        }
        lstClassesFind.setVisible(true);
    }
    
    private void showClasses_Save()
    {
        hidePanels();
        if (intClassesEditClassID > 0)
        {
            try
            {
                Class saveClass = clbWeekendFitnessClub.clubClassRegister.get(intClassesEditClassID);
                Exercise saveClassExercise = clbWeekendFitnessClub.clubExerciseRegister.get(arlClassesEditExerciseMapping.get(cmbClassesEditExercise.getSelectedIndex()));
                
                
                if (Club.checkWeekend(Club.getConvertedDate(txtClassesEditDate.getText())) == false)
                {
                    JOptionPane.showMessageDialog(null, "The class cannot be booked for a weekday");
                    showClasses_Edit();
                    return;
                }
                
                for (Class currentClass: clbWeekendFitnessClub.clubClassRegister.values())
                {
                    if (currentClass.getDate().compareTo(Club.getConvertedDate(txtClassesEditDate.getText())) == 0 && currentClass.getSlot() == cmbClassesEditSlot.getSelectedItem())
                    {
                        JOptionPane.showMessageDialog(null, "The class cannot be booked for a date and time already allocated to another class");
                        showClasses_Edit();
                        return;
                    }
                }
                
                if (txtClassesEditDate.getText().equals(Club.getConvertedDate(saveClass.getDate())) == false)
                {
                    saveClass.setDate(Club.getConvertedDate(txtClassesEditDate.getText()));
                }
                if (cmbClassesEditSlot.getSelectedItem() != saveClass.getSlot())
                {
                    saveClass.setSlot((Slot) cmbClassesEditSlot.getSelectedItem());
                }
                if (saveClassExercise.equals(saveClass.getClassExercise()) == false)
                {
                    saveClass.setClassExercise(saveClassExercise);
                }
                showClasses_Find();
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "The class could not be saved: " + ex.getMessage());
                showClasses_Edit();
            }
        }
        else
        {
            try 
            {
                Exercise saveClassExercise = clbWeekendFitnessClub.clubExerciseRegister.get(arlClassesEditExerciseMapping.get(cmbClassesEditExercise.getSelectedIndex()));
                
                clbWeekendFitnessClub.clubClassRegister.add(Club.getConvertedDate(txtClassesEditDate.getText()), (Slot) cmbClassesEditSlot.getSelectedItem(), saveClassExercise); 
                showClasses_Find();
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "The class could not be saved: " + ex.getMessage());
                showClasses_Add();
            }
        } 
    }
    
    private void showClasses_Delete()
    {
        hidePanels();
        int intDeleteClassReply = JOptionPane.showConfirmDialog(null, "Are you sure you would like to cancel this class? This will also cancel any associated bookings.", "Warning!", JOptionPane.YES_NO_OPTION);
        if (intDeleteClassReply == JOptionPane.YES_OPTION) 
        {
            Integer intCancelClassIndex = arlClassesFindMapping.get(intClassesFindIndex);
            clbWeekendFitnessClub.clubClassRegister.cancel(intCancelClassIndex);
        }
        showClasses_Find();
    }
    
    private void showExercises_Add()
    {
        hidePanels();
        pnlExercisesEdit.setVisible(true);
        intExercisesEditExerciseID = -1;
        lblExercisesEditName.setVisible(false);
        txtExercisesEditName.setText("");
        txtExercisesEditPrice.setText(""); 
    }
    
    private void showExercises_Edit()
    {
        hidePanels();
        pnlExercisesEdit.setVisible(true);
        lblExercisesEditName.setVisible(true);
        
        intExercisesEditExerciseID = arlExercisesFindMapping.get(intExercisesFindIndex);
        Exercise editExercise = clbWeekendFitnessClub.clubExerciseRegister.get(intExercisesEditExerciseID);
        
        txtExercisesEditName.setText(editExercise.getName());
        txtExercisesEditPrice.setText(editExercise.getPrice().toString());
    }
    
    private void showExercises_Find()
    {
        hidePanels();
        pnlExercisesFind.setVisible(true);
        lstExercisesModel.clear();
        arlExercisesFindMapping.clear();
        for (Exercise currentExercise : clbWeekendFitnessClub.clubExerciseRegister.values())
        {
            lstExercisesModel.addElement(currentExercise.getLabel());
            arlExercisesFindMapping.add(currentExercise.getID());
        }
        lstExercisesFind.setVisible(true);
    }
    
    private void showExercises_Save()
    {
        hidePanels();
        if (intExercisesEditExerciseID > 0)
        {
            try
            {
                Exercise saveExercise = clbWeekendFitnessClub.clubExerciseRegister.get(intExercisesEditExerciseID);
                if (txtExercisesEditName.getText().equals(saveExercise.getName()) == false)
                {
                    saveExercise.setName(txtExercisesEditName.getText());
                }
                if (new BigDecimal(txtExercisesEditPrice.getText()).equals(saveExercise.getPrice()) == false)
                {
                    saveExercise.setPrice(new BigDecimal(txtExercisesEditPrice.getText()));
                }
                showExercises_Find();
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "The exercise could not be saved: " + ex.getMessage());
                showExercises_Edit();
            }
        }
        else
        {
            try 
            {
                clbWeekendFitnessClub.clubExerciseRegister.add(txtExercisesEditName.getText(), new BigDecimal(txtExercisesEditPrice.getText())); 
                showExercises_Find();
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "The exercise could not be saved: " + ex.getMessage());
                showExercises_Add();
            }
        } 
    }
        
    private void showExercises_Delete()
    {
        hidePanels();
        int intDeleteExerciseReply = JOptionPane.showConfirmDialog(null, "Are you sure you would like to delete this exercise? This will also cancel any associated classes and bookings.", "Warning!", JOptionPane.YES_NO_OPTION);
        if (intDeleteExerciseReply == JOptionPane.YES_OPTION) 
        {
            Integer intDeleteExerciseIndex = arlExercisesFindMapping.get(intExercisesFindIndex);
            clbWeekendFitnessClub.clubExerciseRegister.cancel(intDeleteExerciseIndex);
        }
        showExercises_Find();
    }
    
    public class aboutHandler extends AbstractAction 
    {
        public aboutHandler() 
        {
            super("About");
        }
        
        public void actionPerformed(ActionEvent e)
        {
            showAbout();  
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
                showBookings_Find();
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
            intBookingsFindIndex = lstBookingsFind.getSelectedIndex();
            try
            {
                if (intBookingsFindIndex >= 0)
                {
                    showBookings_Edit();
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
            intBookingsFindIndex = lstBookingsFind.getSelectedIndex();
            try
            {
                if (intBookingsFindIndex >= 0)
                {
                    showBookings_Delete();
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
                showBookings_Save();
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
            try
            {
                showBookings_Find();
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
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
            try
            {
                showBookings_Add();
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }        
        }
    }
    
    public class membersFindHandler extends AbstractAction 
    {
        public membersFindHandler() 
        {
            super("Find");
        }
        
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                showMembers_Find();
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }           
        }
    }
    
    public class membersFindEditHandler extends AbstractAction 
    {        
        public void actionPerformed(ActionEvent e)
        {
            intMembersFindIndex = lstMembersFind.getSelectedIndex();
            try
            {
                if (intMembersFindIndex >= 0)
                {
                    showMembers_Edit();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please select a Member first");
                }
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }          
        }
    }
    
    public class membersFindDeleteHandler extends AbstractAction 
    {   
        public void actionPerformed(ActionEvent e)
        {
            intMembersFindIndex = lstMembersFind.getSelectedIndex();
            try
            {
                if (intMembersFindIndex >= 0)
                {
                    showMembers_Delete();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please select a Member first");
                }
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
    
    public class membersEditSaveHandler extends AbstractAction 
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                showMembers_Save();
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }           
        }
    }
    
    public class membersEditCancelHandler extends AbstractAction 
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                showMembers_Find();
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }           
        }
    }
    
    public class membersAddHandler extends AbstractAction 
    {
        public membersAddHandler() 
        {
            super("Add");
        }
        
        public void actionPerformed(ActionEvent e)
        {
            
            showMembers_Add();            
        }
    }
    
    public class classesFindHandler extends AbstractAction 
    {
        public classesFindHandler() 
        {
            super("Find");
        }
        
        public void actionPerformed(ActionEvent e)
        {
            
            showClasses_Find();            
        }
    }
    
    public class classesFindEditHandler extends AbstractAction 
    {
        public void actionPerformed(ActionEvent e)
        {
            intClassesFindIndex = lstClassesFind.getSelectedIndex();
            try
            {
                if (intClassesFindIndex >= 0)
                {
                    showClasses_Edit();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please select a Class first");
                }
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }          
        }
    }
    
    public class classesFindDeleteHandler extends AbstractAction 
    {
        public void actionPerformed(ActionEvent e)
        {
            intClassesFindIndex = lstClassesFind.getSelectedIndex();
            try
            {
                if (intClassesFindIndex >= 0)
                {
                    showClasses_Delete();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please select a Class first");
                }
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
    
    public class classesEditSaveHandler extends AbstractAction 
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                showClasses_Save();
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }           
        }
    }
    
    public class classesEditCancelHandler extends AbstractAction 
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                showClasses_Find();
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }           
        }
    }
    
    public class classesAddHandler extends AbstractAction 
    {
        public classesAddHandler() 
        {
            super("Add");
        }
        
        public void actionPerformed(ActionEvent e)
        {
            
            showClasses_Add();            
        }
    }
    
    public class exercisesFindHandler extends AbstractAction 
    {
        public exercisesFindHandler() 
        {
            super("Find");
        }
        
        public void actionPerformed(ActionEvent e)
        {
            
            showExercises_Find();            
        }
    }
    
    public class exercisesFindEditHandler extends AbstractAction 
    {
        public void actionPerformed(ActionEvent e)
        {
            intExercisesFindIndex = lstExercisesFind.getSelectedIndex();
            try
            {
                if (intExercisesFindIndex >= 0)
                {
                    showExercises_Edit();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please select a Exercise first");
                }
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }          
        }
    }
    
    public class exercisesFindDeleteHandler extends AbstractAction 
    {
        public void actionPerformed(ActionEvent e)
        {
            intExercisesFindIndex = lstExercisesFind.getSelectedIndex();
            try
            {
                if (intExercisesFindIndex >= 0)
                {
                    showExercises_Delete();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please select an Exercise first");
                }
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
    
    public class exercisesEditSaveHandler extends AbstractAction 
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                showExercises_Save();
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }           
        }
    }
    
    public class exercisesEditCancelHandler extends AbstractAction 
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                showExercises_Find();
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }           
        }
    }
    
    public class exercisesAddHandler extends AbstractAction 
    {
        public exercisesAddHandler() 
        {
            super("Add");
        }
        
        public void actionPerformed(ActionEvent e)
        {
            
            showExercises_Add();            
        }
    }
    
    public class reportsHandler extends AbstractAction 
    {
        public reportsHandler() 
        {
            super("Reports");
        }
        
        public void actionPerformed(ActionEvent e)
        {
            showReports_Search();         
        }
    }
    
    public class reportsResultsHandler extends AbstractAction 
    {        
        public void actionPerformed(ActionEvent e)
        {
            showReports_Results();         
        }
    }
}