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
    public void findSearchIdByUserIdSuccess() {
        // ARRANGE
        User user = new User();
        user.setUserId(1);

        Search search = new Search();
        search.setUser(user);

        // ACT
        when(searchRepository.findAll()).thenReturn(List.of(search));
        Long result = searchService.findSearchIdByUserId(user.getUserId());

        // ASSERT
        Assertions.assertEquals(0L, result);
    }

    @Test
    public void findSearchIdByUserNameThrowsException() {
        Long userId = 2L;
        Search search = new Search();

        when(searchRepository.findAll()).thenReturn(List.of(search));

        Assertions.assertThrows(RecordNotFoundException.class,
                () -> searchService.findSearchIdByUserId(userId));
    }


    @Test
    public void findSearchByIdSuccess() {
        // ARRANGE
        User user = new User();
        user.setUserId(1);

        Search search = new Search();
        search.setUser(user);

        // ACT
        when(searchRepository.findAll()).thenReturn(List.of(search));
        Search result = searchService.findSearchById(user.getUserId());

        // ASSERT
        Assertions.assertEquals(search, result);
    }

    @Test
    public void findSearchByIdThrowsException() {
        Long userId = 2L;
        Search search = new Search();

        when(searchRepository.findAll()).thenReturn(List.of(search));

        Assertions.assertThrows(RecordNotFoundException.class,
                () -> searchService.findSearchById(userId));
    }

    @Test
    public void createSearchSuccess() {
        // ARRANGE
        User user = new User();
        user.setUserId(1L);

        Search search = new Search();
        search.setSearchId(1L);

        // ACT
        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
        searchService.createSearch(search, user.getUserId());

        // ASSERT
        verify(searchRepository).save(searchCaptor.capture());
        Assertions.assertEquals(1L, searchCaptor.getValue().getUser().getUserId());
    }

    @Test
    public void createSearchThrowsException() {
        Long userId = 2L;
        Search search = new Search();

        Assertions.assertThrows(RecordNotFoundException.class,
                () -> searchService.createSearch(search, userId));
    }

    @Test
    public void updateSearchSuccess() {
        // ARRANGE
        Long searchId = 1L;
        int amount = 80;
        int newAmount = 85;
        String functionTitle = "Back-end Software Developer";
        String newFunctionTitle = "Java Software Developer";
        String location = "Utrecht";
        String newLocation = "Laren";
        String headline = "Passionate Back-end Developer";
        String newHeadline = "Passionate Java Developer";
        String email = "test@test.com";
        String newEmail = "newTest@test.com";
        String fullName = "User Test";
        String newFullName = "New Test";

        // ACT
        Optional<Search> search = Optional.of(new Search());
        search.get().setSearchId(searchId);
        search.get().setFunctionTitle(functionTitle);
        search.get().setAmount(amount);
        search.get().setLocation(location);
        search.get().setHeadline(headline);
        search.get().setEmail(email);
        search.get().setFullName(fullName);
        when (searchRepository.findById(searchId)).thenReturn(search);
        searchService.updateSearch(searchId, newFunctionTitle, newAmount, newLocation, newHeadline, newEmail, newFullName);

        // ASSERT
        verify(searchRepository).save(searchCaptor.capture());
        Assertions.assertEquals(newFunctionTitle, searchCaptor.getValue().getFunctionTitle());
        Assertions.assertEquals(newAmount, searchCaptor.getValue().getAmount());
    }

    @Test
    public void updateSearchThrowsException() {
        Long searchId = 2L;
        String newFunctionTitle = "Java Software Developer";
        int newAmount = 85;
        String newLocation = "Laren";
        String newHeadline = "New Passionate Back-end Developer";
        String newEmail = "newTest@test.com";
        String newFullName = "New Test";

        Assertions.assertThrows(BadRequestException.class,
                () -> searchService.updateSearch(searchId, newFunctionTitle, newAmount, newLocation, newHeadline, newEmail, newFullName));
    }

    @Test
    public void checkSearchIsPresentOnUserSuccess() {
        // ARRANGE
        User user = new User();
        user.setUserId(1L);

        Search search = new Search();
        search.setUser(user);

        // ACT
        when(searchRepository.findAll()).thenReturn(List.of(search));
        boolean searchIsPresent = searchService.checkSearchIsPresentOnUser(user.getUserId());

        // ASSERT
        Assertions.assertTrue(searchIsPresent);
    }

    @Test
    public void checkSearchIsPresentOnUserThrowsException() {
        Long userId = 2L;
        Search search = new Search();

        when(searchRepository.findAll()).thenReturn(List.of(search));

        Assertions.assertThrows(RecordNotFoundException. class,
                () -> searchService.checkSearchIsPresentOnUser(userId));
    }
}