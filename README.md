# GitSearchAndroid
<img width="300" height="300" alt="ic_launcher" src="https://github.com/user-attachments/assets/34c6bf11-bebb-48ae-804e-cbeb78db0383" />

GitHub User Search Client App (Kotlin / Jetpack Compose)

> An Android app for searching GitHub users, viewing their profiles, and listing their repositories.  
> Built using GitHub REST API (v3) with Paging 3, Hilt, and Jetpack Compose.

## 👨‍💻 Development Environment
- Android Studio Narwhal | 2025.1.1 Patch 1
- JDK 11 or higher required
- Kotlin 2.2.0


## 🖥️ Screenshots

| Initial Screen | User Search | User Detail |
|---------------|-------------|-----------------|
| <img width="1440" height="3120" alt="Screenshot_20250715_005133" src="https://github.com/user-attachments/assets/fba59386-2646-471c-98b4-736880a8f538" /> | <img width="1440" height="3120" alt="Screenshot_20250715_005152" src="https://github.com/user-attachments/assets/7cf91047-4b80-4a87-96e4-5148045e25cf" /> | <img width="1440" height="3120" alt="Screenshot_20250715_005158" src="https://github.com/user-attachments/assets/bd08e6c1-7a35-417f-b43f-bb9502a48600" /> |

[Screen_recording_20250715_005525.webm](https://github.com/user-attachments/assets/8abc048a-b658-4bdb-b2d4-9c9754558c79)




## 🚀 Getting Started


1. Clone the project
   ```bash
   git clone https://github.com/SeulGeek/GitSearchAndroid.git
   ```
3. Open with Android Studio
4. (Optional but recommended) Add your Personal Access Token

## 🔐 Personal Access Token Setup

This project uses the GitHub API, which imposes a **rate limit of 60 requests per hour** without authentication.  
To avoid hitting this limit during development, you can configure a **Personal Access Token (PAT)**.

### ✅ Steps

1. [Generate a token on GitHub](https://github.com/settings/tokens)
   - **Token type:** `Classic`
   - **Expiration:** Set as you prefer
   - No special permissions required (default scopes are fine)
      - Recommended scopes:
        - `read:user`
        - `repo` (if private repos are ever supported)

2. In the project root, create a `local.properties` file if it doesn't exist

3. Add the following line:

   ```properties
   GITHUB_TOKEN=ghp_xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

4. Rebuild the project (Build → Rebuild Project or Clean & Run)

⚠️ Note:
  - local.properties is excluded from version control (.gitignore)
  - Do not share your token publicly
  - You can revoke the token anytime from your GitHub settings


## 📡 GitHub API Endpoints Used
This project uses GitHub REST API v3 to implement the following features:

| Endpoint | Description | Docs |
|----------|------|------|
| `GET /search/users` | Search GitHub users by query | [Docs](https://docs.github.com/en/rest/search/search?apiVersion=2022-11-28#search-users) |
| `GET /users/{username}` | Get detailed user profile | [Docs](https://docs.github.com/en/rest/users/users?apiVersion=2022-11-28#get-a-user) |
| `GET /users/{username}/repos` | List a user's public repositories | [Docs](https://docs.github.com/en/rest/repos/repos?apiVersion=2022-11-28#list-repositories-for-a-user) |

