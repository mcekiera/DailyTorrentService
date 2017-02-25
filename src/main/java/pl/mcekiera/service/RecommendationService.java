package pl.mcekiera.service;

import java.util.List;

/**
 * Interface for recommendation service
 * @param <T>
 */
public interface RecommendationService<T> {

    List<T> getRecommendations(String input);
}
