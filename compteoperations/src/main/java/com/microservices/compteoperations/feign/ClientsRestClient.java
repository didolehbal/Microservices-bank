package com.microservices.compteoperations.feign;

import com.microservices.compteoperations.entities.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="CLIENT-SERVICE")
public interface ClientsRestClient {
    @GetMapping("/client/{id}")
    Client findClientById(@PathVariable("id") Long clientId);
}
