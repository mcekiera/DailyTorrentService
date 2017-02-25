package pl.mcekiera.service.DataSource;

/**
 * Exception for variety of problem which may occurs during retrieving, parsing and processing data from diversified sources.
 * Many of exceptions generated during whole process are duo to internal data source problems (ex. errors in XML syntax,
 * service server does not respond) which cannot by ad hoc repaired. Therefore these exceptions are passed over with
 * use of InvalidDataSourceException for debug information, but does not interrupts process execution.
 */
public class InvalidDataSourceException extends Exception {
    private String source;

    InvalidDataSourceException(String source){
        this.source = source;
    }

    @Override
    public String getMessage() {
        return "Invalid data source: " + source;
    }

}
