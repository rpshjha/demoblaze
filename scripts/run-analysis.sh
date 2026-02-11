#!/bin/bash

# Script to run all static analysis checks
# This script runs PMD, SpotBugs, and Checkstyle for comprehensive code quality analysis

set -e

PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$PROJECT_DIR"

echo "Running comprehensive static analysis checks..."
echo ""

# Colors
YELLOW='\033[1;33m'
GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m'

# Run PMD
echo -e "${YELLOW}Running PMD...${NC}"
mvn pmd:check || echo -e "${RED}PMD found issues (non-blocking)${NC}"

# Run SpotBugs
echo ""
echo -e "${YELLOW}Running SpotBugs...${NC}"
mvn compile spotbugs:check || echo -e "${RED}SpotBugs found issues (non-blocking)${NC}"

# Run Checkstyle
echo ""
echo -e "${YELLOW}Running Checkstyle...${NC}"
mvn checkstyle:check || echo -e "${RED}Checkstyle found issues (non-blocking)${NC}"

echo ""
echo -e "${GREEN}✓ Static analysis complete!${NC}"
echo "  Review the output above for any issues that need fixing."
