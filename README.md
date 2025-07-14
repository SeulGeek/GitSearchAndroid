# GitSearchAndroid

## 🔐 Personal Access Token Setup

This project uses the GitHub API, which imposes a **rate limit of 60 requests per hour** without authentication.  
To avoid hitting this limit during development, you can configure a **Personal Access Token (PAT)**.

### ✅ Steps

1. [Generate a token on GitHub](https://github.com/settings/tokens)
   - **Token type:** `Classic`
   - **Expiration:** Set as you prefer
   - No special permissions required (default scopes are fine)

2. In the project root, create a `local.properties` file if it doesn't exist

3. Add the following line:

   ```properties
   GITHUB_TOKEN=ghp_xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

4. Rebuild the project (Build → Rebuild Project or Clean & Run)

⚠️ Note:
  - local.properties is excluded from version control (.gitignore)
  - Do not share your token publicly
  - You can revoke the token anytime from your GitHub settings
