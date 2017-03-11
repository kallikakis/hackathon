package com.doclerholding.hackaton.service;

import com.doclerholding.hackaton.service.model.FilterCriteria;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Created by nikolaos.kallikakis on 11/03/17.
 */
@Component
public class SearchServiceImpl implements SearchService {

	@Override
	public List<FilterCriteria> getFilterCriteria() {
		// TODO: This needs to come from somewhere, DB??

		return null;
	}
}
