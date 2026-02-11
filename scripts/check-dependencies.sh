#!/bin/bash

# Script to check for dependency vulnerabilities
# Uses OWASP Dependency-Check to scan for known CVEs

set -e

PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$PROJECT_DIR"

echo "Running OWASP Dependency-Check..."
echo "This may take a while on the first run as it downloads the CVE database..."
echo ""

mvn dependency-check:check

echo ""
echo "✓ Dependency check complete!"
echo "  If vulnerabilities were found, review them above and update dependencies as needed."
