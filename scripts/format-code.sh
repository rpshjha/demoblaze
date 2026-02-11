#!/bin/bash

# Script to format code using Spotless
# This script applies Google Java Format to all Java files in the project

set -e

PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$PROJECT_DIR"

echo "Formatting code with Spotless (Google Java Format)..."
echo ""

mvn spotless:apply

echo ""
echo "✓ Code formatting applied successfully!"
echo "  All Java files have been formatted according to Google Java Format rules."
echo "  Please review changes and stage them for commit."
