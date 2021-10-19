package com.recontract.recontract.service;

import com.recontract.recontract.domain.Search;
import com.recontract.recontract.domain.User;
import com.recontract.recontract.dto.dtoSearch;
import com.recontract.recontract.dto.dtoSearchHiring;
import com.recontract.recontract.exception.BadRequestException;
import com.recontract.recontract.exception.RecordNotFoundException;
import com.recontract.recontract.repository.SearchRepository;
import com.recontract.recontract.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
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
    public List<dtoSearch> findSearchFreelancer() {
        List<Search> list = searchRepository.findAll();
        List<dtoSearch> listFreelancer = new ArrayList<>();

        try {
            for (int i = 0; i < list.size(); i++) {
                Search search = list.get(i);
                if (search.getUser().getHiringOrFreelancer().equals("freelancer")) {
                    dtoSearch dto = new dtoSearch();
                    dto.setSearchId(search.getSearchId());
                    dto.setUserId(search.getUser().getUserId());
                    dto.setFunctionTitle(search.getFunctionTitle());
                    dto.setAmount(search.getAmount());
                    dto.setLocation(search.getLocation());
                    dto.setHeadline(search.getHeadline());
                    dto.setEmail(search.getEmail());
                    dto.setFullName(search.getFullName());
                    dto.setProfilePicture(search.getProfilePicture());
                    listFreelancer.add(dto);
                }
            }

            return listFreelancer;
        } catch (Exception e) {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public List<dtoSearchHiring> findSearchHiring() {
        List<Search> list = searchRepository.findAll();
        List<dtoSearchHiring> listHiring = new ArrayList<>();

        try {
            for (int i = 0; i < list.size(); i++) {
                Search search = list.get(i);
                if (search.getUser().getHiringOrFreelancer().equals("hiring")) {
                    dtoSearchHiring dto = new dtoSearchHiring();
                    dto.setSearchId(search.getSearchId());
                    dto.setUserId(search.getUser().getUserId());
                    dto.setFunctionTitle(search.getFunctionTitle());
                    dto.setAmount(search.getAmount());
                    dto.setLocation(search.getLocation());
                    dto.setHeadline(search.getHeadline());
                    dto.setEmail(search.getEmail());
                    dto.setFullName(search.getFullName());
                    dto.setProfilePicture(search.getProfilePicture());
                    listHiring.add(dto);
                }
            }

            return listHiring;
        } catch (Exception e) {
            throw new RecordNotFoundException();
        }
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
    public void createSearch(Search search,
                             Long userId) {
        Optional<User> user = userRepository.findById(userId);

        try {
            search.setUser(user.get());
            searchRepository.save(search);
        } catch (Exception e) {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void updateSearch(Long searchId,
                             String functionTitle,
                             int amount,
                             String location,
                             String headline,
                             String email,
                             String fullName) {
        Optional<Search> search = searchRepository.findById(searchId);

        if (search.isPresent()) {
            search.get().setFunctionTitle(functionTitle);
            search.get().setAmount(amount);
            search.get().setLocation(location);
            search.get().setHeadline(headline);
            search.get().setEmail(email);
            search.get().setFullName(fullName);
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

    @Override
    public void uploadProfilePicture(Long searchId, MultipartFile file) throws IOException {
        Optional<Search> search = searchRepository.findById(searchId);

        if (search.isPresent()) {
            search.get().setProfilePicture(file.getBytes());
            searchRepository.save(search.get());
        } else {
            throw new BadRequestException();
        }
    }

    @Override
    public byte[] getProfilePicture(Long searchId) {
        Optional<Search> search = searchRepository.findById(searchId);

        if (search.isPresent()) {
            return search.get().getProfilePicture();
        } else {
            throw new RecordNotFoundException();
        }
    }
}
