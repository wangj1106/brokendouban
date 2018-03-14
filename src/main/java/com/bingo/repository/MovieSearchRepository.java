package com.bingo.repository;

import com.bingo.domain.Movie;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface  MovieSearchRepository extends ElasticsearchRepository<Movie, Integer> {


}
