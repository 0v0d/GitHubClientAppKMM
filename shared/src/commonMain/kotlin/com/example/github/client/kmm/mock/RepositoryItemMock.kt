package com.example.github.client.kmm.mock

import com.example.github.client.kmm.model.RepositoryItem

object RepositoryItemMocks {

    val mockRepo1 = RepositoryItem(
        id = 123456789,
        name = "Spaceship-Project",
        fullName = "starbuilder/Spaceship-Project",
        owner = OwnerItemMocks.mockOwner1,
        htmlUrl = "https://github.com/starbuilder/Spaceship-Project",
        description = "A project to build a spaceship simulator",
        language = "Kotlin",
        stargazersCount = 1500000,
        watchersCount = 1500,
        forksCount = 300,
        openIssuesCount = 4200
    )

    private val mockRepo2 = RepositoryItem(
        id = 987654321,
        name = "Magic-Potion",
        fullName = "wizarddev/Magic-Potion",
        owner = OwnerItemMocks.mockOwner2,
        htmlUrl = "https://github.com/wizarddev/Magic-Potion",
        description = "A repository of magic potion formulas",
        language = "Python",
        stargazersCount = 2500,
        watchersCount = 2500,
        forksCount = 500,
        openIssuesCount = 75
    )

    private val mockRepo3 = RepositoryItem(
        id = 135792468,
        name = "Dream-Visualizer",
        fullName = "imagination/Dream-Visualizer",
        owner = OwnerItemMocks.mockOwner2,
        htmlUrl = "https://github.com/imagination/Dream-Visualizer",
        description = "A tool to visualize dreams using neural networks",
        language = "Swift",
        stargazersCount = 3500,
        watchersCount = 3500,
        forksCount = 700,
        openIssuesCount = 20
    )

    private val mockRepo4 = RepositoryItem(
        id = 246813579,
        name = "SuperBlogEngine",
        fullName = "techwriter/SuperBlogEngine",
        owner = OwnerItemMocks.mockOwner3,
        htmlUrl = "https://github.com/techwriter/SuperBlogEngine",
        description = "A powerful blog engine for creative writers",
        language = "Ruby",
        stargazersCount = 1800,
        watchersCount = 1800,
        forksCount = 400,
        openIssuesCount = 10
    )

    private val mockRepo5 = RepositoryItem(
        id = 975318642,
        name = "DevTools-Suite",
        fullName = "devmaster/DevTools-Suite",
        owner = OwnerItemMocks.mockOwner4,
        htmlUrl = "https://github.com/devmaster/DevTools-Suite",
        description = "A comprehensive suite of DevOps tools",
        language = "Go",
        stargazersCount = 4200,
        watchersCount = 4200,
        forksCount = 1000,
        openIssuesCount = 55
    )

    private val mockRepo6 = RepositoryItem(
        id = 468135792,
        name = "AI-Research-Lab",
        fullName = "aiguru/AI-Research-Lab",
        owner = OwnerItemMocks.mockOwner3,
        htmlUrl = "https://github.com/aiguru/AI-Research-Lab",
        description = "A repository for AI research papers and projects",
        language = "Python",
        stargazersCount = 5000,
        watchersCount = 5000,
        forksCount = 1200,
        openIssuesCount = 100
    )

    private val mockRepo7 = RepositoryItem(
        id = 753951852,
        name = "Web-Builder",
        fullName = "webmaster/Web-Builder",
        owner = OwnerItemMocks.mockOwner2,
        htmlUrl = "https://github.com/webmaster/Web-Builder",
        description = "A lightweight web development framework",
        language = "JavaScript",
        stargazersCount = 2300,
        watchersCount = 2300,
        forksCount = 600,
        openIssuesCount = 35
    )

    val mockRepoList = listOf(
        mockRepo1,
        mockRepo2,
        mockRepo3,
        mockRepo4,
        mockRepo5,
        mockRepo6,
        mockRepo7,
    )
}


