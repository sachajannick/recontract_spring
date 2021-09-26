package com.recontract.recontract.service;

import com.recontract.recontract.domain.Search;

public interface SearchService {

    Search getAllSearches(Long userId);
    void createSearch(Search search ,Long userId);
    boolean checkSearchIsPresentOnUser(Long userId);
}
