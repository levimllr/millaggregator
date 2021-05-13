package com.levimllr.millaggregator.aggregator;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ROLE_OWNER')")
public interface AggregatorRepository extends PagingAndSortingRepository<Aggregator, Long> {

    @Override
    @PreAuthorize("#aggregator?.user == null or #aggregator?.user?.name == authentication?.name")
    Aggregator save(@Param("aggregator") Aggregator aggregator);

    @Override
    @PreAuthorize("@aggregatorRepository.findById(#id)?.user?.name == authentication?name")
    void deleteById(Long aLong);

    @Override
    @PreAuthorize("#aggregator?.user?.name == #authentication?.name")
    void delete(Aggregator aggregator);
}
