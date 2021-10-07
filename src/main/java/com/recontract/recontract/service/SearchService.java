package com.recontract.recontract.service;

import com.recontract.recontract.domain.Search;
import com.recontract.recontract.dto.dtoTest;

import java.util.List;

public interface SearchService {
    List<Search> findAllSearches();
    Search findSearchById(Long userId);
    void createSearch(Search search, Long userId);
    void updateSearch(String newFunctionTitle, int newAmount, Long searchId);
    boolean checkSearchIsPresentOnUser(Long userId);

    List<Long> returnMyDTO();
}
