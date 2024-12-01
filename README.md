# Meal Master (Recipe Management Platform)

## Table of Contents
1. [Introduction](#introduction)
2. [Features](#features)
3. [Installation Instructions](#installation-instructions)
4. [Usage](#usage)
5. [License](#license)
6. [Feedback and Contributions](#feedback-and-contributions)
7. [Group](#group)
8. [User Stories](#user-stories)

---

## Introduction

**Creators**: Jiacan Sun, Qihong Liu, Zenghao Chen, Xiangyu Shen, Yi Fan (Eric) Wang

**What does this project do?**  
Meal Master simplifies recipe organization and meal planning by allowing users to manage their favorite recipes, generate shopping lists, and interact with community-generated content.

**Why was this project made?**  
This system was developed to streamline recipe management and promote collaborative cooking experiences.

---

## Features

- **User Account Management**: Sign up, log in, and save personalized preferences.
- **Recipe Search**: Search for recipes by name, displaying related dishes dynamically.
- **Favorites**: Save favorite recipes for quick access.
- **Shopping List Generator**: Generate shopping lists based on saved favourite recipes.
- **Add Recipes**: Add self-designated recipes.
- **Delete Recipes**: Delete self-designated recipes.
- **Social Interactions**: Like and dislike on recipes.
- **API Integration**: Fetch existing high-quality recipes from TheMealDB API and store them in the cloud using File.io API.
- **Community Interaction**: Share recipes with others and engage with their contributions since the user and recipe databases are stored in File.io API.

---

## Installation Instructions

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/EricWcr7/CSC207-Project.git
2. **Set Up APIs**:
   There is nothing to do with setting up the APIs.
3. **Set Up IDE**:
   Please make sure you have Java 17 or later installed. It is encouraged to use IntelliJ IDEA IDE to build the project.
   Use Java corretto-17 as SDK.
4. **Install and Set Up Dependencies**
   Add Google Gson library to your project and select the latest version.
   ```bash
   google.code.gson
   ```
   Add OKHTTP library to your project and select the latest version. Example in below:
   ```bash
   com.squareup.okhttp3:okhttp:5.0.0-alpha.14
   ```
5. **Run the Application**:
   Run the Main class to start the program.
