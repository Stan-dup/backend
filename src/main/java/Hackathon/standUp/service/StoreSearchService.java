package Hackathon.standUp.service;

import Hackathon.standUp.dto.NaverLocalItem;
import Hackathon.standUp.dto.response.NaverLocalResponse;
import Hackathon.standUp.dto.response.StoreInfoResponse;
import Hackathon.standUp.external.naver.NaverLocalClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreSearchService {

    private final NaverLocalClient naverLocalClient;

    public List<StoreInfoResponse> getStoreInfoByNaverMapApi(String storeName) {
        NaverLocalResponse res = naverLocalClient.search(storeName, 5);

        if (res == null || res.items() == null || res.items().isEmpty()) {
            return List.of();
        }

        return res.items().stream()
            .map(this::toStoreInfoResponse)
            .toList();
    }

    private StoreInfoResponse toStoreInfoResponse(NaverLocalItem item) {
        String address = (item.roadAddress() != null && !item.roadAddress().isBlank())
            ? item.roadAddress()
            : item.address();

        return StoreInfoResponse.create(address, item.description());
    }
}
