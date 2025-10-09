# Contributing to Trip-MateV2

First off, thank you for considering contributing to Trip-MateV2! 🎉

## How Can I Contribute?

### 🐛 Reporting Bugs

Before creating bug reports, please check the issue list as you might find out that you don't need to create one. When you are creating a bug report, please include as many details as possible:

* Use a clear and descriptive title
* Describe the exact steps which reproduce the problem
* Provide specific examples to demonstrate the steps
* Describe the behavior you observed after following the steps
* Explain which behavior you expected to see instead and why
* Include screenshots if possible

### 💡 Suggesting Enhancements

Enhancement suggestions are tracked as GitHub issues. When creating an enhancement suggestion, please include:

* Use a clear and descriptive title
* Provide a detailed description of the suggested enhancement
* Provide specific examples to demonstrate the steps
* Describe the current behavior and explain which behavior you expected to see instead

### 🔧 Pull Requests

* Fill in the required template
* Do not include issue numbers in the PR title
* Follow the Java coding style
* Include thoughtfully-worded, well-structured tests
* Document new code
* End all files with a newline

## Development Process

### Setting Up Your Development Environment

1. Fork the repository
2. Clone your fork:
   ```bash
   git clone https://github.com/your-username/Trip-MateV2.git
   cd Trip-MateV2
   ```

3. Create a branch:
   ```bash
   git checkout -b feature/my-new-feature
   ```

4. Set up environment variables:
   ```bash
   cp .env.example .env
   # Edit .env with your development credentials
   ```

5. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

### Coding Standards

* Follow the existing code style
* Write meaningful commit messages
* Keep commits focused and atomic
* Write tests for new features
* Ensure all tests pass before submitting PR
* Update documentation as needed

### Commit Message Guidelines

* Use the present tense ("Add feature" not "Added feature")
* Use the imperative mood ("Move cursor to..." not "Moves cursor to...")
* Limit the first line to 72 characters or less
* Reference issues and pull requests liberally after the first line

Examples:
```
Add user authentication endpoint
Fix database connection pool issue
Update deployment documentation
```

### Testing

Before submitting a pull request:

```bash
# Run tests (if configured for your environment)
./mvnw test

# Build the project
./mvnw clean package

# Test with Docker
docker compose up --build
```

## Project Structure

```
Trip-MateV2/
├── src/
│   ├── main/
│   │   ├── java/           # Java source code
│   │   └── resources/      # Configuration files
│   └── test/               # Test files
├── .github/                # GitHub workflows
├── Dockerfile              # Docker configuration
├── docker-compose.yml      # Docker Compose configuration
└── pom.xml                # Maven configuration
```

## Additional Notes

### Issue and Pull Request Labels

* `bug` - Something isn't working
* `enhancement` - New feature or request
* `documentation` - Improvements or additions to documentation
* `good first issue` - Good for newcomers
* `help wanted` - Extra attention is needed

## Questions?

Feel free to ask questions by creating an issue or reaching out to the maintainers.

---

Thank you for contributing! 🙏
