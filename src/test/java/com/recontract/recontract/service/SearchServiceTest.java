package com.recontract.recontract.service;

import com.recontract.recontract.domain.Search;
import com.recontract.recontract.domain.User;
import com.recontract.recontract.exception.BadRequestException;
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

    @Test
    public void findSearchByIdSuccess() {
        // ARRANGE


        // ACT


        // ASSERT

    }

    @Test
    public void findSearchByIdThrowsException() {

    }

    @Test
    public void createSearchSuccess() {
        // ARRANGE


        // ACT


        // ASSERT


    }

    @Test
    public void createSearchThrowsException() {

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
