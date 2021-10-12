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
    public List<Search> findAllSearches() {
        return searchRepository.findAll();
    }

    @Override
    public Long findSearchIdByUserId(Long userId) {
        List<Search> list = searchRepository.findAll();
        Long result = 0L;

        try {
            for (int i = 0; i < list.size(); i++) {
                Search search = list.get(i);
                if (search.getUser().getUserId() == userId) {
                    result = search.getSearchId();
                }
            }

            return result;
        } catch (Exception e) {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public Search findSearchById(Long userId) {
        List<Search> searches = searchRepository.findAll();
        Search result = new Search();

        try {
            for (int i = 0; i < searches.size(); i++) {
                if (searches.get(i).getUser().getUserId() == userId) {
                    result = searches.get(i);
                }
            }
        } catch (Exception e) {
            throw new RecordNotFoundException();
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

        if (search.isPresent()) {
            search.get().setFunctionTitle(newFunctionTitle);
            search.get().setAmount(newAmount);
            searchRepository.save(search.get());
        } else {
            throw new BadRequestException();
        }
    }

    @Override
    public boolean checkSearchIsPresentOnUser(Long userId) {
        boolean searchIsPresent = false;
        List<Search> list = searchRepository.findAll();
        Search search = new Search();

        try {
            for (int i = 0; i < list.size(); i++) {
                search = list.get(i);
                if (search.getUser().getUserId() == userId) {
                    searchIsPresent = true;
                }
            }
        } catch (Exception e) {
            throw new RecordNotFoundException();
        }

        return searchIsPresent;
    }
}
