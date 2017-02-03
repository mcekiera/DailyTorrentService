package pl.mcekiera.service.DataSource;

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
