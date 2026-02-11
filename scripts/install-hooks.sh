#!/bin/bash

# Script to install/setup pre-commit hooks for DemoBlaze
# This script sets up the git pre-commit hook and makes it executable

set -e

PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
HOOKS_DIR="$PROJECT_DIR/.git/hooks"

echo "Setting up pre-commit hooks..."

if [ ! -d "$HOOKS_DIR" ]; then
    mkdir -p "$HOOKS_DIR"
fi

# Copy pre-commit hook if it doesn't exist or update it
if [ -f "$HOOKS_DIR/pre-commit" ]; then
    echo "Pre-commit hook already exists. Skipping copy..."
else
    cp "$PROJECT_DIR/scripts/pre-commit-hook.sh" "$HOOKS_DIR/pre-commit"
fi

# Make the hook executable
chmod +x "$HOOKS_DIR/pre-commit"

echo "✓ Pre-commit hooks installed successfully!"
echo "  Hook location: $HOOKS_DIR/pre-commit"
echo ""
echo "The following checks will run before each commit:"
echo "  1. Code formatting with Spotless + Google Java Format"
echo "  2. Code style with Checkstyle"
echo "  3. Static analysis with PMD"
echo "  4. Bug detection with SpotBugs"
echo "  5. Dependency vulnerability check with OWASP Dependency-Check"
echo ""
echo "To bypass the hook (not recommended), use: git commit --no-verify"
