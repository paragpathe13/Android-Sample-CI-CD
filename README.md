# Android CI/CD Demo

Features:
- Simple list application
- Unit Tests
- GitHub Actions
- Firebase App Distribution

# CI/CD Workflow
# Pull Request Validation
- Run Android Lint checks
- Run Unit Tests
- Validate application build
# Post Merge (Master Branch)
- Run Lint checks
- Run Unit Tests
- Generate APK
- Upload APK as GitHub Artifact
- Distribute APK through Firebase App Distribution
- Pipeline Flow

# Versioning
Versioning is managed manually for simplicity. In production environments, versioning can be automated through CI/CD pipelines using build numbers or Git tags.

# Notes
- APK distribution is intentionally used for this assignment to simplify installation and testing.
- In production environments, the same pipeline can be extended to generate and distribute signed AAB files for Play Store releases.
- Firebase credentials are managed securely using GitHub Secrets.
