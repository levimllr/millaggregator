package com.levimllr.millaggregator.aggregator;

import com.levimllr.millaggregator.user.User;
import com.levimllr.millaggregator.user.UserRepository;
import com.levimllr.millaggregator.user.UserRole;
import com.levimllr.millaggregator.user_aggregator.UserAggregator;
import com.levimllr.millaggregator.user_aggregator.UserAggregatorRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;

@Component
@RepositoryEventHandler
public class SpringDataRestEventHandler {

    private final UserRepository userRepository;

    @Autowired
    public SpringDataRestEventHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @HandleBeforeCreate
    @HandleBeforeSave
    public void applyUserInformationUsingSecurityContext(Aggregator aggregator) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = this.userRepository.findByEmail(email);

        if (user == null) {
            User.Builder userBuilder = new User.Builder();
            userBuilder.withEmail(email);
            userBuilder.withRole(UserRole.ROLE_USER.toString());
            user = userBuilder.build();
        }

        UserAggregator.Builder userAggregatorBuilder = new UserAggregator.Builder();
        userAggregatorBuilder.withAggregator(aggregator);
        userAggregatorBuilder.withUser(user);
        userAggregatorBuilder.withRole(UserAggregatorRole.ROLE_VIEWER.toString());
        UserAggregator userAggregator = userAggregatorBuilder.build();

        aggregator.getUserAggregators().add(userAggregator);
    }
}
