package com.recontract.recontract.service;

import com.recontract.recontract.domain.Search;
import java.util.List;

public interface SearchService {

    List<Search> findAllSearches();
    Long findSearchIdByUserId(Long userId);
    Search findSearchById(Long userId);
    void createSearch(Search search, Long userId);
    void updateSearch(String newFunctionTitle, int newAmount, Long searchId);
    boolean checkSearchIsPresentOnUser(Long userId);
}
