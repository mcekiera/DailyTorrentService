package pl.mcekiera.service.DataSource;

import java.util.List;

/**
 * Generic interface, for retrieving data.
 * @param <T> generic type of retrieved elements
 */
public interface DataSource <T> {
    List<T> getData() throws InvalidDataSourceException;
}
