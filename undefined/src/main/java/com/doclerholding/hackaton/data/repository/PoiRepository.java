package com.doclerholding.hackaton.data.repository;

import com.doclerholding.hackaton.data.model.Poi;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by claudiu.arba on 11/03/17.
 */
public interface PoiRepository extends ElasticsearchRepository<Poi, String>
{
}
