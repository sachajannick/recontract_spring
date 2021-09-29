package com.recontract.recontract.service;

import com.recontract.recontract.domain.Search;
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
        Long userId = 0L;

        // ACT
        List<Search> searches = new ArrayList<>();
        Search result = new Search();

        // ASSERT
        when(searchRepository.findAll()).thenReturn(List.of(result));
        Search search = searchService.findSearchById(userId);
        Assertions.assertEquals(searches, search);
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


        // ACT


        // ASSERT


    }

    @Test
    public void updateSearchThrowsException() {

    }
}
