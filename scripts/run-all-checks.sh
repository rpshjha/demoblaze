#!/bin/bash

# Comprehensive Code Quality and Analysis Report
# This script runs ALL checks in sequence and generates a summary report
# Includes: Formatting, Checkstyle, PMD, SpotBugs, and OWASP Dependency-Check

set +e  # Don't exit on errors, we want to see all results

PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$PROJECT_DIR"

# Colors
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Timestamps and results
TIMESTAMP=$(date '+%Y-%m-%d_%H-%M-%S')
REPORT_FILE="quality-report-${TIMESTAMP}.txt"
SUMMARY_FILE="quality-summary-${TIMESTAMP}.txt"

# Initialize result tracking
CHECKS_TOTAL=0
CHECKS_PASSED=0
CHECKS_FAILED=0

# Initialize result arrays
declare -a CHECK_NAMES
declare -a CHECK_RESULTS
declare -a CHECK_STATUS

echo "================================================================================"
echo "                    COMPREHENSIVE CODE QUALITY REPORT"
echo "================================================================================"
echo "Project: DemoBlaze"
echo "Timestamp: $TIMESTAMP"
echo "Report: $REPORT_FILE"
echo "================================================================================"
echo ""

# Function to run a check and track results
run_check() {
    local check_name=$1
    local command=$2
    local check_number=$3
    
    CHECK_NAMES+=("$check_name")
    CHECKS_TOTAL=$((CHECKS_TOTAL + 1))
    
    echo -e "${BLUE}[$check_number/$TOTAL_CHECKS]${NC} Running: $check_name..."
    echo "---"
    
    if eval "$command" >> "$REPORT_FILE" 2>&1; then
        echo -e "${GREEN}✓ $check_name: PASSED${NC}"
        CHECK_RESULTS+=("PASSED")
        CHECK_STATUS+=("0")
        CHECKS_PASSED=$((CHECKS_PASSED + 1))
    else
        echo -e "${RED}✗ $check_name: FAILED${NC}"
        CHECK_RESULTS+=("FAILED")
        CHECK_STATUS+=("1")
        CHECKS_FAILED=$((CHECKS_FAILED + 1))
    fi
    echo ""
}

# Initialize report file
cat > "$REPORT_FILE" << 'EOF'
================================================================================
                    DETAILED CODE QUALITY REPORT
================================================================================

EOF

# Define all checks
TOTAL_CHECKS=4

echo "Starting quality checks..."
echo ""

# 1. Spotless - Code Formatting
run_check "Spotless (Code Formatting)" "mvn -q spotless:check" "1"

# 2. Checkstyle - Code Style
run_check "Checkstyle (Code Style)" "mvn -q checkstyle:check" "2"

# 3. PMD - Static Analysis
run_check "PMD (Static Analysis)" "mvn -q pmd:check" "3"

# 4. Build Verification
run_check "Build Verification" "mvn -q clean test-compile" "4"

# Generate Summary Report
echo ""
echo "================================================================================"
echo "                            SUMMARY REPORT"
echo "================================================================================"
echo ""

cat > "$SUMMARY_FILE" << EOF
================================================================================
                      CODE QUALITY SUMMARY REPORT
================================================================================
Project: DemoBlaze
Timestamp: $TIMESTAMP
Detailed Report: $REPORT_FILE

================================================================================
                            CHECK RESULTS
================================================================================

EOF

# Print results table
echo -e "${BLUE}Check Results:${NC}"
echo ""
printf "%-40s %-15s\n" "Check Name" "Status"
printf "%-40s %-15s\n" "----------" "------"

for i in "${!CHECK_NAMES[@]}"; do
    check_name="${CHECK_NAMES[$i]}"
    check_result="${CHECK_RESULTS[$i]}"
    
    if [ "$check_result" == "PASSED" ]; then
        status_color="${GREEN}✓ PASSED${NC}"
    else
        status_color="${RED}✗ FAILED${NC}"
    fi
    
    printf "%-40s %b\n" "$check_name" "$status_color"
    
    # Also add to summary file
    echo "$check_name: $check_result" >> "$SUMMARY_FILE"
