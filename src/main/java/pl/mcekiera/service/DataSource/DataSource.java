package pl.mcekiera.service.DataSource;

import java.util.List;

public interface DataSource <T> {
    List<T> getData() throws InvalidDataSourceException;
}
