# 🛠 ReqRes API Automation Framework

This is a **RestAssured + TestNG + Maven automation framework** for testing the [ReqRes](https://reqres.in) API.  
The framework includes **user creation, retrieval, and update scenarios**, along with positive and negative test cases. All test results are logged in the console and can be extended to **Allure reporting**.

---

## ⚙ Tools & Technologies Used

- **Java 21** – Programming language  
- **Maven** – Dependency and build management  
- **RestAssured** – API automation and validation  
- **TestNG** – Test execution, assertions, and test dependencies  
- **Log4j2** – Logging requests, responses, and errors  
- **Git & GitHub** – Version control  
- **IntelliJ IDEA** – IDE  

---

## 📂 Project Structure

project-name/
├─ src/main/java/models/ # POJOs (User class)
├─ src/main/java/base/ # Base classes (BaseTest)
├─ src/main/java/utils/ # Utilities (RequestBuilder, LoggerUtil, ConfigManager)
├─ src/test/java/tests/ # API Test classes (UserApiTest)
├─ src/test/resources/ # Configuration files (config.properties)
├─ pom.xml # Maven dependencies
├─ .gitignore
└─ README.md


---

## ⚡ How to Set Up the Framework

1. **Clone the project**

```bash
git clone <your-repo-url>
cd project-name
```

2. **Install Maven dependencies

```bash
mvn clean install
```

3. Set your configuration

- Open src/test/resources/config.properties and set your API base URL or API key if required:

 ```java
base.url=https://reqres.in
api.key=<your-api-key-if-needed>
```

⚠️ Do not commit any sensitive keys to GitHub.
Consider using environment variables for production use.

4. Run the tests
Using TestNG XML:

```bash
mvn test
```
Or directly from IntelliJ IDEA by right-clicking a test class → Run

5. View Logs & Debugging
- All request and response details are printed in the console.
- Custom step messages (like user ID creation or job updates) are logged via Log4j2.

🧪 Features Implemented
- Create a user via POST /api/users
- Retrieve a user via GET /api/users/{id}
- Update a user via PUT /api/users/{id}
- Positive and negative test scenarios
    - Invalid request body
    - Retrieving/updating non-existent users
- Logging of requests, responses, and errors
- TestNG assertions for status codes and response fields
  