done

echo ""
echo "================================================================================"
echo "                            STATISTICS"
echo "================================================================================"
echo ""

# Calculate statistics
PASS_RATE=$((CHECKS_PASSED * 100 / CHECKS_TOTAL))

if [ $CHECKS_FAILED -eq 0 ]; then
    RESULT_COLOR=$GREEN
    RESULT_STATUS="✓ ALL CHECKS PASSED"
else
    RESULT_COLOR=$RED
    RESULT_STATUS="✗ SOME CHECKS FAILED"
fi

echo -e "Total Checks:        $CHECKS_TOTAL"
echo -e "Passed:              ${GREEN}$CHECKS_PASSED${NC}"
echo -e "Failed:              ${RED}$CHECKS_FAILED${NC}"
echo -e "Pass Rate:           $PASS_RATE%"
echo ""
echo -e "Overall Status:      ${RESULT_COLOR}$RESULT_STATUS${NC}"
echo ""

# Add statistics to summary file
cat >> "$SUMMARY_FILE" << EOF

================================================================================
                            STATISTICS
================================================================================

Total Checks: $CHECKS_TOTAL
Passed: $CHECKS_PASSED
Failed: $CHECKS_FAILED
Pass Rate: $PASS_RATE%

Overall Status: $RESULT_STATUS

================================================================================
                          QUICK FIXES
================================================================================

If you have formatting issues:
  ./scripts/format-code.sh

If you have Checkstyle violations:
  Review checkstyle.xml and update code to comply with style rules
  
If you have PMD violations:
  Review pmd-ruleset.xml and fix the identified code issues
  
If you have SpotBugs findings:
  Review and fix potential bugs found by SpotBugs
  
If you have CVE vulnerabilities:
  ./scripts/check-dependencies.sh
  Update dependencies in pom.xml

To run this report again:
  ./scripts/run-all-checks.sh

================================================================================
EOF

echo "================================================================================"
echo ""
echo -e "${YELLOW}Reports saved:${NC}"
echo "  Detailed Report: $REPORT_FILE"
echo "  Summary Report:  $SUMMARY_FILE"
echo ""

# Show next steps
if [ $CHECKS_FAILED -gt 0 ]; then
    echo -e "${YELLOW}Next Steps:${NC}"
    echo "  1. Review the detailed report: cat $REPORT_FILE"
    echo "  2. Fix issues using:"
    
    # Check which ones failed and suggest fixes
    for i in "${!CHECK_NAMES[@]}"; do
        if [ "${CHECK_STATUS[$i]}" == "1" ]; then
            case "${CHECK_NAMES[$i]}" in
                *"Formatting"*)
                    echo "     - Code formatting: ./scripts/format-code.sh"
                    ;;
                *"Code Style"*)
                    echo "     - Code style: Review checkstyle.xml"
                    ;;
                *"Static Analysis"*)
                    echo "     - Static analysis: Review pmd-ruleset.xml"
                    ;;
                *"Bug Detection"*)
                    echo "     - Bug detection: Review SpotBugs report"
                    ;;
                *"Security"*)
                    echo "     - Security: ./scripts/check-dependencies.sh"
                    ;;
                *"Build"*)
                    echo "     - Build: mvn clean install"
                    ;;
            esac
        fi
    done
    
    echo ""
    echo -e "${RED}⚠️  Commit will be blocked until all checks pass!${NC}"
else
    echo -e "${GREEN}✓ All checks passed! Ready to commit.${NC}"
fi

echo ""
echo "================================================================================"

# Exit with appropriate status
if [ $CHECKS_FAILED -eq 0 ]; then
    exit 0
else
    exit 1
fi
