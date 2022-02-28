package com.aditya.springbootelasticsearch.service;

import com.aditya.springbootelasticsearch.model.Customer;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private static final String CUSTOMER_INDEX = "customerindex" ;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public String createIndexBulk(Customer customers){
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(customers.getId())
                .withObject(customers).build();

        String customerId = elasticsearchOperations
                .index(indexQuery, IndexCoordinates.of(CUSTOMER_INDEX));

        return customerId;
    }

    //3 types of Query to Search Element in the Elastic Search
    public void findByLastname(String lastname){
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("lastname",lastname);

        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        SearchHits<Customer> searchHits =
                elasticsearchOperations
                        .search(searchQuery, Customer.class,
                                IndexCoordinates.of(CUSTOMER_INDEX));

    }

    public List<Customer> findByFirstname(final String firstname) {
        Query searchQuery = new StringQuery(
                "{\"match\":{\"name\":{\"query\":\""+ firstname + "\"}}}\"");

        SearchHits<Customer> searchHits  = elasticsearchOperations.search(searchQuery, Customer.class,
                IndexCoordinates.of(CUSTOMER_INDEX));

        List<Customer> customers = new ArrayList<>();

        searchHits.forEach(srhHits-> { customers.add(srhHits.getContent());});

        return customers;
    }

    public void findByAge(final String age) {
        Criteria criteria = new Criteria("age").greaterThan(10).lessThan(100);
        Query searchQuery = new CriteriaQuery(criteria);

        SearchHits<Customer> searchHits = elasticsearchOperations.search(searchQuery, Customer.class,
                IndexCoordinates.of(CUSTOMER_INDEX));
    }


}
