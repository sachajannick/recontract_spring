package com.recontract.recontract.service;

import com.recontract.recontract.domain.Search;
import com.recontract.recontract.dto.dtoSearch;
import com.recontract.recontract.dto.dtoSearchHiring;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface SearchService {

    List<dtoSearch> findSearchFreelancer();
    List<dtoSearchHiring> findSearchHiring();
    Long findSearchIdByUserId(Long userId);
    void createSearch(Search search, Long userId);
    void updateSearch(Long searchId,
                      String functionTitle,
                      int amount,
                      String location,
                      String headline,
                      String email,
                      String fullName);
    boolean checkSearchIsPresentOnUser(Long userId);
    void uploadProfilePicture(Long searchId, MultipartFile file) throws IOException;
    byte[] getProfilePicture(Long searchId);
}
