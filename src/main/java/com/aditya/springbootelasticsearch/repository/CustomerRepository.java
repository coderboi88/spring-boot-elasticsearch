package com.aditya.springbootelasticsearch.repository;

import com.aditya.springbootelasticsearch.model.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends ElasticsearchRepository<Customer,String> {

    List<Customer> findByFirstname(String firstname);
}
