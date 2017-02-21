package pl.mcekiera.service;

import java.util.List;

public interface RecommendationService<T> {

    List<T> getRecommendations(String input);
}
