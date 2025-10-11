#!/bin/bash
# Script to help with Render deployment

# Ensure we're using the right Java version
echo "Checking Java version..."
java --version

# Build the application
echo "Building application with Maven..."
chmod +x mvnw
./mvnw clean package -DskipTests

# Check if build was successful
if [ $? -ne 0 ]; then
    echo "Build failed! Check logs above."
    exit 1
fi

echo "Build successful!"
echo "JAR created at: target/Trip-MateV2-0.0.1-SNAPSHOT.jar"
echo ""
echo "Deploy to Render by:"
echo "1. Pushing these changes to your GitHub repository"
echo "2. Creating a new web service on Render pointing to your repository"
echo "3. Selecting 'Docker' as the environment"
echo "4. Setting the required environment variables as listed in render.yaml"
echo ""
echo "Or use Render's CLI tool:"
echo "render deploy --yaml render.yaml"