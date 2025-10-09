#!/bin/bash

# Trip-MateV2 Quick Start Script
# This script helps you quickly set up and run the application

set -e

echo "🌎 Trip-MateV2 Quick Start"
echo "=========================="
echo ""

# Check if Docker is installed
if ! command -v docker &> /dev/null; then
    echo "❌ Docker is not installed. Please install Docker first."
    exit 1
fi

echo "✅ Docker is installed"

# Check if .env file exists
if [ ! -f .env ]; then
    echo "⚠️  .env file not found. Creating from .env.example..."
    cp .env.example .env
    echo ""
    echo "📝 Please edit the .env file with your configuration:"
    echo "   - Database password"
    echo "   - Google OAuth2 credentials"
    echo "   - GitHub OAuth2 credentials"
    echo "   - JWT secret"
    echo ""
    echo "Run this script again after configuring .env"
    exit 1
fi

echo "✅ .env file found"

# Ask user which deployment method to use
echo ""
echo "Choose deployment method:"
echo "1) Docker Compose (Application + PostgreSQL)"
echo "2) Local build and run (requires Java 17+)"
echo "3) Docker only (requires external PostgreSQL)"
echo ""
read -p "Enter your choice (1-3): " choice

case $choice in
    1)
        echo ""
        echo "🚀 Starting with Docker Compose..."
        docker compose up -d
        echo ""
        echo "✅ Application started!"
        echo "📍 Access the application at: http://localhost:9090"
        echo ""
        echo "To view logs: docker compose logs -f"
        echo "To stop: docker compose down"
        ;;
    2)
        echo ""
        echo "🔨 Building application..."
        ./mvnw clean package -DskipTests
        echo ""
        echo "🚀 Starting application..."
        java -jar target/Trip-MateV2-0.0.1-SNAPSHOT.jar
        ;;
    3)
        echo ""
        echo "🔨 Building Docker image..."
        docker build -t trip-mate:latest .
        echo ""
        echo "🚀 Starting container..."
        docker run -d \
          -p 9090:9090 \
          --env-file .env \
          --name trip-mate \
          trip-mate:latest
        echo ""
        echo "✅ Application started!"
        echo "📍 Access the application at: http://localhost:9090"
        echo ""
        echo "To view logs: docker logs -f trip-mate"
        echo "To stop: docker stop trip-mate && docker rm trip-mate"
        ;;
    *)
        echo "❌ Invalid choice"
        exit 1
        ;;
esac

echo ""
echo "✨ Done!"
