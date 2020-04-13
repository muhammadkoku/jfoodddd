import java.util.ArrayList;
public class DatabaseInvoice
{
    // variabel yang digunakan dalam class
    private static ArrayList<Invoice> INVOICE_DATABASE = new ArrayList<Invoice>();
    private static int lastId=0;

    /**
     * Method of class DatabaseInvoice
     * @return true
     */
    public static ArrayList<Invoice> getInvoiceDatabase(){
        return INVOICE_DATABASE;

    }

    public static int getLastId(){
        return lastId;
    }

    public static  boolean addInvoice(Invoice invoice){
        int customerId = invoice.getCustomer().getId();
        for(Invoice temp : INVOICE_DATABASE)
        {
            if(temp.getCustomer().getId() == customerId && temp.getInvoiceStatus() == InvoiceStatus.Ongoing)
            {
                return false;
            }
        }
        INVOICE_DATABASE.add(invoice);
        lastId = invoice.getId();
        return true;
    }

    public static Invoice getInvoiceById(int id){
        Invoice value=null;
        for(Invoice invoice : INVOICE_DATABASE)
        {
            if(invoice.getId()==id)
            {
                value=invoice;
            }
        }
        return value;

    }

    public static ArrayList<Invoice> getInvoiceByCustomer(int customerId) throws CustomerNotFoundException {
        ArrayList<Invoice> value= new ArrayList<>();
        Customer customer = DatabaseCustomer.getCustomerById(customerId);
        for(Invoice invoiceDB1 : INVOICE_DATABASE)
        {
            if(invoiceDB1.getCustomer().getId()== customerId)
            {
                value.add(invoiceDB1);
            }
        }
        return value;

    }

    public static  boolean changeInvoiceStatus(int id, InvoiceStatus invoiceStatus)
    {
        for(Invoice temp : INVOICE_DATABASE)
        {
            if(temp.getId() == id)
            {
                if(temp.getInvoiceStatus() == InvoiceStatus.Ongoing)
                {
                    temp.setInvoiceStatus(invoiceStatus);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean removeInvoice(int id)
    {
        boolean value=false;
        for(Invoice invoiceDB1 : INVOICE_DATABASE)
        {
            if(invoiceDB1.getId()==id)
            {
                INVOICE_DATABASE.remove(id);
                value=true;
            }
        }
        return value;
    }
}