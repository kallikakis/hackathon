package com.doclerholding.hackaton.service;

import com.doclerholding.hackaton.service.model.FilterCriteria;
import java.util.List;

/**
 * Created by nikolaos.kallikakis on 11/03/17.
 */
public interface SearchService {

	List<FilterCriteria> getFilterCriteria();
}
