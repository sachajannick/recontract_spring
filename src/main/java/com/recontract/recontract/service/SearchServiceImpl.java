package com.recontract.recontract.service;

import com.recontract.recontract.domain.Search;
import com.recontract.recontract.domain.User;
import com.recontract.recontract.exception.BadRequestException;
import com.recontract.recontract.exception.RecordNotFoundException;
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
    public Search findSearchById(Long userId) {
        List<Search> searches = searchRepository.findAll();
        Search result = new Search();

        for (int i = 0; i < searches.size(); i++) {
            if (searches.get(i).getUser().getId() == userId) {
                result = searches.get(i);
            } else {
                throw new RecordNotFoundException();
            }
        }
        return result;
    }

    @Override
    public void createSearch(Search search, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        try {
            search.setUser(user.get());
            searchRepository.save(search);
        } catch (Exception e) {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void updateSearch(String newFunctionTitle, int newAmount, Long searchId) {
        Optional<Search> search = searchRepository.findById(searchId);
        try {
            search.get().setFunctionTitle(newFunctionTitle);
            search.get().setAmount(newAmount);
            searchRepository.save(search.get());
        } catch (Exception e) {
            throw new BadRequestException();
        }
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
