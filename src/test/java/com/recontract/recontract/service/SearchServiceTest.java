package com.recontract.recontract.service;

import com.recontract.recontract.domain.Search;
import com.recontract.recontract.domain.User;
import com.recontract.recontract.exception.BadRequestException;
import com.recontract.recontract.exception.RecordNotFoundException;
import com.recontract.recontract.repository.SearchRepository;
import com.recontract.recontract.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    SearchRepository searchRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    private SearchServiceImpl searchService;

    @Captor
    ArgumentCaptor<Search> searchCaptor;

    // FAILS -- NEEDS REVISION
    @Test
    public void findSearchByIdSuccess() {
        // ARRANGE
        Long userId = 0L;

        // ACT
        List<Search> searches = new ArrayList<>();
        Search result = new Search();

        // ASSERT
        when(searchRepository.findAll()).thenReturn(searches);
        Search result2 = searchService.findSearchById(userId);
        Assertions.assertEquals(result, result2);
    }


    // FAILS -- NEEDS REVISION
    @Test
    public void findSearchByIdThrowsException() {
        Long userId = 0L;

        Assertions.assertThrows(RecordNotFoundException.class, () -> searchService.findSearchById(userId));
    }

    @Test
    public void createSearchSuccess() {
        // ARRANGE
        User user = new User();
        user.setUserId(0L);

        Search search = new Search();
        search.setSearchId(0L);

        // ACT
        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
        searchService.createSearch(search, user.getUserId());

        // ASSERT
        verify(searchRepository).save(searchCaptor.capture());
        Assertions.assertEquals(searchCaptor.getValue().getUser().getUserId(), 0L);
    }

    @Test
    public void createSearchThrowsException() {
        Long userId = 0L;
        Search search = new Search();

        Assertions.assertThrows(RecordNotFoundException.class, () -> searchService.createSearch(search, userId));

    }

    @Test
    public void updateSearchSuccess() {
        // ARRANGE
        Long searchId = 0L;
        String functionTitle = "Back-end Software Developer";
        String newFunctionTitle = "Java Software Developer";
        int amount = 80;
        int newAmount = 85;

        // ACT
        Optional<Search> search = Optional.of(new Search());
        search.get().setSearchId(searchId);
        search.get().setFunctionTitle(functionTitle);
        search.get().setAmount(amount);

        // ASSERT
        when (searchRepository.findById(searchId)).thenReturn(search);
        searchService.updateSearch(newFunctionTitle, newAmount, searchId);
        verify(searchRepository).save(searchCaptor.capture());
        Assertions.assertEquals(newFunctionTitle, searchCaptor.getValue().getFunctionTitle());
        Assertions.assertEquals(newAmount, searchCaptor.getValue().getAmount());
    }

    @Test
    public void updateSearchThrowsException() {
        Long searchId = 0L;
        String newFunctionTitle = "Java Software Developer";
        int newAmount = 85;

        Assertions.assertThrows(BadRequestException.class, () -> searchService.updateSearch(newFunctionTitle, newAmount, searchId));
    }
}
