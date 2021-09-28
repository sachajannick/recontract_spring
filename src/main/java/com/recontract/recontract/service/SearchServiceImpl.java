package com.recontract.recontract.service;

import com.recontract.recontract.domain.Search;
import com.recontract.recontract.domain.User;
import com.recontract.recontract.repository.SearchRepository;
import com.recontract.recontract.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SearchServiceImpl implements SearchService {

    private final SearchRepository searchRepository;
    private final UserRepository userRepository;

    @Autowired
    public SearchServiceImpl(SearchRepository searchRepository, UserRepository userRepository) {
        this.searchRepository = searchRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Search getSearchById(Long userId) {
        List<Search> searches = searchRepository.findAll();
        Search result = new Search();

        for (int i = 0; i < searches.size(); i++) {
            if (searches.get(i).getUser().getId() == userId) {
                result = searches.get(i);
            } else {
                throw new RuntimeException();
            }
        }
        return result;
    }

    @Override
    public void createSearch(Search search, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        search.setUser(user.get());
        searchRepository.save(search);
    }

    @Override
    public void updateSearch(String functionTitle, int amount, Long searchId) {
        Optional<Search> search = searchRepository.findById(searchId);
        search.get().setFunctionTitle(functionTitle);
        search.get().setAmount(amount);
        searchRepository.save(search.get());
    }

    @Override
    public boolean checkSearchIsPresentOnUser(Long userId) {
        boolean searchIsPresent = false;
        List<Search> list = searchRepository.findAll();
        Search search = new Search();

        for (int i = 0; i < list.size(); i++) {
            search = list.get(i);
            if (search.getUser().getId() == userId) {
                searchIsPresent = true;
            }
        }
        return searchIsPresent;
    }
}
