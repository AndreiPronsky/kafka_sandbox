package org.pronsky.product_service.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pronsky.grpc.FetchServiceGrpc;
import com.pronsky.grpc.FetchServiceOuterClass;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.pronsky.product_service.data.entities.Product;
import org.pronsky.product_service.data.repository.ProductRepository;
import org.pronsky.product_service.service.ProductService;
import org.pronsky.product_service.service.dto.ProductDto;
import org.pronsky.product_service.service.events.ProductCreatedEvent;
import org.pronsky.product_service.service.mapper.EntityDtoMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@GrpcService
@RequiredArgsConstructor
public class ProductServiceImpl extends FetchServiceGrpc.FetchServiceImplBase implements ProductService {

    private final ProductRepository productRepository;
    private final EntityDtoMapper mapper;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public ProductDto createProduct(ProductDto productDto)
            throws JsonProcessingException, ExecutionException, InterruptedException {

        ProductDto createdProduct = mapper.toDto(productRepository.save(mapper.toEntity(productDto)));
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(createdProduct.getId(),
                createdProduct.getName(), createdProduct.getDescription(), createdProduct.getPrice());

        String message = objectMapper.writeValueAsString(productCreatedEvent);
        kafkaTemplate.send("product-created-event-topic", createdProduct.getId(), message).get();

        return createdProduct;
    }

    @Override
    public void fetch(FetchServiceOuterClass.FetchRequest request,
                      StreamObserver<FetchServiceOuterClass.FetchResponse> responseObserver) {

        Product product = productRepository.findById(UUID.fromString(request.getId())).orElseThrow();
        String description = product.getDescription();

        FetchServiceOuterClass.FetchResponse fetchResponse = FetchServiceOuterClass.
                FetchResponse.newBuilder()
                .setDescription(description)
                .build();

        responseObserver.onNext(fetchResponse);
        responseObserver.onCompleted();
    }
}
