import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Calendar; 
import java.util.Date;
import java.text.SimpleDateFormat; 
import java.math.BigDecimal;
import java.math.RoundingMode;
/**
* Class to hold methods and state for the club. 
* Not strictly necessary to implement as a class because there is only one instance.
* However this structure allows multiple test routines without duplication of effort.
* 
* 16018361
* 07/01/2019
*/
    public class Club
    {
    private static Date SystemDate = getConvertedDate("01/01/1970");
    
    private static final Integer maxClassSize = 20;
    
    public MemberRegister clubMemberRegister;
    public ExerciseRegister clubExerciseRegister;
    public ClassRegister clubClassRegister;
    public BookingRegister clubBookingRegister;
    
    public static Date getSystemDate()
    {
        return SystemDate;
    }
    
    public static void setSystemDate(Date newDate)
    {
        SystemDate = newDate;
    }
    
    public static Date getConvertedDate(String strDate)
    {
        try
        {
            return new SimpleDateFormat("dd/MM/yyyy").parse(strDate);
        }
        catch (Exception ex)
        {
            throw new IllegalStateException("Date must be in the format 'dd/MM/yyyy'");
        }
    }
    
    public static String getConvertedDate(Date datDate)
    {
        return new SimpleDateFormat("dd/MM/yyyy").format(datDate);
    }
    
    public static Integer getMaxClassSize()
    {
        return maxClassSize;
    }
    
    public static boolean checkWeekend(Date checkDate)
    {
        Calendar newDateCalendar = Calendar.getInstance();
        newDateCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        newDateCalendar.setTime(checkDate);
        int day = newDateCalendar.get(Calendar.DAY_OF_WEEK);
        if (day == Calendar.SATURDAY || day == Calendar.SUNDAY)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public Club(boolean blnTestData)
    {
        if (blnTestData == true) 
        {
            clubMemberRegister = new MemberRegister(true);
            clubExerciseRegister = new ExerciseRegister(true);
            clubClassRegister = new ClassRegister(true);
            clubBookingRegister = new BookingRegister(true);
            setSystemDate(new Date());
        }
        else
        {
            clubMemberRegister = new MemberRegister(false);
            clubExerciseRegister = new ExerciseRegister(false);
            clubClassRegister = new ClassRegister(false);
            clubBookingRegister = new BookingRegister(false);
            setSystemDate(new Date());
        }
    }
    
    public String reportMonthlyClassChampion(int intMonth, int intYear)
    {
        //a report containing the fitness class which has generated the highest income
        if (1 > intMonth || intMonth > 12)
        {
            throw new IllegalArgumentException("Month must fall between January(1) and December(12)");
        }
        else
        {
            BigDecimal highestTakings = new BigDecimal(0.0).setScale(2, RoundingMode.HALF_UP);
            String ReportLine = new String("");
            Integer championClassID = new Integer(0);
            Class championClass;
            for (Class currentClass : clubClassRegister.values())
            {
                Calendar currentDate = Calendar.getInstance();
                currentDate.setTime(currentClass.getDate());
                int currentYear = currentDate.get(Calendar.YEAR);
                int currentMonth = currentDate.get(Calendar.MONTH) + 1;
                if (currentYear == intYear && currentMonth == intMonth)
                {
                    BigDecimal currentTakings = currentClass.getTakings();
                    if (currentTakings.compareTo(highestTakings) > 0) 
                    {
                        championClassID = currentClass.getID();
                        highestTakings = currentTakings;
                    }
                }
            }
            if (highestTakings.compareTo(new BigDecimal(0.0)) > 0)
            {
                championClass = clubClassRegister.get(championClassID);
                ReportLine = Club.getConvertedDate(championClass.getDate()) + ", " + championClass.getSlot().toString() + ", " + championClass.getClassExercise().getName().toString() + ", Takings: " + championClass.getTakings().toString();
            }       
            else
            {
                ReportLine = "No classes have yet taken any money";
            }
            return ReportLine;
        }
    }
    
    public ArrayList<String> reportMonthlyClassResults(int intMonth, int intYear)
    {        
        //a report containing the number of customers per fitness class, along with the average rating;
        if (1 > intMonth || intMonth > 12)
        {
            throw new IllegalArgumentException("Month must fall between January(1) and December(12)");
        }
        else
        {
            ArrayList<String> MonthlyResults = new ArrayList<String>();
            for (Class currentClass : clubClassRegister.values())
            {
                Calendar currentDate = Calendar.getInstance();
                currentDate.setTime(currentClass.getDate());
                int currentYear = currentDate.get(Calendar.YEAR);
                int currentMonth = currentDate.get(Calendar.MONTH) + 1;
                if (currentYear == intYear && currentMonth == intMonth)
                {
                    //Figure out average rating
                    double total = 0.0;
                    double count = 0.0;
                    for (Booking currentBooking : clubBookingRegister.values())
                    {
                        Class BookingClass = currentBooking.getBookingClass();
                        if (BookingClass.equals(currentClass))
                        {
                            switch (currentBooking.getRating())
                            {
                                case None:
                                    count++;
                                    break;
                                case One:
                                    count++;
                                    total += 1.0;
                                    break;
                                case Two:
                                    count++;
                                    total += 2.0;
                                    break;
                                case Three:
                                    count++;
                                    total += 3.0;
                                    break;
                                case Four:
                                    count++;
                                    total += 4.0;
                                    break;
                                case Five:
                                    count++;
                                    total += 5.0;
                                    break;
                            }
                        }
                    }
                    String averageRating = "";
                    Integer intBookings = Club.getMaxClassSize() - currentClass.getSpaces();
                    if (count > 0 && total > 0)
                    {
                        BigDecimal mean;
                        mean = new BigDecimal(total / count).setScale(1, RoundingMode.HALF_UP);
                                                
                        averageRating = ", Average rating: " + mean.toString();
                    }
                    else
                    {       
                        averageRating = ", Average rating: not yet rated";
                    }
                    String ReportLine = Club.getConvertedDate(currentClass.getDate()) + ", " + currentClass.getSlot().toString() + ", " + currentClass.getClassExercise().getName().toString() + ", Bookings: " + intBookings.toString() + averageRating;
                    MonthlyResults.add(ReportLine);
                }
            }
            return MonthlyResults;
        }
    }
    
    public class MemberRegister 
    {
        private HashMap<Integer, Member> mapMemberRegister = new HashMap<Integer, Member>();
        private Integer nextUniqueID = 1;
    
        public MemberRegister(boolean blnTestData)
        {
            if (blnTestData == true)
            {
                //15+ demo customers
                this.add("Bob", "Dylan");
                this.add("Jimi", "Hendrix");
                this.add("Janice", "Joplin");
                this.add("Joni", "Mitchell");
                this.add("Woody", "Guthrie");
                this.add("Ani", "DiFranco");
                this.add("Johnny", "Cash");
                this.add("Cat", "Stevens");
                this.add("Emmylou", "Harris");
                this.add("James", "Taylor");
                this.add("Cab", "Calloway");
                this.add("Aretha", "Franklin");
                this.add("James", "Brown");
                this.add("John Lee", "Hooker");
                this.add("Jake", "Blues");
                this.add("Elwood", "Blues");
                this.add("Kurt", "Cobain");
                this.add("Jack", "Black");
                this.add("Kyle", "Gass");
                this.add("Dave", "Grohl");
                this.add("Brian", "Welch");
                this.add("Jonathan", "Davis");
            }
        }
        
        public void add(String newFirstName, String newLastName)
        {
            Member newMember = new Member(newFirstName, newLastName, nextUniqueID);
            mapMemberRegister.put(nextUniqueID, newMember);
            nextUniqueID++;
        }
        
        public Member get(Integer ID)
        {
            return mapMemberRegister.get(ID);
        }
        
        public void cancel(Integer cancelledMemberID)
        {
            //Also cancel any associated bookings
            Member cancelledMember = get(cancelledMemberID);
            if (cancelledMember != null)
            {
                ArrayList<Integer> cancelledBookings = new ArrayList<Integer>();
                for (Booking currentBooking : clubBookingRegister.values())
                {
                    if (currentBooking.getBookingMember().getID() == cancelledMemberID)
                    {
                        cancelledBookings.add(currentBooking.getID());
                    }
                }
                for (Integer currentID : cancelledBookings)
                {
                    clubBookingRegister.cancel(currentID);
                }
                mapMemberRegister.remove(cancelledMemberID);
            }
            else
            {
                throw new IllegalStateException("No such member exists");
            }
        
        }
        
        public void list()
        {
            for (Member currentMember: mapMemberRegister.values())
            {
                try
                {
                    System.out.println(currentMember.getDetails());
                }
                catch (Exception ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
        }
        
        public Collection<Member> values()
        {
            return mapMemberRegister.values();
        }
    }
    
    public class ExerciseRegister 
    {
        private HashMap<Integer, Exercise> mapExerciseRegister = new HashMap<Integer, Exercise>();
        private Integer nextUniqueID = 1;
    
        public ExerciseRegister(boolean blnTestData)
        {
            if (blnTestData == true)
            {
                //5 types of classes
                this.add("Body Pump", new BigDecimal(10));
                this.add("Spinning", new BigDecimal(20));
                this.add("Yoga", new BigDecimal(15));
                this.add("Body Combat", new BigDecimal(17.49));
                this.add("Over 60s Tai Chi", new BigDecimal(0));
            }
        }
        
        public void add(String newExerciseName, BigDecimal newPrice)
        {
            newPrice = newPrice.setScale(2, RoundingMode.HALF_UP);
            Exercise newExercise = new Exercise(newExerciseName, newPrice, nextUniqueID);
            mapExerciseRegister.put(nextUniqueID, newExercise);
            nextUniqueID++;
        }
        
        public Exercise get(Integer ID)
        {
            return mapExerciseRegister.get(ID);
        }
        
        public void cancel(Integer cancelledExerciseID)
        {
            //Also cancel any associated classes
            ArrayList<Integer> cancelledClasses = new ArrayList<Integer>();
            for (Class currentClass : clubClassRegister.values())
            {
                if (currentClass.getClassExercise().getID() == cancelledExerciseID)
                {
                    cancelledClasses.add(currentClass.getID());
                }
            }
            for (Integer currentID : cancelledClasses)
            {
                clubClassRegister.cancel(currentID);                
            }
            mapExerciseRegister.remove(cancelledExerciseID);
        }
        
        public void list()
        {
            for (Exercise currentExercise: mapExerciseRegister.values())
            {
                try
                {
                    System.out.println(currentExercise.getDetails());
                }
                catch (Exception ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
        }
        
        public Collection<Exercise> values()
        {
            return mapExerciseRegister.values();
        }
    }
    
    public class ClassRegister 
    {
        private HashMap<Integer, Class> mapClassRegister = new HashMap<Integer, Class>();
        private Integer nextUniqueID = 1;
    
        public ClassRegister(boolean blnTestData)
        {
            if (blnTestData == true)
            { 
                //2 Months of classes
                this.add(getConvertedDate("15/12/2018"), Slot.values()[0], clubExerciseRegister.get(1));
                this.add(getConvertedDate("15/12/2018"), Slot.values()[1], clubExerciseRegister.get(2));
                this.add(getConvertedDate("15/12/2018"), Slot.values()[2], clubExerciseRegister.get(3));
                this.add(getConvertedDate("15/12/2018"), Slot.values()[3], clubExerciseRegister.get(4));
                this.add(getConvertedDate("16/12/2018"), Slot.values()[0], clubExerciseRegister.get(5));
                this.add(getConvertedDate("16/12/2018"), Slot.values()[1], clubExerciseRegister.get(4));
                this.add(getConvertedDate("16/12/2018"), Slot.values()[2], clubExerciseRegister.get(3));
                this.add(getConvertedDate("16/12/2018"), Slot.values()[3], clubExerciseRegister.get(2));
                //Christmas break
                this.add(getConvertedDate("29/12/2018"), Slot.values()[0], clubExerciseRegister.get(1));
                this.add(getConvertedDate("29/12/2018"), Slot.values()[1], clubExerciseRegister.get(2));
                this.add(getConvertedDate("29/12/2018"), Slot.values()[2], clubExerciseRegister.get(3));
                this.add(getConvertedDate("29/12/2018"), Slot.values()[3], clubExerciseRegister.get(4));
                this.add(getConvertedDate("30/12/2018"), Slot.values()[0], clubExerciseRegister.get(5));
                this.add(getConvertedDate("30/12/2018"), Slot.values()[1], clubExerciseRegister.get(4));
                this.add(getConvertedDate("30/12/2018"), Slot.values()[2], clubExerciseRegister.get(3));
                this.add(getConvertedDate("30/12/2018"), Slot.values()[3], clubExerciseRegister.get(2));
                
                this.add(getConvertedDate("05/01/2019"), Slot.values()[0], clubExerciseRegister.get(1));
                this.add(getConvertedDate("05/01/2019"), Slot.values()[1], clubExerciseRegister.get(2));
                this.add(getConvertedDate("05/01/2019"), Slot.values()[2], clubExerciseRegister.get(3));
                this.add(getConvertedDate("05/01/2019"), Slot.values()[3], clubExerciseRegister.get(4));
                this.add(getConvertedDate("06/01/2019"), Slot.values()[0], clubExerciseRegister.get(5));
                this.add(getConvertedDate("06/01/2019"), Slot.values()[1], clubExerciseRegister.get(4));
                this.add(getConvertedDate("06/01/2019"), Slot.values()[2], clubExerciseRegister.get(3));
                this.add(getConvertedDate("06/01/2019"), Slot.values()[3], clubExerciseRegister.get(2));
                
                this.add(getConvertedDate("12/01/2019"), Slot.values()[0], clubExerciseRegister.get(1));
                this.add(getConvertedDate("12/01/2019"), Slot.values()[1], clubExerciseRegister.get(2));
                this.add(getConvertedDate("12/01/2019"), Slot.values()[2], clubExerciseRegister.get(3));
                this.add(getConvertedDate("12/01/2019"), Slot.values()[3], clubExerciseRegister.get(4));
                this.add(getConvertedDate("13/01/2019"), Slot.values()[0], clubExerciseRegister.get(5));
                this.add(getConvertedDate("13/01/2019"), Slot.values()[1], clubExerciseRegister.get(4));
                this.add(getConvertedDate("13/01/2019"), Slot.values()[2], clubExerciseRegister.get(3));
                this.add(getConvertedDate("13/01/2019"), Slot.values()[3], clubExerciseRegister.get(2));
                
                this.add(getConvertedDate("19/01/2019"), Slot.values()[0], clubExerciseRegister.get(1));
                this.add(getConvertedDate("19/01/2019"), Slot.values()[1], clubExerciseRegister.get(2));
                this.add(getConvertedDate("19/01/2019"), Slot.values()[2], clubExerciseRegister.get(3));
                this.add(getConvertedDate("19/01/2019"), Slot.values()[3], clubExerciseRegister.get(4));
                this.add(getConvertedDate("20/01/2019"), Slot.values()[0], clubExerciseRegister.get(5));
                this.add(getConvertedDate("20/01/2019"), Slot.values()[1], clubExerciseRegister.get(4));
                this.add(getConvertedDate("20/01/2019"), Slot.values()[2], clubExerciseRegister.get(3));
                this.add(getConvertedDate("20/01/2019"), Slot.values()[3], clubExerciseRegister.get(2));
                
                this.add(getConvertedDate("26/01/2019"), Slot.values()[0], clubExerciseRegister.get(1));
                this.add(getConvertedDate("26/01/2019"), Slot.values()[1], clubExerciseRegister.get(2));
                this.add(getConvertedDate("26/01/2019"), Slot.values()[2], clubExerciseRegister.get(3));
                this.add(getConvertedDate("26/01/2019"), Slot.values()[3], clubExerciseRegister.get(4));
                this.add(getConvertedDate("27/01/2019"), Slot.values()[0], clubExerciseRegister.get(5));
                this.add(getConvertedDate("27/01/2019"), Slot.values()[1], clubExerciseRegister.get(4));
                this.add(getConvertedDate("27/01/2019"), Slot.values()[2], clubExerciseRegister.get(3));
                this.add(getConvertedDate("27/01/2019"), Slot.values()[3], clubExerciseRegister.get(2));
                
                this.add(getConvertedDate("02/02/2019"), Slot.values()[0], clubExerciseRegister.get(1));
                this.add(getConvertedDate("02/02/2019"), Slot.values()[1], clubExerciseRegister.get(2));
                this.add(getConvertedDate("02/02/2019"), Slot.values()[2], clubExerciseRegister.get(3));
                this.add(getConvertedDate("02/02/2019"), Slot.values()[3], clubExerciseRegister.get(4));
                this.add(getConvertedDate("03/02/2019"), Slot.values()[0], clubExerciseRegister.get(5));
                this.add(getConvertedDate("03/02/2019"), Slot.values()[1], clubExerciseRegister.get(4));
                this.add(getConvertedDate("03/02/2019"), Slot.values()[2], clubExerciseRegister.get(3));
                this.add(getConvertedDate("03/02/2019"), Slot.values()[3], clubExerciseRegister.get(2));
                
                this.add(getConvertedDate("09/02/2019"), Slot.values()[0], clubExerciseRegister.get(1));//Senior Saturday!
                this.add(getConvertedDate("09/02/2019"), Slot.values()[1], clubExerciseRegister.get(1));
                this.add(getConvertedDate("09/02/2019"), Slot.values()[2], clubExerciseRegister.get(1));
                this.add(getConvertedDate("09/02/2019"), Slot.values()[3], clubExerciseRegister.get(1));
                this.add(getConvertedDate("10/02/2019"), Slot.values()[0], clubExerciseRegister.get(5));
                this.add(getConvertedDate("10/02/2019"), Slot.values()[1], clubExerciseRegister.get(4));
                this.add(getConvertedDate("10/02/2019"), Slot.values()[2], clubExerciseRegister.get(3));
                this.add(getConvertedDate("10/02/2019"), Slot.values()[3], clubExerciseRegister.get(2));
                
                this.add(getConvertedDate("16/02/2019"), Slot.values()[0], clubExerciseRegister.get(1));
                this.add(getConvertedDate("16/02/2019"), Slot.values()[1], clubExerciseRegister.get(2));
                this.add(getConvertedDate("16/02/2019"), Slot.values()[2], clubExerciseRegister.get(3));
                this.add(getConvertedDate("16/02/2019"), Slot.values()[3], clubExerciseRegister.get(4));
                this.add(getConvertedDate("17/02/2019"), Slot.values()[0], clubExerciseRegister.get(5));
                this.add(getConvertedDate("17/02/2019"), Slot.values()[1], clubExerciseRegister.get(4));
                this.add(getConvertedDate("17/02/2019"), Slot.values()[2], clubExerciseRegister.get(3));
                this.add(getConvertedDate("17/02/2019"), Slot.values()[3], clubExerciseRegister.get(2));
                
                this.add(getConvertedDate("23/02/2019"), Slot.values()[0], clubExerciseRegister.get(1));
                this.add(getConvertedDate("23/02/2019"), Slot.values()[1], clubExerciseRegister.get(2));
                this.add(getConvertedDate("23/02/2019"), Slot.values()[2], clubExerciseRegister.get(3));
                this.add(getConvertedDate("23/02/2019"), Slot.values()[3], clubExerciseRegister.get(4));
                this.add(getConvertedDate("24/02/2019"), Slot.values()[0], clubExerciseRegister.get(5));
                this.add(getConvertedDate("24/02/2019"), Slot.values()[1], clubExerciseRegister.get(4));
                this.add(getConvertedDate("24/02/2019"), Slot.values()[2], clubExerciseRegister.get(3));
                this.add(getConvertedDate("24/02/2019"), Slot.values()[3], clubExerciseRegister.get(2));
            }
        }
        
        public void add(Date newDate, Slot newSlot, Exercise newExercise)
        {
            if (checkWeekend(newDate) == false)
            {
                throw new IllegalStateException("Class cannot be planned for a weekday");
            }
            boolean uniqueDateTime = true;
            for (Class currentClass: mapClassRegister.values())
            {
                if (currentClass.getDate().compareTo(newDate) == 0 && currentClass.getSlot() == newSlot)
                {
                    uniqueDateTime = false;
                }
            }
    
            if (uniqueDateTime == true)
            {
                Class newClass = new Class(newDate, newSlot, newExercise, nextUniqueID);
                mapClassRegister.put(nextUniqueID, newClass);
                nextUniqueID++;
            }
            else
            {
                throw new IllegalStateException("Class cannot be planned for a date and time already allocated to another class");
            }
        }
        
        public Class get(Integer ID)
        {
            return mapClassRegister.get(ID);
        }
        
        public void cancel(Integer cancelledClassID)
        {
            //Also cancel any associated bookings
            ArrayList<Integer> cancelledBookings = new ArrayList<Integer>();
            for (Booking currentBooking : clubBookingRegister.values())
            {
                if (currentBooking.getBookingClass().getID() == cancelledClassID)
                {
                    cancelledBookings.add(currentBooking.getID());
                }
            }
            for (Integer currentID : cancelledBookings)
            {
                clubBookingRegister.cancel(currentID);             
            }
            mapClassRegister.remove(cancelledClassID);
        }
        
        public void list()
        {
            for (Class currentClass: mapClassRegister.values())
            {
                try
                {
                    System.out.println(currentClass.getDetails());
                }
                catch (Exception ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
        }
        
        public Collection<Class> values()
        {
            return mapClassRegister.values();
        }
    }

    public class BookingRegister 
    {
        private HashMap<Integer, Booking> mapBookingRegister = new HashMap<Integer, Booking>();
        private Integer nextUniqueID = 1;
    
        public BookingRegister(boolean blnTestData)
        {
            if (blnTestData == true)
            {
                try
                {
                    Booking thisBooking = null;                    
                    
                    //20 reviews and some payments
                    this.add(clubMemberRegister.get(1), clubClassRegister.get(1), PaymentMethods.Card, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(2), clubClassRegister.get(1), PaymentMethods.Card, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(3), clubClassRegister.get(3), PaymentMethods.Cash, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(4), clubClassRegister.get(4), PaymentMethods.Cash, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(5), clubClassRegister.get(4), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(6), clubClassRegister.get(4), PaymentMethods.Cash, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(7), clubClassRegister.get(7), PaymentMethods.Cash, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(8), clubClassRegister.get(7), PaymentMethods.Card, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(9), clubClassRegister.get(9), PaymentMethods.Card, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(10), clubClassRegister.get(9), PaymentMethods.Card, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(11), clubClassRegister.get(9), PaymentMethods.Cash, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(12), clubClassRegister.get(9), PaymentMethods.Cash, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(13), clubClassRegister.get(10), PaymentMethods.Cash, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(14), clubClassRegister.get(10), PaymentMethods.Card, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(15), clubClassRegister.get(10), PaymentMethods.Cash, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(16), clubClassRegister.get(11), PaymentMethods.Cash, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(17), clubClassRegister.get(11), PaymentMethods.Cash, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(18), clubClassRegister.get(11), PaymentMethods.Card, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(19), clubClassRegister.get(12), PaymentMethods.Cash, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(20), clubClassRegister.get(13), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(1), clubClassRegister.get(13), PaymentMethods.Cash, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(2), clubClassRegister.get(13), PaymentMethods.Card, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(3), clubClassRegister.get(13), PaymentMethods.Cash, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(4), clubClassRegister.get(14), PaymentMethods.Card, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(5), clubClassRegister.get(14), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(6), clubClassRegister.get(14), PaymentMethods.Cash, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(7), clubClassRegister.get(17), PaymentMethods.Cash, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(8), clubClassRegister.get(17), PaymentMethods.Cash, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(9), clubClassRegister.get(18), PaymentMethods.Cash, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(10), clubClassRegister.get(18), PaymentMethods.Cash, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(11), clubClassRegister.get(18), PaymentMethods.Card, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(12), clubClassRegister.get(18), PaymentMethods.Cash, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(13), clubClassRegister.get(18), PaymentMethods.Cash, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(14), clubClassRegister.get(19), PaymentMethods.Card, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(15), clubClassRegister.get(19), PaymentMethods.Cash, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(16), clubClassRegister.get(20), PaymentMethods.Card, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(17), clubClassRegister.get(21), PaymentMethods.Card, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(18), clubClassRegister.get(21), PaymentMethods.Cash, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(19), clubClassRegister.get(21), PaymentMethods.Card, BookingStatus.Attended);
                    this.add(clubMemberRegister.get(20), clubClassRegister.get(24), PaymentMethods.Card, BookingStatus.Booked);
                    
                    setSystemDate(getConvertedDate("09/01/2019"));
                    thisBooking = get(1);
                    thisBooking.setReview("I enjoyed the atmosphere, but the music was not very good");
                    thisBooking.setRating(Ratings.Three);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(2);
                    thisBooking.setReview("Not what was advertised at all");
                    thisBooking.setRating(Ratings.One);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(3);
                    thisBooking.setReview("They could do a LOT better");
                    thisBooking.setRating(Ratings.Two);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(4);
                    thisBooking.setReview("Really good time and great teaching");
                    thisBooking.setRating(Ratings.Four);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(6);
                    thisBooking.setReview("I had a great time and really hope they keep running this class");
                    thisBooking.setRating(Ratings.Five);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(7);
                    thisBooking.setReview("Terrible. Will be asking for a refund.");
                    thisBooking.setRating(Ratings.Two);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(8);
                    thisBooking.setReview("Only thing holding me back from giving a five is the noise from next door");
                    thisBooking.setRating(Ratings.Four);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(9);
                    thisBooking.setReview("Best class so far!");
                    thisBooking.setRating(Ratings.Five);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(10);
                    thisBooking.setReview("Really high quality session, great people");
                    thisBooking.setRating(Ratings.Four);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(12);
                    thisBooking.setReview("I have a lost a lot of weight on this class");
                    thisBooking.setRating(Ratings.Five);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(13);
                    thisBooking.setReview("I've been to better classes");
                    thisBooking.setRating(Ratings.Three);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(14);
                    thisBooking.setReview("Not even good value for money");
                    thisBooking.setRating(Ratings.Two);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(15);
                    thisBooking.setReview("Never coming back EVER");
                    thisBooking.setRating(Ratings.One);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(16);
                    thisBooking.setReview("I had a really good time and can't wait until next week");
                    thisBooking.setRating(Ratings.Four);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(17);
                    thisBooking.setReview("i have not lost any weight on this class");
                    thisBooking.setRating(Ratings.Two);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(18);
                    thisBooking.setReview("Terrific teaching, quality backing track");
                    thisBooking.setRating(Ratings.Five);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(19);
                    thisBooking.setReview("Not bad for the price");
                    thisBooking.setRating(Ratings.Three);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(21);
                    thisBooking.setReview("I had a great time");
                    thisBooking.setRating(Ratings.Four);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(22);
                    thisBooking.setReview("Good value for money");
                    thisBooking.setRating(Ratings.Three);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(23);
                    thisBooking.setReview("Will be reccomeding this to all my friends");
                    thisBooking.setRating(Ratings.Five);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(24);
                    thisBooking.setReview("Really impressed, will be coming back for sure.");
                    thisBooking.setRating(Ratings.Four);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(26);
                    thisBooking.setReview("this was highly reccomended but was not all that good.");
                    thisBooking.setRating(Ratings.Three);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(27);
                    thisBooking.setReview("The classroom has a really strong damp odour");
                    thisBooking.setRating(Ratings.One);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(28);
                    thisBooking.setReview("I was really impressed with the quality of the instruction");
                    thisBooking.setRating(Ratings.Five);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(29);
                    thisBooking.setReview("Had a really good backing track");
                    thisBooking.setRating(Ratings.Four);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(30);
                    thisBooking.setReview("I've been to better but OK I guess");
                    thisBooking.setRating(Ratings.Three);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(32);
                    thisBooking.setReview("Not bad for the price");
                    thisBooking.setRating(Ratings.Three);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(33);
                    thisBooking.setReview("I had a terrific time");
                    thisBooking.setRating(Ratings.Four);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(34);
                    thisBooking.setReview("I would really reccomend this class");
                    thisBooking.setRating(Ratings.Four);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(35);
                    thisBooking.setReview("Let down by very poor planning");
                    thisBooking.setRating(Ratings.Two);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(36);
                    thisBooking.setReview("the best class I've been to in years");
                    thisBooking.setRating(Ratings.Five);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(37);
                    thisBooking.setReview("I enjoyed the atmosphere, but the music was not very goodIt was alright");
                    thisBooking.setRating(Ratings.Three);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(38);
                    thisBooking.setReview("Not very good value for money");
                    thisBooking.setRating(Ratings.Two);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    thisBooking = get(39);
                    thisBooking.setReview("I am never going to this class again");
                    thisBooking.setRating(Ratings.One);
                    thisBooking.setStatus(BookingStatus.Reviewed);
                    
                    setSystemDate(getConvertedDate("01/01/1970"));
                    
                    this.add(clubMemberRegister.get(1), clubClassRegister.get(25), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(2), clubClassRegister.get(25), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(3), clubClassRegister.get(25), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(4), clubClassRegister.get(26), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(5), clubClassRegister.get(26), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(6), clubClassRegister.get(27), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(7), clubClassRegister.get(27), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(8), clubClassRegister.get(27), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(9), clubClassRegister.get(27), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(10), clubClassRegister.get(27), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(11), clubClassRegister.get(27), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(12), clubClassRegister.get(27), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(13), clubClassRegister.get(28), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(14), clubClassRegister.get(28), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(15), clubClassRegister.get(29), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(16), clubClassRegister.get(29), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(17), clubClassRegister.get(30), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(18), clubClassRegister.get(30), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(19), clubClassRegister.get(30), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(20), clubClassRegister.get(30), PaymentMethods.Unpaid, BookingStatus.Booked);
                    
                    this.add(clubMemberRegister.get(1), clubClassRegister.get(33), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(2), clubClassRegister.get(33), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(3), clubClassRegister.get(33), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(4), clubClassRegister.get(33), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(5), clubClassRegister.get(33), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(6), clubClassRegister.get(33), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(7), clubClassRegister.get(33), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(8), clubClassRegister.get(33), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(9), clubClassRegister.get(33), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(10), clubClassRegister.get(33), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(11), clubClassRegister.get(33), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(12), clubClassRegister.get(33), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(13), clubClassRegister.get(33), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(14), clubClassRegister.get(33), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(15), clubClassRegister.get(33), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(16), clubClassRegister.get(33), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(17), clubClassRegister.get(33), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(18), clubClassRegister.get(33), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(19), clubClassRegister.get(33), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(20), clubClassRegister.get(33), PaymentMethods.Unpaid, BookingStatus.Booked);
                    
                    this.add(clubMemberRegister.get(1), clubClassRegister.get(35), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(2), clubClassRegister.get(37), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(3), clubClassRegister.get(37), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(4), clubClassRegister.get(37), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(5), clubClassRegister.get(40), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(6), clubClassRegister.get(41), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(7), clubClassRegister.get(48), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(8), clubClassRegister.get(48), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(9), clubClassRegister.get(50), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(10), clubClassRegister.get(51), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(11), clubClassRegister.get(55), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(12), clubClassRegister.get(55), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(13), clubClassRegister.get(55), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(14), clubClassRegister.get(57), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(15), clubClassRegister.get(57), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(16), clubClassRegister.get(59), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(17), clubClassRegister.get(59), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(18), clubClassRegister.get(59), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(19), clubClassRegister.get(60), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(20), clubClassRegister.get(60), PaymentMethods.Unpaid, BookingStatus.Booked);
                    
                    this.add(clubMemberRegister.get(1), clubClassRegister.get(61), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(2), clubClassRegister.get(62), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(3), clubClassRegister.get(62), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(4), clubClassRegister.get(62), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(5), clubClassRegister.get(64), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(6), clubClassRegister.get(64), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(7), clubClassRegister.get(64), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(8), clubClassRegister.get(67), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(9), clubClassRegister.get(67), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(10), clubClassRegister.get(70), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(11), clubClassRegister.get(70), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(12), clubClassRegister.get(70), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(13), clubClassRegister.get(73), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(14), clubClassRegister.get(74), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(15), clubClassRegister.get(75), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(16), clubClassRegister.get(75), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(17), clubClassRegister.get(78), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(18), clubClassRegister.get(79), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(19), clubClassRegister.get(80), PaymentMethods.Unpaid, BookingStatus.Booked);
                    this.add(clubMemberRegister.get(20), clubClassRegister.get(80), PaymentMethods.Unpaid, BookingStatus.Booked);
                }
                catch (Exception ex)
                {
                    throw new IllegalStateException("Booking register error: " + ex.getMessage());
                }
            }
        }
        
        public void add(Member bookedMember, Class bookedClass, PaymentMethods newPaymentMethod, BookingStatus newBookingStatus)
        {
            boolean uniqueMemberClass = true;
            for (Booking currentBooking: mapBookingRegister.values())
            {
                if (currentBooking.getBookingMember().getID() == bookedMember.getID() && currentBooking.getBookingClass().getID() == bookedClass.getID())
                {
                    uniqueMemberClass = false;
                }
            }
            
            if (uniqueMemberClass == true)
            {
                try
                {
                    Booking newBooking = new Booking(bookedMember, bookedClass, newPaymentMethod, newBookingStatus, nextUniqueID);
                    bookedClass.incrementTakings();
                    bookedClass.decrementSpaces();
                    mapBookingRegister.put(nextUniqueID, newBooking);
                    nextUniqueID++;
                }
                catch (Exception ex)
                {
                    throw new IllegalStateException(ex.getMessage());
                }
            }
            else
            {
                throw new IllegalStateException("Cannot book the same member into the same class twice");
            }
        }
        
        public Booking get(Integer ID)
        {
            return mapBookingRegister.get(ID);
        }
        
        public void cancel(Integer cancelledBookingID)
        {
            Booking cancelledBooking = get(cancelledBookingID);
            Class bookedClass = cancelledBooking.getBookingClass();
            if (cancelledBooking != null)
            {
                bookedClass.decrementTakings();
                bookedClass.incrementSpaces();
                mapBookingRegister.remove(cancelledBookingID);
            }
            else
            {
                throw new IllegalStateException("No such booking exists");
            }
        }
        
        public void list()
        {
            for (Booking currentBooking: mapBookingRegister.values())
            {
                try
                {
                    System.out.println(currentBooking.getDetails());
                }
                catch (Exception ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
        }
        
        public Collection<Booking> values()
        {
            return mapBookingRegister.values();
        }
    }
}
