package vttp.paf.day23workshoppdf.models.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(int orderId) {
        super(String.format("Cannot find record for orderId %d", orderId));
    }
    
}
