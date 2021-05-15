package com.levimllr.millaggregator.aggregator;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
public interface AggregatorRepository extends PagingAndSortingRepository<Aggregator, Long> {

    // TO-DO: FIGURE OUT HOW TO SEARCH FOR USER WITH EMAIL THAT MATCHES AUTHENTICATION PRINCIPAL
    @Override
    @PreAuthorize("#aggregator?.userAggregators == null or #aggregator?.userAggregators?.![user.email].contains(authentication.principal)")
    Aggregator save(@Param("aggregator") Aggregator aggregator);

    @Override
    @PreAuthorize("@aggregatorRepository.findById(#id)?.userAggregators?.![user.email].contains(authentication.principal)")
    void deleteById(Long aLong);

    @Override
    @PreAuthorize("#aggregator?.userAggregators?.![user.email].contains(authentication.principal)")
    void delete(Aggregator aggregator);
}
