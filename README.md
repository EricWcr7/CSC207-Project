# Meal Master (Recipe Management Platform)

## Table of Contents
1. [Introduction](#introduction)
2. [Features](#features)
3. [Installation Instructions](#installation-instructions)
4. [Usage](#usage)
5. [Feedback and Contributions](#feedback-and-contributions)

---

## Introduction

**Creators**: Jiacan Sun, Qihong Liu, Zenghao Chen, Xiangyu Shen, Yi Fan (Eric) Wang

**What does this project do?**  
Meal Master simplifies recipe organization and meal planning by allowing users to manage their favorite recipes, generate shopping lists, and interact with community-generated content.

**Why was this project made?**  
This platform was developed to streamline recipe management and promote collaborative cooking experiences.

---

## Features

## Features

- **User Account Management**: Sign up, log in, and save personalized preferences.
  - Example: A user can create an account with their email and password. Upon logging in, they can view their saved recipes and preferences.
  
- **Recipe Search**: Search for recipes by name, displaying related dishes dynamically.
  - Example: Typing "Pasta" in the search bar returns a list of recipes like "Spaghetti Bolognese," "Carbonara," and "Fettuccine Alfredo," each displayed as clickable buttons.

- **Favorites**: Save favorite recipes for quick access.
  - Example: A user can click the "Save to Favorites" button on a recipe, and it will appear under their "Favorites" section for easy retrieval.

- **Shopping List Generator**: Generate shopping lists based on saved favorite recipes.
  - Example: Clicking the "Generate Shopping List" button creates a detailed shopping list with all the ingredients required for the selected recipes.

- **Add Recipes**: Add self-designated recipes.
  - Example: A user can upload a recipe by providing its name, category, ingredients, and step-by-step instructions, which are saved to the user's account.

- **Delete Recipes**: Delete self-designated recipes.
  - Example: Users can click the "Delete Recipe" button on their own recipes to remove them permanently from their collection.

- **Social Interactions**: Like and dislike recipes.
  - Example: Clicking the "like" or "dislike button on a recipe adjusts its popularity ranking.

- **API Integration**: Fetch existing high-quality recipes from TheMealDB API and store them in the cloud using File.io API.
  - Example: TheMealDB API provides a collection of curated recipes that are downloaded, saved in a local JSON file, and backed up to File.io.

- **Community Interaction**: Share recipes with others and engage with their contributions since the user and recipe databases are stored in File.io API.
  - Example: Recipes added by one user are accessible to others, allowing them to like/dislike, or save those recipes to their favorites.

---

## Installation Instructions

1. **Clone the Repository**:
   ```bash
   https://github.com/EricWcr7/CSC207-Project.git
2. **Set Up APIs**:
   There is nothing to do with setting up the APIs.
3. **Set Up IDE**:
   Please make sure you have Java 17 or later installed. It is encouraged to use IntelliJ IDEA IDE to build the project.
   Use Java corretto-17 as SDK.
4. **Install and Set Up Dependencies**:
   Add Google Gson library to your project in your **Project Structure/Library** by clicking the "add" icon on the left and selecting the following version. Enter the code below just like the image below. (You may use the latest version if the app can run properly.)
   
   ```bash
   com.google.code.gson:gson:2.11.0
   ```
   
   <img width="1504" alt="Screenshot 2024-12-03 at 9 32 24 PM" src="https://github.com/user-attachments/assets/eccffdcf-c49c-479e-8418-39f987d47183">
   Add OKHTTP library to your project in your **Project Structure/Library** by clicking the "add" icon on the left and selecting the following version. Enter the code below just like the image below. (You may use the latest version if the app can run properly.)
   
   ```bash
   com.squareup.okhttp3:okhttp:5.0.0-alpha.14
   ```
   
   <img width="1512" alt="Screenshot 2024-12-03 at 9 29 57 PM" src="https://github.com/user-attachments/assets/afb7c31f-6008-4d95-9df7-4c29165c9521">
6. **Run the Application**:
   Run the Main class to start the program.

---

## Usage

The Meal Master platform is designed for users to manage their recipes and engage with community content. Here's how to get started:

### 1. **Running the Application**
- Run the `Main` class to start the program and begin exploring Meal Master’s features.

### 2. **Account Management**
- **Sign Up**: Create a new account with a unique username and password.
- **Log In**: Access your account to manage your personalized recipe collection.
- **Profile Management**: Update your preferences or reset your password.

### 3. **Exploring Recipes**
- **Search for Recipes**: Use the search bar to find recipes by name. Results will dynamically display matching dishes.
- **View Recipes**: Click on a recipe to view its ingredients, instructions, and user ratings.
- **Like/Dislike Recipes**: Engage with community recipes by liking or disliking them.
- **Favorites**: Save recipes to your favorites for easy access.

### 4. **Shopping List Generation**
- **Generate Shopping Lists**: Meal Master will generate a shopping list with all required ingredients for all favorite recipes.
- **Edit Shopping Lists**: Customize your shopping list by adding or removing favorite recipes.

### 5. **Creating and Managing Recipes**
- **Add Recipes**: Use the "Add Recipe" feature to upload your own creations. Input the recipe name, ingredients, and step-by-step instructions.
- **Edit Recipes**: Modify your previously uploaded recipes as needed.
- **Delete Recipes**: Remove recipes you no longer want to share or manage.

---

## Feedback and Contributions

### Feedback
We welcome feedback on the software to help improve its functionality and user experience. Here are the ways you can provide feedback:

- **Google Forms**: [Submit Feedback](https://forms.gle/rB6JPXrWfYPmu2r58)

### Contributions
We encourage contributions from the community to make the software better. Below are the guidelines and protocols for contributing:

#### How to Contribute
1. **Fork the Repository**: Start by creating a fork of the repository. Click the "Fork" button on GitHub and clone it to your local machine.
2. **Make Changes**: Work on your fork to implement features, fix bugs, or improve documentation.
3. **Submit a Pull Request (PR)**: Once you're satisfied with your changes, submit a pull request to the main repository.

#### Guidelines for a Good Merge Request
- Clearly describe the changes made and the purpose of the update.
- Ensure your code adheres to the project's coding standards.
- Include relevant tests for the feature or bug fix, if applicable.
- Keep your pull requests small and focused on a single change or issue.

#### Contribution Review Protocols
- All contributions are reviewed by maintainers to ensure they align with the project's goals and coding standards.
- Discussions on pull requests are encouraged to clarify the changes and resolve potential issues.
- Once reviewed and approved, the contribution will be merged into the main repository.


Thank you for helping us make this project better!
