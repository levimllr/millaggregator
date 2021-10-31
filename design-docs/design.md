# Millaggregator Design Doc

## Features

1. Digestable, actionable financials tracking
2. Contextual ticket presentation
3. Commonplace rendering of `/worklife`
4. Sleep rating
5. Apple Exercise data / Myfitnesspal data summary

## Who, What, When, Where, How, Why

### Who is the Millaggregator for?

As elucidated above, the Millaggregator is for the person living in this day in age who needs to shave some spatial, digital, and/or temporal friction off of how they get a grip on their obligations and intentions in this crazy, crazy world.

### What is the Millaggregator?

The Millaggregator is a personal CRM, to-do list, and finance tracker.

### Why the Millaggregator?

The Millaggregator is a personal dashboard, designed for the life and times of Paul Levi Miller. Mr. Miller is a man like anyone else, and like anyone else he's overwhelmed by the multi-headed hydra of demands placed upon him. While the original hydra was ultimately slain with sword and fire, the postmodern hydra can't be bested so easily. Nor should besting or slaying be our aim. 

The Millagregator aims tenders love and logic instead of steel and flame. It is a means of organizing one's life by interfacing with existing, useful tools, and giving the user the power to generate and track their own transient states.

### How does the Millaggregator work?

The Millaggregator largely relies on existing technologies. It is an ecosystem of microservices. [Monica](https://www.monicahq.com/) serves as the personal CRM platform; an instance of Monica is served exclusively for the Millaggregator. A [GitHub](https://github.com/) repository is used as a platform for containing projects and issues. A user may choose to have an existing GitHub repository used or have a repository created and associated with their GitHub account. [You Need A Budget](https://www.youneedabudget.com/) manages financial data syncing and tracking. 

In addition to interfacing with these services, the Millaggregator offers custom to-do list widget-modules. Want some intention set or GitHub issue tag prioritized depending on the moon phase or dice roll or Tarot card draw? You got it. Instead of a static, finite to-do list, do you want a cyclical series of tasks which repeat? It will make it so. Do you want to step through an acitivity algorithm for the given day? You can do it (or ignore it). The Millaggregator aims to serves as a one-stop-shop and a versatile portal for your web app productivity suite.

### Where is the Millaggregator?

In its early stages, the Millaggregator will be hosted on a local Raspberry Pi server for us by its inventor. Later on, it will migrated to The Cloud.

### When is the Millaggreator intended to be used?
