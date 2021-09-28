package com.recontract.recontract.service;

import com.recontract.recontract.domain.Search;

public interface SearchService {

    Search getSearchById(Long userId);
    void createSearch(Search search, Long userId);
    void updateSearch(String functionTitle, int amount, Long searchId);
    boolean checkSearchIsPresentOnUser(Long userId);
}
