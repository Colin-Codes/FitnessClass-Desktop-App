import java.math.BigDecimal;
import java.util.Date;
/**
 * Write a description of class FitnessClassSession here.
 *
 * 16018361
 * 07/01/2019
 */
public class Class
{    
    private Integer ClassID = 0; // reference type Integer allows compatibilty with Hashmap
    private Date Date = null;
    private Slot Slot = null;
    private Exercise Exercise = null;
    private Integer Spaces = Club.getMaxClassSize(); //Allows conversion to string
    private BigDecimal Takings = new BigDecimal(0.00);
    
    public Class(Date newDate, Slot newSlot, Exercise newExercise, Integer newClassID)
    {
        if (newDate.compareTo(Club.getSystemDate()) >= 0) 
        {
                Date = newDate;
                Slot = newSlot;
                Exercise = newExercise;
                ClassID = newClassID;
        }
        else
        {
            throw new IllegalArgumentException("Classes cannot be scheduled in the past");
        }
    }
        
    public String getDetails()
    {
        return ClassID.toString() + "; Date: " + Date.toString() + ", Slot: " + Slot.toString() + ", Exercise details: " + Exercise.getDetails() + ", Remaining spaces: " + Spaces.toString() + ", Takings: " + Takings.toString();
    }
        
    public String getLabel()
    {
        return Club.getConvertedDate(Date) + ", " + Slot.toString() + ", " + Exercise.getName() + ", Remaining spaces: " + Spaces.toString();
    }
    
    public int getID()
    {
        return ClassID;
    }
    
    public Exercise getClassExercise()
    {
        return Exercise;
    }
    
    public Date getDate()
    {
        return Date;
    }
    
    public Slot getSlot()
    {
        return Slot;
    }
    
    public Integer getSpaces()
    {
        return Spaces;
    }
    
    public BigDecimal getTakings()
    {
        return Takings;
    }
    
    public void setClassExercise(Exercise newExercise)
    {
        Exercise = newExercise;
    }
    
    public void setDate(Date newDate)
    {
        Date = newDate;
    }
    
    public void setSlot(Slot newSlot)
    {
        Slot = newSlot;
    }
    
    public void incrementSpaces()
    {
        Spaces++;
    }
    
    public void decrementSpaces()
    {
        Spaces--;
    }
    
    public void incrementTakings()
    {
        Takings = Takings.add(Exercise.getPrice());
    }
    
    public void decrementTakings()
    {
        Takings = Takings.subtract(Exercise.getPrice());
    }
}
