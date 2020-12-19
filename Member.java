/**
 * The Member class will handle data relatign to individual members of the club.
 * It is better to record members by an arbitrary ID number in a hashmap to ensure uniqueness. 
 * This will also translate well to other electronic systems, eg. magnetic strip / barcode scanners.
 *
 * 16018361
 * 07/01/2019
 */
public class Member
{
    private Integer CustomerID = 0; // reference type Integer allows compatibilty with Hashmap
    private String FirstName = "";
    private String LastName = "";
    public Member(String newFirstName, String newLastName, Integer newCustomerID)
    {
        if (newFirstName != "" && newLastName != "")
        {
                FirstName = newFirstName;
                LastName = newLastName;
                CustomerID= newCustomerID;
        }
        else
        {
            throw new IllegalArgumentException("Member must have a First name and Last name");
        }
    }
        
    public String getDetails()
    {
        return CustomerID.toString() + "; First Name: " + FirstName + ", Last Name: " + LastName;
    }
        
    public String getLabel()
    {
        return CustomerID.toString() + "; " + FirstName + " " + LastName;
    }
        
    public String getFirstName()
    {
        return FirstName;
    }
        
    public String getLastName()
    {
        return LastName;
    }
        
    public void setFirstName(String newFirstName)
    {
        FirstName = newFirstName;
    }
        
    public void setLastName(String newLastName)
    {
        LastName = newLastName;
    }
    
    public int getID()
    {
        return CustomerID;
    }
}
