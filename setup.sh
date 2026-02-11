#!/bin/bash

# ========================================================
# DemoBlaze Selenium Automation Framework Setup Script
# ========================================================

echo "========================================================="
echo "DemoBlaze Automation Framework Setup"
echo "========================================================="

# Color codes
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m' # No Color

# Check Java installation
echo -e "\n${YELLOW}Checking Java installation...${NC}"
if ! command -v java &> /dev/null; then
    echo -e "${RED}Java is not installed. Please install Java 11 or higher.${NC}"
    exit 1
fi

JAVA_VERSION=$(java -version 2>&1 | grep -oP 'version "\K[^"]*' | cut -d'.' -f1)
if [ "$JAVA_VERSION" -lt 11 ]; then
    echo -e "${RED}Java version is $JAVA_VERSION. Please upgrade to Java 11 or higher.${NC}"
    exit 1
fi
echo -e "${GREEN}✓ Java $JAVA_VERSION found${NC}"

# Check Maven installation
echo -e "\n${YELLOW}Checking Maven installation...${NC}"
if ! command -v mvn &> /dev/null; then
    echo -e "${RED}Maven is not installed. Please install Maven 3.6 or higher.${NC}"
    exit 1
fi
echo -e "${GREEN}✓ Maven found${NC}"

# Clean and install dependencies
echo -e "\n${YELLOW}Installing project dependencies...${NC}"
mvn clean install

if [ $? -eq 0 ]; then
    echo -e "\n${GREEN}=========================================================${NC}"
    echo -e "${GREEN}✓ Setup completed successfully!${NC}"
    echo -e "${GREEN}=========================================================${NC}"
    
    echo -e "\n${YELLOW}To run tests, use:${NC}"
    echo -e "  ${GREEN}mvn clean test${NC}"
    echo -e "\n${YELLOW}To run in headless mode:${NC}"
    echo -e "  ${GREEN}mvn clean test -Dheadless=true${NC}"
    echo -e "\n${YELLOW}To run with specific browser:${NC}"
    echo -e "  ${GREEN}mvn clean test -Dbrowser=firefox${NC}"
    echo -e "  ${GREEN}mvn clean test -Dbrowser=edge${NC}"
else
    echo -e "\n${RED}Setup failed. Please check the error messages above.${NC}"
    exit 1
fi
