import java.math.BigDecimal;
/**
 * Write a description of class FitnessClassType here.
 *
 * 16018361
 * 07/01/2019
 */
public class Exercise
{        
    private Integer ExerciseID = 0; // reference type Integer allows compatibilty with Hashmap
    private String ExerciseName = "";
    private BigDecimal Price = new BigDecimal(0.00); 

    public Exercise(String newExerciseName, BigDecimal newPrice, Integer newExerciseID)
    {
        if (newPrice.compareTo(new BigDecimal(0.00)) >= 0 && newExerciseName != "") //Price could conceivably be zero.
        {
            try
            {
                ExerciseID = newExerciseID;
                ExerciseName = newExerciseName;
                Price = newPrice;
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        else
        {
            throw new IllegalArgumentException("Exercise Type must have a name and a positive cost price");
        }
    }
        
    public String getDetails()
    {
        return ExerciseID.toString() + "; Ex. Name: " + ExerciseName + " Ex. Price: " + Price.toString();
    }
        
    public String getLabel()
    {
        return ExerciseName;
    }
    
    public Integer getID()
    {
        return ExerciseID;
    }
    
    public String getName()
    {
        return ExerciseName;
    }
    
    public BigDecimal getPrice()
    {
        return Price;
    }
    
    public void setName(String newName)
    {
        ExerciseName = newName;
    }
    
    public void setPrice(BigDecimal newPrice)
    {
        Price = newPrice;
    }
}
