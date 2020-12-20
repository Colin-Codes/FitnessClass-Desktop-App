
/**
 * Write a description of class Booking here.
 *
 * 16018361
 * 07/01/2019
 */
public class Booking
{   
    private Integer BookingID = 0; // reference type Integer allows compatibilty with Hashmap
    private Member Member = null;
    private Class Class = null;
    private PaymentMethods Payment = PaymentMethods.Unpaid;
    private Ratings Rating = Ratings.None;
    private String Review = "";
    private BookingStatus Status = BookingStatus.Booked;
    
    public Booking(Member newMember, Class newClass, PaymentMethods newPaymentMethod, BookingStatus newStatus, Integer newBookingID)
    {
        if (newClass.getDate().compareTo(Club.getSystemDate()) < 0)
        {
            throw new IllegalArgumentException("Cannot book a member into a class that happened in the past");
        }
        else if (newClass.getSpaces() == 0)
        {
            throw new IllegalArgumentException("Cannot book a member into a class with no more available spaces");
        }
        else
        {
            Member = newMember;
            Class = newClass;
            Payment = newPaymentMethod;
            Status = newStatus;
            BookingID = newBookingID;
        }
    }
    
    public Class getBookingClass()
    {
        return Class;
    }
    
    public Member getBookingMember()
    {
        return Member;
    }
        
    public String getDetails()
    {
        return BookingID.toString() + "; Member details: <" + Member.getDetails() + ">, Class details: <" + Class.getDetails() + ">, Payment: " + Payment.name() + ", Rating: " + Rating.name() + ", Review: " + Review + ", Booking Status: " + Status.name();
    }
        
    public String getLabel()
    {
        return Member.getFirstName() + " " + Member.getLastName() + ", " + Class.getClassExercise().getName() + ", " + Club.getConvertedDate(Class.getDate()) + ", " + Class.getSlot();
    }
    
    public int getID()
    {
        return BookingID;
    }
    
    public PaymentMethods getPayment()
    {
        return Payment;
    }
    
    public BookingStatus getStatus()
    {
        return Status;
    }
    
    public Ratings getRating()
    {
        return Rating;
    }
    
    public String getReview()
    {
        return Review;
    }
    
    public void setRating(Ratings newRating)
    {
        if (Status == BookingStatus.Attended || Status == BookingStatus.Reviewed)
        {
            //Allow rating to be left only if attended or already reviewed (in this case replaces old review)
            Rating = newRating;
        }
        else
        {
            throw new IllegalStateException("Cannot rate a class that has not been attended");
        }
    }
    
    public void setReview(String newReview)
    {
        if (Status == BookingStatus.Attended || Status == BookingStatus.Reviewed)
        {
            //Allow review to be left only if attended or already reviewed (in this case replaces old review)
            Review = newReview;
        }
        else
        {
            throw new IllegalStateException("Cannot review a class that has not been attended");
        }
    }
    
    public void setStatus(BookingStatus newStatus)
    {    
        if (Class.getDate().compareTo(Club.getSystemDate()) > 0)
        {
            throw new IllegalStateException("Cannot attend or review a class in the future");
        }
        else if (Payment == PaymentMethods.Unpaid && newStatus != BookingStatus.Booked)
        {
            throw new IllegalStateException("Cannot attend or review a class that has not been paid for");
        }
        else
        {
            //Allow status to be changed to Attended or reviewed only if Class was today or in the past, and has been paid for
            Status = newStatus;
        }
    }
    
    public void setPayment(PaymentMethods newPaymentMethod)
    {
        //Allow payment to be made without any validation - we should always allow the customer to pay
        Payment = newPaymentMethod;
    }
}
