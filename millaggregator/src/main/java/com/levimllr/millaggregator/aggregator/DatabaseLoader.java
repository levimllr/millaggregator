package com.levimllr.millaggregator.aggregator;

import com.levimllr.millaggregator.user.AuthProvider;
import com.levimllr.millaggregator.user.User;
import com.levimllr.millaggregator.user.UserRepository;
import com.levimllr.millaggregator.user.UserRole;
import com.levimllr.millaggregator.user_aggregator.UserAggregator;
import com.levimllr.millaggregator.user_aggregator.UserAggregatorRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final AggregatorRepository aggregatorRepository;
    private final UserRepository userRepository;

    @Autowired
    public DatabaseLoader(AggregatorRepository aggregatorRepository, UserRepository userRepository) {
        this.aggregatorRepository = aggregatorRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        Aggregator aggregator = new Aggregator.Builder()
                .withName("Millaggregator")
                .build();

        UserAggregator userAggregator = new UserAggregator.Builder()
                .withAggregator(aggregator)
                .withRole(UserAggregatorRole.ROLE_OWNER.toString())
                .build();

        User user = new User.Builder()
                .withName("Eugenia Maldives")
                .withAuthProvider(AuthProvider.SELF.toString())
                .withEmail("emaldives@example.com")
                .withPassword("password")
                .withRole(UserRole.ROLE_USER.toString())
                .build();

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("emaldives@example.com", "PASSWORD",
                        AuthorityUtils.createAuthorityList("ROLE_OWNER")));

        User dbUser = userRepository.save(user);
        userAggregator.setUser(dbUser);
        aggregator.setUserAggregators(Collections.singletonList(userAggregator));

        aggregatorRepository.save(aggregator);

        SecurityContextHolder.clearContext();
    }
}
