package com.recontract.recontract.service;

import com.recontract.recontract.domain.Search;

public interface SearchService {
    Search findSearchById(Long userId);
    void createSearch(Search search, Long userId);
    void updateSearch(String newFunctionTitle, int newAmount, Long searchId);
    boolean checkSearchIsPresentOnUser(Long userId);
}
