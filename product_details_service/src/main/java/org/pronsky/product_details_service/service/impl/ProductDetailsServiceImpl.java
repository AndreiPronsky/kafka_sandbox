package org.pronsky.product_details_service.service.impl;

import com.pronsky.grpc.FetchServiceGrpc;
import com.pronsky.grpc.FetchServiceOuterClass;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.pronsky.product_details_service.data.repository.ProductDetailsRepository;
import org.pronsky.product_details_service.service.ProductDetailsService;
import org.pronsky.product_details_service.service.dto.ProductDetailsDto;
import org.pronsky.product_details_service.service.mapper.EntityDtoMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductDetailsServiceImpl implements ProductDetailsService {

    private final ProductDetailsRepository productDetailsRepository;
    private final EntityDtoMapper mapper;
    @GrpcClient("product-service")
    private FetchServiceGrpc.FetchServiceBlockingStub stub;

    @Override
    public ProductDetailsDto saveProductDetails(ProductDetailsDto dto) {
        dto.setDescription(null);
        ProductDetailsDto saved = mapper.toDto(
                productDetailsRepository.save(mapper.toEntity(dto)));

        saved.setDescription(fetch(dto.getId()));
        productDetailsRepository.save(mapper.toEntity(saved));

        return saved;
    }

    public String fetch(String id) {

        FetchServiceOuterClass.FetchRequest request = FetchServiceOuterClass.FetchRequest.newBuilder()
                .setId(id)
                .build();

        return stub.fetch(request).getDescription();
    }
}
